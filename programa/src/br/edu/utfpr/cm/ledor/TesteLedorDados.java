package br.edu.utfpr.cm.ledor;

import java.util.Iterator;

import br.edu.utfpr.cm.algoritmo.ContaComponentesConexas;
import br.edu.utfpr.cm.algoritmo.Jpso;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.Vertice;

public class TesteLedorDados {

    public static void main(String[] args) {
        Iterator<ArestaPonderada<Vertice, Vertice>> i;
        LedorDados ld = LedorDados.getInstance("instancias");
        ContaComponentesConexas ccc = new ContaComponentesConexas();
        ConjuntoDados cd;

        // System.out.println(cd.getNumeroDeVertices() + " " +
        // cd.getNumeroDeRotulos() + "\n" + cd.getMatriz());

        /*
         * i = cd.getGrafo().getArestas(); while (i.hasNext()) {
         * ArestaPonderada<Vertice, Vertice> aresta = i.next();
         * System.out.println(aresta); }
         */

        cd = ld.next();
        /*ccc.setGrafo(cd.getGrafo());
        ccc.executar();
        System.out.println(ccc.getQuantidadeDeComponentesConexas());
*/
        
        Jpso jpso = new Jpso();
        jpso.setConjuntoDados(cd);
        jpso.executar();
        }
}