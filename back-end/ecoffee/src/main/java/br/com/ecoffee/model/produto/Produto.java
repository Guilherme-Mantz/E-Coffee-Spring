package br.com.ecoffee.model.produto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ecoffee.exception.BusinessException;
import br.com.ecoffee.model.categoria.Categoria;
import br.com.ecoffee.model.marca.Marca;

@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROD_SEQ")
    @SequenceGenerator(name="PROD_SEQ", sequenceName="PROD_SEQ", allocationSize=100)
	@Column(columnDefinition = "serial")
	private Long idProduto;

	@Column(name = "nome_produto", nullable = false, length = 200)
	private String nomeProduto;

	@Column(nullable = false, length = 150)
	private String sku;

	@Column(name = "codigo_de_barra", nullable = false, length = 13)
	private String codigoBarra;

	@Column(name = "status_produto", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusProduto statusProduto;

	@OneToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;

	@Column(name = "descricao_produto", nullable = false, columnDefinition = "TEXT")
	private String descricaoProduto;

	@Column(name = "informacoes_tecnicas", nullable = false, columnDefinition = "TEXT")
	private String informacoesTecnicas;

	@Column(nullable = false)
	private Double peso;

	@Column(nullable = false)
	private BigDecimal preco;

	@Column(nullable = false)
	private BigDecimal custo;

	@Column(name = "titulo_na_pagina", nullable = false, length = 200)
	private String tituloPagina;

	@Column(name = "palavras_chave", nullable = false, length = 200)
	private String palavrasChave;

	@Column(nullable = false)
	private Long quantidadeEstoque;

	@Column(nullable = false, length = 200)
	private String imagem;

	@Column(nullable = false, length = 200)
	private String miniaturaUm;

	@Column(nullable = false, length = 200)
	private String miniaturaDois;

	@ManyToOne
	@JoinColumn(name = "idMarca")
	private Marca marca;

	public Long getIdProduto() {
		return idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public String getSku() {
		return sku;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public StatusProduto getStatusProduto() {
		return statusProduto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public String getInformacoesTecnicas() {
		return informacoesTecnicas;
	}

	public Double getPeso() {
		return peso;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public BigDecimal getCusto() {
		return custo;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public String getPalavrasChave() {
		return palavrasChave;
	}

	public Long getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public String getImagem() {
		return imagem;
	}

	public String getMiniaturaUm() {
		return miniaturaUm;
	}

	public String getMiniaturaDois() {
		return miniaturaDois;
	}

	public Marca getMarca() {
		return marca;
	}
	
	public void subtrairEstoque(Integer quantidade) {
		
		if(quantidade <= 0 && quantidade > this.quantidadeEstoque) {
			throw new BusinessException("Quantidade não pode ser menor ou igual a 0 e maior que a quantidade disponivel no estoque");
		}
		
		this.quantidadeEstoque -= quantidade;
		
	}

}
