/*
    CRIAR LOGOUT
*/

export default function HeaderAdmin () {

    return (
        <>
            <header class="navbar navbar-expand-lg bg-primary-color">
                <div class="container">
                    <a class="navbar-brand ms-3" href="#"><img src={require("../../../images/E- COFFEE 3.png")} alt="E-Coffee" id="banner-home" /></a>

                    <h2 class="text-white">Backoffice</h2>
                </div>
            </header>
            <div class="container mt-3">
                <ul class="navbar-nav align-items-center d-flex flex-row justify-content-around">
                    <li class="nav-item">
                        <a class="nav-link text-black" aria-current="page" href="#">Painel</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-black" href="#">Vendas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-black" id="link-cafeteira" href="#">Produtos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-black" href="#">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-black" href="#">Promoções</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-black" href="#">Google Shopping</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-black" href="./login.html">Encerrar Sessão</a>
                    </li>
                </ul>
            </div>
        </>

    );

}