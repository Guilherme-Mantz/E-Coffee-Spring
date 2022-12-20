import { Link } from 'react-router-dom';

import './AdresUser.css'

export default function AdresUser(props) {

    const { nomeDoEndereco, logradouro, numeroResidencial, bairro, cep, cidade, estado, uf, complemento} = props;

    return (
        <div className='card-adress p-4 ms-5'>
            <h5 className='fw-bold text-center'>{nomeDoEndereco}</h5>
            <ul className='list-group' id='adress-information'>
                <li>{logradouro}, N° {numeroResidencial}</li>
                <li>{bairro}</li>
                <li>{cep}</li>
                <li>{cidade}, {estado}, {uf}</li>
                <li>Complemento: {complemento}</li>

                <li className='d-flex justify-content-between' style={{ listStyle: 'none' }}>
                    <Link className='btn mt-2' to='#' id="btn-primary" style={{ width: "max-content" }}>Alterar</Link>
                    <Link className='btn mt-2' to='#' id="btn-primary" style={{ width: "max-content", marginLeft: "10%" }}>Excluir</Link>
                </li>
            </ul>
        </div>
    );
};