import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';



class LogButton extends React.Component {
    constructor(props){
        super(props);
        this.handleChangeUsername = this.handleChangeUsername.bind(this);
        this.state = ({
            msg: "",
            username: ""
        })
    }

    loginClick(username){
        fetch('/kotlin/kotlin/' + username  , {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(result => result.text())
            .then(res => {
                this.setState({msg: res});
                console.log(res);
            });
    }

    handleChangeUsername = event => {
        this.setState({username: event.target.value})
    };

    render() {
        if (this.state.msg !== "")
            return (
                <div className="logbutton">
                    <input type="text" value={this.state.username} onChange={this.handleChangeUsername}/>
                    <button class="btn btn-primary" onClick={() => this.loginClick(this.state.username)}>Esegui Login</button>
                    <label class="alert alert-success">{this.state.msg}</label>
                </div>
            );
        else
            return (
                <div className="logbutton">
                    <input type="text" value={this.state.username} onChange={this.handleChangeUsername}/>
                    <button class="btn btn-primary" onClick={() => this.loginClick(this.state.username)}>Esegui Login</button>
                </div>
            );
    }

}

class LogHistoryButton extends React.Component {
    constructor(props){
        super(props);
        this.state=({data:[]})
    }

    logRequestClick(){
        fetch('/kotlin/kotlin', {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                this.setState({
                    data: res
                })
            });
        console.log(this.state.data);
        this.render()
    }

    render() {
        return (
            <div className="loghistorybutton">
                <button class="btn btn-primary" onClick={() => this.logRequestClick()}>Richiedi log accessi</button>
                <LogsHistory data={this.state.data} />
            </div>
        )
    }

}

class LogsHistory extends React.Component {
    render(){
        if (this.props.data.length > 0)
            return (
                <div>
                    <table>
                        <tr>
                            <th>Identificativo Utente</th>
                            <th>Nome Utente</th>
                            <th>Data Login</th>
                        </tr>
                        {
                            this.props.data.map((item, key) => {
                                return <tr key={key}><td>{item.userid}</td><td>{item.username}</td><td>{item.lastLoginDate}</td></tr>
                            })
                        }
                    </table>
                </div>
            );
        else
            return (
                <div></div>
            )
    }
}

class Window extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }


    render(){
        return (
            <div className="window">
                <LogButton />
                <LogHistoryButton />
            </div>
        );
    }
}


// ========================================

ReactDOM.render(
    <Window />,
    document.getElementById('root')
);
