import { useContext, useState } from 'react';
import { Link } from 'react-router-dom';

import api from '../../../hook/api';
import { Context } from '../../../context/AuthContext';

import Header from '../../../components/Header/Header';
import Footer from '../../../components/Footer/Footer';

import './AdresFormUser.css';
import history from '../../../history';

export default function AdresFormUser () {

    const { dataCliente } = useContext(Context);

    const [ emptyValue, setEmptyValue ] = useState(false);
    const [ form, setForm ] = useState({
        nomeEndereco: "",
        cep: "", //implementar VIACEP, *Validar numeros*
        logradouro: "",
        numeroResidencia: "",
        bairro: "",
        cidade: "",
        uf: "",
        complemento: ""
    });

    function handleChange(e){
        let newProp = form;

        newProp[e.target.name] = e.target.value;
        setForm({ ...newProp })
    };

    async function handleCadaster(e){
        e.preventDefault();

        /* Campos preenchidos */
        let emptyValues = Object.values(form).some(obj => obj === "");
        setEmptyValue(emptyValues);

        if(!emptyValues || form["complemento"] == ''){

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

    return (
        <>
        <Header/>
            <main>
                <div className='container w-50'>
                    <h2 className='title-primary-color fw-bold mt-4'>Cadastrar Novo Endereço</h2>
                    <form onSubmit={(e) => handleCadaster(e)}>
                        <ul className='list-group list-group-flush mt-4' id='lista-informacoes'>

                            <li className="list-group-item mx-4 mt-2">
                                <label htmlFor="nomeEndereco" className='fw-bold'>Nome do Endereço:</label>   
                                <input type="text" className="form-control w-50" id="nomeEndereco" name='nomeEndereco' placeholder='Minha casa' onChange={(e) => handleChange(e)}/>
                                { emptyValue && form["nomeEndereco"] === "" ? <span>É necessário informar um nome de identificação!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4 mt-2">
                                <label htmlFor="cep" className='fw-bold'>Cep:</label>
                                <input type="text" className="form-control w-50" id="cep" name='cep' maxLength='8' placeholder='12345678' onChange={(e) => handleChange(e)}/>
                                { emptyValue && form["cep"] === "" ? <span>É necessário informar o cep!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor="logradouro" className='fw-bold'>Logradouro:</label>
                                <label htmlFor="numeroResidencia" style={{marginLeft: '42%'}} className='fw-bold'>Número:</label>

                                <div className='d-flex'>
                                    <input type="text" className="form-control w-50" id="logradouro" name='logradouro' placeholder='Rua fulano' onChange={(e) => handleChange(e)}/>
                                    <input type="text" className="form-control ms-4" style={{width: "10%"}} id="numeroResidencia" name='numeroResidencia' placeholder='20' onChange={(e) => handleChange(e)}/>
                                </div>
                                { emptyValue && form["logradouro"] === "" ? <span>É necessário informar o logradouro!</span> : ''}
                                { emptyValue && form["numeroResidencia"] === "" ? <span style={{marginLeft: '23%'}}>É necessário informar o número da residência!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='bairro' className='fw-bold'>Bairro:</label> 
                                <input type="text" className="form-control w-50" id="bairro" name='bairro' placeholder='Bairro sudeste' onChange={(e) => handleChange(e)}/>
                                { emptyValue && form["bairro"] === "" ? <span>É necessário informar o bairro!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='cidade' className='fw-bold'>Cidade:</label> 
                                <label htmlFor='estado' className='fw-bold' style={{marginLeft: '46%'}}>Estado:</label>

                                <div className='d-flex'>
                                    <input type="text" className="form-control w-50" id="cidade" name='cidade' placeholder='Pindamonhangaba' onChange={(e) => handleChange(e)}/>
                                    <input type="text" className="form-control ms-4" id="estado" name='uf' placeholder='SP' maxLength='2' style={{width: "10%"}} onChange={(e) => handleChange(e)}/>
                                </div>
                                { emptyValue && form["cidade"] === "" ? <span>É necessário informar a cidade!</span> : ''}
                                { emptyValue && form["uf"] === "" ? <span style={{marginLeft: '27%'}}>É necessário informar o estado!</span> : ''}
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='complemento' className='fw-bold'>Complemento:</label> 
                                <input type="text" className="form-control w-50" id="complemento" name='complemento' placeholder='Condomínio São João' onChange={(e) => handleChange(e)}/>
                            </li>

                            <li style={{listStyle: 'none'}}>
                                <Link className='btn mt-3 mb-3 ms-4' to='/user/enderecos' id="btn-primary" style={{width: "max-content"}}>Cancelar</Link>
                                <button className='btn mt-3 mb-3' type='submit' id="btn-primary" style={{width: "max-content", marginLeft: "10%"}}>Salvar Endereço</button>
                            </li>
                        </ul>
                    </form>
                </div>
            </main>
        <Footer/>
        </>
    );
};