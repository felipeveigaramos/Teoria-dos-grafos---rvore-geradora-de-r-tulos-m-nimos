package br.edu.utfpr.cm.ledor;

import java.util.Scanner;

import br.edu.utfpr.cm.algoritmo.Dijkstra;
import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaLargura;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;

public class TesteLedorDadosTrabalho {
    public static void main(String[] args) {
        System.out.println("Iniciando");
        GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>> grafo = LedorDadosTrabalho
                .leGrafo("roadNet-PA.txt");
        Dijkstra dijkstra;

        Scanner input = new Scanner(System.in);
        VerticeBuscaLargura v1, v2;
        String v1s = "", v2s = "";
        while (!v1s.equals("-1") && !v2s.equals("-1")) {
            System.out.println("Digite o primeiro vértice (-1 fim)");
            v1s = input.next();
            System.out.println("Digite o segundo vértice (-1 fim)");
            v2s = input.next();
            if (v1s.equals("-1") || v2s.equals("-1")) {
                System.out.println("Fim.");
                break;
            }
            v1 = grafo.getVertice(v1s);
            v2 = grafo.getVertice(v2s);
            if (v1 == null || v2 == null) {
                System.out.println("Vértices nulos encontrados.");
            }
            dijkstra = new Dijkstra(grafo, v1);
            try {
                dijkstra.executar();
                System.out.println("Tempo de execução: " + dijkstra.getTempoFinalizacao() + " ms");
                if (v2.getDistancia() != (int)Double.POSITIVE_INFINITY) {
                System.out.println("Distância: " + v2.getDistancia());
                imprimeCaminho(v1, v2);
                } else {
                    System.out.println("Não há caminho entre estes vértices.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        input.close();
    }

    private static void imprimeCaminho(VerticeBuscaLargura v1, VerticeBuscaLargura v2) {
        String s = "";
        while (!v2.equals(v1)) {
            s = v2.getId() + "->";
            v2 = v2.getPai();
        }
        s = v2.getId() + "->" + s;
        s = s.substring(0, s.length()-2);
        System.out.println(s);
    }
}
