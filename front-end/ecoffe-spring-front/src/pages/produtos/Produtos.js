import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import api from '../../hook/api';
import CardProduto from '../../components/CardProduto/CardProduto';

import './Produtos.css'

export default function Produtos () {

    const { categoria } = useParams();

    const [ dataProduto, setDataProduto ] = useState();
    const [ loading, setLoading ] = useState(true);

    useEffect(() => {
        api.get(`/produto/listar/${categoria}`).then((res) => { 
            setDataProduto(res.data);
        })
        .finally(() => {
            setLoading(false)
        })

    }, [categoria])

    if(loading){
        return <h2>Carregando produtos...</h2>
    }

    return (
        <>
        <Header/>
            <div className='container mt-5 d-flex'>
                <div className="filtro">

                    <div id='filtro-preco'>
                        <h5>Filtrar preço</h5>
                        <select id="option-preco">
                            <option value="" selected></option>
                            <option value="maior para o menor">Maior para o menor</option>
                            <option value="menor para o maior">Menor para o maior</option>
                        </select>
                    </div>

                    <div className="filtro-marcas" data-toggle="buttons">

                        <label className="btn-marca">
                            <input type="checkbox" name="options1" id="option1" /> Mondial
                        </label>

                        <label className="btn-marca">
                            <input type="checkbox" name="options2" id="option2" /> Oster
                        </label>

                        <label className="btn-marca">
                            <input type="checkbox" name="options3" id="option3" /> Starbucks
                        </label>

                        <label className="btn-marca">
                            <input type="checkbox" name="options4" id="option4" />Philco
                        </label>

                        <label className="btn-marca">
                            <input type="checkbox" name="options5" id="option5" /> Lor
                        </label>

                        <label className="btn-marca">
                            <input type="checkbox" name="options6" id="option6" /> Multilaser
                        </label>

                    </div>
                </div>
                <div className='row row-cols-6' style={{marginLeft: "25px", maxWidth: "984px"}}>  
                    {dataProduto?.map((produto) => <CardProduto key={produto.idProduto} produto={produto}/>)}
                    
                </div>
            </div>
        <Footer/>
        </>
    );
};