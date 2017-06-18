import java.util.ArrayList;

/**
 *
 * @author 17180512
 */
public class Main {
  
    public static void main(String[] args) {
        Leitura leitura = new Leitura();
        
        leitura.leitura();

        ArrayList<Palavra> listaDePalavras;
        listaDePalavras = leitura.buscaPalavras("isa");

        for (Palavra p : listaDePalavras) {
            System.out.println(p.toString());
        }
    }
}
