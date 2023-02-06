import { Link } from 'react-router-dom';

export default function CheckoutEndereco (props) {

    const { idEndereco, nomeEndereco, logradouro, numeroResidencia, bairro, cep, cidade, uf, complemento} = props.endereco;

    return (
        <>
            <div className="d-flex mt-3 p-3" id="select-endereco">
                <input type="radio" id="selectEndereco" name="selectEndereco" value={idEndereco} defaultChecked={props.index === 0} onChange={(e) => props.func(e)} />
                <ul className="w-100 mb-0">
                    <li><h6>{nomeEndereco}</h6></li>
                    <li>{logradouro}, N° {numeroResidencia}</li>
                    <li>{cep}</li>
                    <li>{bairro} - {cidade} - {uf}</li>
                    {complemento ? <li>{complemento}</li> : ''}
                    <div className="d-flex justify-content-between">
                        <Link className="text-decoration-none title-primary-color" to={`/user/enderecos/alterar/${idEndereco}`}>Editar</Link>
                        <Link className="text-decoration-none title-primary-color" to="/user/enderecos">Adicionar Endereço</Link>
                    </div>
                </ul>
            </div>
        </>
    );
};