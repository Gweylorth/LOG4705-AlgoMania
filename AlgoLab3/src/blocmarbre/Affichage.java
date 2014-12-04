/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

/**
 *
 * Affichage est la classe contenant toutes les fonctions d'affichage de
 * solutions
 *
 * @author Eric
 * @version 1.0
 */
public class Affichage {

    /**
     *
     * Affiche la solution suivant la maniere demandee.
     *
     * @param solution la solution
     * @param temps    le chrono
     * @param champ    la maniere d'afficher
     */
    public static void afficher(Solution solution, Chrono temps, String champ) {
        switch (champ) {
            case "Propre":
                afficherPropre(solution, temps);
                break;
            case "Longueur":
                afficherLongueur(solution, temps);
                break;
            case "Perte":
                afficherPerte(solution, temps);
                break;
            case "Reduit":
                afficherReduit(solution, temps);
                break;
            default:
                afficherCorrect(solution, temps);
                break;
        }
    }

    /**
     *
     * Affiche la solution comme dit dans l'enonce.
     *
     * @param solution la solution
     * @param temps    le chrono
     */
    public static void afficherCorrect(Solution solution, Chrono temps) {
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
     * Affiche la solution reduite.
     *
     * @param solution la solution
     * @param temps    le chrono
     */
    public static void afficherReduit(Solution solution, Chrono temps) {
        // Premiere ligne
        System.out.print(solution.getPerte());
        System.out.print(" ");
        System.out.print(temps.getTemps());
        System.out.print(" ");
        System.out.print(solution.size());
        System.out.println();
    }

    /**
     *
     * Affiche la solution proprement.
     *
     * @param solution la solution
     * @param temps    le chrono
     */
    public static void afficherPropre(Solution solution, Chrono temps) {
        // Premiere ligne
        System.out.print("Perte : " + solution.getPerte() + " / ");
        System.out.print("Temps : " + temps.getTemps() + " / ");
        System.out.print("Taille : " + solution.size());
        System.out.println();

        // Lignes suivantes
        for (Bloc bloc : solution) {
            System.out.print("\t");
            System.out.println("Bloc " + bloc.getNumero() + " : " + bloc.getNbCoupesAssignees() + " coupe(s)");
            System.out.print("\t\t");
            // Coupes
            for (int coupe : bloc.getCoupes()) {
                System.out.print(coupe + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    /**
     *
     * Affiche la solution en fonction des longueurs de coupes.
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
     * Affiche la solution en fonction des pertes.
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
