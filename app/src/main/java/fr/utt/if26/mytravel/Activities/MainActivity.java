package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.CarnetDAO;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.Model.Page;

import fr.utt.if26.mytravel.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean first_run = true;
    private Bdd database;

    public void display(ArrayList ps) {
        Iterator<Carnet> it = ps.iterator();
        String sb = "";
        while(it.hasNext()) {
            Carnet c = it.next();
            sb = sb + " " + c.toString() + "\n";
        }
        Log.e("====", sb);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // ==== Exemples pour interagire avec la base de données simplement
        // initialisation/creation de la bdd
        database = new Bdd(this);

        // instanciation d'un Objet PageDao pour interagir avec la table Page
        PageDAO pdao = new PageDAO(database);
        /*CarnetDAO cdao = new CarnetDAO(database);

        Carnet c = new Carnet("test");
        Carnet c2 = new Carnet("ctest2");

        c.setId(cdao.insertRow(c));
        c2.setId(cdao.insertRow(c2));

        Log.e("test", c.toString());
        Log.e("test", c2.toString());

        ArrayList<String> aa = cdao.getList();
        display(aa);*/



        if(first_run) {
            deleteDatabase(Bdd.DATABASE_NAME);
            // Création d'objets Page
            Page t = new Page("TitleTest", "ContentTest","SummaryTest");
            Page t2 = new Page("Title2", "ContentTest2","SummaryTest2");
            Page t3 = new Page("Title3", "ContentTest3","Summary3");

            // INSERTION d'une page dans la table Page
            // Idée de récupéré l'id que lui a attribué la base de données pour completer l'objet Page
            // precedemment construit
            t.setId(pdao.insertRow(t));
            t2.setId(pdao.insertRow(t2));
            t3.setId(pdao.insertRow(t3));

            first_run = false;
        }

        Button page_listButton = (Button)findViewById(R.id.page_listButton);
        page_listButton.setOnClickListener(this);

        CarnetDAO cdao = new CarnetDAO(database);

        Carnet c = new Carnet("test");
        Carnet c2 = new Carnet("ctest2");

        c.setId(cdao.insertRow(c));
        c2.setId(cdao.insertRow(c2));

        Log.e("test", c.toString());
        Log.e("test", c2.toString());

        ArrayList<String> aa = cdao.getList();
        display(aa);

    }

    @Override
    public void onClick(View v) {
        Class page_listActivityClass =Page_listActivity.class;
        Intent page_listIntent = new Intent(MainActivity.this, page_listActivityClass);
        startActivity(page_listIntent);
    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}
