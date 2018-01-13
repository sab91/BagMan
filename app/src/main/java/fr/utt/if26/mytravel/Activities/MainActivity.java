package fr.utt.if26.mytravel.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

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
    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button login;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // ==== Exemples pour interagire avec la base de données simplement
        // initialisation/creation de la bdd
        database = new Bdd(this);

        // Text enter in text field in log in page
        Email = (EditText) findViewById(R.id.login_button);
        Password = (EditText) findViewById(R.id.password_login);

        // Button to log in
        login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(this);


        // Button for seeing carnets
        Button carnet_listbtn = (Button)findViewById(R.id.carnet_listButton);
        carnet_listbtn.setOnClickListener(this);

    }

    private void validate(String userEmail, String userPassword) {

        if ((userEmail == "mail") && (userPassword == "mm")) {
            Intent intent = new Intent(MainActivity.this, Carnet_listActivity.class);
        }
    }

    @Override
    public void onClick(View v) {


//        switch (v.getId()) {
//            case R.id.carnet_listButton :
//                Class carnet_listActivityClass = Carnet_listActivity.class;
//                Intent carnet_listIntent = new Intent(MainActivity.this, carnet_listActivityClass);
//                startActivity(carnet_listIntent);
//                break;
//
//        }

    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}
