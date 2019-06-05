import React, { Component } from 'react'
import api from '../services/api'
import { login } from "../services/auth";

export default class Tarefas extends Component {

    constructor(props) {
        super(props);
        this.handleLogar = this.handleLogar.bind(this)
    }

    state = {
        email: "jose@gmail.com",
        senha: "123",
        erro: ""
    };

    handleLogar = async e => {
        const { email, senha } = this.state;
        if (!email || !senha) {
          this.setState({ erro: "Preencha e-mail e senha para continuar!" });
        } else {
          try {
            const response = await api.post("/login", { email, senha });
            login(response.headers.authorization);
            this.props.history.push("/tarefas");
          } catch (err) {
            this.setState({
              erro:
                "Houve um problema com o login, verifique suas credenciais."
            });
          }
        }
    };

    render() { 
        return (
            <div className="col-sm-4">
                <div>
                    <h1 className="logo-name"><i className="fa fa-check"></i></h1>
                </div>
                <h3>Bem vindo ao Tarefas</h3>
                
                {this.state.erro && <div className="alert alert-danger">{this.state.erro}</div>}

                <form className="m-t" onSubmit={this.handleLogar}>
                    <div className="form-group">
                        <input type="email" className="form-control" placeholder="e-mail" required="" value={this.state.email} onChange={e => this.setState({ email: e.target.value })}/>
                    </div>
                    <div className="form-group">
                        <input type="password" className="form-control" placeholder="senha" required="" value={this.state.senha} onChange={e => this.setState({ senha: e.target.value })}/>
                    </div>
                    <button type="submit" className="btn btn-primary block full-width m-b">Entrar</button>
                </form>
            </div>
        )
    }
}