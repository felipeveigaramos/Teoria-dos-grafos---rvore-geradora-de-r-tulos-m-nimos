/**
 * 
 */
package br.edu.utfpr.cm.ledor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author felipevr
 *
 */
public class LedorDados {
    private ArrayList<File> arquivos;
    private ArrayList<ConjuntoDados> listConjuntoDados;
    private static LedorDados instance;

    /**
     * @param raiz
     */
    private LedorDados(File raiz) {
        super();
        this.arquivos = new ArrayList<File>();
        this.listConjuntoDados = new ArrayList<ConjuntoDados>();
        System.out.println("#Lendo arquivos de: " + raiz.getAbsolutePath());
        adicionaArquivosParaLeitura(raiz);
        System.out.println("#arquivos para leitura: " + this.arquivos);
    }

    private void adicionaArquivosParaLeitura(File file) {
        if (file.isFile()) {
            this.arquivos.add(file);
            return;
        }

        for (File f : file.listFiles()) {
            this.adicionaArquivosParaLeitura(f);
        }
    }

    public static LedorDados getInstance() {
        if (LedorDados.instance == null) {
            throw new IllegalArgumentException(
                    "É necessário indicar um caminho base ao criar a classe.");
        } else {
            return LedorDados.instance;
        }
    }

    public static LedorDados getInstance(String raiz) {
        if (LedorDados.instance == null) {
            File f = new File(raiz);
            if (!f.exists()) {
                throw new IllegalArgumentException("O caminho especificado \""
                        + f.getAbsolutePath() + "\" não existe.");
            } else {
                LedorDados.instance = new LedorDados(f);
                return LedorDados.instance;
            }

        } else {
            return LedorDados.instance;
        }
    }

    public ConjuntoDados next() {
        carregaConjuntoDados();
        return this.listConjuntoDados.remove(0);

    }

    private void carregaConjuntoDados() {
        if (!this.listConjuntoDados.isEmpty()) {
            return;
        }

        File arquivoConjuntoDados = this.arquivos.remove(0);
        FileReader reader;
        BufferedReader buffer;
        try {
            reader = new FileReader(arquivoConjuntoDados);
            buffer = new BufferedReader(reader);

            String texto = buffer.readLine();
            String[] listaTextos = texto.split(" ");
            int numeroDeVertices = Integer.parseInt(listaTextos[0]);
            int numeroDeRotulos = Integer.parseInt(listaTextos[0]);

            for (int i = 0; i < 10; i++) {

            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + arquivoConjuntoDados.getName()
                    + " não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro carregando conjunto de dados do arquivo: " + arquivoConjuntoDados.getAbsolutePath());
            
            e.printStackTrace();
        }

    }
}