import { Link } from 'react-router-dom';

import './CardProduto.css'

export default function CardProduto (props) {

    const { idProduto, nomeProduto, preco, imagem } = props.produto;

    return (
        <div className="card col align-items-center p-3 text-center" id="card-produto">
            <Link to={`/produto/${idProduto}`} id='link-product'>
                <img src={require('../../../../../images/uploads/'+imagem)} className="card-img-top mt-3" style={{height: "230px"}} id="img-card" alt={nomeProduto}/>
                <div className="card-body">
                    <p className="card-text">{nomeProduto}</p>
                    <p className="card-text">{preco.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'})}</p>
                </div>
            </Link>
        </div>
    );
};