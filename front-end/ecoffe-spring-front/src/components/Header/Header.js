import { useContext, useEffect, useState } from "react";
import { Context } from "../../context/AuthContext";
import { Link } from 'react-router-dom';
import api from '../../hook/api';

import './header.css';

export default function Header () {

    const { authenticated } = useContext(Context);
    const [ nomeUsuario, setNomeUsuario ] = useState('');

    useEffect(() => {
    
        if (authenticated) {
            api.get('/cliente/get/data').then((res) => { 
                setNomeUsuario(res.data.nome);
            });
        };
    
    }, [authenticated]);

    return (
        <nav className="navbar navbar-expand-lg bg-primary-color">
            <div className="container">
                <Link className="navbar-brand ms-3" to="/"><img src={require("../../images/E- COFFEE 3.png")} alt="E-Coffee" id="banner-home"/></Link>

                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav align-items-center">
                        <li className="nav-item">
                            <Link className="nav-link text-white" aria-current="page" to="/">Home</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/">Sobre nós</Link>
                        </li>
                        <li className="nav-item">
                            <form action="" id="formPesquisar">
                                <input className="form-control text-center" type="search" placeholder="Pesquisar" id="search-bar"/>
                            </form>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/">Cafeteira<i className="bi bi-chevron-down"></i></Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/">Cápsulas<i className="bi bi-chevron-down"></i></Link>
                        </li>
                        <li className="nav-item">
                            { nomeUsuario !== '' ? <Link className="nav-link text-white" to="/user/home">Olá, {nomeUsuario}</Link> 
                                : <Link className="nav-link text-white" to="/iniciarsessao">Login</Link>
                            }
                            
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/"><i className="bi bi-cart2" id="cart-icon"></i></Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};