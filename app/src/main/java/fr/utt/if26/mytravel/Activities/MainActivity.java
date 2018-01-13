package fr.utt.if26.mytravel.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.AuthDAO;
import fr.utt.if26.mytravel.DAO.CarnetDAO;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.Model.Account;
import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.Model.Page;

import fr.utt.if26.mytravel.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Bdd database;
    private EditText Email;
    private EditText Password;
    private Button login;
    private Button register;
    private AuthDAO adao;
    private Account account;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // ==== Exemples pour interagire avec la base de données simplement
        // initialisation/creation de la bdd
        database = new Bdd(this);
        adao = new AuthDAO(database);

        // Text enter in text field in log in page
        Email = (EditText) findViewById(R.id.email_login);
        Password = (EditText) findViewById(R.id.password_login);

        // Button to log in
        login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(this);

        // Button to register
        register = (Button)findViewById(R.id.register_button);
        register.setOnClickListener(this);




    }

    private void validate(String userEmail, String userPassword) {

        ArrayList<Account> aa = new ArrayList<>(adao.getRowByEmail(userEmail));

        if (aa.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Wrong information given", Toast
                    .LENGTH_SHORT);
            toast.show();
        } else {
            System.out.println("mama");
            if (userEmail.equals(aa.get(0).getEmail()) && userPassword.equals(aa.get(0).getPassword())) {
                Toast toast = Toast.makeText(getApplicationContext(), "Authentication Successful",
                        Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(MainActivity.this, Carnet_listActivity.class);
                intent.putExtra("EMAIL_ACCOUNT", Email.getText().toString());
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Wrong password given", Toast
                        .LENGTH_SHORT);
                toast.show();
            }
        }




    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.login_button :
                validate(Email.getText().toString(), Password.getText().toString());
                break;
            case R.id.register_button :
                Intent register_view = new Intent(MainActivity.this, Register_view.class);
                startActivity(register_view);
                break;
        }

    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}
