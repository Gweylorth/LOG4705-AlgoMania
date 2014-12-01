/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

import java.util.ArrayList;
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
     * @return une solution vorace
     */
    public Solution traiter() {
        // Initialisation des couts
        ArrayList<Integer> couts = new ArrayList<>();
        for (int[] coupe : marbre.getCoupes()) {
            couts.add(evaluer(coupe));
        }

        // Tant que la solution n'est pas complete
        while (true) {
            // Calcul des bords de la borne
            int coutMin = Collections.min(couts);
            int coutMax = Collections.max(couts);

            // Calcul de la borne
            ArrayList<Integer> RCL = new ArrayList<>();
            double alpha = bornage * randInt(1, 100);
            int borne = (int) (coutMin + alpha * (coutMax - coutMin));

            // Calcul des meilleures coupes
            for (int i = 0; i < couts.size(); i++) {
                if (couts.get(i) <= borne) {
                    RCL.add(i);
                }
            }

            // Choix de la coupe parmi les meilleures coupes
            int choixCoupe = randInt(0, RCL.size());

            // Reponse pour savoir si la coupe a ete ajoutee
            boolean reponse = false;

            // Ajout de la coupe dans un bloc existant
            for (Bloc bloc : solution) {
                reponse = bloc.ajoutCoupe(choixCoupe);
                if (reponse) {
                    break;
                }
            }

            // Si la coupe n'a pas trouve de bloc libre
            if (reponse == false) {
                solution.add(new Bloc(this.marbre));
            }

            // Mettre a jour l'ensemble des coupes
            // Mettre a jour les couts
            break;
        }

        return getSolution();
    }

    /**
     *
     * Evalue le cout d'une coupe.
     *
     * @param coupe la coupe a evaluer
     * @return le cout de la coupe
     */
    private int evaluer(int[] coupe) {
        return 0;
    }

    /**
     *
     * Renvoie un random int dans l'intervale [min, max]
     *
     * @param min la borne min
     * @param max la borne max
     * @return le random int entre min et max
     */
    private int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
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
        return solution;
    }

    /**
     *
     * Modifie la solution.
     *
     * @param solution la nouvelle solution
     */
    public void setSolution(Solution solution) {
        this.solution = solution;
    }

}
