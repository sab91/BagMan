package fr.utt.if26.mytravel.DAO;

import android.database.Cursor;

import java.util.ArrayList;

import fr.utt.if26.mytravel.Bdd;

public abstract class DAO {
    private String modelName;
    private Bdd db;

    /**
     * Constructeur
     * @param db_pf
     * @param modelName_pf
     */
    public DAO(Bdd db_pf, String modelName_pf) {
        modelName = modelName_pf;
        db = db_pf;
    }

    /**
     * Récuperation de l'ensemble des entités
     * @return la liste des pages
     */
    public abstract ArrayList getList();

    /**
     * Insertion d'un objet
     * @param ob
     * @return le succes (id de l'entité) ou echec (-1)
     */
    public abstract int insertRow(Object ob);

    /**
     * Suppression d'un element (ligne)
     * @param ob
     * Ajouter un return si succes ou echec
     */
    public abstract void deleteRow(Object ob);

    /**
     * Recuperation d'un objet en fonction d'un id
     * @param id id recherché
     * @return une instance de la classe de Modele visé
     */
    public abstract Object getRow(int id);

    /**
     * Mise à jour d'un element en fonction d'un Id
     * Idée de remplacement
     * @param id id de la ligne visée
     * @param ob objet de remplacement
     */
    public abstract void updateRow(int id, Object ob);

    /**
     * Convertisseur de Cursor (ce qu'on recupere lorsque l'on fait
     * une requete dans la BDd) en Objet de la classe désiré
     * @param c_pf le curseur contenant un element
     * @return un objet avec les elements du curseur
     */
    public abstract Object itemToObject(Cursor c_pf);

    /**
     * Vide la table
     * @return
     */
    public boolean emptyTable() {
        try {
            getDb().getWritableDatabase().delete(getModelName(), null, null);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Bdd getDb() {
        return db;
    }

    public void setDb(Bdd db) {
        this.db = db;
    }
}
