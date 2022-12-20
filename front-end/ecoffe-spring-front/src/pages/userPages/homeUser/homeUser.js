import Header from '../../../components/Header/Header';
import Footer from '../../../components/Footer/Footer';
import LinkUser from '../../../components/LinkUser/LinkUser';

export default function homeUser () {

    return (
        <>
        <Header/>
            <main>
                <div className="container">
                    <h1 className="title-primary-color fw-bold mt-5" style={{marginLeft: "34px"}}>Sua Conta</h1>
                    <div className="row row-cols-6 justify-content-center mt-5">

                        <LinkUser icon="bi bi-box-seam" titulo="Meus Pedidos" descricao="Rastrear Seus Pedidos ou Comprar  Novamente" link="/user/seguranca"/>
                        <LinkUser icon="bi bi-shield" titulo="Acesso e Segurança" descricao="Alterar Nome ou Senha" link="/user/seguranca" />
                        <LinkUser icon="bi bi-house" titulo="Meus Endereços" descricao="Cadastrar novos endereços ou alterá-los" link="/user/enderecos"/>
                        <LinkUser icon="bi bi-credit-card" titulo="Formas de Pagamentos" descricao="Gerenciar formas de pagamento" link="/user/seguranca"/>

                    </div>
                </div>
            </main>
        <Footer/>
        </>
    );
};