package br.com.ecoffee.model.pedido;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.model.endereco.Endereco;
import br.com.ecoffee.model.produto.Produto;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PEDIDO_SEQ")
    @SequenceGenerator(name="PEDIDO_SEQ", sequenceName="PEDIDO_SEQ", allocationSize=100)
	@Column(columnDefinition = "serial")
	private Long idPedido;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private LocalDateTime dataPedido;

	@Column(nullable = false)
	private Double valorTotal;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "idProduto")
	private Produto produto;

	public Pedido() {
	}

	public Pedido(Integer quantidade, LocalDateTime dataPedido, Double valorTotal, Cliente cliente, Endereco endereco,
			Produto produto) {
		this.quantidade = quantidade;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.endereco = endereco;
		this.produto = produto;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Produto getProduto() {
		return produto;
	}

}
