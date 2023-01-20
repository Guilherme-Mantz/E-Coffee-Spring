import { useEffect } from "react";

export default function DescricaoProduto (props){

    const { titulo, descricao } = props;

    useEffect(()=> {

        let descricaoProduto = document.querySelectorAll("#text-product");

        Array.from(descricaoProduto).map((l)=> {
            l.innerHTML = l.innerHTML.replace(/#/gi, "</br>");
        });

    },[])
    
    return (
        <div className="descricao-pedido mt-5">
            <div className="titulo-descricao">
                <h5 className="p-3">{titulo}</h5>
            </div>
            <p id="text-product" className="p-3">{descricao}</p>
        </div>
    );

};