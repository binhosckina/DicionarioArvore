/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 17180512
 */
public class Palavra {
 
    private String palavra, significado;

    public Palavra(String palavra, String significado) {
        this.palavra = palavra;
        this.significado = significado;
    }

    public String getPalavra() {
        return palavra;
    }

    public String getSignificado() {
        return significado;
    }

    @Override
    public String toString() {
        return palavra + " " + significado;
    }
}
