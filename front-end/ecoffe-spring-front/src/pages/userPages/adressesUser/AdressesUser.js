import { Link } from 'react-router-dom';

import Header from '../../../components/Header/Header';
import Footer from '../../../components/Footer/Footer';
import AdresUser from '../../../components/AdresUser/AdresUser';

import './AdressesUser.css';

export default function AdressesUser () {

    return (
        <>
        <Header/>
            <main>
                <div className='container w-50'>
                    <h2 className='title-primary-color fw-bold mt-4'>Seus Endereços de Entrega</h2>
                    <div className='mt-5 row'>
                        <Link to="#" id='link-addAdres' className='d-flex flex-column text-center'>
                            <i className="bi bi-plus-lg mt-auto"></i>
                            <p className='text-wite fw-bold lead mb-auto'>Adicionar Endereço</p>
                        </Link>

                        <AdresUser nomeDoEndereco="endereco exemplo" 
                            logradouro="Rua tal tal" 
                            numeroResidencial="90" 
                            bairro="tal tal" 
                            cep="14785236" 
                            cidade="Piracicaba" 
                            estado="São Paulo" uf="SP" 
                            complemento=""
                        />
                    </div>
                </div>
            </main>
        <Footer/>
        </>
    );
};