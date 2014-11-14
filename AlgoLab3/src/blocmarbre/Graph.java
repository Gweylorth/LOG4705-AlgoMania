/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * Graph est la classe implementant un graphe
 *
 * @author Eric
 * @version 1.0
 */
public class Graph {

    /**
     * Le nombre de sommets
     */
    private int nbSommet;
    /**
     * La largeur du graphe
     */
    private int largeur;
    /**
     * L'ensemble des arcs
     */
    private ArrayList<int[]> arcs;
    /**
     * L'ensemble des sommets valides
     */
    private ArrayList<Boolean> sommetsValides;

    /**
     * Constructeur par defaut d'un graphe
     */
    public Graph() {
        nbSommet = 0;
        largeur = 0;
        arcs = new ArrayList<>();
        arcs.add(new int[2]);
        sommetsValides = new ArrayList<>();
    }

    /**
     * Affiche les valeurs du graphe.
     */
    public void afficher() {
        // Affiche le nombre de sommets du graphe
        System.out.println("Nombre de sommets : " + nbSommet);
        // Affiche la largeur du graphe
        System.out.println("Largeur : " + largeur);
        // Affiche le nombre d'arcs
        System.out.println("Nombre d'arcs : " + arcs.size());
        // Affiche successivement les arcs
        for (int[] arc : arcs) {
            System.out.print(arc[0] + "\t" + arc[1]);
            // Arc suivant
            System.out.println();
        }
    }

    /**
     * Cree un graphe a partir de valeurs importees d'un fichier.
     *
     * @param chemin Le chemin du fichier
     * @throws FileNotFoundException Si le fichier n'est pas trouve, la matrice
     *                               n'est pas creee.
     */
    public void importer(String chemin) throws FileNotFoundException {
        // Creation du fichier
        File fichier = new File(chemin);
        // Ouverture d'un scanner a partir du fichier
        try (Scanner scanner = new Scanner(fichier)) {
            // Recuperation de la largeur
            String nomFichier = fichier.getName().replaceAll("[^0-9]+", " ");
            largeur = Integer.parseInt(Arrays.asList(nomFichier.trim().split(" ")).get(1));

            // Recuperation du nombre de sommets
            if (scanner.hasNext()) {
                nbSommet = scanner.nextInt();
            } else {
                return;
            }

            // Activation des sommets
            for (int i = 0; i < nbSommet; i++) {
                sommetsValides.add(true);
            }

            // Recuperation du nombres d'arcs
            if (scanner.hasNext()) {
                int maxArcs = scanner.nextInt();
                for (int i = 1; i < maxArcs; i++) {
                    arcs.add(new int[2]);
                }
            } else {
                return;
            }

            // Remplissage des arcs avec les valeurs contenues dans le fichier
            for (int[] arc : arcs) {
                if (scanner.hasNext()) {
                    arc[0] = scanner.nextInt();
                } else {
                    // A la fin du fichier, sortie
                    return;
                }
                if (scanner.hasNext()) {
                    arc[1] = scanner.nextInt();
                } else {
                    // A la fin du fichier, sortie
                    return;
                }
            }
        } // Si le fichier n'est pas trouve, renvoie l'erreur. // Si le fichier n'est pas trouve, renvoie l'erreur. // Si le fichier n'est pas trouve, renvoie l'erreur. // Si le fichier n'est pas trouve, renvoie l'erreur.
        catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Initialise les sommets du graphe
     */
    public void initialisation() {
        // Activation des sommets
        for (int i = 0; i < nbSommet; i++) {
            sommetsValides.set(i, true);
        }
    }

    /**
     * Recupere le nombre de sommets du graphe.
     *
     * @return le nombre de sommet du graphe
     */
    public int getNbSommet() {
        return nbSommet;
    }

    /**
     * Modifie le nombre de sommets du graphe.
     *
     * @param nbSommet le nouveau nombre de sommets du graphe
     */
    public void setNbSommet(int nbSommet) {
        this.nbSommet = nbSommet;
    }

    /**
     * Recupere la largeur du graphe.
     *
     * @return la largeur du graphe
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Modifie la largeur du graphe.
     *
     * @param largeur la nouvelle largeur du graphe
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    /**
     * Recupere l'ensemble des arcs.
     *
     * @return l'ensemble des arcs
     */
    public ArrayList<int[]> getArcs() {
        return arcs;
    }

    /**
     * Modifie l'ensemble d'arcs.
     *
     * @param arcs Le nouvel ensemble d'arcs
     */
    public void setArcs(ArrayList<int[]> arcs) {
        this.arcs = arcs;
    }

    /**
     * Recupere l'ensemble des sommets valides
     *
     * @return l'ensembles des sommets valides
     */
    public ArrayList<Boolean> getSommetsValides() {
        return sommetsValides;
    }

    /**
     * Modifie l'ensemble des sommets valides.
     *
     * @param sommetsValides
     */
    public void setSommetsValides(ArrayList<Boolean> sommetsValides) {
        this.sommetsValides = sommetsValides;
    }

}
