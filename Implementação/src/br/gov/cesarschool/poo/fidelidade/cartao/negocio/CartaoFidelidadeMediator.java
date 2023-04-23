package br.gov.cesarschool.poo.fidelidade.cartao.negocio;

import br.gov.cesarschool.poo.fidelidade.cartao.dao.*;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.*;
import br.gov.cesarschool.poo.fidelidade.geral.negocio.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.geral.negocio.Cliente;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.*;

import java.time.LocalDate;


public class CartaoFidelidadeMediator {
    private CartaoFidelidadeDAO repositorioCartao;
    private LancamentoExtratoDAO repositorioLancamento;

    private static CartaoFidelidadeMediator instance = null;

    private CartaoFidelidadeMediator() {
        repositorioCartao = new CartaoFidelidadeDAO();
        repositorioLancamento = new LancamentoExtratoDAO();
    }

    public static CartaoFidelidadeMediator getinstance() {
        if (instance == null) {
            instance = new CartaoFidelidadeMediator();
        }
        return instance;
    }

    public long gerarCartao(Cliente cliente) {
        LocalDate dataAtual = LocalDate.now();
        String numeroCartao = cliente.getCpf() + dataAtual.getYear() +
            String.format("%02d", dataAtual.getMonthValue()) +
            String.format("%02d", dataAtual.getDayOfMonth());
        CartaoFidelidade cartao = new CartaoFidelidade(numeroCartao, cliente);
        if (repositorioCartao.incluir(cartao)) {
            return Long.parseLong(numeroCartao);
        } else {
            return 0;
        }
    }

    public String pontuar(long numeroCartao, int quantidadePontos) {
        if (quantidadePontos <= 0) {
            return "A quantidade de pontos deve ser maior do que zero.";
        }
        CartaoFidelidade cartao = repositorioCartao.buscar(numeroCartao);
        if (cartao == null) {
            return "O cartão fidelidade informado não foi encontrado.";
        }
        cartao.creditar(quantidadePontos);
        repositorioCartao.alterar(cartao);
        LancamentoExtratoPontuacao lancamento =  new LancamentoExtratoPontuacao(numeroCartao, quantidadePontos, null);
        repositorioLancamento.incluir(lancamento);
        return null;
    }

    public String resgatar(long numeroCartao, int quantidadePontos, TipoResgate tipoResgate) {
        if (quantidadePontos <= 0) {
            return "A quantidade de pontos deve ser maior do que zero.";
        }
        CartaoFidelidade cartao = repositorioCartao.buscar(numeroCartao);
        if (cartao == null) {
            return "O cartão fidelidade informado não foi encontrado.";
        }
        if (cartao.getSaldo() < quantidadePontos) {
            return "O saldo de pontos do cartão é insuficiente para o resgate solicitado.";
        }
        cartao.debitar(quantidadePontos);
        repositorioCartao.alterar(cartao);
        LancamentoExtratoResgate lancamento = new LancamentoExtratoResgate(numeroCartao,quantidadePontos,null, tipoResgate);
        repositorioLancamento.incluir(lancamento);
        return null;
    }
}