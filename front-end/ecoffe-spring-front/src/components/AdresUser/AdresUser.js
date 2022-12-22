import { Link } from 'react-router-dom';
import api from '../../hook/api';

import './AdresUser.css'

export default function AdresUser(props) {

    const { idEndereco, nomeEndereco, logradouro, numeroResidencia, bairro, cep, cidade, uf, complemento} = props.adres;

    async function handleRemove(){
        await api.delete(`/endereco/deletar/${props.user}/${idEndereco}`); //Futuramente validar se o endereco foi utilizado em alguma compra;
        window.location.reload();
    }

    return (
        <div className='card-adress p-4 ms-5'>
            <h5 className='fw-bold text-center'>{nomeEndereco}</h5>
            <ul className='list-group' id='adress-information'>
                <li>{logradouro}, N° {numeroResidencia}</li>
                <li>{bairro}</li>
                <li>{cep}</li>
                <li>{cidade}, {uf}</li>
                <li>Complemento: {complemento}</li>

                <li className='d-flex justify-content-between' style={{ listStyle: 'none' }}>
                    <Link className='btn mt-2' to='#' id="btn-primary" style={{ width: "max-content" }}>Alterar</Link>
                    <button className='btn mt-2' id="btn-primary" style={{ width: "max-content", marginLeft: "10%" }} onClick={() => handleRemove()}>Excluir</button>
                </li>
            </ul>
        </div>
    );
};