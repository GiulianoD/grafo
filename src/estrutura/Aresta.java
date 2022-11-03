package estrutura;
public class Aresta<TIPO> {
    private Float peso;
    private Vertice<TIPO> inicio;
    private Vertice<TIPO> fim;

    public Aresta(Float peso, Vertice<TIPO> inicio, Vertice<TIPO> fim){
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Float getPeso() {
        return peso;
    }
    public void setPeso(Float peso) {
        this.peso = peso;
    }
    public Vertice<TIPO> getInicio() {
        return inicio;
    }
    public void setInicio(Vertice<TIPO> inicio) {
        this.inicio = inicio;
    }
    public Vertice<TIPO> getFim() {
        return fim;
    }
    public void setFim(Vertice<TIPO> fim) {
        this.fim = fim;
    }
}
