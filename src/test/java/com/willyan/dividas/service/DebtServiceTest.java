package com.willyan.dividas.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import com.willyan.dividas.dto.DebtResponse;
import com.willyan.dividas.dto.NewDebtRequest;
import com.willyan.dividas.mapper.DebtMapper;
import com.willyan.dividas.models.Debt;
import com.willyan.dividas.repository.DebtRepository;

@ExtendWith(MockitoExtension.class)
public class DebtServiceTest {

	@Mock
	private DebtRepository debtRepository;

	@Mock
	private DebtMapper debtMapper;

	@InjectMocks
	private DebtService debtService;

	private Debt debt;

	private NewDebtRequest newDebtRequest;

	private DebtResponse debtResponse;

	@BeforeEach
	void setUp() {
		debt = new Debt();
		debt.setId(1L);
		debt.setCpfDevedor("52998224725");
		debt.setValorPego(new BigDecimal("1000.00"));
		debt.setValorComJuros(new BigDecimal("1200.00"));
		debt.setValorComDesconto(new BigDecimal("900.00"));

		newDebtRequest = new NewDebtRequest("52998224725", new BigDecimal("1000.00"), new BigDecimal("1200.00"),
				new BigDecimal("900.00"));

		debtResponse = new DebtResponse("52998224725", new BigDecimal("1000.00"), new BigDecimal("1200.00"),
				new BigDecimal("900.00"));
	}

	@Test
	void shouldRegisterDebtSuccessfully() {

		// Arrange
		when(debtMapper.toDebt(newDebtRequest)).thenReturn(debt);
		when(debtRepository.save(debt)).thenReturn(debt);

		// Act
		debtService.register(newDebtRequest);

		// Assert
		verify(debtMapper, times(1)).toDebt(newDebtRequest);
		verify(debtRepository, times(1)).save(debt);
	}

}
