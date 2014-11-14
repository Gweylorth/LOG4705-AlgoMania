/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocmarbre;

/**
 *
 * Chrono est la classe implementant un chronometre pour mesurer le temps
 * d'execution.
 *
 * @author Eric
 * @version 1.0
 */
public class Chrono {

    /**
     * Le nombre de ms enregistrees
     */
    private long temps;
    /**
     * Le temps de depart du chronometre
     */
    private long memoire;

    /**
     * Constructeur par defaut d'un chronometre
     */
    public Chrono() {
        this.temps = 0;
        this.memoire = 0;
    }

    /**
     * Demarre le chronometre.
     */
    public void demarrer() {
        memoire = java.lang.System.nanoTime();
    }

    /**
     * Reinitialise le chronometre.
     */
    public void reset() {
        this.temps = 0;
        this.memoire = 0;
    }

    /**
     * Arrete le chronometre.
     */
    public void arreter() {
        this.temps = java.lang.System.nanoTime() - memoire;
        this.memoire = 0;
    }

    /**
     * Affiche le temps du chronometre.
     */
    public void afficher() {
        System.out.println("Temps (en ns) : " + temps);
    }

    /**
     * Recupere le temps du chronometre.
     *
     * @return le temps du chronometre
     */
    public long getTemps() {
        return temps;
    }

    /**
     * Recupere l'instant ou le chronometre a demarre.
     *
     * @return l'instant ou le chronometre a demarre
     */
    public long getMemoire() {
        return memoire;
    }

    /**
     * Modifie le temps contenu dans le chronometre.
     *
     * @param temps Le nouveau temps contenu dans le chronometre
     */
    public void setTemps(long temps) {
        this.temps = temps;
    }

    /**
     * Modifie l'instant ou le chronometre a demarre.
     *
     * @param memoire Le nouvel instant ou le chronometre a demarre
     */
    public void setMemoire(long memoire) {
        this.memoire = memoire;
    }
}
