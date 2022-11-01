package estrutura;

import java.util.ArrayList;

public class Grafo<TIPO> {
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;

    public Grafo(ArrayList<Vertice<TIPO>> vertices, ArrayList<Aresta<TIPO>> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
    }

    public void addVertice(TIPO dado){
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado);
        this.vertices.add(novoVertice);
    }

    public void addAresta(Double peso, TIPO dadoInicio, TIPO dadoFim){
        Vertice<TIPO> inicio = this.getVertice(dadoInicio); // busca pelo vertice da ponta de inicio da aresta
        Vertice<TIPO> fim    = this.getVertice(dadoFim); // busca pelo vertice do fim da aresta

        Aresta<TIPO> novaAresta = new Aresta<TIPO>(peso, inicio, fim); // cria aresta com inicio e fim necessariamente definidos

        // agora adiciona a informaacao do novo caminho entre os vertices que compoem suas pontas
        inicio.addArestaSaida(novaAresta);
        fim.addArestaEntrada(novaAresta);

        // por fim, adiciona a aresta na lista de arestas do grafo
        this.arestas.add(novaAresta);
    }

    public Vertice<TIPO> getVertice (TIPO dado){
        for (Vertice<TIPO> v : vertices)
            if (v.getDado() == dado) return v;
        return null;
    }
}
