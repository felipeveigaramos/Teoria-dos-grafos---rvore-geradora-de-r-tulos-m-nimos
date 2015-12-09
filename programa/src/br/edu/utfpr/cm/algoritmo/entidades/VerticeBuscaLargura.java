package br.edu.utfpr.cm.algoritmo.entidades;

import br.edu.utfpr.cm.grafo.Vertice;

public class VerticeBuscaLargura extends Vertice implements Comparable<VerticeBuscaLargura> {
    private CorVertice cor = new CorVertice(Cor.Branco);
    private int distancia = (int) Float.POSITIVE_INFINITY;
    private VerticeBuscaLargura pai = null;

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + distancia;
        result = prime * result + ((pai == null) ? 0 : pai.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        VerticeBuscaLargura other = (VerticeBuscaLargura) obj;
        if (distancia != other.distancia)
            return false;
        if (pai == null) {
            if (other.pai != null)
                return false;
        } else if (!pai.equals(other.pai))
            return false;
        return true;
    }

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
