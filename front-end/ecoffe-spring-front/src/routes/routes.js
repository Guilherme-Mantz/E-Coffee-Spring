import { useContext } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

import { Context } from '../context/AuthContext';

import Index from '../pages/index/Index';
import LoginPage from '../pages/login/LoginPage';
import AdressesUser from '../pages/userPages/adressesUser/AdressesUser';
import HomeUser from '../pages/userPages/homeUser/homeUser';
import SecurityUser from '../pages/userPages/securityUser/SecurityUser';
import SecurityUserForm from '../pages/userPages/securityUserForm/SecurityUserForm';

function CustomRoute({ isPrivate, notLogged, ...rest }) {
  const { loading, authenticated } = useContext(Context);

  if (loading) {
    return <h1>Loading...</h1>;
  }

  if (notLogged && authenticated) {
    return <Redirect to="/user/home" />
  }

  if (isPrivate && !authenticated) {
    return <Redirect to="/iniciarsessao" />
  }

  return <Route {...rest} />;
}

export default function Router() {
    
  return (
    <Switch>
      <CustomRoute exact path='/' component={Index} />
      <CustomRoute notLogged path='/iniciarsessao' component={LoginPage} />
      <CustomRoute isPrivate path='/user/home' component={HomeUser} />
      <CustomRoute isPrivate exact path='/user/seguranca' component={SecurityUser} />
      <CustomRoute isPrivate exact path='/user/seguranca/editar' component={SecurityUserForm} />
      <CustomRoute isPrivate exact path='/user/enderecos' component={AdressesUser} />
    </Switch>
  );
};