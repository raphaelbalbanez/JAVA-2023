package poo;

public class Pessoas {
	private String nome;
	private int idade;
	private String cpf;
	
	public Pessoas(String nome,String cpf){
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public void andar() {
		System.out.println("Andei");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
