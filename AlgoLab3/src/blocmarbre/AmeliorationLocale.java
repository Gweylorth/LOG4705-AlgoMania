package blocmarbre;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe AmeliorationLocale, algorithme de recherche d'un optimum local par
 * recherche de voisinage
 *
 * @author Gwenegan
 * @version 1.0
 */
public class AmeliorationLocale {

    private Vorace vorace;

    public AmeliorationLocale(Vorace v) {
        this.vorace = v;
    }

    /**
     * Genere une solution vorace, puis parcourt l'ensemble de ses solutions
     * voisines pour trouver la meilleure
     *
     * @return Solution optimale localement
     */
    public Solution ameliorer() {
        Solution si = this.vorace.getSolution();
        Set<Solution> Vs = voisinage(si);

        for (Solution s : Vs) {
            if (s.getPerte() < si.getPerte()) {
                si = s;
            } else {
                continue;
            }
        }

        return si;
    }

    /**
     * Construit les solutions voisines de la solution s0
     *
     * @param s0 Solution originale
     * @return Ensemble des solutions voisines
     */
    private HashSet<Solution> voisinage(Solution s0) {
        HashSet<Solution> set = new HashSet<>();

        Solution si;

        for (Integer coupe = 0; coupe <= this.vorace.getMarbre().getNbCoupes(); coupe++) {
            for (Bloc bloc : s0) {
                si = (Solution) s0.clone();
                if (!bloc.getCoupes().contains(coupe)) {
                    if (!s0.retraitCoupe(coupe)) {
                        continue;
                    }
                    if (!bloc.ajoutCoupe(coupe)) {
                        continue;
                    }
                    set.add(si);
                }
            }
        }

        return set;
    }
}
