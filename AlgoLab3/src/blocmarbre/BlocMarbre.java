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

        // Benchtest
        // marbre.afficher();
        // Algorithme vorace
        Vorace algoVorace = new Vorace(marbre);
        algoVorace.traiter();
        algoVorace.afficher();
    }
}
