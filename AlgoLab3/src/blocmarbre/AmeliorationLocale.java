package blocmarbre;

import java.util.ArrayList;
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

    private final Random random;

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
        Solution solutionBackup, solutionTemporaire = solution;

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
            solutionBackup = new Solution(solutionTemporaire);

            // Recupere un bloc au hasard
            rand[0] = this.random.nextInt(solutionTemporaire.size());
            bloc[0] = solutionTemporaire.get(rand[0]);

            // Recupere un numero au hasard
            if (bloc[0].getCoupes().isEmpty()) {
                solutionTemporaire.remove(rand[0]);
                essai++;
                continue;
            }
            int numeroCoupe = this.random.nextInt(bloc[0].getCoupes().size());
            Integer randomCoupe = bloc[0].getCoupes().get(numeroCoupe);

            // Retire la coupe du bloc 0
            // Si echec, remonte au debut
            if (!bloc[0].retraitCoupe(randomCoupe)) {
                essai++;
                continue;
            }

            // Construit une liste de blocs potentiels
            ArrayList<Integer> blocsPotentiels = new ArrayList<>();
            int longueurRandomCoupe = bloc[0].getMarbre().getCoupes()[randomCoupe][0];
            for (int i = 0; i < solutionTemporaire.size(); i++) {
                if (i != rand[0]) {
                    if (solutionTemporaire.get(i).getPerte() >= longueurRandomCoupe) {
                        blocsPotentiels.add(i);
                    }
                }
            }
            // Si aucun bloc potentiel, remonte au debut
            if (blocsPotentiels.isEmpty()) {
                essai++;
                continue;
            }
            // Recupere un bloc potentiel ou la coupe peut etre inseree
            int numeroBloc = this.random.nextInt(blocsPotentiels.size());
            rand[1] = blocsPotentiels.get(numeroBloc);
            while (rand[0] == rand[1]) {
                numeroBloc = this.random.nextInt(blocsPotentiels.size());
                rand[1] = blocsPotentiels.get(numeroBloc);
            }
            bloc[1] = solutionTemporaire.get(rand[1]);

            // Ajoute la coupe retiree dans le bloc 1
            // Si echec, replace la coupe dans le bloc 0 et remonte au debut
            if (!bloc[1].ajoutCoupe(randomCoupe)) {
                bloc[0].ajoutCoupe(randomCoupe);
                essai++;
                continue;
            }

            // Si le bloc 0 est devenu vide, le supprimer
            if (bloc[0].getCoupes().isEmpty()) {
                solutionTemporaire.remove(rand[0]);
            }

            // Reduction de la solution
            solutionTemporaire.reduire();

            // Si la nouvelle solution est moins bonne que l'ancienne, reprendre
            if (solutionBackup.getPerte() < solutionTemporaire.getPerte()) {
                solutionTemporaire = solutionBackup;
                essai++;
                continue;
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
