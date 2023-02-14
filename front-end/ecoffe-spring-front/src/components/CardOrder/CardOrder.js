import { Link } from "react-router-dom";
import { modedaBr } from "../../utils/formatCoinUtil";

export default function CardOrder (props) {

    const { idPedido, dataPedido, valorTotal, quantidade, imagem, nomeProduto } = props.data;

    return (

        <div className="card p-0 mt-4">

            <div className="card-header" id="card-pedido-header">
                <ul className="d-flex justify-content-between p-0" style={{ listStyle: "none" }}>
                    <li className="text-center">
                        <h6>Pedido realizado em</h6>
                        <span>{dataPedido}</span>
                    </li>
                    <li className="text-center">
                        <h6>Total</h6>
                        <span>{modedaBr(valorTotal)}</span>
                    </li>
                    <li className="text-center">
                        <h6>Quantidade</h6>
                        <span>{quantidade}</span>
                    </li>
                    <li className="text-center">
                        <h6>N° do Pedido</h6>
                        <span>{idPedido}</span>
                    </li>
                </ul>
            </div>

            <div className="card-body" id="card-pedido-body">
                <div className="d-flex align-items-center">
                    <img src={require(`../../../../../images/uploads/${imagem}`)} alt={nomeProduto} width='130' height="140"/>
                    <div className="ms-5">
                        <p className="fw-bold">{nomeProduto}</p>
                        <Link to="#" className="text-white text-decoration-none btn bg-secondary-color">Comprar Novamente</Link>
                    </div>
                    <div className="ms-auto d-flex flex-column">
                        <Link to="#" className="text-white text-decoration-none btn bg-secondary-color">Rastrear Pacote</Link>
                        <Link to="#" className="text-white text-decoration-none btn bg-secondary-color mt-1">Avaliar Produto</Link>
                    </div>
                </div>
            </div>
        </div>

    );
};