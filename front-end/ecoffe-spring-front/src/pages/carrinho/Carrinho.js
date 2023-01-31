import { Link, useParams } from 'react-router-dom';
import { useEffect, useState, useContext } from 'react';

import { Context } from '../../context/AuthContext';

import { modedaBr } from '../../utils/formatCoinUtil';

import history from '../../history';

import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';

import api from '../../hook/api';

import './Carrinho.css'
import ProdutoCarrinho from '../../components/ProdutoSelecionado/ProdutoCarrinho';

export default function Carrinho () {

    const { dataCliente } = useContext(Context);

    const [ dataProduto, setDataProduto ] = useState([]);
    const [ loading, setLoading ] = useState(true);

    useEffect(() => {
        if(dataCliente !== null){
            api.get(`/carrinho/getdata/${dataCliente.idCliente}`)
            .then((res) => {
                setDataProduto(res.data);
            })
            .catch(() => {
                history.push('/');
                window.location.reload();
            })
            .finally(() => {
                setLoading(false)
            })
        }

    }, [dataCliente])

    if(loading){
        return <h2>Carregando produtos...</h2>
    }

    let totalCompra = dataProduto.reduce((total, valor) => total + valor.preco * valor.quantidade, 0);

    async function handleCleanCart () {
        await api.delete(`/carrinho/deletarcarrinho/${dataCliente.idCliente}`)
        .then((res) => {
            if(res.status === 204){
                history.replace('/');
                window.location.reload();
            }
        })
        .catch((err) => {
            console.log(err);
        })
    }

    return (
        <>
        <Header/>
        <main>
            <div className="container mt-5">
                <h2 className="mb-4">Carrinho</h2>

                {dataProduto.length > 0 ? 

                    <>
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
                            { dataProduto?.map((produto) => <ProdutoCarrinho produto={produto} key={produto.idCarrinho}/> ) }
                        </tbody>
                    </table>
                    <Link to="#" onClick={() => { handleCleanCart() }} className="text-decoration-none text-dark"><i className="bi bi-trash-fill"></i> Limpar Carrinho</Link>

                    <div id="frete-section" className="p-4 w-50 mt-5">
                        <h5>Calcular Frete</h5>
                        <p>Digite o CEP para calcular o valor do frete o prazo de entrega</p>
                        <input type="text" disabled value="00000-000" />
                        <button type="button">Calcular</button>
                    </div>

                    <div className="bg-primary-color text-white p-3 mt-5">
                        <h5>Valor total {modedaBr(totalCompra)}</h5>
                    </div>

                    <div className="mt-5">
                        <Link to="finalizarcompra" className="bg-primary-color text-decoration-none text-white p-3 h5">Finalizar Compra</Link>
                    </div>
                    </>
                    :
                    <>
                        <h4>Você não possui produtos no carrinho</h4>
                        <div className="mt-5">
                            <Link to="/" className="bg-primary-color text-decoration-none text-white p-3 h5">Voltar</Link>
                        </div>
                    </>                    
                }
            
            </div>
        </main>
        { dataProduto.length > 0 ? <Footer/> : ''}
        </>
    );
};