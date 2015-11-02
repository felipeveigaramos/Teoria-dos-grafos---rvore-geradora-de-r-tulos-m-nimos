/**
 * 
 */
package br.edu.utfpr.cm.ledor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Felipe Veiga Ramos
 *
 */
public class ConjuntoDados {
	private File fileclass;
	private FileReader filereader;
	private BufferedReader buffer;
	private Scanner scan;
	private ArrayList<Integer> conteudo;
	private int numeroDeRotulos;
	private int numeroDeVertices;
	

	public ConjuntoDados(String path) {
		fileclass = new File(path);
		try {
			this.filereader = new FileReader(fileclass);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.buffer = new BufferedReader(filereader);
		this.scan = new Scanner(buffer);
		this.conteudo = new ArrayList<Integer>();
		this.setConteudo();
		this.setNumeroDeVertices(this.getConteudo().get(0));
		this.setNumeroDeRotulos(this.getConteudo().get(1));
	}

	private ArrayList<String> carregarConteudo() {
		ArrayList<String> texto = new ArrayList<String>();
		// String[] s;
		while (this.scan.hasNext()) {
			texto.add(this.scan.next());

		}
		return texto;

	}

	public void setConteudo() {
		ArrayList<String> texto = new ArrayList<String>();
		texto = this.carregarConteudo();
		for (int i = 0; i < texto.size(); i++) {
			this.conteudo.add(Integer.parseInt(texto.get(i)));
		}

	}

	public ArrayList<Integer> getConteudo() {
		return this.conteudo;
	}
	
	public void printConteudo() {
		for(int i = 0; i < this.getConteudo().size();i++) {
			System.out.println(this.getConteudo().get(i));
		}
	}
	
	public int getNumeroDeRotulos() {
		return numeroDeRotulos;
	}

	public void setNumeroDeRotulos(int numeroDeRotulos) {
		this.numeroDeRotulos = numeroDeRotulos;
	}

	public int getNumeroDeVertices() {
		return numeroDeVertices;
	}

	public void setNumeroDeVertices(int numeroDeVertices) {
		this.numeroDeVertices = numeroDeVertices;
	}
}
