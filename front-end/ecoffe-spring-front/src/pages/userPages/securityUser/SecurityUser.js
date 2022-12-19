import { useContext } from 'react';
import { Link } from 'react-router-dom';

import Header from '../../../components/Header/Header';
import Footer from '../../../components/Footer/Footer';

import './SecurityUser.css';
import { Context } from '../../../context/AuthContext';

export default function SecurityUser () {

    const { dataCliente } = useContext(Context);

    return (
        <>
        <Header/>
            <main>
                <div className='container w-50'>
                    <h2 className='title-primary-color fw-bold mt-4'>Acesso e Segurança</h2>
                    <ul className='list-group list-group-flush mt-4' id='lista-informacoes'>

                        <li className="list-group-item mx-4 mt-2"><span className='fw-bold'>Nome:</span> {dataCliente !== null && dataCliente.nome} {dataCliente !== null && dataCliente.sobrenome}</li>

                        <li className="list-group-item mx-4"><span className='fw-bold'>Telefone:</span> {dataCliente !== null && dataCliente.telefone}</li>

                        <li className="list-group-item mx-4"><span className='fw-bold'>CPF:</span> {dataCliente !== null && dataCliente.cpf}</li>

                        <li className="list-group-item mx-4"><span className='fw-bold'>E-mail:</span> {dataCliente !== null && dataCliente.email}</li>

                        <li className="list-group-item mx-4"><span className='fw-bold'>Senha:</span> ********</li>

                        <li style={{listStyle: 'none'}}>
                            <Link className='btn mt-3 mb-3 ms-4' to='/user/home' id="btn-primary" style={{width: "max-content"}}>voltar</Link>
                            <Link className='btn mt-3 mb-3' to='/user/seguranca/editar' id="btn-primary" style={{width: "max-content", marginLeft: "10%"}}>Editar</Link>
                        </li>
                    </ul>
                </div>
            </main>
        <Footer/>
        </>
    );
};