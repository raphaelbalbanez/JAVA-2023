package br.gov.cesarschool.poo.fidelidade.geral.negocio;

public class LancamentoExtratoResgate {
	private TipoResgate tipoResgate;

	public LancamentoExtratoResgate(long numeroCartao, int quantidadePontos, LocalDateTime dataHoraLancamento,
			TipoResgate tipoResgate) {
		super(numeroCartao, quantidadePontos, dataHoraLancamento);
		this.tipoResgate = tipoResgate;
	}

	public TipoResgate getTipoResgate() {
		return tipoResgate;
	}

	// metodos herdados de LancamentoExtrato

}
