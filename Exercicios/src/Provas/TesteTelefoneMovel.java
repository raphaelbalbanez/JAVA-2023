package Provas;

public class TesteTelefoneMovel {
	public static void main(String[] args) {
		TelefoneMovel celular = new TelefoneMovel("motorola",1200,15.550f);
		System.out.print(celular.calcularTempoUso(1));
	}
}
