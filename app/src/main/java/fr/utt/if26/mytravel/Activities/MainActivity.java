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
    private Bdd database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // ==== Exemples pour interagire avec la base de données simplement
        // initialisation/creation de la bdd
        database = new Bdd(this);


        // Button for seeing pages
        Button page_listButton = (Button)findViewById(R.id.page_listButton);
        page_listButton.setOnClickListener(this);


        // Button for seeing carnets
        Button carnet_listbtn = (Button)findViewById(R.id.carnet_listButton);
        carnet_listbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.carnet_listButton :
                Class carnet_listActivityClass = Carnet_listActivity.class;
                Intent carnet_listIntent = new Intent(MainActivity.this, carnet_listActivityClass);
                startActivity(carnet_listIntent);
                break;
            case R.id.page_listButton :
                Class page_listActivityClass =Page_listActivity.class;
                Intent page_listIntent = new Intent(MainActivity.this, page_listActivityClass);
                startActivity(page_listIntent);
                break;
        }

    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}
