package br.edu.utfpr.cm.grafo;

public class ArestaPonderada<U extends Vertice, V extends Vertice> extends Aresta<U, V> {
    double peso = Double.POSITIVE_INFINITY;

    public ArestaPonderada(U v1, V v2, double peso) {
        super(v1, v2);
        this.peso = peso;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(peso);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof ArestaPonderada)) {
            return false;
        }
        ArestaPonderada<?, ?> other = (ArestaPonderada<?, ?>) obj;
        if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + getVertice1() + ", " + getVertice2() + "):" + peso;
    }
}
