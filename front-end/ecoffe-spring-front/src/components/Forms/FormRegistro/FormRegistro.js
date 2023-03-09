import { useState } from "react";
import { cpf } from 'cpf-cnpj-validator';

import history from "../../../history";
import api from "../../../hook/api";

export default function FormRegistro () {

    const [ form, setForm ] = useState({
        nome: "",
        sobrenome: "",
        telefone: "",
        cpf: "",
        email: "",
        senha: "",
        confirmarSenha: ""
    });
    const [ emptyValue, setEmptyValue ] = useState(false);
    const [ validEmail, setValidEmail ] = useState(false);
    const [ validCpf, setValidCpf ] = useState(true);
    const [ lengthPassword, setLengthPassword ] = useState('');
    const [ confirmedPassword, setConfirmedPassword ] = useState(false);

    const [ errorCpf, setErrorCpf ] = useState({status: false, error: ''});
    const [ errorEmail, setErrorEmail ] = useState({status: false, error: ''});

    function handleChange(e){
        let newProp = form;
        setValidEmail(true);

        /* Valida CPF */
        const formatCpf = cpf.format(form["cpf"]);
        const validaCpf = cpf.isValid(formatCpf);
        setValidCpf(validaCpf);

        /* Valida length da senha */
        let password = document.getElementById('senha').value
        password.length <= 5 ? setLengthPassword("Senha muito curta") : setLengthPassword('');

        newProp[e.target.name] = e.target.value;
        setForm({ ...newProp })
    };

    async function handleCadaster(e){
        e.preventDefault();

        setErrorEmail({status: false });
        setErrorCpf({status: false });

        /* Campos preenchidos */
        let emptyValues = Object.values(form).some(obj => obj === "");
        setEmptyValue(emptyValues);

        /* Validar email */
        let validEmail = form["email"].toLocaleLowerCase().match(/[a-z]+@[a-z]+\.com(\.br)*/);
        setValidEmail(validEmail);

        /* Valida senha */
        let confirmePassword = form["senha"] !== form["confirmarSenha"];
        setConfirmedPassword(confirmePassword);

        if(!emptyValues && validEmail && validCpf && !confirmePassword){

            await api.post('/cliente/cadastrar', form)
            .then((res) => { 
                if(res.status === 201){
                    history.go('/');
                };
            })
            .catch((error) => {

                if(error.response.data.campo === "email" ){
                    setErrorEmail({status: true, error: error.response.data.erro});
                };

                if(error.response.data.campo === "cpf" ){
                    setErrorCpf({status: true, error: error.response.data.erro});
                };
            })
        };
    };

    if(emptyValue === true){
        document.getElementById('container').style.minHeight = '750px';
    };

    return (
        <div className="form-container sign-up-container">
            <form id="formCadastro" onSubmit={e => handleCadaster(e)}>
                <h1>Cadastre-se</h1>
                <div className="formCadastro">
                    <input type="text" name="nome" placeholder="Nome" id="nome" onBlur={(e) => handleChange(e)}/>
                    { emptyValue && form["nome"] === "" ? <span className='text-white'>É necessário informar o nome</span> : ''}
                </div>

                <div className="formCadastro">
                    <input type="text" name="sobrenome" placeholder="Sobrenome" id="sobrenome" onBlur={(e) => handleChange(e)}/>
                    { emptyValue && form["sobrenome"] === "" ? <span className='text-white'>É necessário informar o Sobrenome</span> : ''}
                </div>

                <div className="formCadastro">
                    <input type="text" name="telefone" maxLength="11" placeholder="Telefone" id="telefone" onBlur={(e) => handleChange(e)}/>
                    { emptyValue && form["telefone"] === ""? <span className='text-white'>É necessário informar o Telefone</span> : ''}
                </div>

                <div className="formCadastro">
                    <input type="text" name="cpf" maxLength="11" placeholder="CPF" id="cpf" onBlur={(e) => handleChange(e)}/>
                    { emptyValue && form["cpf"] === "" ? <span className='text-white'>É necessário informar o CPF</span> : ''}
                    { !validCpf && form["cpf"] !== "" ? <span className='text-white'>CPF inválido </span> : ''}
                    { errorCpf["status"] ? <span className='text-white'>{errorCpf["error"]}</span> : ''}
                </div>

                <div className="formCadastro">
                    <input type="email" name="email" placeholder="E-mail" id="email" onBlur={(e) => handleChange(e)}/>
                    { emptyValue && form["email"] === "" ? <span className='text-white'>É necessário informar o E-mail</span> : ''}
                    { !validEmail && form["email"] !== "" ? <span className='text-white'>E-mail inválido </span> : '' }
                    { errorEmail["status"] ? <span className='text-white'>{errorEmail["error"]}</span> : ''}
                </div>

                <div className="formCadastro">
                    <input type="password" name="senha" placeholder="Senha" id="senha" onBlur={(e) => handleChange(e)}/>
                    { emptyValue && form["senha"] === ""? <span className='text-white'>É necessário informar a Senha</span> : ''}
                    { lengthPassword && form["senha"] !== "" ? <span className='text-white'>{lengthPassword}</span> : ''}
                </div>

                <div className="formCadastro">
                    <input type="password" name="confirmarSenha" placeholder="Confirme a Senha" id="confirmarSenha" onChange={(e) => handleChange(e)}/>
                    { confirmedPassword ? <span className='text-white'>As senhas precisam ser iguais!</span> : '' }
                </div>
                <button style={{marginTop: "17px"}} type="submit">Registrar-se</button>
            </form>
        </div>
    );
};