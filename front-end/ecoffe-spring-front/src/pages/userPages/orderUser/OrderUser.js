import { useContext, useEffect, useState } from "react";
import { Context } from "../../../context/AuthContext";

import CardOrder from "../../../components/CardOrder/CardOrder";

import Footer from "../../../components/Footer/Footer";
import Header from "../../../components/Header/Header";

import api from "../../../hook/api";

import './OrderUser.css';
import history from "../../../history";
import { Link } from "react-router-dom";

export default function OrderUser () {

    const { dataCliente } = useContext(Context);
    const [ loading, setLoading ] = useState(true);
    const [ dataPedidos, setDataPedidos ] = useState(null);

    useEffect(() => {
        
        if(dataCliente !== null){
            api.get(`/pedido/getpedidos/${dataCliente.idCliente}`)
            .then((res) => {
                setDataPedidos(res.data);
                console.log(dataPedidos)
            })
            .catch(() => {
            })
            .finally(() => {
                setLoading(false)
            });
        }

    }, [dataCliente])

    if(loading){
        return <h2>Loading...</h2>
    }

    return (

        <>
        <Header/>
            <main>
                <div className='container'>
                    <h2 className='title-primary-color fw-bold mt-4'>Seus Pedidos</h2>
                    <div className='mt-5 row'>
                        { dataPedidos !== null ? dataPedidos.map((pedido) => <CardOrder data={pedido}/>) : 
                            <>
                                <div>
                                    <h3>Você não possui pedidos</h3>
                                    <Link to="/user/home" className="text-white text-decoration-none btn bg-secondary-color fw-bold mt-4">Voltar</Link>
                                </div>
                            </>
                        } 

                    </div>
                </div>
            </main>
        <Footer/>
        </>

    );
};