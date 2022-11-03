import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

import estrutura.Grafo;
import estrutura.Aresta;
import estrutura.Vertice;

public class Main {

    private static void menu(){
        System.out.print("Escolha: \n");
        System.out.print("1- Ver vizinhos \n");
        System.out.print("2- Ver destinos possiveis \n");
        System.out.print("3- Sair \n");
    }

    private static Grafo<Integer> leArquivoParaGrafo(){
        ArrayList<Vertice<Integer>> vertices = new ArrayList<Vertice<Integer>>();
        ArrayList<Aresta<Integer>> arestas = new ArrayList<Aresta<Integer>>();
        Grafo<Integer> grf = new Grafo<Integer>(vertices, arestas);

        // le arquivo e salva o grafo em memoria
        try {
            File myObj = new File("entrada.txt");
            Scanner myReader = new Scanner(myObj);
            
            int qtdCidades = myReader.nextInt();myReader.nextLine();

            int i, i2;
            String linha;
            
            for (i=0; i < qtdCidades; i++){ // adiciona vertices
                linha = myReader.nextLine();
                String[] partes = linha.split(";");

                grf.addVertice(Integer.parseInt(partes[0]), partes[1]);
            }

            vertices = grf.getVerticeList();
            for (i=0; i < qtdCidades; i++){ // adiciona arestas
                linha = myReader.nextLine();
                String[] partes = linha.split(";");

                for (i2=0; i2 < qtdCidades; i2++){
                    if (partes[i2].equals("0,00")) continue; // caso o peso seja igual a zero, ignora e continua

                    partes[i2] = partes[i2].replace(",", "."); // converte para o padrao de numero decimal
                    Float peso = Float.parseFloat(partes[i2]);

                    // neste momento, a variavel "i" representa o indice do vertice inicial da aresta
                    // a variavel "i2" representa o indice do vertice final da aresta
                    grf.addAresta(peso,
                                vertices.get(i).getid(),
                                vertices.get(i2).getid()
                                );
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return grf;
    }

    public static void main(String[] args){
        Grafo<Integer> grf = Main.leArquivoParaGrafo();
        int op = 0;
        Scanner scan = new Scanner(System.in); 
        int id;

        while (op != 3){
            Main.menu();
            op = scan.nextInt();
            switch (op){
                case 1:
                    System.out.print("Digite o id de inicio: \n");
                    id = scan.nextInt();
                    grf.getVizinhos(id);
                break;

                case 2:
                    System.out.print("Digite o id de inicio: \n");
                    id = scan.nextInt();
                    grf.buscaEmLargura(id);
                break;

                case 3:
                break;
            }
        }
    }
}
