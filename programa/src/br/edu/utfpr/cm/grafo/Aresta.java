package br.edu.utfpr.cm.grafo;

public class Aresta<U extends Vertice, V extends Vertice> {
    private Vertice v1 = null;
    private Vertice v2 = null;

    public Aresta(U v1, V v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertice getVertice1() {
        return v1;
    }

    public void setVertice1(Vertice v1) {
        this.v1 = v1;
    }

    public Vertice getVertice2() {
        return v2;
    }

    public void setVertice2(Vertice v2) {
        this.v2 = v2;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
        result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Aresta)) {
            return false;
        }
        Aresta other = (Aresta) obj;
        if (v1 == null) {
            if (other.v1 != null) {
                return false;
            }
        } else if (!v1.equals(other.v1)) {
            return false;
        }
        if (v2 == null) {
            if (other.v2 != null) {
                return false;
            }
        } else if (!v2.equals(other.v2)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + v1 + ", " + v2 + ")";
    }

}
