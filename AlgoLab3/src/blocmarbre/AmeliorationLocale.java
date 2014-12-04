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

    /**
     * L'algorithme vorace
     */
    private final Vorace vorace;

    /**
     * Le nombre maximum d'essais
     */
    private static final int MAX_ESSAIS = 10;

    /**
     *
     * Constructeur public de l'objet Amelioration
     *
     * @param vorace
     */
    public AmeliorationLocale(Vorace vorace) {
        this.vorace = vorace;
    }

    /**
     * A partir d'une solution vorace, parcourt l'ensemble de ses solutions
     * voisines pour trouver la meilleure
     *
     * @param solution la solution a ameliorer
     * @return Solution optimale localement
     */
    public Solution ameliorer(Solution solution) {
        // Recuperation de l'ensemble des solutions voisines
        Set<Solution> voisinage = voisinage(solution);
        // Critere de comparaison
        Solution reponse = new Solution(solution);

        // Recupere la solution avec le moins de perte
        for (Solution soluce : voisinage) {
            if (soluce.getPerte() < reponse.getPerte()) {
                reponse = soluce;
            }
        }

        // Renvoie la meilleure solution
        return reponse;
    }

    /**
     *
     * Ameliore la solution vorace.
     *
     * @return la solution amelioree
     */
    public Solution ameliorer() {
        return this.ameliorer(this.vorace.getSolution());
    }

    /**
     * Construit l'ensemble des solutions voisines de la solution de depart
     *
     * @param solution la solution originale
     * @return l'ensemble des solutions voisines
     */
    private HashSet<Solution> voisinage(Solution solution) {
        // Ensemble de solutions voisines
        HashSet<Solution> setSolutions = new HashSet<>();

        // Initialisation de la solution temporaire
        Solution solutionTemporaire = solution;

        // Initialisation des blocs variables
        Bloc[] bloc = new Bloc[2];
        int[] rand = new int[2];
        rand[0] = 0;
        rand[1] = 0;

        // Essais successifs rates
        int essai = 0;
        while (essai < MAX_ESSAIS) {
            // Copie de la derniere solution
            solutionTemporaire = new Solution(solutionTemporaire);

            // Tire des nombres random
            rand[0] = new Random().nextInt(solutionTemporaire.size());
            rand[1] = new Random().nextInt(solutionTemporaire.size());
            while (rand[0] == rand[1]) {
                rand[1] = new Random().nextInt(solutionTemporaire.size());
            }

            // Recupere deux blocs differents au hasard
            bloc[0] = solutionTemporaire.get(rand[0]);
            bloc[1] = solutionTemporaire.get(rand[1]);

            // Recupere un numero au hasard
            int randomCoupe = bloc[0].getCoupes().get(new Random().nextInt(bloc[0].getCoupes().size()));

            // Retire la coupe du bloc 0
            // Si echec, remonte au debut
            if (!bloc[0].retraitCoupe(randomCoupe)) {
                essai++;
                continue;
            }

            // Ajoute la coupe retiree dans le bloc 1
            // Si echec, replace la coupe dans le bloc 0 et remonte au debut
            if (!bloc[1].ajoutCoupe(randomCoupe)) {
                bloc[0].ajoutCoupe(randomCoupe);
                essai++;
                continue;
            }

            // Reduction de la solution
            solutionTemporaire.reduire();

            // Si les blocs sont vides, les supprimer
            if (bloc[0].getCoupes().isEmpty()) {
                solutionTemporaire.remove(rand[0]);
            }
            if (bloc[1].getCoupes().isEmpty()) {
                solutionTemporaire.remove(rand[1]);
            }

            // RAZ du compteur d'echecs
            essai = 0;

            // Ajout de la nouvelle solution a l'ensemble
            setSolutions.add(solutionTemporaire);
        }

        // Renvoie l'ensemble de solutions
        return setSolutions;
    }
}
