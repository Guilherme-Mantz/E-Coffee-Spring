import { useContext } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

import { Context } from '../context/AuthContext';

import jwtDecode from 'jwt-decode';

import Index from '../pages/index/Index';
import LoginPage from '../pages/login/LoginPage';
import Produtos from '../pages/produtos/Produtos';
import DetalhesProduto from '../pages/detalhesProduto/DetalhesProduto';
import AdresFormUser from '../pages/userPages/adresFormUser/AdresFormUser';
import AdressesUser from '../pages/userPages/adressesUser/AdressesUser';
import HomeUser from '../pages/userPages/homeUser/homeUser';
import SecurityUser from '../pages/userPages/securityUser/SecurityUser';
import SecurityUserForm from '../pages/userPages/securityUserForm/SecurityUserForm';
import Carrinho from '../pages/carrinho/Carrinho';
import FinalizarCompra from '../pages/finalizarCompra/FinalizarCompra';
import OrderUser from '../pages/userPages/orderUser/OrderUser';

/*Admin Pages*/
import LoginAdmin from '../pages/Admin/loginAdmin/LoginAdmin';
import PainelAdmin from '../pages/Admin/painelAdmin/PainelAdmin';

function isAuthorized(userRoles, allowedRoles) {
  return allowedRoles[0] === userRoles[0].authority
}

function CustomRoute({ isPrivate, notLogged, allowedRoles, ...rest }) {
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

  if(allowedRoles){
    const token = localStorage.getItem('token');

    if (!token) {
      return <Redirect to="/" />;
    }

    const decodedToken = jwtDecode(token);
    const userRoles = decodedToken.roles;

    if (!isAuthorized(userRoles, allowedRoles)) {
      return <Redirect to="/" />;
    }
  }

  return <Route {...rest} />;
}

export default function Router() {
    
  return (
    <Switch>
      <CustomRoute exact path='/' component={Index} />
      <CustomRoute notLogged path='/iniciarsessao' component={LoginPage} />

      <CustomRoute isPrivate path='/user/home' component={HomeUser} allowedRoles={['USER']}/>
      <CustomRoute isPrivate exact path='/user/seguranca' component={SecurityUser} allowedRoles={['USER']}/>
      <CustomRoute isPrivate exact path='/user/seguranca/editar' component={SecurityUserForm} allowedRoles={['USER']}/>
      <CustomRoute isPrivate exact path='/user/enderecos' component={AdressesUser} allowedRoles={['USER']}/>
      <CustomRoute isPrivate exact path='/user/enderecos/novo' component={AdresFormUser} allowedRoles={['USER']}/>
      <CustomRoute isPrivate exact path='/user/enderecos/alterar/:idEndereco' component={AdresFormUser} allowedRoles={['USER']}/>
      <CustomRoute isPrivate exact path='/user/pedidos' component={OrderUser} allowedRoles={['USER']}/>

      <CustomRoute exact path="/produtos" component={Produtos} />
      <CustomRoute exact path='/produtos/:categoria' component={Produtos} />
      <CustomRoute exact path='/produto/:idProduto' component={DetalhesProduto} />

      <CustomRoute isPrivate exact path='/carrinho' component={Carrinho} allowedRoles={['USER']}/>
      <CustomRoute isPrivate exact path='/finalizarcompra' component={FinalizarCompra} allowedRoles={['USER']}/>

      <CustomRoute exact path='/admin' component={LoginAdmin} />
      <CustomRoute isPrivate exact path='/admin/painel' component={PainelAdmin} allowedRoles={['EDITOR']}/>
    </Switch>
  );
};