package estrutura;

import java.util.ArrayList;

public class Vertice<TIPO> {
    private TIPO id;
    private String nome;
    private ArrayList<Aresta<TIPO>> arestasEntradas;
    private ArrayList<Aresta<TIPO>> arestasSaidas;

    public ArrayList<Aresta<TIPO>> getArestasEntradas() {
        return arestasEntradas;
    }
    public ArrayList<Aresta<TIPO>> getArestasSaidas() {
        return arestasSaidas;
    }

    public Vertice(TIPO id, String nome){
        this.id = id;
        this.nome = nome;
        this.arestasEntradas = new ArrayList<Aresta<TIPO>>();
        this.arestasSaidas = new ArrayList<Aresta<TIPO>>();
    }

    public String getNome() {
        return nome;
    }
    
    public TIPO getid() {
        return id;
    }
    public void setDado(TIPO id) {
        this.id = id;
    }

    public void addArestaEntrada (Aresta<TIPO> aresta){
        this.arestasEntradas.add(aresta);
    }
    public void addArestaSaida (Aresta<TIPO> aresta){
        this.arestasSaidas.add(aresta);
    }
}
