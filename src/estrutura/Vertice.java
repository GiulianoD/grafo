package estrutura;

import java.util.ArrayList;

public class Vertice<TIPO> {
    private TIPO dado;
    private ArrayList<Aresta<TIPO>> arestasEntradas;
    private ArrayList<Aresta<TIPO>> arestasSaidas;

    public Vertice(TIPO dado){
        this.dado = dado;
        this.arestasEntradas = new ArrayList<Aresta<TIPO>>();
        this.arestasSaidas = new ArrayList<Aresta<TIPO>>();
    }

    public TIPO getDado() {
        return dado;
    }
    public void setDado(TIPO dado) {
        this.dado = dado;
    }

    public void addArestaEntrada (Aresta<TIPO> aresta){
        this.arestasEntradas.add(aresta);
    }
    public void addArestaSaida (Aresta<TIPO> aresta){
        this.arestasSaidas.add(aresta);
    }
}
