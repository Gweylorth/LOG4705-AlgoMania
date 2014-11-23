/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * Marbre est la classe implementant un marbre.
 *
 * @author Eric
 * @version 1.0
 */
public class Marbre {

    /**
     * Le nombre de types de blocs
     */
    private int nbTypesBlocs;
    /**
     * Le nombre de classes
     */
    private int nbClasses;
    /**
     * Le nombre de coupes
     */
    private int nbCoupes;
    /**
     * Le tableau de capacité des blocs
     */
    private int[] capaciteBlocs;
    /**
     * Le tableau des coupes avec longueur et classe
     */
    private int[][] coupes;

    public Marbre() {
        nbTypesBlocs = 0;
        nbClasses = 0;
        nbCoupes = 0;
        capaciteBlocs = new int[0];
        coupes = new int[0][2];
    }

    /**
     * Affiche les valeurs du marbre.
     */
    public void afficher() {
        // Affiche le nombre de types de blocs
        System.out.println("Nombre de types de blocs : " + nbTypesBlocs);
        // Affiche le nombre de classes
        System.out.println("Nombre de classes : " + nbClasses);
        // Affiche le nombre de coupes
        System.out.println("Nombre de classes : " + nbCoupes);

        // Affiche successivement les capacites des blocs
        System.out.print("Capacite des blocs : ");
        for (int capaciteBloc : capaciteBlocs) {
            System.out.print(capaciteBloc + " ");
        }

        System.out.println();

        // Affiche successivement les coupes
        System.out.println("Coupes");
        System.out.println("L\tC");
        for (int[] coupe : coupes) {
            System.out.print(coupe[0] + "\t" + coupe[1]);
            // Coupe suivante
            System.out.println();
        }
    }

    /**
     * Cree un marbre a partir de valeurs importees d'un fichier.
     *
     * @param chemin le chemin du fichier
     * @throws FileNotFoundException Si le fichier n'est pas trouve.
     */
    public void importer(String chemin) throws FileNotFoundException {
        // Creation du fichier
        File fichier = new File(chemin);
        // Ouverture d'un scanner a partir du fichier
        try (Scanner scanner = new Scanner(fichier)) {
            // Recuperation du nombre de types de blocs
            if (scanner.hasNext()) {
                nbTypesBlocs = scanner.nextInt();
                capaciteBlocs = new int[nbTypesBlocs];
            } else {
                return;
            }

            // Remplissage du tableau de capacite des blocs
            for (int i = 0; i < nbTypesBlocs; i++) {
                if (scanner.hasNext()) {
                    capaciteBlocs[i] = scanner.nextInt();
                } else {
                    return;
                }
            }

            // Recuperation du nombre de classes
            if (scanner.hasNext()) {
                nbClasses = scanner.nextInt();
            } else {
                return;
            }

            // Recuperation du nombre de coupes
            if (scanner.hasNext()) {
                nbCoupes = scanner.nextInt();
                coupes = new int[nbCoupes][2];
            } else {
                return;
            }

            // Remplissage du tableau des coupes avec longueur et classe
            for (int i = 0; i < nbCoupes; i++) {
                if (scanner.hasNext()) {
                    coupes[i][0] = scanner.nextInt();
                } else {
                    return;
                }
                if (scanner.hasNext()) {
                    coupes[i][1] = scanner.nextInt();
                } else {
                    return;
                }
            }
        } // Si le fichier n'est pas trouve, renvoie l'erreur. // Si le fichier n'est pas trouve, renvoie l'erreur. // Si le fichier n'est pas trouve, renvoie l'erreur. // Si le fichier n'est pas trouve, renvoie l'erreur.
        catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Recupere le nombre de types de blocs.
     *
     * @return le nombre de types de blocs
     */
    public int getNbTypesBlocs() {
        return nbTypesBlocs;
    }

    /**
     * Modifie le nombre de types de blocs.
     *
     * @param nbTypesBlocs le nouveau nombre de types de blocs
     */
    public void setNbTypesBlocs(int nbTypesBlocs) {
        this.nbTypesBlocs = nbTypesBlocs;
    }

    /**
     * Recupere le nombre de classes.
     *
     * @return le nombre de classes
     */
    public int getNbClasses() {
        return nbClasses;
    }

    /**
     * Modifie le nombre de classes.
     *
     * @param nbClasses le nouveau nombre de classes
     */
    public void setNbClasses(int nbClasses) {
        this.nbClasses = nbClasses;
    }

    /**
     * Recupere le nombre de coupes.
     *
     * @return le nombre de coupes
     */
    public int getNbCoupes() {
        return nbCoupes;
    }

    /**
     * Modifie le nombre de coupes.
     *
     * @param nbCoupes le nouveau nombre de coupes
     */
    public void setNbCoupes(int nbCoupes) {
        this.nbCoupes = nbCoupes;
    }

    /**
     * Recupere le tableau de capacite des blocs.
     *
     * @return le tableau de capacite des blocs
     */
    public int[] getCapaciteBlocs() {
        return capaciteBlocs;
    }

    /**
     * Modifie le tableau de capacite des blocs.
     *
     * @param capaciteBlocs le nouveau tableau de capacite des blocs
     */
    public void setCapaciteBlocs(int[] capaciteBlocs) {
        this.capaciteBlocs = capaciteBlocs;
    }

    /**
     * Recupere le tableau des coupes avec longueur et classe.
     *
     * @return le tableau des coupes avec longueur et classe
     */
    public int[][] getCoupes() {
        return coupes;
    }

    /**
     * Modifie le tableau des coupes avec longueur et classe.
     *
     * @param coupes le nouveau tableau des coupes avec longueur et classe
     */
    public void setCoupes(int[][] coupes) {
        this.coupes = coupes;
    }

}
