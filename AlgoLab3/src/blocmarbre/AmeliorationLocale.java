package blocmarbre;

import java.util.ArrayList;
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
     * A partir d'une solution vorace, parcourt l'ensemble de ses solutions
     * voisines pour trouver la meilleure
     *
     * @return Solution optimale localement
     */
    public Solution ameliorer(Solution solution) {
        Set<Solution> Vs = voisinage(solution);

        for (Solution s : Vs) {
            if (s.getPerte() < solution.getPerte()) {
                solution = s;
            } else {
                continue;
            }
        }

        return solution;
    }

    public Solution ameliorer() {
        return this.ameliorer(this.vorace.getSolution());
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
        Bloc bloc;

        for (Integer coupe = 0; coupe < this.vorace.getMarbre().getNbCoupes(); coupe++) {
            for (int i = 0; i < s0.size(); i++) {
                si = new Solution(s0);
                bloc = si.get(i);
                if (!bloc.getCoupes().contains(coupe)) {
                    if (!si.retraitCoupe(coupe)) {
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
