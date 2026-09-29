package com.willyan.dividas.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record NewDebtRequest(
		
		@NotBlank(message = "O CPF é obrigatório.")
		@CPF(message = "O CPF informado é inválido.")
		String cpfDevedor, 
		
		@Positive(message = "O valor pego deve ser maior que zero.")
		BigDecimal valorPego,
		
		@Positive(message = "O valor com juros deve ser maior que zero.")
		BigDecimal valorComJuros,
		
		@Positive(message = "O valor com desconto deve ser maior que zero.")
		BigDecimal valorComDesconto) {

}
