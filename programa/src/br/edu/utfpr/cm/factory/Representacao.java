package br.edu.utfpr.cm.factory;

public enum Representacao {
    LISTA_ADJACENCIA(0), 
    MATRIZ_ADJACENCIA(1),
    PONDERADO_LISTA_ADJACENCIA(2);

    private int representacao;

    Representacao(int representacao) {
        this.setRepresentacao(representacao);
    }

    public int getRepresentacao() {
        return representacao;
    }

    public void setRepresentacao(int representacao) {
        this.representacao = representacao;
    }
}
