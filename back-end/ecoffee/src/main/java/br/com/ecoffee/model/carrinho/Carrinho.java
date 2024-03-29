package br.com.ecoffee.model.carrinho;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.ecoffee.exception.BusinessException;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.model.produto.Produto;

@Entity
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_SEQ")
	@SequenceGenerator(name = "CART_SEQ", sequenceName = "CART_SEQ", allocationSize = 100)
	@Column(columnDefinition = "serial")
	private Long idCarrinho;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "idProduto")
	private Produto produto;

	@Column(nullable = false)
	private Integer quantidade;

	public Carrinho() {
	}

	public Carrinho(Cliente cliente, Produto produto, Integer quantidade) {
		this.cliente = cliente;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Long getIdCarrinho() {
		return idCarrinho;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void adicionarQuantidade(Integer quantidade) {
		
		if(quantidade <= 0) {
			throw new BusinessException("Quantidade não pode ser menor ou igual a 0");
		}
		
		this.quantidade += quantidade;
	}

}
