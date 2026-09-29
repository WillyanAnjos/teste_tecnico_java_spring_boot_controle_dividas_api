package com.willyan.dividas.dto;

import java.math.BigDecimal;

public record DebtResponse(
		String cpfDevedor, 
		BigDecimal valorPego, 
		BigDecimal valorComJuros,
		BigDecimal valorComDesconto) {

}
