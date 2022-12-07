import './carrossel.css';

export default function Carrossel (){

    return (
        <div className="carrossel">
            <button className="carrossel_button carrossel_button--left is-hidden">
                <img src={require("../../images/seta-carrosel.png")} alt="seta carrossel direita"/>
            </button>
            <div className="carrossel_track-container">
                <ul className="carrossel_track">
                    <li className="carrossel_slide current-slide">
                        <img className="carrossel_img" src={require("../../images/ima-carrossel01.png")} alt="cafeteira"/>
                    </li>
                    <li className="carrossel_slide">
                        <img className="carrossel_img" src={require("../../images/capsulas-carrossel.jpg")} alt="xícara de café"/>
                    </li>
                    <li className="carrossel_slide">
                        <img className="carrossel_img" src={require("../../images/2coffees.png")} alt="Duas xícaras de café"/>
                    </li>
                </ul>
            </div>
            <button className="carrossel_button carrossel_button--right">
                <img src={require("../../images/seta-carrosel.png")} alt="seta carrossel esquerda"/>
            </button>
            <div className="carrossel_nav">
                <button className="carrossel_indicador current-slide"></button>
                <button className="carrossel_indicador"></button>
                <button className="carrossel_indicador"></button>
            </div>
        </div>
    );
};