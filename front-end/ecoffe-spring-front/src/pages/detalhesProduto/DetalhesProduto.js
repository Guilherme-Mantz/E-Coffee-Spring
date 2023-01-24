import { useParams } from 'react-router-dom';
import { useEffect, useState, useContext } from 'react';

import { Context } from '../../context/AuthContext';

import { modedaBr } from '../../utils/formatCoinUtil';

import history from '../../history';

import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import DescricaoProduto from '../../components/DescricaoProduto/DescricaoProduto';

import api from '../../hook/api';

import './DetalhesProduto.css'

export default function DetalhesProduto () {

    const { dataCliente } = useContext(Context);

    const { idProduto } = useParams();

    const [ dataProduto, setDataProduto ] = useState();
    const [ loading, setLoading ] = useState(true);

    const [ quantidade, setQuantidade ] = useState(1);

    useEffect(() => {
        api.get(`/produto/${idProduto}`)
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

    }, [idProduto])

    if(loading){
        return <h2>Carregando produtos...</h2>
    }

    async function handlePushOnCart(e){
        e.preventDefault();

        if(dataCliente === null){
            history.push('/iniciarsessao');
            window.location.reload();
        };

        await api.post(`/carrinho/inserirproduto/${idProduto}/${dataCliente.idCliente}`, {quantidade: quantidade})
        .then((res) => { 
            console.log(res)
        })
        .catch((error) => {
            console.log(error)
        })
    };

    return (
        <>
        <Header/>
            <div className="container">
                <div className="row mt-5">
                    <div className="images-produto col-4">
                        <img src={require('../../../../../images/uploads/'+dataProduto.imagem)} alt={dataProduto.tituloPagina} width="380px" height="380px" className="float-start" />
                    </div>
                    
                    <div className="comprar-produto col pt-5">
                        <form onSubmit={e => handlePushOnCart(e)}>
                            <h2>{dataProduto.tituloPagina}</h2>
                            <h4 className="mt-5">Selecione a quantidade:</h4>

                            <select id="quantidades-produto" name="quantidade" onChange={(e) => setQuantidade(e.target.value)}>
                                
                                {[1,2,3,4,5,6,7,8,9,10].map((x, i) =>
                                    <option value={x} key={i}>{x}</option>
                                )}

                            </select>

                            <h3 className="mt-3">{modedaBr(dataProduto.preco)}</h3>
                            <p>Ou em até 10x de {modedaBr(dataProduto.preco / 10)} sem juros</p>
                            <div id="button-buy">
                                <button type="submit">Colocar no Carrinho</button>
                            </div>
                        </form>    
                    </div>
                </div>

                <div className="descricoes">
                    
                    <DescricaoProduto titulo="Descrição do Produto" descricao={dataProduto.descricaoProduto}/>
                    <DescricaoProduto titulo="Informacoes Técnicas" descricao={dataProduto.informacoesTecnicas}/>

                </div>

            </div>
        <Footer/>
        </>
    );
};