import React from 'react'
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
import { isAuthenticated, logout } from "../services/auth";

import Tarefas from '../tarefas/Tarefas'
import Sobre from '../sobre/Sobre'
import Login from '../login/Login'

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      isAuthenticated() ? (
        <Component {...props} />
      ) : (
        <Redirect to={{ pathname: "/", state: { from: props.location } }} />
      )
    }
  />
);

const Routes = () => (
  <BrowserRouter>
    <Switch>
      <Route exact path="/" component={Login} />
      <Route path="/signup" component={() => <h1>SignUp</h1>} />
      <PrivateRoute path="/tarefas" component={Tarefas} />
      <PrivateRoute path="/sobre" component={Sobre} />
      <Route path="*" component={() => <h1>Page not found</h1>} />
    </Switch>
  </BrowserRouter>
);

export default Routes;