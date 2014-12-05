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
     * Le marbre original
     */
    private Marbre marbre;

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
     * La liste des coupes
     */
    private ArrayList<Integer> coupes;
    /**
     * La liste des classes
     */
    private ArrayList<Integer> classes;

    /**
     * Le nombre de classes maximum
     */
    final private int nbClassesMax = 3;

    /**
     *
     * Constructeur public d'un bloc vide
     *
     * @param marbre   l'ensmeble des donnees
     * @param numero   le numero du bloc
     * @param capacite la capacite du bloc
     */
    public Bloc(Marbre marbre, int numero, int capacite) {
        this.numero = numero;
        this.capacite = capacite;
        this.perte = capacite;
        this.marbre = marbre;
        this.coupes = new ArrayList<>();
        this.classes = new ArrayList<>();
    }

    /**
     * Constructeur public vide d'un bloc par defaut
     *
     * @param marbre l'ensemble des donnees
     */
    public Bloc(Marbre marbre) {
        this(marbre, 0, 0);
    }

    /**
     * Constructeur par deep copy
     *
     * @param origine
     */
    public Bloc(Bloc origine) {
        this(origine.getMarbre(), origine.getNumero(), origine.getCapacite());
        this.perte = origine.getPerte();
        this.coupes = new ArrayList<>(origine.getCoupes());
        this.classes = new ArrayList<>(origine.getClasses());
    }

    /**
     * Reduit la taille du bloc si possible
     */
    public void reduire() {
        if (numero == 0) {
            return;
        }
        // Initialisation des parametres
        int index = numero;
        int j = 1;
        int difference = 0;
        boolean reduction = false;

        // Tant que la perte est superieure a la difference, reduire le bloc
        while (perte > marbre.getCapaciteBlocs()[index] - marbre.getCapaciteBlocs()[index - j]) {
            reduction = true;
            difference = marbre.getCapaciteBlocs()[index] - marbre.getCapaciteBlocs()[index - j];
            if (index - j < 1) {
                break;
            }
            j++;
        }

        if (reduction) {
            // MAJ des parametres
            perte -= difference;
            numero = index - j;
            capacite = marbre.getCapaciteBlocs()[numero];
        }
    }

    /**
     *
     * Ajoute une coupe au bloc.
     *
     * @param numeroCoupe le numero de la coupe
     * @return si la coupe a ete ajoutee au bloc
     */
    public boolean ajoutCoupe(int numeroCoupe) {
        // Recupere la longueur et la classe de la coupe
        Integer longueurCoupe = this.marbre.getCoupes()[numeroCoupe][0];
        Integer classeCoupe = this.marbre.getCoupes()[numeroCoupe][1];

        // Si la coupe ne peut pas etre ajoutee, abandonner
        if (this.classes.size() >= nbClassesMax || longueurCoupe > this.perte) {
            return false;
        }

        // Mise a jour du bloc
        this.perte -= longueurCoupe;
        this.coupes.add(numeroCoupe);
        // Mettre a jour les classes
        if (!classes.contains(classeCoupe)) {
            classes.add(classeCoupe);
        }

        // Renvoie que l'ajout s'est bien effectue
        return true;
    }

    public boolean retraitCoupe(Integer numeroCoupe) {
        // Si la coupe n'est pas presente dans le bloc, abandonner
        if (this.coupes.isEmpty() || !this.coupes.contains(numeroCoupe)) {
            return false;
        }

        // Recupere la longueur et la classe de la coupe
        Integer longueurCoupe = this.marbre.getCoupes()[numeroCoupe][0];
        Integer classeCoupe = this.marbre.getCoupes()[numeroCoupe][1];

        // Mise a jour du bloc
        this.perte += longueurCoupe;
        this.coupes.remove(numeroCoupe);

        // Si cette coupe est la seule de sa classe appliquee a ce bloc, retirer la classe
        boolean doitRetirerClasse = true;
        for (Integer c : this.coupes) {
            Integer classe = this.marbre.getCoupes()[c][1];
            if (classe.equals(classeCoupe)) {
                doitRetirerClasse = false;
                break;
            }
        }
        if (doitRetirerClasse) {
            this.classes.remove(classeCoupe);
        }

        // Renvoie que le retrait s'est bien effectue
        return true;
    }

    /**
     *
     * Recupere l'ensemble de donnees
     *
     * @return l'ensemble de donnees
     */
    public Marbre getMarbre() {
        return marbre;
    }

    /**
     *
     * Modifie l'ensemble de donnees
     *
     * @param marbre le nouvel ensemble de donnees
     */
    public void setMarbre(Marbre marbre) {
        this.marbre = marbre;
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
        return this.coupes.size();
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

    /**
     *
     * Recupere le nombre de classes assignees au bloc.
     *
     * @return le nombre de classes assignees
     */
    public int getNbClassesAssignees() {
        return this.classes.size();
    }

    /**
     *
     * Recupere la liste de classes assignees au bloc.
     *
     * @return la liste de classes assignees
     */
    public ArrayList<Integer> getClasses() {
        return classes;
    }

    /**
     *
     * Modifie la liste de classes assignees au bloc.
     *
     * @param classes la nouvelle liste de classes assignees
     */
    public void setClasses(ArrayList<Integer> classes) {
        this.classes = classes;
    }

}
