package blocmarbre;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

/**
 * Classe AmeliorationLocale, algorithme de recherche d'un optimum local par
 * recherche de voisinage
 *
 * @author Gwenegan
 * @version 1.0
 */
public class AmeliorationLocale {

    private final Vorace vorace;
    private final Random random;

    private static final int MAX_ESSAIS = 10;

    public AmeliorationLocale(Vorace v)
    {
        this.vorace = v;
        this.random = new Random();
    }

    /**
     * A partir d'une solution vorace, parcourt l'ensemble de ses solutions
     * voisines pour trouver la meilleure
     *
     * @param solution la solution a ameliorer
     * @return Solution optimale localement
     */
    public Solution ameliorer(Solution solution) {
        Set<Solution> Vs = voisinage(solution);
        // System.out.println("Set size : " + Vs.size());

        for (Solution s : Vs) {
            System.out.println(s.getPerte());
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
        // Ensemble de solutions voisines
        HashSet<Solution> set = new HashSet<>();

        Solution si = s0;
        Bloc bloc1, bloc2;

        // Essais successifs rates
        int essais = 0;
        while(essais < MAX_ESSAIS) {
            // Copie de la derniere solution
            si = new Solution(si);
            // On prend deux blocs au pif dans cette solution
            bloc1 = si.get(this.random.nextInt(si.size()));
            bloc2 = si.get(this.random.nextInt(si.size()));

            // On regarde si le bloc a bien au moins une coupe, qu'on recupere
            if (bloc1.getCoupes().size() <= 0) {
                essais++;
                continue;
            }
            int randomCoupe = bloc1.getCoupes().get(this.random.nextInt(bloc1.getCoupes().size()));

            // On retire la coupe du bloc. Si ca foire, on laisse tomber cette iteration
            if (!bloc1.retraitCoupe(randomCoupe)) {
                essais++;
                continue;
            }

            // On ajoute la coupe retiree au deuxieme bloc. Si ca foire, on la remet au bloc original et on reitere
            if (!bloc2.ajoutCoupe(randomCoupe)) {
                bloc1.ajoutCoupe(randomCoupe);
                essais++;
                continue;
            }

            // Si tout est ok, on garde cette solution, et on reprend a 0 erreur successive
            essais = 0;
            set.add(si);
        }
        return set;
    }
}
