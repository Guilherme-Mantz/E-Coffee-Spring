import { Link } from 'react-router-dom';

import { modedaBr } from '../../utils/formatCoinUtil';

import './CardProduto.css'

export default function CardProduto (props) {

    const { idProduto, nomeProduto, preco, imagem } = props.produto;

    return (
        <div className="card col align-items-center p-3 text-center" id="card-produto">
            <Link to={`/produto/${idProduto}`} id='link-product'>
                <img src={require('../../../../../images/uploads/'+imagem)} className="card-img-top mt-3" style={{height: "230px"}} id="img-card" alt={nomeProduto}/>
                <div className="card-body">
                    <p className="card-text" id='nomeproduto'>{nomeProduto}</p>
                    <p className="card-text mt-1">{modedaBr(preco)}</p>
                </div>
            </Link>

            <Link to="#" className='text-decoration-none text-white p-1' id='comprar-produto'>Comprar <i class="bi bi-cart2"></i></Link>
        </div>
    );
};