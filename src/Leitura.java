
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
                String palavra = sc.next().toLowerCase();
                String sig = sc.next();
                addNode(palavra, sig);
            }

        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }

    }
    
//    public imprimeArvore(){
//    
//    }
    
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
        addNodeAux(palavra, sig, 0, root);
             
    }
    
    private void addNodeAux(String palavra,String sig, int pos, Node n){
        if(pos == palavra.length()){
            n.significado = sig;
            System.out.println(n.element + " " + n.significado);
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
        
        addNodeAux(palavra, sig, pos+1, aux);
        
    }
    
//    public LinkedList positionsPre() {
//        LinkedList res = new LinkedList();
//        Node root = getRoot('a');
//        positionsPreAux(root, res);
//        return res;
//    }
//    private void positionsPreAux(Node n, LinkedList res) {
//        if(n == null)
//            return;
//        res.add(n.element);
//        if(n.sons != null)
//            positionsPreAux(n.left, res);
//        if(n.right != null)
//            positionsPreAux(n.right, res);
//    }
    
    
}
