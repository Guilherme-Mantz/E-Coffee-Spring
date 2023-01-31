import FormLogin from "../../components/Forms/FormLogin/FormLogin";
import FormRegistro from "../../components/Forms/FormRegistro/FormRegistro";
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';

import './loginPage.css';

export default function LoginPage () {

    /* Trocar formulario */
    function handleForm(e){
        let container = document.getElementById("container");
        if(e.target.id === 'signUp'){
            container.classList.add("right-panel-active");

        } else if(e.target.id === 'signIn'){
            container.classList.remove("right-panel-active");
        };
    };

    return (
        <>
        <Header />
        <div className="body-login">
            <div className="container" id="container">

                <FormRegistro />

                <FormLogin />

                <div className="overlay-container">
                    <div className="overlay">
                        <div className="overlay-panel overlay-left">
                            <h1>Olá, Amigo!</h1>
                            <p>Insira seus dados pessoais e comece a jornada conosco</p>
                            <button className="ghost" id="signIn" onClick={handleForm}>Registrar-se</button>
                        </div>
                        <div className="overlay-panel overlay-right">
                            <h1>Bem-Vindo de Volta!</h1>
                            <button className="ghost" id="signUp" onClick={handleForm}>Login</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <Footer />
        </>
    );
};