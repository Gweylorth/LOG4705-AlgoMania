package blocmarbre;

import java.util.ArrayList;

/**
 * Classe Solution, une ArrayList de Blocs proposant des methodes
 * supplementaires
 *
 * @author Gwenegan
 * @version 1.0
 */
public class Solution extends ArrayList<Bloc> {

    /**
     * Constructeur vide
     */
    public Solution() {
        super();
    }

    /**
     *
     * Construit une solution a partir d'une solution existante
     *
     * @param origine la solution d'origine
     */
    public Solution(Solution origine) {
        for (Bloc bloc : origine) {
            this.add(new Bloc(bloc));
        }
    }

    /**
     * Retourne la perte totale des blocs d'une solution
     *
     * @return Entier, perte totale
     */
    public int getPerte() {
        // Si vide, perte infinie
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        // Calcul de la perte
        int perte = 0;
        for (Bloc bloc : this) {
            perte += bloc.getPerte();
        }

        // Renvoie la perte
        return perte;
    }

    /**
     * Reduit les blocs dans la solution
     */
    public void reduire() {
        for (Bloc bloc : this) {
            bloc.reduire();
        }
    }

}
