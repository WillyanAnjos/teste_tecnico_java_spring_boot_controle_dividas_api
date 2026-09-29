package com.willyan.dividas.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.RestController;

import com.willyan.dividas.dto.DebtResponse;
import com.willyan.dividas.dto.NewDebtRequest;
import com.willyan.dividas.service.DebtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/debts")
public class DebtController {

	private final DebtService debtService;

	public DebtController(DebtService debtService) {
		this.debtService = debtService;
	}

	@PostMapping
	public ResponseEntity<Void> register(@Valid @RequestBody NewDebtRequest newDebtRequest) {
		debtService.register(newDebtRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{cpf}/search")
	public ResponseEntity<DebtResponse> findByCpf(
			@PathVariable String cpf) {
		return ResponseEntity.ok(debtService.findByCpf(cpf));
	}

	@GetMapping("/{debtId}")
	public ResponseEntity<DebtResponse> findById(@PathVariable Long debtId) {
		DebtResponse debtResponse = debtService.findById(debtId);
		return ResponseEntity.ok(debtResponse);
	}

	@GetMapping
	public ResponseEntity<Page<DebtResponse>> findAll(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<DebtResponse> debts = debtService.findAll(pageable);
		return ResponseEntity.ok(debts);
	}

	@DeleteMapping("/{debtId}")
	public ResponseEntity<Void> delete(@PathVariable Long debtId) {
		debtService.delete(debtId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{debtId}")
	public ResponseEntity<Void> update(@PathVariable Long debtId, @Valid @RequestBody NewDebtRequest newDebtRequest) {
		debtService.update(debtId, newDebtRequest);
		return ResponseEntity.noContent().build();
	}

}
