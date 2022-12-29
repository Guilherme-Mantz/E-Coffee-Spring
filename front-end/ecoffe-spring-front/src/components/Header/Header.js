import { useContext } from "react";
import { Context } from "../../context/AuthContext";
import { Link } from 'react-router-dom';

import './header.css';

export default function Header () {

    const { dataCliente, handleLogout } = useContext(Context);

    return (
        <nav className="navbar navbar-expand-lg bg-primary-color" id="navbar">
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
                            <Link className="nav-link text-white" id="link-cafeteiras" to="/produtos/cafeteira">Cafeteiras</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/produtos/capsula">Cápsulas</Link>
                        </li>
                        { dataCliente !== null ? 
                            <>
                            <li className="nav-item">
                                <Link to='#' className="nav-link text-white dropdown-toggle-split" data-bs-toggle="dropdown">Olá, {dataCliente.nome}<i className="bi bi-chevron-down"></i></Link>

                                <ul className="dropdown-menu" style={{left: "auto", top: "99%"}}>
                                    <li><Link className="dropdown-item btn text-wite mt-2" to="/user/home">Minha Conta</Link></li>
                                    <li><button className="dropdown-item btn text-wite mt-3" type="button" onClick={ () => handleLogout() }>Sair</button></li>
                                </ul>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link text-white" to="/"><i className="bi bi-cart2" id="cart-icon"></i></Link>
                            </li>
                            </>
                            :
                            <li className="nav-item">
                                <Link className="nav-link text-white" to="/iniciarsessao" style={{width: 'auto'}}>Realize o Login ou Cadastre-se</Link>
                            </li>
                        }

                    </ul>
                </div>
            </div>
        </nav>
    );
};