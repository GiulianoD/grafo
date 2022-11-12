package estrutura;

import java.util.ArrayList;

public class Grafo<TIPO> {
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;
    private int qtdVertices;

    public ArrayList<Vertice<TIPO>> getVerticeList() {
        return vertices;
    }
    public ArrayList<Aresta<TIPO>> getArestaList() {
        return arestas;
    }

    public Grafo(ArrayList<Vertice<TIPO>> vertices, ArrayList<Aresta<TIPO>> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
        this.qtdVertices = 0;
    }

    public void addVertice(int id, TIPO dados){
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(id, dados);
        this.vertices.add(novoVertice);
        this.qtdVertices++;
    }

    public void addAresta(Float peso, int idInicio, int idFim){ // adiciona uma aresta ao grafo
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
    } // fim addAresta

    public Vertice<TIPO> getVertice (int id){
        // busca por um vertice com o ID
        for (Vertice<TIPO> v : vertices)
            if (v.getid() == id) return v; // retorna o vertice encontrado
        return null;
        // retorna null, caso nao encontre
    }

    public void getVizinhos(int id){
        if (id > this.vertices.size() -1) return; // verifica se entrou com um id disponivel

        Vertice<TIPO> v = this.getVerticeList().get(id); // recebe o vertice o qual sera o ponto de inicio

        System.out.println("O seguintes Vertices sao vizinhos de " + v.getid() + " - " + vertices.get(id).getDados());
        for (int i=0; i < v.getArestasSaidas().size(); i++){ // ira na busca por cada vertice visitavel a partir de "v"
            System.out.print(v.getArestasSaidas().get(i).getFim().getid()); // imprime o nome do vertice
            System.out.println(" || " 
                                + v.getArestasSaidas().get(i).getFim().getDados()
                                + " || peso: "
                                + v.getArestasSaidas().get(i).getPeso()
                            ); // imprime o peso do caminho entre os vertices
        }
    } // fim getVizinhos

    public void buscaEmLargura(int id){
        if (id > this.vertices.size() -1) return; // verifica se entrou com um id disponivel

        ArrayList<Vertice<TIPO>> verticesMarcados = new ArrayList<Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> verticesVisitados = new ArrayList<Vertice<TIPO>>();

        Vertice<TIPO> verticeAtual = this.vertices.get(id); // recebe o vertice o qual sera o ponto de inicio

        verticesMarcados.add(verticeAtual); // adiciona o ponto inicial na lista de marcado
        System.out.println("O seguintes vertices podem ser alcancados partindo inicialmente de: " + verticeAtual.getid() + " - " + verticeAtual.getDados());
        verticesVisitados.add(verticeAtual); // adiciona o ponto inicial na lista de visitado

        while (verticesVisitados.size() > 0){ // fica em loop enquanto ainda houver Vertice na lista de visitados
            verticeAtual = verticesVisitados.get(0);

            for (int i=0; i < verticeAtual.getArestasSaidas().size(); i++){ // agora ira na busca por cada vertice visitavel a partir do verticeAtual
                Vertice<TIPO> proximo = verticeAtual.getArestasSaidas().get(i).getFim(); // pega o vertice que esta no fim da aresta de indice "i"
                if (!verticesMarcados.contains(proximo)){ // adiciona este proximo vertice nas listas
                    verticesMarcados.add(proximo);
                    System.out.println(proximo.getid() + " - " + proximo.getDados());
                    verticesVisitados.add(proximo);
                }
            }
            verticesVisitados.remove(0); // apos visitar todas as arestas do vertice, o mesmo foi retirado da lista
        }
    } // fim busca em largura

    public void dijkstra(Vertice<TIPO> origem, Vertice<TIPO> destino){
        /*
         * por causa da maneira de como sao tratados os vetores criados abaixo
         * este metodo funciona partindo do pressuposto que:
         * 1 - os vertices foram adicionados com ID crescente (emulando um AUTO_INCREMENT);
         * 2 - nenhum vertice foi deletado.
         * concluindo que a quantidade de vertices existentes
         * é igual ao id do ultimo vertice adicionado
        */
        float[] distancia = new float[this.qtdVertices+1];
        int[] predecessor = new int[this.qtdVertices+1];
        /*
         * * exemplo *
         * posicao X do vetor, sera relacionada ao vertice de id X
         * os valores da posicao zero nao sao tratados em nenhum momento pois nao existe vertice com id zero
         *
         * * possivel maneira de apresentar erro:*
         * se respeitando o padrão mencionado a cima, a unica forma de dar erro, seria se 
         * a quantidade de vertices for exatamente o limite de um int,
         * impossibilitando a criacao de um vetor com mais elementos que o valor maximo de um int
         * 
         * o comentario inicial ficou quase maior que o metodokkkkk
         * segue o jogo
        */

        // pre define a distancia e o predecessor de todos os vertices
        for (int i = this.qtdVertices; i > 0; i++){
            distancia[i] = Float.MAX_VALUE; // distancias sendo "infinito"
            predecessor[i] = 0;
        }

        // define o predecessor e distancia do vertice origem
        distancia[origem.getid()] = 0;
        predecessor[origem.getid()] = -1;

        return;
    } // fim dijkstra

}
