import { useContext, useState } from "react";

import { Context } from "../../context/AuthContext";

export default function FormLogin () {
    const { authenticated, handleLogin } = useContext(Context);
    console.debug(authenticated)

    const [ form, setForm ] = useState({
        email: "",
        senha: ""
    });

    function handleChange(e){
        let newProp = form;

        newProp[e.target.name] = e.target.value;
        setForm({ ...newProp })
    };


    return (
        <div className="form-container sign-in-container">
            <form id="formularioLogin">
                <h1>Login</h1>
                <div className="form-Login">
                    <input type="email" name="email" placeholder="E-mail" id="loginemail" onBlur={(e) => handleChange(e)} />
                </div>
                <div className="form-Login">
                    <input type="password" name="senha" placeholder="Senha" id="loginsenha" onBlur={(e) => handleChange(e)} />
                </div>
                <a href="#">Esqueceu sua senha?</a>
                <button type="button" onClick={() => handleLogin(form)} >Login</button>
            </form>
        </div>
    );
}