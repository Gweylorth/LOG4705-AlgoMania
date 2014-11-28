/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

import java.util.ArrayList;

/**
 *
 * Bloc est la classe implémentant l'utilisation d'un bloc.
 *
 * @author Eric
 * @version 1.0
 */
public class Bloc {

    /**
     * Le numero du bloc
     */
    private int numero;
    /**
     * La capacite du bloc
     */
    private int capacite;
    /**
     * La perte du bloc
     */
    private int perte;
    /**
     * Le nombres de coupes assignees
     */
    private int nbCoupesAssignees;
    /**
     * La liste des coupes
     */
    private ArrayList<Integer> coupes;

    /**
     * Constructeur public vide d'un bloc par defaut
     */
    public Bloc() {
        this.numero = 0;
        this.capacite = 0;
        this.perte = 0;
        this.nbCoupesAssignees = 0;
        this.coupes = new ArrayList<>();
    }

    /**
     * Constructeur public d'un bloc vide
     *
     * @param numero   le numero du bloc
     * @param capacite la capacite du bloc
     */
    public Bloc(int numero, int capacite) {
        this.numero = numero;
        this.capacite = capacite;
        this.perte = capacite;
        this.nbCoupesAssignees = 0;
        this.coupes = new ArrayList<>();
    }

    public void ajoutCoupe(int numeroCoupe, int longueurCoupe) {
        if (longueurCoupe <= this.perte) {
            this.perte -= longueurCoupe;
            this.nbCoupesAssignees++;
            this.coupes.add(numeroCoupe);
        }
    }

    /**
     *
     * Recupere le numero du bloc.
     *
     * @return le numero du bloc
     */
    public int getNumero() {
        return numero;
    }

    /**
     *
     * Modifie le numero du bloc.
     *
     * @param numero le nouveau numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     *
     * Recupere la capacite du bloc.
     *
     * @return la capacite du bloc
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     *
     * Modifie la capacite du bloc.
     *
     * @param capacite la nouvelle capacite du bloc
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    /**
     *
     * Recupere la perte du bloc.
     *
     * @return la perte du bloc
     */
    public int getPerte() {
        return perte;
    }

    /**
     *
     * Modifie la perte du bloc.
     *
     * @param perte la nouvelle perte du bloc
     */
    public void setPerte(int perte) {
        this.perte = perte;
    }

    /**
     *
     * Recupere le nombre de coupes assignees au bloc.
     *
     * @return le nombre de coupes assignees
     */
    public int getNbCoupesAssignees() {
        return nbCoupesAssignees;
    }

    /**
     *
     * Modifie le nombre de coupes assignees au bloc.
     *
     * @param nbCoupesAssignees le nouveau nombre de coupes assignees
     */
    public void setNbCoupesAssignees(int nbCoupesAssignees) {
        this.nbCoupesAssignees = nbCoupesAssignees;
    }

    /**
     *
     * Recupere la liste de coupes assignees au bloc.
     *
     * @return la liste de coupes assignees
     */
    public ArrayList<Integer> getCoupes() {
        return coupes;
    }

    /**
     *
     * Modifie la liste de coupes assignees au bloc.
     *
     * @param coupes la nouvelle liste de coupes assignees
     */
    public void setCoupes(ArrayList<Integer> coupes) {
        this.coupes = coupes;
    }

}
