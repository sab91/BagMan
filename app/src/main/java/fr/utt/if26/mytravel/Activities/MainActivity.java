package fr.utt.if26.mytravel.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.Model.Page;

import fr.utt.if26.mytravel.R;

public class MainActivity extends AppCompatActivity {

    private Bdd database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // ==== Exemples pour interagire avec la base de données simplement
        // initialisation/creation de la bdd
        database = new Bdd(this);
        // Suppression de son contenu si il en existe une (Pour tester dans une base de données vierge)
        deleteDatabase(Bdd.DATABASE_NAME);
        // instanciation d'un Objet PageDao pour interagir avec la table Page
        PageDAO pdao = new PageDAO(database);

        // Création d'objets Page
        Page t = new Page("TitleTest", "ContentTest","SummaryTest");
        Page t2 = new Page("Title2", "ContentTest2","SummaryTest2");
        Page t3 = new Page("Title3", "ContentTest3","Summary3");

        // INSERTION d'une page dans la table Page
            // Idée de récupéré l'id que lui a attribué la base de données pour completer l'objet Page
            // precedemment construit
        Log.i("=====", "INSERTION");
        t.setId(pdao.insertRow(t));
        t2.setId(pdao.insertRow(t2));
        t3.setId(pdao.insertRow(t3));
            //fonction d'affichage de liste en debug pour constater les resultats
        display_pages(pdao.getList());

        // SUPPRESSION d'une page de la table
        Log.i("=====", "SUPPRESSION");
        pdao.deleteRow(t3);
        display_pages(pdao.getList());

        // OBTENTION d'une seule page
            // Ici recuperation de la page avec l'id "1" (normalement l'objet correspondant à la variable t)
        Log.i("=====", "GET 1");
        Page p = pdao.getRow(1);
        Log.i("===", p.toString());

        // OBTENTION de la liste des pages
        Log.i("=====", "GET ALL");
        ArrayList<Page> arr_page = pdao.getList();
        display_pages(arr_page);

        // MISE A JOUR d'une page en fonction de son ID
            // Ici le contenu de la table avec precedemment l'objet t est remplacé par l'objet t3
        Log.i("=====", "UPDATE");
        pdao.updateRow(t.getId(), t3);
        display_pages(pdao.getList());

    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }

    /**
     * Affichage d'une liste de page à partir d'un arrayList
     * @param ps
     */
    public void display_pages(ArrayList ps) {
        Iterator<Page> it = ps.iterator();
        String sb = "";
        while(it.hasNext()) {
            Page p = it.next();
            sb = sb + " " + p.toString() + "\n";
        }
        Log.i("===", sb);
    }
}
