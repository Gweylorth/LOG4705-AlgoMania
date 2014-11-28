/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

import java.util.ArrayList;

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
     * La liste des blocs
     */
    private ArrayList<Bloc> listeBlocs;

    /**
     * Le chrono
     */
    private Chrono temps;

    /**
     *
     * Constructeur public de l'objet Vorace.
     *
     * @param marbre l'objet Marbre
     */
    public Vorace(Marbre marbre) {
        this.marbre = marbre;
        this.listeBlocs = new ArrayList<>();
        this.temps = new Chrono();
    }

    /**
     * Affiche les resultats.
     */
    public void afficher() {
        // Calcul des pertes
        int perte = 0;
        for (Bloc listeBloc : listeBlocs) {
            perte += listeBloc.getPerte();
        }

        // Premiere ligne
        System.out.print(perte);
        System.out.print(" ");
        System.out.print(temps.getTemps());
        System.out.print(" ");
        System.out.print(listeBlocs.size());
        System.out.println();

        // Lignes suivantes
        for (Bloc listeBloc : listeBlocs) {
            System.out.print(listeBloc.getNumero());
            System.out.print(" ");
            System.out.print(listeBloc.getNbCoupesAssignees());
            // Coupes
            for (int coupe : listeBloc.getCoupes()) {
                System.out.print(" ");
                System.out.print(coupe);
            }
            System.out.println();
        }
    }

    /**
     * Traite l'ensemble des donnees.
     */
    public void traiter() {
        // Demarrage du chrono
        temps.demarrer();

        // Arret du chrono
        temps.arreter();
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
     * Recupere la liste des blocs.
     *
     * @return la liste des blocs
     */
    public ArrayList<Bloc> getListeBlocs() {
        return listeBlocs;
    }

    /**
     *
     * Modifie la liste des blocs
     *
     * @param listeBlocs la nouvelle liste des blocs
     */
    public void setListeBlocs(ArrayList<Bloc> listeBlocs) {
        this.listeBlocs = listeBlocs;
    }

    /**
     *
     * Recupere le chrono
     *
     * @return le chrono
     */
    public Chrono getTemps() {
        return temps;
    }

    /**
     *
     * Modifie le chrono
     *
     * @param temps le nouveau chrono
     */
    public void setTemps(Chrono temps) {
        this.temps = temps;
    }
}
