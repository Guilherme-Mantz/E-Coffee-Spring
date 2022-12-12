import { useContext } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

import { Context } from '../context/AuthContext';

import Index from '../pages/index/Index';
import LoginPage from '../pages/login/LoginPage';
import HomeUser from '../pages/userPages/homeUser/homeUser';

function CustomRoute({ isPrivate, ...rest }) {
    const { loading, authenticated } = useContext(Context);
  
    if (loading) {
      return <h1>Loading...</h1>;
    }
  
    if (isPrivate && !authenticated) {
      return <Redirect to="/iniciarsessao" />
    }
  
    return <Route {...rest} />;
}

export default function Router() {
    
    return(
      <Switch>
        <CustomRoute exact path='/' component={Index}/>
        <CustomRoute path='/iniciarsessao' component={LoginPage}/>
        <CustomRoute isPrivate path='/user/home' component={HomeUser}/>
      </Switch>
    );
};