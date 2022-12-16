import { Link } from 'react-router-dom';

import './Footer.css';

export default function Footer () {
    return (
        <footer className="bd-footer py-4 py-md-5 mt-5 bg-primary-color">
            <div className="container py-4 py-md-5 px-4 px-md-3">
                <div className="row">
                    <div className="col-lg-2 mb-3">
                        <img id="banner-footer" src={require('../../images/E- COFFEE 3.png')} />
                    </div>
                    <div className="col-6 col-lg-2 offset-lg-1 mb-3">
                        <h4 className="text-wite fw-bold">Início</h4>
                        <ul className="list-unstyled">
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Home</Link></li>
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Cafeteiras</Link></li>
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Cápsulas</Link></li>
                        </ul>
                    </div>
                    <div className="col-6 col-lg-2 mb-3">
                        <h4 className="text-wite fw-bold">Sobre Nós</h4>
                        <ul className="list-unstyled">
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Informações</Link></li>
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Blog</Link></li>
                        </ul>
                    </div>
                    <div className="col-6 col-lg-2 mb-3">
                        <h4 className="text-wite fw-bold">Suporte</h4>
                        <ul className="list-unstyled">
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">FAQ</Link></li>
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Contato</Link></li>
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Chat</Link></li>
                        </ul>
                    </div>
                    <div className="col-6 col-lg-2 mb-3">
                        <h4 className="text-wite fw-bold">Minha Conta</h4>
                        <ul className="list-unstyled">
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Meus pedidos</Link></li>
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Endereços de entrega</Link></li>
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Alterar Cadastro</Link></li>
                            <li className="mb-2"><Link className="text-wite footer-link" to="/">Minha Conta</Link></li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    );
};