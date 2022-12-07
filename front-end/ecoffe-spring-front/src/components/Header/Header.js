import './header.css';

export default function Header () {
    return (
        <nav className="navbar navbar-expand-lg bg-primary-color">
            <div className="container">
                <a className="navbar-brand ms-3" href="#"><img src={require("../../images/E- COFFEE 3.png")} alt="E-Coffee" id="banner-home"/></a>

                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav align-items-center">
                        <li className="nav-item">
                            <a className="nav-link text-white" aria-current="page" href="#">Home</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link text-white" href="#">Sobre nós</a>
                        </li>
                        <li className="nav-item">
                            <form action="" id="formPesquisar">
                                <input className="form-control text-center" type="search" placeholder="Pesquisar" id="search-bar"/>
                            </form>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link text-white" href="#">Cafeteira<i className="bi bi-chevron-down"></i></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link text-white" href="#">Cápsulas<i className="bi bi-chevron-down"></i></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link text-white" href="./login.html">Login</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link text-white" href="#"><i className="bi bi-cart2" id="cart-icon"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};