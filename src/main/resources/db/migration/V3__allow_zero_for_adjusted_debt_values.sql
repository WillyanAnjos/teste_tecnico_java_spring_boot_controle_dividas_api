ALTER TABLE debts
    DROP CONSTRAINT chk_debts_valor_com_juros_positive,
    DROP CONSTRAINT chk_debts_valor_com_desconto_positive;

ALTER TABLE debts
    ADD CONSTRAINT chk_debts_valor_com_juros_positive_or_zero CHECK (valor_com_juros >= 0),
    ADD CONSTRAINT chk_debts_valor_com_desconto_positive_or_zero CHECK (valor_com_desconto >= 0);
