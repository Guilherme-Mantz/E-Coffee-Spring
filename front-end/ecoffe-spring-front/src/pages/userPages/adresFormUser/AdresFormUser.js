import { useContext, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';

import { Context } from '../../../context/AuthContext';
import api from '../../../hook/api';
import history from '../../../history';

import Header from '../../../components/Header/Header';
import Footer from '../../../components/Footer/Footer';

import './AdresFormUser.css';

export default function AdresFormUser () {

    const { idEndereco } = useParams();

    const { dataCliente } = useContext(Context);

    const [ isEditing, setIsEditing ] = useState(false);

    const [ emptyValue, setEmptyValue ] = useState(false);
    const [ errorCep, setErrorCep ] = useState(false);
    const [ form, setForm ] = useState({
        nomeEndereco: "",
        cep: "",
        logradouro: "",
        numeroResidencia: "",
        bairro: "",
        cidade: "",
        uf: "",
        complemento: ""
    });

    useEffect(() => {

        if(window.location.pathname == `/user/enderecos/alterar/${idEndereco}`){
            setIsEditing(true);
        };

        getEndereco();
    },[isEditing, dataCliente]);

    async function getEndereco(){
        if(isEditing && dataCliente !== null){
            await api.get(`endereco/buscar/${dataCliente.idCliente}/${idEndereco}`).then((res) => {
                setForm(res.data);
            });

            document.getElementById('logradouro').removeAttribute("disabled");
            document.getElementById('bairro').removeAttribute("disabled");
            document.getElementById('cidade').removeAttribute("disabled");
            document.getElementById('uf').removeAttribute("disabled");
        };
    }

    async function handleChange(e){
        let newProp = form;

        newProp[e.target.name] = e.target.value;
        setForm({ ...newProp })
    };

    useEffect(() => {
        if(form['cep'].length === 8 && (isEditing && form['logradouro'] === "" )){

            const rua = document.getElementById('logradouro');
            const bairroForm = document.getElementById('bairro');
            const cidadeForm = document.getElementById('cidade');
            const estadoForm = document.getElementById('uf');

            const URL = `https://viacep.com.br/ws/${form['cep']}/json/`;

            axios.get(URL).then((res) => {

                if(res.data.erro){
                    setErrorCep(true)
                }
                else {
                    const { logradouro, bairro, localidade, uf } = res.data;

                    setErrorCep(false)

                    rua.value = logradouro;
                    bairroForm.value = bairro;
                    cidadeForm.value = localidade;
                    estadoForm.value = uf;

                    setForm({ nomeEndereco: form['nomeEndereco'],
                        cep: form["cep"], 
                        numeroResidencia: form['numeroResidencia'],
                        logradouro: logradouro,  
                        bairro: bairro, 
                        cidade: localidade, 
                        uf: uf
                    });
                };

            }).finally(() => {
                rua.removeAttribute("disabled");
                bairroForm.removeAttribute("disabled");
                cidadeForm.removeAttribute("disabled");
                estadoForm.removeAttribute("disabled");
            });
        };
    },[form['cep']])

    async function handleCadaster(e){
        e.preventDefault();

        /* Campos preenchidos */
        let emptyValues = Object.values(form).some(obj => obj === "");
        setEmptyValue(emptyValues);

        if((!emptyValues && errorCep === false ) || form["complemento"] === ''){

            await api.post(`/endereco/cadastrar/${dataCliente.idCliente}`, form)
            .then((res) => { 
                if(res.status === 201){
                    history.push('/user/enderecos');
                    window.location.reload();
                };
            })
            .catch((error) => {
            })
        };
    };

    async function handleEdit(e){
        e.preventDefault()
    
        /* Campos preenchidos */
        let emptyValues = Object.values(form).some(obj => obj === "");
        setEmptyValue(emptyValues);

        if((!emptyValues && errorCep === false ) || form["complemento"] === ''){

            await api.put(`/endereco/atualizar/${idEndereco}`, form)
            .then((res) => { 
                if(res.status === 200){
                    history.push('/user/enderecos');
                    window.location.reload();
                };
            })
            .catch((error) => {
            })
        };
    };

    return (
        <>
        <Header/>
            <main>
                <div className='container w-50'>
                    <h2 className='title-primary-color fw-bold mt-4'>Cadastrar Novo Endereço</h2>
                    <form onSubmit={(e) => isEditing ? handleEdit(e) : handleCadaster(e)}>
                        <ul className='list-group list-group-flush mt-4' id='lista-informacoes'>

                            <li className="list-group-item mx-4 mt-2">
                                <label htmlFor="nomeEndereco" className='fw-bold'>Nome do Endereço:</label>   
                                <input type="text" className="form-control w-50" id="nomeEndereco" name='nomeEndereco' placeholder='Minha casa' 
                                value={form['nomeEndereco']}
                                onChange={(e) => handleChange(e)}/>
                                { emptyValue && form["nomeEndereco"] === "" ? <span>É necessário informar um nome de identificação!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4 mt-2">
                                <label htmlFor="cep" className='fw-bold'>Cep:</label>
                                <input type="text" className="form-control w-50" id="cep" name='cep' maxLength='8' placeholder='12345678' 
                                value={form['cep']}
                                onChange={(e) => handleChange(e)}/>
                                { emptyValue && form["cep"] === "" ? <span>É necessário informar o cep!</span> : ''}
                                { errorCep ? <span>Cep inválido!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor="logradouro" className='fw-bold'>Logradouro:</label>
                                <label htmlFor="numeroResidencia" style={{marginLeft: '42%'}} className='fw-bold'>Número:</label>

                                <div className='d-flex'>
                                    <input type="text" className="form-control w-50" id="logradouro" name='logradouro' placeholder='Rua fulano' disabled 
                                    value={form['logradouro']}
                                    onChange={(e) => handleChange(e)}/>

                                    <input type="text" className="form-control ms-4" style={{width: "10%"}} id="numeroResidencia" name='numeroResidencia' placeholder='20' 
                                    value={form['numeroResidencia']}
                                    onChange={(e) => handleChange(e)}/>
                                </div>
                                { emptyValue && form["logradouro"] === "" ? <span>É necessário informar o logradouro!</span> : ''}
                                { emptyValue && form["numeroResidencia"] === "" ? <span style={{marginLeft: '23%'}}>É necessário informar o número da residência!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='bairro' className='fw-bold'>Bairro:</label> 
                                <input type="text" className="form-control w-50" id="bairro" name='bairro' placeholder='Bairro sudeste' disabled 
                                value={form['bairro']}
                                onChange={(e) => handleChange(e)}/>
                                { emptyValue && form["bairro"] === "" ? <span>É necessário informar o bairro!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='cidade' className='fw-bold'>Cidade:</label> 
                                <label htmlFor='uf' className='fw-bold' style={{marginLeft: '46%'}}>Estado:</label>

                                <div className='d-flex'>
                                    <input type="text" className="form-control w-50" id="cidade" name='cidade' placeholder='Pindamonhangaba' disabled 
                                    value={form['cidade']}
                                    onChange={(e) => handleChange(e)}/>

                                    <input type="text" className="form-control ms-4" id="uf" name='uf' placeholder='SP' maxLength='2' style={{width: "10%"}} disabled 
                                    value={form['uf']}
                                    onChange={(e) => handleChange(e)}/>
                                </div>
                                { emptyValue && form["cidade"] === "" ? <span>É necessário informar a cidade!</span> : ''}
                                { emptyValue && form["uf"] === "" ? <span style={{marginLeft: '27%'}}>É necessário informar o estado!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='complemento' className='fw-bold'>Complemento:</label> 
                                <input type="text" className="form-control w-50" id="complemento" name='complemento' placeholder='Condomínio São João' 
                                value={form['complemento']}
                                onChange={(e) => handleChange(e)}/>
                            </li>

                            <li style={{listStyle: 'none'}}>
                                <Link className='btn mt-3 mb-3 ms-4' to='/user/enderecos' id="btn-primary" style={{width: "max-content"}}>Cancelar</Link>
                                <button className='btn mt-3 mb-3' type='submit' id="btn-primary" style={{width: "max-content", marginLeft: "10%"}}>{isEditing ? "Salvar Alterações" : "Salvar Endereço"}</button>
                            </li>
                        </ul>
                    </form>
                </div>
            </main>
        <Footer/>
        </>
    );
};