package br.gov.cesarschool.poo.fidelidade.geral.negocio;

import java.time.LocalDateTime;

public class LancamentoExtrato {
	private long numeroCartao;
    private int quantidadePontos;
    private LocalDateTime dataHoraLancamento;
    
    public LancamentoExtrato(long numeroCartao, int quantidadePontos, LocalDateTime dataHoraLancamento) {
        this.numeroCartao = numeroCartao;
        this.quantidadePontos = quantidadePontos;
        this.dataHoraLancamento = dataHoraLancamento;
    }
    
    // getters dos atributos privados
    
    public long getNumeroCartao() {
        return numeroCartao;
    }

    public int getQuantidadePontos() {
        return quantidadePontos;
    }

    public LocalDateTime getDataHoraLancamento() {
        return dataHoraLancamento;
    }
}
