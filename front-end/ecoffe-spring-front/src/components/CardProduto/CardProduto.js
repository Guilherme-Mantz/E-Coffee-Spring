import { useContext } from 'react';
import { Link } from 'react-router-dom';
import { Context } from '../../context/AuthContext';
import history from '../../history';
import api from '../../hook/api';

import { modedaBr } from '../../utils/formatCoinUtil';

import './CardProduto.css'

export default function CardProduto (props) {

    const { idProduto, nomeProduto, preco, imagem } = props.produto;

    const { dataCliente } = useContext(Context);

    async function handlePushOnCart(e){

        if(dataCliente === null){
            history.push('/iniciarsessao');
            window.location.reload();
        };

        await api.post(`/carrinho/inserirproduto/${idProduto}/${dataCliente.idCliente}`, {quantidade: 1})
        .then((res) => { 
            if(res.status === 201){
                
                history.push("/carrinho");
                window.location.reload();
            };
        })
        .catch((error) => {
            console.log(error)
        })
    };

    return (
        <div className="card col align-items-center p-3 text-center" id="card-produto">
            <Link to={`/produto/${idProduto}`} id='link-product'>
                <img src={require('../../../../../images/uploads/'+imagem)} className="card-img-top mt-3" style={{height: "230px"}} id="img-card" alt={nomeProduto}/>
                <div className="card-body">
                    <p className="card-text" id='nomeproduto'>{nomeProduto}</p>
                    <p className="card-text mt-1">{modedaBr(preco)}</p>
                </div>
            </Link>

            <button type='button' onClick={e => handlePushOnCart(e)} className='text-white p-1 border-0' id='comprar-produto'>Comprar <i className="bi bi-cart2"></i></button>
        </div>
    );
};