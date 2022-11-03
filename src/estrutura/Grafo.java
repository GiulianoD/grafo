package estrutura;

import java.util.ArrayList;

public class Grafo<TIPO> {
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;

    public ArrayList<Vertice<TIPO>> getVerticeList() {
        return vertices;
    }
    public ArrayList<Aresta<TIPO>> getArestaList() {
        return arestas;
    }

    public Grafo(ArrayList<Vertice<TIPO>> vertices, ArrayList<Aresta<TIPO>> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
    }

    public void addVertice(TIPO id, String nome){
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(id, nome);
        this.vertices.add(novoVertice);
    }

    public void addAresta(Float peso, TIPO idInicio, TIPO idFim){ // adiciona uma aresta ao grafo
        // peso : peso do caminho entre o inicio e o fim
        // id inicio : representa o valor do Vertice inicio.
        // id fim: representa o valor do Vertice fim.
        // estes valores serao utilizados para encontrar o vertice na lista do grafo com o getVertice

        Vertice<TIPO> inicio = this.getVertice(idInicio); // busca pelo vertice da ponta de inicio da aresta
        Vertice<TIPO> fim    = this.getVertice(idFim); // busca pelo vertice do fim da aresta

        Aresta<TIPO> novaAresta = new Aresta<TIPO>(peso, inicio, fim); // cria aresta com inicio e fim necessariamente definidos

        // agora adiciona a informaacao do novo caminho entre os vertices que compoem suas pontas
        inicio.addArestaSaida(novaAresta);
        fim.addArestaEntrada(novaAresta);

        // por fim, adiciona a aresta na lista de arestas do grafo
        this.arestas.add(novaAresta);
    }

    public Vertice<TIPO> getVertice (TIPO id){
        for (Vertice<TIPO> v : vertices)
            if (v.getid() == id) return v;
        return null;
    }

    public void getVizinhos(int id){
        if (id > this.vertices.size() -1) return; // verifica se entrou com um id disponivel

        Vertice<TIPO> v = this.getVerticeList().get(id);

        for (int i=0; i < v.getArestasSaidas().size(); i++){ // ira na busca por cada vertice visitavel a partir de "v"
            System.out.print(v.getArestasSaidas().get(i).getFim().getid()); // imprime o nome do vertice
            System.out.println(" || " 
                                + v.getArestasSaidas().get(i).getFim().getNome()
                                + " || peso: "
                                + v.getArestasSaidas().get(i).getPeso()
                            ); // imprime o peso do caminho entre os vertices
        }
    }

    public void buscaEmLargura(int id){
        if (id > this.vertices.size() -1) return; // verifica se entrou com um id disponivel

        ArrayList<Vertice<TIPO>> verticesMarcados = new ArrayList<Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> verticesVisitados = new ArrayList<Vertice<TIPO>>();
        Vertice<TIPO> verticeAtual = this.vertices.get(id);

        verticesMarcados.add(verticeAtual);
        System.out.println(verticeAtual.getid() + " - " + verticeAtual.getNome());
        verticesVisitados.add(verticeAtual);

        while (verticesVisitados.size() > 0){
            verticeAtual = verticesVisitados.get(0);

            for (int i=0; i < verticeAtual.getArestasSaidas().size(); i++){ // agora ira na busca por cada vertice visitavel a partir do verticeAtual
                Vertice<TIPO> proximo = verticeAtual.getArestasSaidas().get(i).getFim(); // pega o vertice que esta no fim da aresta de indice "i"
                if (!verticesMarcados.contains(proximo)){ // adiciona este proximo vertice nas listas
                    verticesMarcados.add(proximo);
                    System.out.println(proximo.getid() + " - " + proximo.getNome());
                    verticesVisitados.add(proximo);
                }
            }
            verticesVisitados.remove(0);
        }
    }
}
