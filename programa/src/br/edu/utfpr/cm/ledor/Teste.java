package br.edu.utfpr.cm.ledor;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	String path = System.getProperty("user.dir") + "/instancias/GROUP 1/HDGraph20_20.txt";
	//System.out.println(path);
	ConjuntoDados c = new ConjuntoDados(path);
	c.printConteudo();
	}

}
