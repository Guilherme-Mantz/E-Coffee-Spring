import { useQuery } from 'react-query';

import Header from '../../components/Header/Header';
import Carrossel from '../../components/Carrossel/Carrossel';
import Footer from '../../components/Footer/Footer';

import CardProduto from '../../components/CardProduto/CardProduto';

import './Index.css'
import api from '../../hook/api';
import { useEffect, useState } from 'react';

export default function Index () {

    const [ dataProdutos, setDataProdutos ] = useState(null);
    const [ isFetching, setIsFetching ] = useState(true);

    useEffect(() => {
        if(dataProdutos === null){
            api.get('/produto/listar/destaques').then((res) => {
                setDataProdutos(res.data);
            })
            .finally(() => {
                setIsFetching(false)
            })
        }
    }, [])

    return(
        <>
        <Header />
            <main>
                <div className="container">
                    <Carrossel/>
                    <div className="row row-cols-3 justify-content-center mt-5 ms-auto">
                        { isFetching && <h4>Carregando produtos</h4> }
                        {dataProdutos?.map((produto) => <CardProduto key={produto.idProduto} produto={produto}/>)}
                    </div>
                </div>
            </main>
        <Footer />
        </>
    );
};