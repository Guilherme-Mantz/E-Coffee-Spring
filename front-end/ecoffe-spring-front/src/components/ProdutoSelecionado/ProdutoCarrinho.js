export default function ProdutoCarrinho (props) {

    const { imagem, nomeProduto, preco, quantidade } = props;

    return (
        <tr>
            <th scope="row">
                <img src="./img/cafeteira1.png" alt="" className="w-100" />
                <a href="#" className="text-decoration-none text-dark h6">Remover Produto</a>
            </th>
            <td>Cafeteira mondial 220v</td>
            <td className="text-center">R$575</td>
            <td className="text-center">
                <input type="text" name="quantidade" value="1" disabled="disabled" id="quantidade-produto" />
            </td>
            <td className="text-center">R$575</td>
        </tr>
    );
};