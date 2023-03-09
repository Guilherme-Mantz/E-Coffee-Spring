import { useContext, useState } from "react";
import { Context } from "../../../context/AuthContext";
import history from "../../../history";

import LoginPage from './LoginAdminStyles';

export default function LoginAdmin () {

    const { handleLogin } = useContext(Context);

    const [ form, setForm ] = useState({
        username: "",
        password: ""
    });

    const [ emptyValue, setEmptyValue ] = useState(false);
    const [ errorLogin, setErrorLogin ] = useState('');

    function handleChange(e){
        let newProp = form;

        newProp[e.target.name] = e.target.value;
        setForm({ ...newProp })
    };

    function handleSubmit (e){
        e.preventDefault();

        /* Campos preenchidos */
        let emptyValues = Object.values(form).some(obj => obj === "");
        setEmptyValue(emptyValues);

        if(!emptyValues){
            handleLogin(form)
            .then((res) => {

                history.push('/admin/painel');
                window.location.reload();

            }).catch((error) => {

                if(error.response.status === 403){
                    setErrorLogin('Usuário ou senha inválidos!');
                };
            })
        };        
    };

    return (
        <>
        <LoginPage/>
        <main className="form-signin w-100 m-auto text-center">
            <img className="mb-4" src={require('../../../images/E- COFFEE 3.png')} alt="e-coffee" width="100" height="100" />
            <form onSubmit={(e) => handleSubmit(e)}>
                { errorLogin !== "" ? <div className='text-black mb-4'>{errorLogin}</div> : ''}

                <div className="form-floating">
                    <input type="text" name="username" className="form-control" id="floatingInput" placeholder="usuario" onChange={(e) => handleChange(e)} />
                    <label htmlFor="floatingInput">Usuário</label>
                    { emptyValue && form["username"] === "" ? <span className='text-white'>É necessário informar o E-mail</span> : ''}
                </div>

                <div className="form-floating">
                    <input type="password" name="password" className="form-control" id="floatingPassword" placeholder="Password" onChange={(e) => handleChange(e)} />
                    <label htmlFor="floatingPassword">Senha</label>
                    { emptyValue && form["password"] === ""? <span className='text-white'>É necessário informar a Senha</span> : ''}
                </div>

                <button className="w-100 btn btn-lg btn-primary" type="submit">Login</button>
            </form>
        </main>
        </>

    );
}