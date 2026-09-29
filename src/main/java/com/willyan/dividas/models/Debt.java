package com.willyan.dividas.models;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "debts")
public class Debt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CPF(message = "CPF inválido")
	private String cpfDevedor;
	
	private BigDecimal valorPego;
	private BigDecimal valorComJuros;
	private BigDecimal valorComDesconto;
	
	public Debt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfDevedor() {
		return cpfDevedor;
	}

	public void setCpfDevedor(String cpfDevedor) {
		this.cpfDevedor = cpfDevedor;
	}

	public BigDecimal getValorPego() {
		return valorPego;
	}

	public void setValorPego(BigDecimal valorPego) {
		this.valorPego = valorPego;
	}

	public BigDecimal getValorComJuros() {
		return valorComJuros;
	}

	public void setValorComJuros(BigDecimal valorComJuros) {
		this.valorComJuros = valorComJuros;
	}

	public BigDecimal getValorComDesconto() {
		return valorComDesconto;
	}

	public void setValorComDesconto(BigDecimal valorComDesconto) {
		this.valorComDesconto = valorComDesconto;
	}
	
	
}