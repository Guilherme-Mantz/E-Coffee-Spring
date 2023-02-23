import './carrossel.css';

export default function Carrossel (){

    return (
        <div id="slider-container">
            <div className="carousel slide" id="slider" data-bs-ride="carousel">
                <div className="carousel-indicators">
                    <button
                        type="button"
                        className="active"
                        data-bs-target="#slider"
                        data-bs-slide-to="0"
                        arial-current="true"
                        aria-label="Slide 1"
                    ></button>
                    <button
                        type="button"
                        data-bs-target="#slider"
                        data-bs-slide-to="1"
                        aria-label="Slide 2"
                    ></button>
                    <button
                        type="button"
                        data-bs-target="#slider"
                        data-bs-slide-to="2"
                        aria-label="Slide 3"
                    ></button>
                </div>
                <div className="carousel-inner">
                    <div className="carousel-item active">
                        <img src={require("../../images/ima-carrossel01.png")} alt="cafeteira" className="d-block m-auto rounded" id="carousel-image" />
                    </div>
                    <div className="carousel-item">
                        <img src={require("../../images/capsulas-carrossel.jpg")} alt="xícara de café" className="d-block m-auto rounded" id="carousel-image" />
                    </div>
                    <div className="carousel-item">
                        <img src={require("../../images/2coffees.png")} alt="Duas xícaras de café" className="d-block m-auto rounded" id="carousel-image" />
                    </div>
                </div>
                <button className="carousel-control-prev justify-content-start" type="button" data-bs-target="#slider" data-bs-slide="prev" >
                    <i className="bi bi-chevron-compact-left"></i>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next justify-content-end" type="button" data-bs-target="#slider" data-bs-slide="next" >
                    <i className="bi bi-chevron-compact-right"></i>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    );
};