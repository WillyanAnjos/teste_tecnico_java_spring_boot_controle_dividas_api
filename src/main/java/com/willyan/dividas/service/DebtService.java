package com.willyan.dividas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.willyan.dividas.config.MaskCpf;
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

	@Transactional
	public void register(NewDebtRequest newDebtRequest) {
		log.info("Registering new debt");
		Debt savedDebt = debtRepository.save(debtMapper.toDebt(newDebtRequest));

		log.info("Debt saved. debtId={}", savedDebt.getId());

	}

	@Transactional(readOnly = true)
	public Page<DebtResponse> findAll(Pageable pageable) {

		log.debug("Fetching all debts with pagination. pageNumber={}, pageSize={}", pageable.getPageNumber(),
				pageable.getPageSize());

		Page<Debt> debts = debtRepository.findAll(pageable);

		log.debug("Fetched {} debts", debts.getNumberOfElements());
		return debts.map(debtMapper::toDebtResponse);
	}

	@Transactional(readOnly = true)
	public DebtResponse findById(Long debtId) {

		log.debug("Fetching debt by id: {}", debtId);

		Debt debt = debtRepository.findById(debtId).orElseThrow(() -> {
			log.warn("Debt not found with id: {}", debtId);
			return new ResourceNotFoundException("Debt", debtId);

		});

		return debtMapper.toDebtResponse(debt);
	}

	@Transactional
	public void delete(Long debtId) {
		log.info("Deleting debt with id: {}", debtId);

		Debt debt = debtRepository.findById(debtId).orElseThrow(() -> {
			log.warn("Debt not found with id: {}", debtId);
			return new ResourceNotFoundException("Debt", debtId);
		});

		debtRepository.delete(debt);

		log.info("Debt deleted with id: {}", debtId);
	}

	@Transactional
	public void update(Long debtId, NewDebtRequest newDebtRequest) {
		log.info("Updating debt with id: {}", debtId);

		Debt debt = debtRepository.findById(debtId).orElseThrow(() -> {
			log.warn("Debt not found with id: {}", debtId);
			return new ResourceNotFoundException("Debt", debtId);
		});

		debtMapper.updateDebtFromRequest(newDebtRequest, debt);

		debtRepository.save(debt);

		log.info("Debt updated with id: {}", debtId);
	}
	
	@Transactional(readOnly = true)
	public DebtResponse findByCpf(String cpf) {

		log.debug("Fetching debt by CPF: {}", MaskCpf.maskCpf(cpf));

		Debt debt = debtRepository.findByCpfDevedor(cpf).orElseThrow(() -> {
			log.warn("Debt not found with CPF: {}", MaskCpf.maskCpf(cpf));
			return new ResourceNotFoundException("Debt", MaskCpf.maskCpf(cpf));
		});

		log.debug("Fetched debt with CPF: {}", MaskCpf.maskCpf(cpf));
		return debtMapper.toDebtResponse(debt);
	}
}
