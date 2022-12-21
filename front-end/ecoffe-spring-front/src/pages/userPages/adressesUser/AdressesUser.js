import { Link } from 'react-router-dom';

import Header from '../../../components/Header/Header';
import Footer from '../../../components/Footer/Footer';
import AdresUser from '../../../components/AdresUser/AdresUser';

import './AdressesUser.css';
import { useContext, useEffect, useState } from 'react';
import api from '../../../hook/api';
import { Context } from '../../../context/AuthContext';

export default function AdressesUser () {

    const { dataCliente } = useContext(Context);
    const [ enderecos, setEnderecos ] = useState([]);

    useEffect(() => {
        getAdresses()
    }, [dataCliente])

    async function getAdresses(){
        if(dataCliente !== null){
            await api.get(`/endereco/listar/${dataCliente.idCliente}`).then((res) =>  {
                setEnderecos(res.data);
            });
        };
    }

    console.log(enderecos)

    return (
        <>
        <Header/>
            <main>
                <div className='container'>
                    <h2 className='title-primary-color fw-bold mt-4'>Seus Endereços de Entrega</h2>
                    <div className='mt-5 row'>
                        { enderecos.length < 3 ? 
                            <Link to="/user/enderecos/novo" id='link-addAdres' className='d-flex flex-column text-center'>
                                <i className="bi bi-plus-lg mt-auto"></i>
                                <p className='text-wite fw-bold lead mb-auto'>Adicionar Endereço</p>
                            </Link> 
                        : ''}

                        { enderecos !== [] && enderecos.map((endereco) => <AdresUser key={endereco.idEndereco} adres={endereco} />) }
                    </div>
                </div>
            </main>
        <Footer/>
        </>
    );
};