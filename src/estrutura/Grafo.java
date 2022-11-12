package estrutura;

import java.util.ArrayList;

/*
 * mesmo com os vertices sendo adicionados com ID crescente (emulando um AUTO_INCREMENT) por causa do Gerador,
 * todos os metodos estao tratando os IDs de maneira generica
 * podendo entao os vertices terem ID aleatorio, vertices excluidos, etc
 * ID esta sendo tratado partindo do pressuposto que SEMPRE sera um valor POSITIVO
 */
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

    public void addVertice(int id, TIPO dados){
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(id, dados);
        this.vertices.add(novoVertice);
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
        Vertice<TIPO> v = this.getVertice(id); // recebe o vertice o qual sera o ponto de inicio
        if (v == null) return; // finaliza o metodo caso nao exista o vertice com o criterio passado por paramtro

        System.out.println("O seguintes Vertices sao vizinhos de " + v.getid() + " - " + this.getVertice(id).getDados());
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
        Vertice<TIPO> verticeAtual = this.getVertice(id); // recebe o vertice o qual sera o ponto de inicio

        if (verticeAtual == null) return; // finaliza o metodo caso nao exista o vertice com o criterio passado por paramtro

        ArrayList<Vertice<TIPO>> verticesMarcados  = new ArrayList<Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> verticesVisitados = new ArrayList<Vertice<TIPO>>();

        verticesMarcados.add(verticeAtual); // adiciona o ponto inicial na lista de marcado
        System.out.println("O seguintes vertices podem ser alcancados partindo inicialmente de: " + verticeAtual.getid() + " - " + verticeAtual.getDados());
        verticesVisitados.add(verticeAtual); // adiciona o ponto inicial na lista de visitado

        while (verticesVisitados.size() > 0){ // fica em loop enquanto ainda houver Vertice na lista de visitados
            verticeAtual = verticesVisitados.get(0);

            for (Aresta<TIPO> a : verticeAtual.getArestasSaidas()){ // agora ira na busca por cada vertice visitavel a partir do verticeAtual
                Vertice<TIPO> proximo = a.getFim(); // pega o vertice que esta no fim da aresta de indice "i"
                if (!verticesMarcados.contains(proximo)){ // adiciona este proximo vertice nas listas
                    verticesMarcados.add(proximo);
                    System.out.println(proximo.getid() + " - " + proximo.getDados());
                    verticesVisitados.add(proximo);
                }
            }
            verticesVisitados.remove(0); // apos visitar todas as arestas do vertice, o mesmo foi retirado da lista
        }
    } // fim busca em largura

    public boolean existeCaminhoEntre(int origem, int destino){
        // sim, este é o metodo busca em largura alterado.

        Vertice<TIPO> verticeAtual = this.getVertice(origem);
        Vertice<TIPO> vDestino = this.getVertice(destino);

        // verifica se existem os vertices de origem e destino
        if (true){ // indentacao feita para poder minimizar tudo em uma unica linha na IDE
            if (verticeAtual == null && vDestino == null){
                System.out.println("Nao foram encontrados os Vertices com os IDs entrados como origem e destino.");
                return false;
            }
            if (verticeAtual == null){
                System.out.println("Nao foi encontrado o Vertice com o ID entrado como origem.");
                return false;
            }
            if (vDestino == null){
                System.out.println("Nao foi encontrado o Vertice com o ID entrado como destino.");
                return false;
            }
        }

        ArrayList<Vertice<TIPO>> verticesMarcados  = new ArrayList<Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> verticesVisitados = new ArrayList<Vertice<TIPO>>();

        verticesMarcados.add(verticeAtual); // adiciona o ponto inicial na lista de marcado
        verticesVisitados.add(verticeAtual); // adiciona o ponto inicial na lista de visitado

        while (verticesVisitados.size() > 0){ // fica em loop enquanto ainda houver Vertice na lista de visitados
            verticeAtual = verticesVisitados.get(0);

            for (Aresta<TIPO> a : verticeAtual.getArestasSaidas()){ // agora ira na busca por cada vertice visitavel a partir do verticeAtual
                Vertice<TIPO> proximo = a.getFim(); // pega o vertice que esta no fim da aresta de indice "i"
                if (!verticesMarcados.contains(proximo)){ // adiciona este proximo vertice nas listas
                    verticesMarcados.add(proximo);
                    if (proximo.getid() == destino) return true;
                    verticesVisitados.add(proximo);
                }
            }
            verticesVisitados.remove(0); // apos visitar todas as arestas do vertice, o mesmo foi retirado da lista
        }
        return false;
    } // fim busca em largura

    public class ClasseAuxDijkstra{ // nao consegui pensar em um nome melhor, desculpe o nome grande
        /*
         * esta classe contem todos os dados necessarios para as comparacoes do metodo de dijkstra
         */
        int idVertice; // id do vertice
        int idPredecessor; // id do vertice predecessor
        float distancia; // distancia entre vertice e a origem

        public ClasseAuxDijkstra(int idVertice, int idPredecessor, float distancia) {
            this.idVertice = idVertice;
            this.idPredecessor = idPredecessor;
            this.distancia = distancia;
        }

        public int getIdVertice() {
            return idVertice;
        }

        public int getIdPredecessor() {
            return idPredecessor;
        }
        public void setIdPredecessor(int idPredecessor) {
            this.idPredecessor = idPredecessor;
        }

        public float getDistancia() {
            return distancia;
        }
        public void setDistancia(float distancia) {
            this.distancia = distancia;
        }
    
        public boolean distanciaMenor(float dist){
            if (dist < this.distancia) return true;
            return false;
        }
    }

    public void dijkstra(int idOrigem, int idDestino){
        Vertice<TIPO> vAtual   = this.getVertice(idOrigem);

        if (!this.existeCaminhoEntre(idOrigem, idDestino)){ // este metodo tambem verifica se existem os dois vertices com os IDs passado como parametro
            System.out.println("Nao existe um caminho entre o destino e origem desejados.");
            return;
        }

        ArrayList<ClasseAuxDijkstra> lstDistPred = new ArrayList<ClasseAuxDijkstra>();
        ArrayList<Integer> lstIdNaoRotulados = new ArrayList<Integer>();

        // pre define a distancia (para "infinito") e o predecessor (para zero) de todos os vertices
        for (Vertice<TIPO> verticeAux : this.vertices){
            lstDistPred.add(new ClasseAuxDijkstra(verticeAux.getid(), 0, Float.MAX_VALUE));
            lstIdNaoRotulados.add(verticeAux.getid());
        }

        // define o predecessor e distancia do vertice origem
        for (ClasseAuxDijkstra vertice : lstDistPred){
            if (vertice.getIdVertice() == idOrigem){
                vertice.setDistancia(0);
                vertice.setIdPredecessor(-1);
                break;
            }
        }

        // nao consegui pensar um nome que fosse auto explicativo para essas variaveis
        // durante a execucao do For principal aqui em baixo:
        ClasseAuxDijkstra verticeAux = null; // esta variavel vai guardar o objeto auxiliar referente ao vertice que marca o final de cada aresta tratada
        ClasseAuxDijkstra vAtualAux = null; // esta variavel vai guardar o objeto auxiliar referente ao vertice atual (inicio das arestas analisadas)
        int intAux; // intAux sera sempre igual a algum ID. comentando toda vez que tiver seu valor redefinido
        float floatAux; // floatAux sera para comparar as distancias e encontrar a menor distancia, para saber qual o proximo vertice a ser analisado

        while (!lstIdNaoRotulados.isEmpty()){ // visita todos os vertices nao rotulados

            // na primeira execucao, vAtual aponta para o vertice de origem definido no inicio do metodo
            intAux = vAtual.getid(); // pega o ID do vertice atual

            // como a lista esta contendo Inteiros, foi necessario esse valueOf, caso contrario, o parametro seria o indice do vetor a ser removido, e nao o valor do elemento a ser removido
            lstIdNaoRotulados.remove(Integer.valueOf(intAux)); // remove o ID do vAtual da lista de vertices NAO rotulados para que nao seja analisado novamente

            for (ClasseAuxDijkstra v : lstDistPred){ // recebe o objeto auxiliar do vertice atual a partir do ID
                if (v.getIdVertice() == intAux){
                    vAtualAux = v;
                    break;
                }
            }
            // neste momento, a variavel 'vAtualAux' contem o objeto do vertice atual 'vAtual'

            // agora olharemos cada aresta que parte do vertice atual
            for (Aresta<TIPO> arestaAtual : vAtual.getArestasSaidas()){ // pega cada aresta que sai do vertice atual
                
                intAux = arestaAtual.getFim().getid(); // pega o ID do vertice no final da aresta
                for (ClasseAuxDijkstra v : lstDistPred){ // recebe o objeto auxiliar do vertice final a partir do ID
                    if (v.getIdVertice() == intAux){
                        verticeAux = v;
                        break;
                    }
                }
                // neste momento, a variavel 'verticeAux' contem o objeto vertice destino da aresta atual

                // compara a distancia atual com o peso da aresta + a distancia do vertice atual ate a origem
                if (verticeAux.distanciaMenor(arestaAtual.getPeso()+vAtualAux.getDistancia())){ // caso a distancia for menor, substitui as informacoes
                    verticeAux.setDistancia(arestaAtual.getPeso()+vAtualAux.getDistancia());
                    verticeAux.setIdPredecessor(vAtual.getid());
                }
            }
            floatAux = Float.MAX_VALUE; // recebe valor maximo para sempre ser 'maior ou igual' na primeira comparacao
            // agora devemos olhar entre os vertices nao rotulados, qual esta com menor valor de distancia
            for (ClasseAuxDijkstra v : lstDistPred){
                if (floatAux < v.getDistancia()){ // ira dar continue, caso o vertice analisado agora seja maior que o anterior (ou maior que o primeiro)
                    continue;
                }
                if (!lstIdNaoRotulados.contains(v.getIdVertice())){ // verifica se o vertice NAO esta entre os NAO rotulados
                    continue; // ou seja, pula verificacao caso o vertice ja esteja rotulado
                }
                // caso chegue aqui, entao sabemos que este vertice NAO esta rotulado e que tem distancia MENOR ou IGUAL ao vertice anterior dentro do loop
                vAtual = this.getVertice(v.getIdVertice()); // vAtual sera o proximo vertice a ser analisado
                floatAux = v.getDistancia(); // floatAux agora tem a distancia ate o vAtual
            }
            // neste ponto, a variavel 'vAtual' ja aponta para o proximo vertice a ser analisado, iniciando o loop novamente
        } // terminou de visitar de todos os vertices

        // neste ponto, lstDistPred ja possui a informacao de como chegar ao destino pelo menor caminho, basta imprimir na tela
        ArrayList<ClasseAuxDijkstra> caminhoFinal = new ArrayList<ClasseAuxDijkstra>();

        intAux = idDestino; // intAux agora recebera cada ID do caminho
        while (intAux != -1)
            for (ClasseAuxDijkstra v : lstDistPred)
                if (v.getIdVertice() == intAux){
                    caminhoFinal.add(v);
                    intAux = v.getIdPredecessor();
                }

        // neste momento, a lista caminhoFinal possui (em ordem inversa) o caminho entre origem e destino
        java.util.Collections.reverse(caminhoFinal); // agora esta na ordem correta

        Vertice<TIPO> vAnterior = null;
        float distanciaAnterior = 0;
        System.out.println("O menor caminho encontrado foi o seguinte:");
        for (ClasseAuxDijkstra v : caminhoFinal){ // loop para imprimir na tela o menor caminho
            if (vAnterior == null) { // primeiro loop
                vAtual = this.getVertice(v.getIdVertice());
                vAnterior = vAtual;
                continue; // como preciso saber sobre o atual e o anterior, no primeiro loop apenas pego algumas informacoes
            }

            vAnterior = vAtual;
            vAtual = this.getVertice(v.getIdVertice());

            System.out.println(vAnterior.getid()+": "+vAnterior.getDados()+
                                " -> " +
                                vAtual.getid()+": "+vAtual.getDados()+
                                " || Distancia: "+(v.getDistancia()-distanciaAnterior)
                            );
            distanciaAnterior = v.getDistancia();

            if (v.getIdVertice() == idDestino) // se estiver imprimindo referente ao ultimo passo, entao adiciona a distancia total
                System.out.println("Distancia total: "+v.getDistancia());
        } // final da impressao do caminho 
        return;
    } // fim dijkstra
}
