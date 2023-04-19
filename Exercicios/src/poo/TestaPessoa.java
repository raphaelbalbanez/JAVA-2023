package poo;

public class TestaPessoa {
	public static void main (String[] args) {
		 Pessoas rapha = new Pessoas("raphael","103472387"); 
//		 rapha.setNome("Rauphas");
		 System.out.println(rapha.getNome());
		 
		 rapha.setIdade(20);
//		 rapha.setCpf("97812631"); Por causa do construtor não precisa da o set
		 System.out.println(rapha.getCpf());
		 System.out.println(rapha.getIdade());
		 rapha.andar();
		 
	}
}
