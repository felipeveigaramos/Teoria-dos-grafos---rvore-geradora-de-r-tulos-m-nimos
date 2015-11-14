package br.edu.utfpr.cm.ledor;

import java.util.Iterator;

import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Vertice;

public class TesteLedorDados {

    public static void main(String[] args) {
        Iterator<ArestaPonderada<Vertice, Vertice>> i;
        LedorDados ld = LedorDados.getInstance("instancias");
        ConjuntoDados cd = ld.next();
        System.out.println(cd.getNumeroDeVertices() + " "
                + cd.getNumeroDeRotulos() + "\n" + cd.getMatriz());
        i = cd.getGrafo().getArestas();
        while (i.hasNext()) {
            ArestaPonderada<Vertice, Vertice> aresta = i.next();
            System.out.println(aresta);
        }
    }

}
