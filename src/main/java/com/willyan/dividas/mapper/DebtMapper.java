package com.willyan.dividas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.willyan.dividas.dto.DebtResponse;
import com.willyan.dividas.dto.NewDebtRequest;
import com.willyan.dividas.models.Debt;

@Mapper(componentModel = "spring")
public interface DebtMapper {
	
	NewDebtRequest toNewDebtRequest(Debt debt);
	DebtResponse toDebtResponse(Debt debt);
	
	Debt toDebt(NewDebtRequest newDebtRequest);
	
	  void updateDebtFromRequest(
		        NewDebtRequest newDebtRequest,
		        @MappingTarget Debt debt
		    );
	
}
