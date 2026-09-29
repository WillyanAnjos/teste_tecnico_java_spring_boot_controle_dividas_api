package com.willyan.dividas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.willyan.dividas.dto.DebtResponse;
import com.willyan.dividas.dto.NewDebtRequest;
import com.willyan.dividas.exceptions.ResourceNotFoundException;
import com.willyan.dividas.mapper.DebtMapper;
import com.willyan.dividas.models.Debt;
import com.willyan.dividas.repository.DebtRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DebtService {

	private final DebtRepository debtRepository;
	private final DebtMapper debtMapper;

	public DebtService(DebtRepository debtRepository, DebtMapper debtMapper) {
		this.debtRepository = debtRepository;
		this.debtMapper = debtMapper;
	}

	@Transactional(readOnly = false)
	public void register(NewDebtRequest newDebtRequest) {
		debtRepository.save(debtMapper.toDebt(newDebtRequest));
	}

	@Transactional(readOnly = true)
	public Page<DebtResponse> findAll(Pageable pageable) {
		Page<Debt> debts = debtRepository.findAll(pageable);
		return debts.map(debtMapper::toDebtResponse);
	}

	@Transactional(readOnly = true)
	public DebtResponse findById(Long debtId) {
		Debt debt = debtRepository.findById(debtId).orElseThrow(() -> new ResourceNotFoundException("Debt", debtId));

		return debtMapper.toDebtResponse(debt);
	}

	@Transactional(readOnly = false)
	public void delete(Long debtId) {
		Debt debt = debtRepository.findById(debtId).orElseThrow(() -> new ResourceNotFoundException("Debt", debtId));

		debtRepository.delete(debt);
	}

	@Transactional(readOnly = false)
	public void update(Long debtId, NewDebtRequest newDebtRequest) {
		Debt debt = debtRepository.findById(debtId).orElseThrow(() -> new ResourceNotFoundException("Debt", debtId));

		debtMapper.updateDebtFromRequest(newDebtRequest, debt);
		debtRepository.save(debt);
	}
}
