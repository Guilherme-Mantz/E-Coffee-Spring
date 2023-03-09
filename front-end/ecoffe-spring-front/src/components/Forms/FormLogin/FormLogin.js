import { useContext, useState } from "react";

import { Context } from "../../../context/AuthContext";
import history from "../../../history";

export default function FormLogin () {
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

                history.push('/');
                window.location.reload();

            }).catch((error) => {

                if(error.response.status === 403){
                    setErrorLogin('E-mail ou senha inválidos!');
                };
            })
        };        
    };

    return (
        <div className="form-container sign-in-container">
            <form id="formularioLogin" onSubmit={e => handleSubmit(e)}>
                <h1>Login</h1>
                { errorLogin !== "" ? <span className='text-white'>{errorLogin}</span> : ''}
                <div className="form-Login">
                    <input type="email" name="username" placeholder="E-mail" id="loginemail" onChange={(e) => handleChange(e)} />
                    { emptyValue && form["username"] === "" ? <span className='text-white'>É necessário informar o E-mail</span> : ''}
                </div>
                <div className="form-Login">
                    <input type="password" name="password" placeholder="Senha" id="loginsenha" onChange={(e) => handleChange(e)} />
                    { emptyValue && form["password"] === ""? <span className='text-white'>É necessário informar a Senha</span> : ''}
                </div>
                <a href="#">Esqueceu sua senha?</a>
                <button type="submit" >Login</button>
            </form>
        </div>
    );
}