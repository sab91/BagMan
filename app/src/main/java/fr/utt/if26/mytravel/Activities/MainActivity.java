package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.Model.Page;

import fr.utt.if26.mytravel.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean first_run = true;
    private Bdd database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // ==== Exemples pour interagire avec la base de données simplement
        // initialisation/creation de la bdd
        database = new Bdd(this);

        // instanciation d'un Objet PageDao pour interagir avec la table Page
        PageDAO pdao = new PageDAO(database);

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
