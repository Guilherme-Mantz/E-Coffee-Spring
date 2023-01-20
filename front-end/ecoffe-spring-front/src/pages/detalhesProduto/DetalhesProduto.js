import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import DescricaoProduto from '../../components/DescricaoProduto/DescricaoProduto';

import api from '../../hook/api';

import './DetalhesProduto.css'

export default function DetalhesProduto () {

    const { idProduto } = useParams();

    const [ dataProduto, setDataProduto ] = useState();
    const [ loading, setLoading ] = useState(true);

    useEffect(() => {
        api.get(`/produto/${idProduto}`).then((res) => { 
            setDataProduto(res.data);
        })
        .finally(() => {
            setLoading(false)
        })

    }, [idProduto])

    if(loading){
        return <h2>Carregando produtos...</h2>
    }

    return (
        <>
        <Header/>
            <div className="container">
                <div className="row mt-5">
                    <div className="images-produto col-4">
                        <img src={require('../../../../../images/uploads/'+dataProduto.imagem)} alt={dataProduto.tituloPagina} width="380px" height="380px" className="float-start" />
                    </div>
                    
                    <div className="comprar-produto col pt-5">
                        <h2>{dataProduto.tituloPagina}</h2>
                        <h4 className="mt-5">Selecione a quantidade:</h4>

                        <select id="quantidades-produto" name="quantidade">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>

                        <h3 className="mt-3">{dataProduto.preco.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'})}</h3>
                        <p>Ou em até 10x de {(dataProduto.preco / 10).toLocaleString('pt-br',{style: 'currency', currency: 'BRL'})} sem juros</p>
                        <div id="button-buy">
                            <button type="submit">Colocar no Carrinho</button>
                        </div>
                        
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