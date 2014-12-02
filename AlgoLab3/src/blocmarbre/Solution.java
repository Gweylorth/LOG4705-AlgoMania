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

    public Solution () {
        super();
    }

    public Solution (Solution origin) {
        for (Bloc bloc : origin) {
            this.add(new Bloc(bloc));
        }
    }

    /**
     * Retourne la perte totale des blocs d'une solution
     *
     * @return Entier, perte totale
     */
    public int getPerte() {
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        int perte = 0;
        for (Bloc bloc : this) {
            perte += bloc.getPerte();
        }
        return perte;
    }

    /**
     * Retire la coupe numeroCoupe de tous les blocs de la solution
     *
     * @param numeroCoupe Entier, coupe a retirer
     * @return Booleen, indique si l'operation a pu s'effectuer
     */
    public boolean retraitCoupe(int numeroCoupe) {
        for (Bloc b : this) {
            if (!b.getCoupes().contains(numeroCoupe)) {
                continue;
            }
            if (!b.retraitCoupe(numeroCoupe)) {
                return false;
            }
        }
        return true;
    }
}
