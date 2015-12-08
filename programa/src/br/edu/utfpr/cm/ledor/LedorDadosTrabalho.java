package br.edu.utfpr.cm.ledor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaLargura;
import br.edu.utfpr.cm.factory.GrafoFactory;
import br.edu.utfpr.cm.factory.Representacao;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;

public class LedorDadosTrabalho {
    public static GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>> leGrafo(
            String fileName) {
        System.out.println("Carregando arquivo: " + fileName);
        FileReader reader = null;
        BufferedReader buffer = null;
        GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>> grafo = null;

        try {
            reader = new FileReader(new File(fileName));
            buffer = new BufferedReader(reader);

            grafo = (GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>>) GrafoFactory
                    .constroiGrafo(Representacao.PONDERADO_LISTA_ADJACENCIA);
            String linha = buffer.readLine();

            int i = 0;
            int j = 2000000;
            while (linha != "" && i <=j) {
                i++;
                if (i % (j/100) == 0) {
                System.out.println("#: " + i);
                }
                if (linha.charAt(0) == '#') {
                    linha = buffer.readLine();
                    continue;
                }

                String[] colunas = linha.split("\t");
                VerticeBuscaLargura u = new VerticeBuscaLargura(colunas[0]);
                VerticeBuscaLargura v = new VerticeBuscaLargura(colunas[1]);
                ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura> a = new ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>(
                        u, v, Math.abs(Double.parseDouble(colunas[1]) - Double.parseDouble(colunas[0])));
                grafo.adicionaAresta(a);
                linha = buffer.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffer != null && reader != null) {
                try {
                    buffer.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return grafo;

    }
}