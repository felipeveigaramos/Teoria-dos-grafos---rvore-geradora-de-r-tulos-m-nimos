package br.edu.utfpr.cm.algoritmo.entidades;

import br.edu.utfpr.cm.grafo.Vertice;

public class VerticeBuscaLargura extends Vertice implements Comparable<VerticeBuscaLargura> {
    private CorVertice cor = new CorVertice(Cor.Branco);
    private int distancia = (int) Float.POSITIVE_INFINITY;
    private VerticeBuscaLargura pai = null;

    public VerticeBuscaLargura(String id) {
        super(id);
    }

    public VerticeBuscaLargura() {
        super();
    }

    public CorVertice getCor() {
        return cor;
    }

    public void setCor(CorVertice cor) {
        this.cor = cor;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public VerticeBuscaLargura getPai() {
        return pai;
    }

    public void setPai(VerticeBuscaLargura pai) {
        this.pai = pai;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String s = this.getId() + "(";
        if (getPai() != null) {
            s += getPai().getId();
        }
        s += "):" + getDistancia();

        return s;
    }

    @Override
    public int compareTo(VerticeBuscaLargura arg0) {
return Integer.compare(getDistancia(), arg0.getDistancia());
    }
}
