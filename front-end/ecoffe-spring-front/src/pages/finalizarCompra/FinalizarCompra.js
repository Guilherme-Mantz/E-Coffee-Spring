import { Link } from 'react-router-dom';
import React, { useEffect, useState, useContext } from 'react';

import { Context } from '../../context/AuthContext';

import history from '../../history';

import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import CheckoutProduto from '../../components/Checkout/CheckoutProduto/CheckoutProduto';
import CheckoutEndereco from '../../components/Checkout/CheckoutEndereco/CheckoutEndereco';

import api from '../../hook/api';

import './FinalizarCompra.css'

export default function FinalizarCompra () {

    const { dataCliente } = useContext(Context);

    const [ dataProduto, setDataProduto ] = useState([]);
    const [ enderecos, setEnderecos ] = useState([]);
    const [ loading, setLoading ] = useState(true);

    const [ idendereco, setIdendereco ] = useState(null);

    useEffect(() => {
        if(dataCliente !== null){
            api.get(`/carrinho/getdata/${dataCliente.idCliente}`)
            .then((res) => {
                if(res.data.length === 0) {
                    history.push('/');
                    window.location.reload();
                }
                setDataProduto(res.data);
            })
            .catch(() => {
                history.push('/');
                window.location.reload();
            })
            .finally(() => {
                setLoading(false)
            });

            api.get(`/endereco/listar/${dataCliente.idCliente}`)
            .then((res) => {
                setEnderecos(res.data);
            })
            .catch((err) => console.log(err))
        }

    }, [dataCliente])

    useEffect(() => {
        setTimeout(() => {
            const elements = document.querySelectorAll('#selectEndereco');
    
            elements.forEach((e) => {
                if(e.checked && idendereco === null){
                    setIdendereco(e.value)
                }
            });
    
        }, 300)

    },[idendereco]);

    function handleChange(e){
        setIdendereco(valorAntigo => valorAntigo = e.target.value);
    };

    if(loading){
        return <h2>Carregando produtos...</h2>
    };

    async function handleSubmit(){
        api.post("pedido/finalizarcompra", {idCliente: dataCliente.idCliente, idEndereco: idendereco})
        .then((res) => {
            if(res.status === 201){
                /*Pagina de pedidos pendente*/
                history.replace("/user/home");
                window.location.reload();
            }
        })
    };
    
    return (
        <>
        <Header/>

        <main>
            <div className="container mt-5">
                <h2 className="mb-4">Finalizar Compra</h2>
                <div className="row mb-5">
                    { dataProduto.length > 0 && <CheckoutProduto produtos={dataProduto}/>}              
                </div>

                <div className="row mt-5">
                    <div className="col-6 p-3" id="checkout-endereco">
                        <div>
                            <div className="d-flex align-items-center mb-3">
                                <i className="bi bi-house-fill h5 mb-0"></i> 
                                <h5 className="mb-0 ms-1 title-primary-color fw-bold">Endereço de Entrega</h5>
                            </div>

                            { enderecos.length === 0 ?  
                                <>
                                    <div className='text-center'>
                                        <p className='fw-bold'>Você não possui nenhum endereço cadastrado!</p> 
                                        <Link to="/user/enderecos" className='text-black'>Crie um novo endereço aqui</Link>
                                    </div>
                                </> 
                                : 
                                <>
                                    <span>Seus Endereços: </span>
                                    {enderecos.map((endereco, i) => <CheckoutEndereco endereco={endereco} key={endereco.idEndereco} index={i} func={handleChange}/> )}
                                </>
                            }

                            <h5 className="title-primary-color mt-3 text-center">Selecione a forma de Entrega</h5>

                            <ul style={{listStyle: "none"}} className="p-0">
                                <li className="d-flex border-bottom border-secondary mt-2">
                                    <input type="radio" id="selectEntrega" name="selectEntrega" value="normal" defaultChecked /> <div className="d-flex justify-content-between w-100"><span>Normal</span> <span>2 dias úteis</span> <span>R$xx,xx</span></div>
                                </li>
                                <li className="d-flex border-bottom border-secondary mt-2">
                                    <input type="radio" id="selectEntrega" name="selectEntrega" value="normal" /> <div className="d-flex justify-content-between w-100"><span>Agendada</span> <span>Agendar</span> <span>R$xx,xx</span></div>
                                </li>
                                <li className="d-flex border-bottom border-secondary mt-2">
                                    <input type="radio" id="selectEntrega" name="selectEntrega" value="normal" /> <div className="d-flex justify-content-between w-100"><span>Expressa</span> <span>1 dia útil</span> <span>R$xx,xx</span></div>
                                </li>
                            </ul>

                        </div>
                    </div>
                    <div className="col-4 p-3 h-50 ms-5" id="checkout-pagamento" >
                        <div className="d-flex align-items-center mb-3">
                            <i className="bi bi-cash-coin h5 mb-0"></i> 
                            <h5 className="mb-0 ms-1 title-primary-color fw-bold">Selecione a forma de pagamento</h5>
                        </div>

                        <ul style={{listStyle: "none"}} className="p-0">
                            <li className="d-flex border-bottom border-secondary mt-2">
                                <input type="radio" id="selectPagamento" name="selectPagamento" value="normal" defaultChecked />
                                <div className="d-flex justify-content-between "><span>Crédito</span></div>
                            </li>
                            <li className="d-flex border-bottom border-secondary mt-2">
                                <input type="radio" id="selectPagamento" name="selectPagamento" value="normal" />
                                <div className="d-flex justify-content-between "><span>Boleto</span></div>
                            </li>
                            <li className="d-flex border-bottom border-secondary mt-2">
                                <input type="radio" id="selectPagamento" name="selectPagamento" value="normal" />
                                <div className="d-flex justify-content-between "><span>Débito</span></div>
                            </li>
                        </ul>
                    </div>
                    
                </div>

                { enderecos.length > 0 && <Link to="#" onClick={() => handleSubmit()} className="text-white text-decoration-none btn bg-secondary-color fw-bold mt-4">Finalizar Compra</Link> }
            </div>
        </main>

        <Footer/>
        </>
    );
};