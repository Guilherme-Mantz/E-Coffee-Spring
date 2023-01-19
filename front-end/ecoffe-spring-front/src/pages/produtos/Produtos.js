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

    const [ marcas, setMarcas ] = useState(new Set());

    useEffect(() => {
        api.get(`/produto/listar/${categoria}`).then((res) => { 
            setDataProduto(res.data);
        })
        .finally(() => {
            setLoading(false)
        })

    }, [categoria])

    useEffect(() => {
        marcas.clear();
    },[categoria]);

    dataProduto?.map((p) => {
        marcas.add(p.marca)
    });

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
                        <select defaultValue={"deault"} id="option-preco">
                            <option value="deault"></option>
                            <option value="maior para o menor">Maior para o menor</option>
                            <option value="menor para o maior">Menor para o maior</option>
                        </select>
                    </div>

                    <div className="filtro-marcas" data-toggle="buttons">
                        
                        {Array.from(marcas).map((marca) => 
                            <label className="btn-marca" key={marca}>
                                <input type="checkbox" name="options1" id="option1" />{marca}
                            </label>)
                        }

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