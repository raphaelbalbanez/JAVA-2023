Não é possível criar, editar ou fazer upload de arquivos … Armazenamento insuficiente. Compre mais espaço ou remova arquivos do Drive, do Google Fotos ou do Gmail.
package br.gov.cesarschool.poo.fidelidade.cliente.negocio;

import br.gov.cesarschool.poo.fidelidade.cliente.dao.ClienteDAO;
import br.gov.cesarschool.poo.fidelidade.geral.negocio.Cliente;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;
import br.gov.cesarschool.poo.fidelidade.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;



public class ClienteMediator {
	private static ClienteMediator instance;
	private final ClienteDAO repositorioCliente;
	private final CartaoFidelidadeMediator cartaoMediator;
	
	private ClienteMediator() {
		this.repositorioCliente = new ClienteDAO();
		this.cartaoMediator = CartaoFidelidadeMediator.getinstance();
	}
	public static ClienteMediator getInstance() {
	    if (instance == null) {
	        instance = new ClienteMediator();
	    }
	    return instance;
	}
	public ResultadoInclusaoCliente incluir(Cliente cliente) {
	    String erro = validar(cliente);
	    if (erro != null) {
	        return new ResultadoInclusaoCliente(0, erro);
	    }
	    
	    long numeroCartao = cartaoMediator.gerarCartao(cliente);
	    if (numeroCartao == 0) {
	        return new ResultadoInclusaoCliente(0, "Erro ao gerar o cartão fidelidade");
	    }
	    
	    repositorioCliente.incluir(cliente);
	    
	    return new ResultadoInclusaoCliente(numeroCartao, null);
	}
	
	public String alterar(Cliente cliente) {
		    if (cliente == null) {
		        return "Cliente nulo";
		    }
		    
		    String erro = validar(cliente);
		    if (erro != null) {
		        return erro;
		    }
		    
		    if (!cliente.getCpf().equals(cliente.getCpf())) {
		        return "Não é permitido alterar o CPF do cliente";
		    }
		    
		    repositorioCliente.alterar(cliente);
		    
		    return null;
		}
	
	
    private static String validar(Cliente cliente) {
        String mensagem = null;
        
        if (cliente.getNomeCompleto() == null || cliente.getNomeCompleto().isEmpty()) {
            mensagem = "O nome do cliente não pode estar vazio";
        }
        if(cliente.getSexo()==null) {
        	mensagem = "Digite o Sexo";
        }
        
        if (!ValidadorCPF.ehCpfValido(cliente.getCpf())) {
            mensagem = "CPF inválido";
        }
        if(cliente.getCpf() == null) {
        	mensagem = "CPF é obrigatório!";
        }
        
        if (cliente.getDataNascimento() == null) {
            mensagem = "Data de nascimento é obrigatória";
        } else {
            LocalDateTime dataNascimento = cliente.getDataNascimento();
            LocalDate dataAtual = LocalDate.now();
            long idade = ChronoUnit.YEARS.between(dataNascimento, dataAtual);
            if (idade < 18) {
                mensagem = "Idade mínima é de 18 anos";
            }
        }

        
        
        if (cliente.getEndereco()== null){
        	mensagem = "Endereço inválido";
        }
        
        return mensagem;
    }
}