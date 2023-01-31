import { useContext, useState } from "react";

import { Context } from "../../../context/AuthContext";

export default function FormLogin () {
    const { handleLogin } = useContext(Context);

    const [ form, setForm ] = useState({
        email: "",
        senha: ""
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
                { errorLogin !== "" ? <span className='text-wite'>{errorLogin}</span> : ''}
                <div className="form-Login">
                    <input type="email" name="email" placeholder="E-mail" id="loginemail" onChange={(e) => handleChange(e)} />
                    { emptyValue && form["email"] === "" ? <span className='text-wite'>É necessário informar o E-mail</span> : ''}
                </div>
                <div className="form-Login">
                    <input type="password" name="senha" placeholder="Senha" id="loginsenha" onChange={(e) => handleChange(e)} />
                    { emptyValue && form["senha"] === ""? <span className='text-wite'>É necessário informar a Senha</span> : ''}
                </div>
                <a href="#">Esqueceu sua senha?</a>
                <button type="submit" >Login</button>
            </form>
        </div>
    );
}