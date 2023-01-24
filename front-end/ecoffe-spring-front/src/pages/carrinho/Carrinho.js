import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

import { modedaBr } from '../../utils/formatCoinUtil';

import history from '../../history';

import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';

import api from '../../hook/api';

import './Carrinho.css'
import ProdutoCarrinho from '../../components/ProdutoSelecionado/ProdutoCarrinho';

export default function Carrinho () {

    // const { idProduto } = useParams();

    // const [ dataProduto, setDataProduto ] = useState();
    // const [ loading, setLoading ] = useState(true);

    // useEffect(() => {
    //     api.get(`/produto/${idProduto}`)
    //     .then((res) => {
    //         setDataProduto(res.data);
    //     })
    //     .catch(() => {
    //         history.push('/');
    //         window.location.reload();
    //     })
    //     .finally(() => {
    //         setLoading(false)
    //     })

    // }, [idProduto])

    // if(loading){
    //     return <h2>Carregando produtos...</h2>
    // }

    return (
        <>
        <Header/>
        <main>
            <div className="container mt-5">
                <h2 className="mb-4">Carrinho</h2>
                <table className="table table-bordered">
                    <thead id="head-table">
                        <tr>
                            <th scope="col" style={{width: "11%"}}></th>
                            <th scope="col" className="w-50">Nome do Produto</th>
                            <th scope="col" className="text-center" style={{width: "10%"}}>Preço unitário</th>
                            <th scope="col" className="text-center" style={{width: "10%"}}>Qtd.</th>
                            <th scope="col" className="text-center" style={{width: "10%"}}>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody id="body-table">
                        <ProdutoCarrinho/>
                    </tbody>
                </table>
                <a href="#" className="text-decoration-none text-dark"><i className="bi bi-trash-fill"></i> Limpar Carrinho</a>

                <div id="frete-section" className="p-4 w-50 mt-5">
                    <h5>Calcular Frete</h5>
                    <p>Digite o CEP para calcular o valor do frete o prazo de entrega</p>
                    <input type="text" disabled value="00000-000" />
                    <button type="button">Calcular</button>
                </div>

                <div className="bg-primary-color text-white p-3 mt-5">
                    <h5>Valor total R$575</h5>
                </div>

                <div className="mt-5">
                    <a href="#" className="bg-primary-color text-decoration-none text-white p-3 h5">Finalizar Compra</a>
                </div>            
            </div>
        </main>
        <Footer/>
        </>
    );
};