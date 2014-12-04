/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * Vorace est la classe implémentant l'algorithme vorace.
 *
 * @author Eric
 * @version 1.0
 */
public class Vorace {

    /**
     * L'objet Marbre contenant toutes les informations
     */
    private Marbre marbre;

    /**
     * La solution
     */
    private Solution solution;

    /**
     * La valeur limite de bornage
     */
    final private double bornage = 0.01;

    /**
     *
     * Constructeur public de l'objet Vorace.
     *
     * @param marbre l'objet Marbre
     */
    public Vorace(Marbre marbre) {
        this.marbre = marbre;
        this.solution = new Solution();
    }

    /**
     * Traite l'ensemble des donnees.
     *
     */
    public void traiter() {
        // Initialisation des couts
        ArrayList<Integer> couts = new ArrayList<>();
        // Deep copie des coupes
        int[][] coupes = copie(marbre.getCoupes());
        // Calcul du cout de chaque coupe
        for (int[] coupe : coupes) {
            couts.add(evaluer(coupe));
        }

        // Tant que la solution n'est pas complete
        int compteur = 0;
        while (compteur < coupes.length) {
            compteur++;
            // Calcul des bords de la borne
            int coutMin = minimum(couts);
            int coutMax = Collections.max(couts);

            // Calcul de la borne
            ArrayList<Integer> RCL = new ArrayList<>();
            double alpha = bornage * randInt(1, 10);
            int borne = (int) (coutMax - alpha * (coutMax - coutMin));

            // Calcul des meilleures coupes
            for (int i = 0; i < couts.size(); i++) {
                int cout = couts.get(i);
                if (cout >= borne && cout > 0) {
                    RCL.add(i);
                }
            }

            // Choix de la coupe parmi les meilleures coupes
            int choixCoupe = randInt(0, RCL.size() - 1);

            // Reponse pour savoir si la coupe a ete ajoutee
            boolean reponse = false;

            // Ajout de la coupe dans un bloc existant
            for (Bloc bloc : solution) {
                reponse = bloc.ajoutCoupe(RCL.get(choixCoupe));
                if (reponse) {
                    break;
                }
            }

            // Si la coupe n'a pas trouve de bloc libre
            if (reponse == false) {
                int[] capacite = this.marbre.getCapaciteBlocs();
                solution.add(new Bloc(this.marbre, capacite.length - 1, capacite[capacite.length - 1]));
                solution.get(solution.size() - 1).ajoutCoupe(RCL.get(choixCoupe));
            }

            // Mettre a jour l'ensemble des coupes
            coupes[RCL.get(choixCoupe)][0] = -1;
            // Mettre a jour les couts
            couts.clear();
            for (int[] coupe : coupes) {
                couts.add(evaluer(coupe));
            }
        }

        // Reduire les blocs
        solution.reduire();
    }

    /**
     *
     * Copie en deep copie un tableau en 2D
     *
     * @param input le tableau a copier
     * @return le tableau copie
     */
    public int[][] copie(int[][] input) {
        int[][] cible = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            cible[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return cible;
    }

    /**
     *
     * Renvoie le minimum positif d'une liste
     *
     * @param couts les differents couts
     * @return le minimum positif
     */
    public int minimum(ArrayList<Integer> couts) {
        int minimum = Collections.max(couts);

        for (int cout : couts) {
            if (minimum > cout && cout > 0) {
                minimum = cout;
            }
        }

        return minimum;
    }

    /**
     *
     * Evalue le cout d'une coupe.
     *
     * @param coupe la coupe a evaluer
     * @return le cout de la coupe
     */
    private int evaluer(int[] coupe) {
        return coupe[0];
    }

    /**
     *
     * Renvoie un random int positif dans l'intervale [min, max]
     *
     * @param min la borne min
     * @param max la borne max
     * @return le random int positif entre min et max
     */
    private int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return Math.max(0, randomNum);
    }

    /**
     *
     * Recupere l'objet Marbre
     *
     * @return l'objet Marbre
     */
    public Marbre getMarbre() {
        return marbre;
    }

    /**
     *
     * Modifie l'objet Marbre
     *
     * @param marbre le nouvel objet Marbre
     */
    public void setMarbre(Marbre marbre) {
        this.marbre = marbre;
    }

    /**
     *
     * Recupere la solution.
     *
     * @return la solution
     */
    public Solution getSolution() {
        return new Solution(solution);
    }

    /**
     *
     * Modifie la solution.
     *
     * @param solution la nouvelle solution
     */
    public void setSolution(Solution solution) {
        this.solution = new Solution(solution);
    }

}
