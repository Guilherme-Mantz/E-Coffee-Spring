import { useContext, useState } from "react";
import { Context } from "../../context/AuthContext";
import { Link } from 'react-router-dom';

import api from "../../hook/api";
import history from "../../history";

import './header.css';

export default function Header () {

    const { dataCliente, handleLogout } = useContext(Context);
    const [ dataSearchBar, setDataSearchBar ] = useState(null);

    function handleChangeSearchBar (e){

        let value = e.target.value.replace(/\s/g, '');

        if(value.length >= 2){
            api.get(`/produto?nomeProduto=${value}`)
            .then((res) => {
                if (res.data.length > 0) { setDataSearchBar(res.data.slice(0,5)); }
            })
        }
        else {
            setDataSearchBar(null);
        }
    }

    async function handleSearchProduct (e){
        e.preventDefault();

        history.replace(`/produtos?nomeProduto=${e.target[1].value}`);
        window.location.reload()
    }

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
                            <form id="formPesquisar" onSubmit={e => handleSearchProduct(e)}>
                                <div className="input-group">
                                    <button type="submit" className="input-group-text" id="basic-addon1">
                                        <i className="bi bi-search"></i>
                                    </button>
                                    <input 
                                        className="form-control text-center" 
                                        name="nomeProduto" 
                                        type="search" 
                                        placeholder="Pesquisar" 
                                        id="search-bar" 
                                        aria-describedby="basic-addon1" 
                                        onChange={handleChangeSearchBar}
                                        style={dataCliente !== null ? {width: 33+'rem'} : {}}
                                    />
                                </div>
                                {
                                    dataSearchBar !== null &&

                                    <div id="produtos-searchbar">
                                        {dataSearchBar?.map(produto => (

                                            <div className="m-2 border-bottom border-secondary pb-2" key={produto.idProduto}>
                                                <Link to={"/produto/"+produto.idProduto} className="text-decoration-none text-black d-inline-block">
                                                    <img src={require('../../../../../images/uploads/' + produto.imagem)} alt={produto.nomeProduto} width="115" height="115" />
                                                    <span className="ms-2" id="nomeproduto-searchbar">{produto.nomeProduto}</span>
                                                </Link>
                                            </div>
                                        ))}
                                    </div>
                                }
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
                                    <li><Link className="dropdown-item btn text-white mt-2" to="/user/home">Minha Conta</Link></li>
                                    <li><button className="dropdown-item btn text-white mt-3" type="button" onClick={ () => handleLogout() }>Sair</button></li>
                                </ul>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link text-white" to="/carrinho"><i className="bi bi-cart2" id="cart-icon"></i></Link>
                            </li>
                            </>
                            :
                            <li className="nav-item">
                                <Link className="nav-link text-white" to="/iniciarsessao" style={{width: 'auto'}}>Login ou Cadastre-se</Link>
                            </li>
                        }

                    </ul>
                </div>
            </div>
        </nav>
    );
};