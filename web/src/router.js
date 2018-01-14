import React from 'react';
import { Router, Route, Switch } from 'dva/router';
import IndexPage from './routes/IndexPage';
import Products from './routes/Products';
import Login from './routes/Login';
import forgetPwd from './routes/forgetPwd';
import main from './routes/main';

function RouterConfig({ history }) {
  return (
    <Router history={history}>
      <Switch>
        <Route path="/" exact component={Login} />
        <Route path="/products" exact component={Products} />
        <Route path="/IndexPage" exact component={IndexPage} />
        <Route path="/forgetPwd" exact component={forgetPwd} />
        <Route path="/main" exact component={main} />
      </Switch>
    </Router>
  );
}

export default RouterConfig;
