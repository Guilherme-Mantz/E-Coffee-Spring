import { modedaBr } from "../../../utils/formatCoinUtil";

export default function CheckoutProduto (props) {

    const produtos = props.produtos;

    let totalCompra = produtos.reduce((total, valor) => total + valor.preco * valor.quantidade, 0);

    return (
        <>
        <div className="col-lg-6">
            { produtos.map((produto) => (

                <div className="d-flex mt-3" key={produto.idProduto}>
                    <img src={require('../../../../../../images/uploads/' + produto.imagem)} alt={produto.nomeProduto} width="150" height="150" />
                    <ul>
                        <li>{produto.nomeProduto}</li>
                        <li>Preço únitario {modedaBr(produto.preco)}</li>
                        <li>Quantidade {produto.quantidade}</li>
                        <li>Entrega Normal em até 2 dias úteis</li>
                    </ul>
                </div>
                
            )) }
        </div>

        <div className="col-6 d-grid">
            <ul id="checkout-produtos" className="p-4 ms-auto">
                {produtos.map((p, i) => (
                    <li className="d-flex justify-content-between" key={i}><span>Produto {i+1}</span><span>{modedaBr(p.preco * p.quantidade)}</span></li>
                ))}
                <li className="d-flex justify-content-between">frete <span>R$ XX,XX</span></li>
                <li className="d-flex justify-content-between">Total <span>{modedaBr(totalCompra)}</span></li>
            </ul>
        </div>
        </>
    );

};