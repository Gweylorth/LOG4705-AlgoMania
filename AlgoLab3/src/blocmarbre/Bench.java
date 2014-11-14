/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * Fonctions de tests
 *
 * @author Eric
 * @version 1.0
 */
public class Bench {

    /**
     *
     * Fonction de benchtest
     *
     * @param cheminDossier le chemin vers le dossier de donnees
     * @throws FileNotFoundException si le fichier n'est pas trouve
     */
    public static void Benchtest(String cheminDossier) throws FileNotFoundException {
        // Initialisation de la liste de fichiers
        File dossier = new File(cheminDossier);
        ArrayList<String> listeFichiers = new ArrayList<>();

        // Recuperation de tous les fichiers
        for (File fichier : dossier.listFiles()) {
            if (!fichier.isDirectory()) {
                listeFichiers.add(fichier.getName());
            }
        }
        // Rangement alphabetique
        Collections.sort(listeFichiers);

    }
}
