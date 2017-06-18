
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 17180512
 */
public class Leitura {
    
    private List<Node> raizes;
   // private Node sentinela;
    
    public Leitura(){
        this.raizes = new ArrayList<>();
    }
    
    private static final class Node {

        public Node father;
        public char element;
        public String significado;
        public List<Node> sons;

        public Node(char element) {
            father = null;
            this.element = element;
            this.significado = null;
            this.sons = new LinkedList<>();
        }
    }

    public void leitura() {             
            Path path1 = Paths.get("nomes.csv");
        try (Scanner sc = new Scanner(Files.newBufferedReader(path1, Charset.defaultCharset()))) {
            sc.useDelimiter("[;\n]");
            while (sc.hasNext()) {
                String palavra = sc.next();
                String sig = sc.next();
                addNode(palavra, sig);
            }

        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }

    }

    /**
     * Método res
     * @param entrada
     * @return
     */
    public ArrayList<Palavra> buscaPalavras(String entrada) {
        if(entrada == null) return null;
        ArrayList<Palavra> listaDePalavras = new ArrayList<>();
        entrada = entrada.toUpperCase();
        Node root = getRoot(entrada.charAt(0));
        Node subRoot = buscaSubArvore(root, entrada, 1);
        listaDePalavras = positionsPreAux(subRoot, "", entrada, listaDePalavras);
        return listaDePalavras;
    }

    public ArrayList positionsPre() {
        ArrayList res = new ArrayList();
        Node root = getRoot('a');
        res = positionsPreAux(root, "", "", res);
        return res;
    }

    private ArrayList<Palavra> positionsPreAux(Node n, String palavraIncompleta, String iniciais, ArrayList<Palavra> listaDePalavras) {
        if(n == null)
            return listaDePalavras;
        if(n.significado != null && n.significado != "") {
            String iniciaisAux = "";
            if (iniciais.length() > 0) {
                for (int i = 0; i < iniciais.length() - 1; i++) {
                    iniciaisAux += iniciais.charAt(i);
                }
            }
            listaDePalavras.add(new Palavra(iniciaisAux + palavraIncompleta + n.element, n.significado));
        }
        if(n.sons != null) {
            for (int i = 0; i < n.sons.size(); i++) {
                positionsPreAux(n.sons.get(i), palavraIncompleta + n.element, iniciais, listaDePalavras);
            }
        }
        return listaDePalavras;
    }

    private Node buscaSubArvore(Node raiz, String entrada, int pos) {
        if(raiz == null) {
            return raiz;
        }

        if (pos == entrada.length()) {
            return raiz;
        }

        if(raiz.sons != null) {
            for (int i = 0; i < raiz.sons.size(); i++) {
                if (raiz.sons.get(i).element == entrada.charAt(pos)) {
                    raiz = buscaSubArvore(raiz.sons.get(i), entrada, pos + 1);
                    break;
                }
            }
        }
        return raiz;
    }

    public String imprimeArvore(){

        return "";
    }

    private Node getRoot(char c) {
        Node arvoreRoot = null;

        for (int i = 0; i < raizes.size(); i++) {
            if (c == raizes.get(i).element) {
                arvoreRoot = raizes.get(i);
                break;
            }
        }
        if (arvoreRoot == null) {
            Node aux = new Node(c);
            raizes.add(aux);
            arvoreRoot = aux;
        }
        return arvoreRoot;
    }


    public void addNode(String palavra, String sig) {

        Node root = getRoot(palavra.charAt(0));
        addNodeAux(palavra.toUpperCase(), sig, 1, root, "");

    }

    private void addNodeAux(String palavra, String sig, int pos, Node n, String print){

        if(pos == palavra.length()){
            n.significado = sig;
            //System.out.println(print + " " + n.significado);
            return;
        }

        char letra = palavra.charAt(pos);
        Node aux = null;

        for (int i = 0; i < n.sons.size(); i++) {
            if(letra == n.sons.get(i).element){
                aux = n.sons.get(i);
                break;
            }
        }

        if(aux == null){
            aux = new Node(letra);
            n.sons.add(aux);
        }

        addNodeAux(palavra, sig, pos+1, aux, print+letra);

    }
    
}
