import React, { Component } from 'react'
import api from '../services/api'

export default class Tarefas extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            listaTarefas : []
         }
    }

    componentDidMount() {
        api.get('/tarefas')
            .then( response => {

                console.log(response.data)
                this.setState({ listaTarefas: response.data });
            })
            .catch(erro => {
                if (erro.response.status === 403) {
                    this.props.history.push("/");    
                }
            })

    }

    render() { 
        return (
            <div className="wrapper wrapper-content animated fadeInRight">
                <div className="row">
                    <div className="col-lg-6">
                        <div className="ibox ">
                            <div className="ibox-content">
                                <h2>Lista de Tarefas</h2>
                                <small>1 pendentes 5 concluídas</small>
                                <ul className="todo-list m-t small-list ui-sortable">
                                    {this.state.listaTarefas.map( tarefa => 
                                        <li key={tarefa.id}>
                                            <a href="#a" className="check-link"><i className={`fa ${tarefa.concluida ? 'fa-check-square-o' : 'fa-square-o' }`}></i> </a>
                                            <span className={`m-l-xs ${tarefa.concluida ? 'todo-completed' : '' }`}> {tarefa.descricao} </span>
                                        </li>
                                    )}
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
         );
    }
}
 
