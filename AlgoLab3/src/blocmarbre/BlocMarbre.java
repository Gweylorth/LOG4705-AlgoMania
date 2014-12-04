/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

import java.io.FileNotFoundException;
import static blocmarbre.Affichage.*;

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
            System.out.println("Not enough args.");
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
            AmeliorationLocale amelioration = new AmeliorationLocale(vorace);
            solutionTemporaire = amelioration.ameliorer(solutionTemporaire);
            temps.arreter();

            if (solutionTemporaire.getPerte() < optimum.getPerte()) {
                optimum = solutionTemporaire;
                afficher(optimum, temps, "Reduit");
            }

            temps.reset();
        }
    }

}
