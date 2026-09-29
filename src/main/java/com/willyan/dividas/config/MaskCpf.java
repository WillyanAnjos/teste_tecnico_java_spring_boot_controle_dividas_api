package com.willyan.dividas.config;

public class MaskCpf {
	public static String maskCpf(String cpf) {
		if (cpf == null || cpf.length() != 11) {
			return "***";
		}

		return "***.***.***-" + cpf.substring(9);
	}
}
