/**
 * 
 */
package br.edu.utfpr.cm.algoritmo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import br.edu.utfpr.cm.algoritmo.entidades.Particula;
import br.edu.utfpr.cm.factory.GrafoFactory;
import br.edu.utfpr.cm.factory.GrafoPonderadoListaAdjacencia;
import br.edu.utfpr.cm.factory.Representacao;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;
import br.edu.utfpr.cm.grafo.Vertice;
import br.edu.utfpr.cm.ledor.ConjuntoDados;

/**
 * @author Felipe
 *
 */
public class Jpso implements Algoritmo {
    private ConjuntoDados conjuntoDados;
    private GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> grafo;
    private GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> grafoResultante;
    private ArrayList<Double> rotulosResultantes;

    /**
     * @return the grafo
     */
    public GrafoPonderado<?, ?> getGrafo() {
        return grafo;
    }

    /**
     * @return the conjuntoDados
     */
    public ConjuntoDados getConjuntoDados() {
        return conjuntoDados;
    }

    /**
     * @param conjuntoDados
     *            the conjuntoDados to set
     */
    public void setConjuntoDados(ConjuntoDados conjuntoDados) {
        this.conjuntoDados = conjuntoDados;
        this.grafo = conjuntoDados.getGrafo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.utfpr.cm.algoritmo.Algoritmo#executar()
     */
    @Override
    public void executar() {
        ArrayList<Double> c = new ArrayList<Double>();
        GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> h = (GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>>) GrafoFactory
                .constroiGrafo(Representacao.PONDERADO_LISTA_ADJACENCIA);
        int ns = 100;
        ArrayList<Double> rotulos = geraListaDeRotulos();
        ArrayList<Particula> particulas = generateSwarmAtRandom(ns, rotulos);
        Particula melhorPosicaoGlobal = encontreMelhorPosicao(particulas);
        ArrayList<ArrayList<Double>> melhorPosicaoDaVizinhanca = new ArrayList<ArrayList<Double>>(ns);
        Random random = new Random();
        double rand;
        ArrayList<Double> rotulosSelecionados;

        do {
            for (int i = 0; i < ns; i++) {
                if (i == 0) {
                    melhorPosicaoDaVizinhanca.get(i).clear();
                    melhorPosicaoDaVizinhanca.get(i).addAll(rotulos);
                } else {
                    melhorPosicaoDaVizinhanca.get(i).clear();
                    melhorPosicaoDaVizinhanca.get(i).addAll(melhorPosicaoDaVizinhanca.get(i - 1));
                }

                rand = random.nextDouble();

                Particula particula = particulas.get(i);
                if (0 <= rand && rand < 0.25) {
                    rotulosSelecionados = particula.getRotulos();
                } else if (0.25 <= rand && rand < 0.5) {
                    rotulosSelecionados = particula.getMelhorPosicao();
                } else if (0.5 <= rand && rand < 0.75) {
                    rotulosSelecionados = melhorPosicaoDaVizinhanca.get(i);
                } else {
                    rotulosSelecionados = melhorPosicaoGlobal.getRotulos();
                }

                // particulas.set(i,
                combine(particula, rotulosSelecionados);
                localSearch(particula);

                if (particula.getNumeroDeRotulos() < particula.getNumeroDeRotulosNaMelhorPosicao()) {
                    particula.setMelhorPosicao(particula.getRotulos());
                }

                if (particula.getNumeroDeRotulos() < melhorPosicaoDaVizinhanca.get(i).size()) {
                    melhorPosicaoDaVizinhanca.set(i, particula.getRotulos());
                }

                if (particula.getNumeroDeRotulos() < melhorPosicaoGlobal.getNumeroDeRotulos()) {
                    melhorPosicaoGlobal = particula;
                }
            }

        } while (1 == 1);

        grafoResultante = melhorPosicaoGlobal.getGrafo();
        rotulosResultantes = melhorPosicaoGlobal.getRotulos();
    }

    private void localSearch(Particula particula) {
        ContaComponentesConexas contaComponentesConexas = new ContaComponentesConexas();
        GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> g = particula.getGrafo();
        ArrayList<Double> rotulos = particula.getRotulos();

        for (Double rotulo : particula.getRotulos()) {
            rotulos.remove(rotulo);
            g = this.induzGrafo(this.grafo, rotulos);
            contaComponentesConexas.setGrafo(g);
            contaComponentesConexas.executar();
            if (contaComponentesConexas.getQuantidadeDeComponentesConexas() > 1) {
                rotulos.add(rotulo);
            }
        }

        particula.setGrafo(g);
        particula.setRotulos(rotulos);

    }

    private void combine(Particula particula, ArrayList<Double> rotulosSelecionados) {
        ArrayList<Double> rotulosParticula = particula.getRotulos();
        ArrayList<Double> posicao = new ArrayList<Double>(rotulosParticula);
        double rotulo;
        GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> g;
        ContaComponentesConexas contaComponentesConexas = new ContaComponentesConexas();
        Random random = new Random();
        int rand = random.nextInt(rotulosParticula.size());

        for (int i = 0; i < rand; i++) {
            if (random.nextDouble() < 0.5) {
                posicao.remove(rotulosParticula.remove(random.nextInt(rotulosParticula.size())));
            } else {
                do {
                    rotulo = rotulosSelecionados.remove(random.nextInt(rotulosSelecionados.size()));
                    if (!posicao.contains(rotulo)) {
                        posicao.add(rotulo);
                        rotulo = -1;
                    }
                } while (rotulo != -1);
            }
        }
        g = this.induzGrafo(this.grafo, posicao);
        contaComponentesConexas.setGrafo(g);
        contaComponentesConexas.executar();

        while (contaComponentesConexas.getQuantidadeDeComponentesConexas() > 1) {
            do {
                rotulo = rotulosParticula.remove(random.nextInt(rotulosParticula.size()));
                if (!posicao.contains(rotulo)) {
                    posicao.add(rotulo);
                    rotulo = -1;
                }
            } while (rotulo != -1);
            g = this.induzGrafo(this.grafo, posicao);
            contaComponentesConexas.setGrafo(g);
            contaComponentesConexas.executar();
        }

        particula.setGrafo(g);
        particula.setRotulos(posicao);
    }

    private Particula encontreMelhorPosicao(ArrayList<Particula> particulas) {
        Particula melhorPosicaoGlobal;
        melhorPosicaoGlobal = particulas.get(0);
        for (Particula particula : particulas) {
            if (particula.getNumeroDeRotulos() < melhorPosicaoGlobal.getNumeroDeRotulos()) {
                melhorPosicaoGlobal = particula;
            }
        }

        return melhorPosicaoGlobal;
    }

    private ArrayList<Particula> generateSwarmAtRandom(int ns, ArrayList<Double> rotulos) {
        ArrayList<Particula> particulas = new ArrayList<Particula>();
        Particula p;
        for (int i = 0; i < ns; i++) {
            p = geraParticula(rotulos);
            p.setMelhorPosicao(p.getRotulos());
            particulas.add(p);
            System.out.println("#gpi " + p.getRotulos());
        }
        return particulas;
    }

    private Particula geraParticula(ArrayList<Double> rotulos) {
        Particula particula = new Particula();

        Random random = new Random();
        ArrayList<Double> rotulosUsados = new ArrayList<Double>();
        ContaComponentesConexas contaComponentesConexas = new ContaComponentesConexas();
        GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> grafoFinal = (GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>>) GrafoFactory
                .constroiGrafo(Representacao.PONDERADO_LISTA_ADJACENCIA);

        do {
            rotulosUsados.add(rotulos.remove(random.nextInt(rotulos.size())));
            grafoFinal = this.induzGrafo(this.grafo, rotulosUsados);
            contaComponentesConexas.setGrafo(grafoFinal);
            contaComponentesConexas.executar();
        } while (contaComponentesConexas.getQuantidadeDeComponentesConexas() > 1);

        particula.setRotulos(rotulosUsados);
        particula.setGrafo(grafoFinal);

        return particula;
    }

    private ArrayList<Double> geraListaDeRotulos() {
        ArrayList<Double> rotulos = new ArrayList<Double>();
        for (int i = 0; i < this.conjuntoDados.getNumeroDeRotulos(); i++) {
            rotulos.add(new Double(i));
        }
        return rotulos;
    }

    private GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> induzGrafo(
            GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> g, ArrayList<Double> labels) {
        GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> grafoInduzido = (GrafoPonderadoListaAdjacencia) GrafoFactory
                .constroiGrafo(Representacao.PONDERADO_LISTA_ADJACENCIA);

        Iterator<Vertice> vertices = g.getVertices();
        while (vertices.hasNext()) {
            grafoInduzido.adicionaVertice(vertices.next());
        }

        Iterator<ArestaPonderada<Vertice, Vertice>> arestas = g.getArestas();
        ArestaPonderada<Vertice, Vertice> a;
        while (arestas.hasNext()) {
            a = arestas.next();
            if (labels.contains(a.getPeso())) {
                grafoInduzido.adicionaAresta(a);
            }
        }

        return grafoInduzido;
    }
}