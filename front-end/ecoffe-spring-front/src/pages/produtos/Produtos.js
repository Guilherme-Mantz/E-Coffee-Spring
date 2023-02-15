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
    const [marcasSelecionadas, setMarcasSelecionadas] = useState([]);

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

    function handleChangePreco(e) {
        
        if(e.target.value === 'crescente'){
            const precoCrescente = dataProduto.sort((p1, p2) => p1.preco - p2.preco);
            setDataProduto([...precoCrescente]);
        }
        else {
            const precoDecrescente = dataProduto.sort((p1, p2) => p2.preco - p1.preco);
            setDataProduto([...precoDecrescente]);
        }

    }

    function handleChangeMarca(e){
        const marcaSelecionada = e.target.value;

        if(e.target.checked){
            setMarcasSelecionadas([...marcasSelecionadas, marcaSelecionada]);
        }
        else {
            setMarcasSelecionadas(marcasSelecionadas.filter((marca) => marca !== marcaSelecionada));
        }
    }

    const produtosFiltrados = dataProduto.filter((produto) =>
        marcasSelecionadas.length === 0 ? true : marcasSelecionadas.includes(produto.marca)
    );

    return (
        <>
        <Header/>
            <div className='container mt-5 d-flex'>
                <div className="filtro">

                    <div id='filtro-preco'>
                        <h5>Filtrar preço</h5>
                        <select defaultValue={"crescente"} id="option-preco" onChange={e => handleChangePreco(e)}>
                            <option value="crescente">Crescente</option>
                            <option value="decrescente">Decrescente</option>
                        </select>
                    </div>

                    <div className="filtro-marcas" data-toggle="buttons">
                        
                        {Array.from(marcas).map((marca) => 
                            <label className="btn-marca" key={marca}>
                                <input type="checkbox" name="options1" id="option1" className='me-1' value={marca} onChange={e => handleChangeMarca(e)} />{marca}
                            </label>)
                        }

                    </div>
                </div>
                <div className='row row-cols-6' style={{marginLeft: "1.5625rem", maxWidth: "61.5rem"}}>  
                    {produtosFiltrados?.map(produto => <CardProduto key={produto.idProduto} produto={produto}/>)}
                    
                </div>
            </div>
        <Footer/>
        </>
    );
};