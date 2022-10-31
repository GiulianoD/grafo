package estrutura;

import java.util.List;

public class Grafo<TIPO> {
    private ArrayList<TIPO> vertices;
    private ArrayList<TIPO> arestas;
    private List<Aresta> arestaLst;
    private List<Vertice> verticeLst;

    private Aresta predecessor;
}
