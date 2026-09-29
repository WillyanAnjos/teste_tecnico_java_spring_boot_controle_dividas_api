package com.willyan.dividas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.willyan.dividas.models.Debt;

public interface DebtRepository extends JpaRepository<Debt, Long> {

}
