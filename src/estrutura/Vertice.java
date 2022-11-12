package estrutura;

import java.util.ArrayList;

public class Vertice<TIPO> {
    private int id;
    private TIPO dados;
    private ArrayList<Aresta<TIPO>> arestasEntradas;
    private ArrayList<Aresta<TIPO>> arestasSaidas;

    public ArrayList<Aresta<TIPO>> getArestasEntradas() {
        return arestasEntradas;
    }
    public ArrayList<Aresta<TIPO>> getArestasSaidas() {
        return arestasSaidas;
    }

    public Vertice(int id, TIPO dados){
        this.id = id;
        this.dados = dados;
        this.arestasEntradas = new ArrayList<Aresta<TIPO>>();
        this.arestasSaidas = new ArrayList<Aresta<TIPO>>();
    }

    public TIPO getDados() {
        return dados;
    }
    
    public int getid() {
        return id;
    }
    public void setDado(TIPO dados) {
        this.dados = dados;
    }

    public void addArestaEntrada (Aresta<TIPO> aresta){
        this.arestasEntradas.add(aresta);
    }
    public void addArestaSaida (Aresta<TIPO> aresta){
        this.arestasSaidas.add(aresta);
    }
}
