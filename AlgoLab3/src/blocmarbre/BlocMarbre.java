/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

import java.io.FileNotFoundException;

/**
 *
 * @author Eric
 * @version 1.0
 */
public class BlocMarbre {

    /**
     * Main de l'application
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException Si le fichier n'est pas trouve.
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Recuperation des arguments
        if (args.length < 3) {
            return;
        }

        int i = 0;
        while (!"-f".equals(args[i])) {
            i++;
            if (i >= args.length - 2) {
                return;
            }
        }
        // Chemin vers le fichier
        String chemin = args[i + 1];

        boolean print = false;
        int j = 0;
        while (!"-p".equals(args[j])) {
            j++;
            if (j >= args.length) {
                break;
            }
        }
        if (j < args.length) {
            print = true;
        }

        // Creation du marbre
        Marbre marbre = new Marbre();

        // Importation du fichier
        marbre.importer(chemin);

        // Resolution du probleme
        resolutionProbleme(marbre);
    }

    /**
     *
     * Resout le probleme d'optimisation
     *
     * @param marbre l'ensemble de donnees
     */
    public static void resolutionProbleme(Marbre marbre) {
        Chrono temps = new Chrono();
        Solution optimum = new Solution();

        while (true) {
            temps.demarrer();
            // Vorace ramdomise
            Vorace vorace = new Vorace(marbre);
            vorace.traiter();

            Solution solutionTemporaire = vorace.getSolution();

            // Amelioration locale
            // AmeliorationLocale amelioration = new AmeliorationLocale(vorace);
            // Solution solutionTemporaire = amelioration.ameliorer();
            temps.arreter();

            if (solutionTemporaire.getPerte() < optimum.getPerte()) {
                optimum = solutionTemporaire;
                afficher(optimum, temps);
                // afficherLongueur(optimum, temps);
                // afficherPerte(optimum, temps);
            }

            temps.reset();
        }
    }

    /**
     *
     * Affiche la solution
     *
     * @param solution la solution
     * @param temps    le chrono
     */
    public static void afficher(Solution solution, Chrono temps) {
        // Premiere ligne
        System.out.print(solution.getPerte());
        System.out.print(" ");
        System.out.print(temps.getTemps());
        System.out.print(" ");
        System.out.print(solution.size());
        System.out.println();

        // Lignes suivantes
        for (Bloc bloc : solution) {
            System.out.print(bloc.getNumero());
            System.out.print(" ");
            System.out.print(bloc.getNbCoupesAssignees());
            // Coupes
            for (int coupe : bloc.getCoupes()) {
                System.out.print(" ");
                System.out.print(coupe);
            }
            System.out.println();
        }

        System.out.println();
    }

    /**
     *
     * Affiche la solution en fonction des longueurs de coupes
     *
     * @param solution la solution
     * @param temps    le chrono
     */
    public static void afficherLongueur(Solution solution, Chrono temps) {
        // Premiere ligne
        System.out.print(solution.getPerte());
        System.out.print(" ");
        System.out.print(temps.getTemps());
        System.out.print(" ");
        System.out.print(solution.size());
        System.out.println();

        // Lignes suivantes
        for (Bloc bloc : solution) {
            System.out.print(bloc.getNumero());
            System.out.print(" ");
            System.out.print(bloc.getNbCoupesAssignees());
            // Coupes
            for (int coupe : bloc.getCoupes()) {
                System.out.print(" ");
                System.out.print(bloc.getMarbre().getCoupes()[coupe][0]);
            }
            System.out.println();
        }

        System.out.println();
    }

    /**
     *
     * Affiche la solution en fonction des pertes
     *
     * @param solution la solution
     * @param temps    le chrono
     */
    public static void afficherPerte(Solution solution, Chrono temps) {
        // Premiere ligne
        System.out.print(solution.getPerte());
        System.out.print(" ");
        System.out.print(temps.getTemps());
        System.out.print(" ");
        System.out.print(solution.size());
        System.out.println();

        // Lignes suivantes
        for (Bloc bloc : solution) {
            System.out.print(bloc.getNumero());
            System.out.print(" ");
            System.out.print(bloc.getNbCoupesAssignees());
            System.out.print(" ");
            System.out.print(bloc.getPerte());
            System.out.println();
        }

        System.out.println();
    }
}
