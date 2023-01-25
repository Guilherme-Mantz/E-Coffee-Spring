import api from "../../hook/api";
import { modedaBr } from "../../utils/formatCoinUtil";

export default function ProdutoCarrinho (props) {

    const { idCarrinho, imagem, nomeProduto, preco, quantidade } = props.produto;

    function handleRemove(){
        api.delete(`/carrinho/removerproduto/${idCarrinho}`)
        .then((res) => {
            if(res.status === 204){
                window.location.reload();
            }
        })
        .catch((e) => console.debug(e))
    }

    return (
        <tr>
            <th scope="row">
                <img src={require('../../../../../images/uploads/'+imagem)} alt={nomeProduto} className="w-100" style={{height: "139px"}} />
                <a href="#" onClick={() => handleRemove()} className="text-decoration-none text-dark h6">Remover Produto</a>
            </th>
            <td>{nomeProduto}</td>
            <td className="text-center">{modedaBr(preco)}</td>
            <td className="text-center">
                <input type="text" name="quantidade" value={quantidade} disabled="disabled" id="quantidade-produto" />
            </td>
            <td className="text-center" id="subtotal">{modedaBr(preco * quantidade)}</td>
        </tr>
    );
};