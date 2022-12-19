import { useContext } from 'react';
import { Link } from 'react-router-dom';

import Header from '../../../components/Header/Header';
import Footer from '../../../components/Footer/Footer';

import './SecurityUserForm.css';
import { Context } from '../../../context/AuthContext';

export default function SecurityUserForm () {

    const { dataCliente } = useContext(Context);

    return (
        <>
        <Header/>
            <main>
                <div className='container w-50'>
                    <h2 className='title-primary-color fw-bold mt-4'>Editar Informações</h2>
                    <form>
                        <ul className='list-group list-group-flush mt-4' id='lista-informacoes'>

                            <li className="list-group-item mx-4 mt-2">
                                <label htmlFor="nome" className='fw-bold'>Nome:</label>   
                                <input type="text" className="form-control" id="nome" value={dataCliente !== null && dataCliente.nome}/>
                            </li>

                            <li className="list-group-item mx-4 mt-2">
                                <label htmlFor="sobrenome" className='fw-bold'>Sobrenome:</label>
                                <input type="text" className="form-control" id="sobrenome" value={dataCliente !== null && dataCliente.sobrenome}/>
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor="telefone" className='fw-bold'>Telefone:</label>
                                <input type="text" className="form-control" id="telefone" value={dataCliente !== null && dataCliente.telefone}/>
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='cpf' className='fw-bold'>CPF:</label> 
                                <input type="text" className="form-control" id="cpf" value={dataCliente !== null && dataCliente.cpf}/>
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='email' className='fw-bold'>E-mail:</label> 
                                <input type="email" className="form-control" id="email" value={dataCliente !== null && dataCliente.email}/>
                            </li>

                            <li className="list-group-item mx-4">
                                <label htmlFor='senha' className='fw-bold'>Senha:</label> 
                                <input type="password" className="form-control" id="senha" placeholder='Nova Senha' />
                            </li>

                            <li style={{listStyle: 'none'}}>
                                <Link className='btn mt-3 mb-3 ms-4' to='/user/seguranca' id="btn-primary" style={{width: "max-content"}}>Cancelar</Link>
                                <button className='btn mt-3 mb-3' id="btn-primary" style={{width: "max-content", marginLeft: "10%"}}>Salvar</button>
                            </li>
                        </ul>
                    </form>
                </div>
            </main>
        <Footer/>
        </>
    );
};