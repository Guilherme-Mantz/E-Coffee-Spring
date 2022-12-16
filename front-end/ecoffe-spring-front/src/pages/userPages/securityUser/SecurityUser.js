import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

import api from '../../../hook/api';

import Header from '../../../components/Header/Header';
import Footer from '../../../components/Footer/Footer';

import './SecurityUser.css';

export default function SecurityUser () {

    return (
        <>
        <Header/>
            <main>
                <div className='container w-50'>
                    <h2 className='title-primary-color fw-bold mt-4'>Acesso e Segurança</h2>
                    <ul className='list-group list-group-flush mt-4' id='lista-informacoes'>

                        <li className="list-group-item mx-4 mt-2"><span className='fw-bold'>Nome:</span> Guilherme Mantz</li>

                        <li className="list-group-item mx-4"><span className='fw-bold'>Telefone:</span> (12)123456789</li>

                        <li className="list-group-item mx-4"><span className='fw-bold'>CPF:</span> 12345678975</li>

                        <li className="list-group-item mx-4"><span className='fw-bold'>E-mail:</span> seuemail@email</li>

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