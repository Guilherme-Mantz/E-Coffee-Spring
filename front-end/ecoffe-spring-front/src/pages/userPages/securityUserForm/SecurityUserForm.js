import { useContext, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { cpf } from 'cpf-cnpj-validator';

import { Context } from '../../../context/AuthContext';
import history from "../../../history";
import api from '../../../hook/api';

import Header from '../../../components/Header/Header';
import Footer from '../../../components/Footer/Footer';

import './SecurityUserForm.css';

export default function SecurityUserForm () {

    const { dataCliente } = useContext(Context);

    const [ form, setForm ] = useState(dataCliente);

    const [ emptyValue, setEmptyValue ] = useState(false);
    const [ validEmail, setValidEmail ] = useState(false);
    const [ validCpf, setValidCpf ] = useState(true);

    function handleChange(e){
        let newProp = form;
        setValidEmail(true);

        /* Valida CPF */
        const formatCpf = cpf.format(form["cpf"]);
        const validaCpf = cpf.isValid(formatCpf);
        setValidCpf(validaCpf);

        newProp[e.target.name] = e.target.value;
        setForm({ ...newProp })
    };

    async function handleCadaster(e){
        e.preventDefault();

        /* Campos preenchidos */
        let emptyValues = Object.values(form).some(obj => obj === "");
        setEmptyValue(emptyValues);

        /* Validar email */
        let validEmail = form["email"].toLocaleLowerCase().match(/[a-z]+@[a-z]+\.com(\.br)*/);
        setValidEmail(validEmail);

        if(!emptyValues && validEmail && validCpf || form['senha']){

            await api.put(`/cliente/atualizar/${dataCliente.idCliente}`, form)
            .then((res) => { 
                if(res.status === 200){
                    history.push('/user/seguranca');
                    window.location.reload()
                };
            })
            .catch((error) => {
                //criar validação de email e cpf UNIQUE
            })
        };
    };

    return (
        <>
        <Header/>
            <main>
                <div className='container w-50'>
                    <h2 className='title-primary-color fw-bold mt-4'>Editar Informações</h2>
                    <form onSubmit={e => handleCadaster(e)}>
                        <ul className='list-group list-group-flush mt-4' id='lista-informacoes'>

                            <li className="list-group-item mx-4 mt-2">
                                <label htmlFor="nome" className='fw-bold'>Nome:</label>   
                                <input type="text" className="form-control w-50" id="nome" name='nome' defaultValue={form !== null && form['nome']} onBlur={(e) => handleChange(e)}/>
                                { emptyValue && form["nome"] === "" ? <span>É necessário informar o nome</span> : ''}
                            </li>

                            <li className="list-group-item mx-4 mt-2">
                                <label htmlFor="sobrenome" className='fw-bold'>Sobrenome:</label>
                                <input type="text" className="form-control w-50" id="sobrenome" name='sobrenome' defaultValue={form !== null && form['sobrenome']} onBlur={(e) => handleChange(e)}/>
                                { emptyValue && form["sobrenome"] === "" ? <span>É necessário informar o Sobrenome</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor="telefone" className='fw-bold'>Telefone:</label>
                                <input type="text" className="form-control w-50" id="telefone" name='telefone' defaultValue={form !== null && form['telefone']} onBlur={(e) => handleChange(e)}/>
                                { emptyValue && form["telefone"] === ""? <span>É necessário informar o Telefone</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='cpf' className='fw-bold'>CPF:</label> 
                                <input type="text" className="form-control w-50" id="cpf" name='cpf' defaultValue={form !== null && form['cpf']} onBlur={(e) => handleChange(e)}/>
                                { emptyValue && form["cpf"] === "" ? <span>É necessário informar o CPF</span> : ''}
                                { !validCpf && form["cpf"] !== "" ? <span>CPF inválido </span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='email' className='fw-bold'>E-mail:</label> 
                                <input type="email" className="form-control w-50" id="email" name='email' defaultValue={form !== null && form['email']} onBlur={(e) => handleChange(e)}/>
                                { emptyValue && form["email"] === "" ? <span>É necessário informar o E-mail</span> : ''}
                                { !validEmail && form["email"] !== "" ? <span>E-mail inválido </span> : '' }
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='senha' className='fw-bold'>Senha:</label> 
                                <input type="password" className="form-control w-50" id="senha" name='senha' placeholder='Nova Senha' onBlur={(e) => handleChange(e)}/>
                            </li>

                            <li style={{listStyle: 'none'}}>
                                <Link className='btn mt-3 mb-3 ms-4' to='/user/seguranca' id="btn-primary" style={{width: "max-content"}}>Cancelar</Link>
                                <button className='btn mt-3 mb-3' type='submit' id="btn-primary" style={{width: "max-content", marginLeft: "10%"}}>Salvar</button>
                            </li>
                        </ul>
                    </form>
                </div>
            </main>
        <Footer/>
        </>
    );
};