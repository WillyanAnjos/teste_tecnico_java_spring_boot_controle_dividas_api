CREATE TABLE debts (
    id BIGSERIAL PRIMARY KEY,
    cpf_devedor VARCHAR(14) NOT NULL,
    valor_pego NUMERIC(19, 2) NOT NULL,
    valor_com_juros NUMERIC(19, 2) NOT NULL,
    valor_com_desconto NUMERIC(19, 2) NOT NULL,
    CONSTRAINT chk_debts_valor_pego_positive CHECK (valor_pego > 0),
    CONSTRAINT chk_debts_valor_com_juros_positive CHECK (valor_com_juros > 0),
    CONSTRAINT chk_debts_valor_com_desconto_positive CHECK (valor_com_desconto > 0)
);
