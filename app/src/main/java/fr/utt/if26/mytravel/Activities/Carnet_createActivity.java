package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.CarnetDAO;
import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.R;

public class Carnet_createActivity extends MenuHeader {
    private Bdd database;
    private CarnetDAO cdao;
    private EditText layout_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carnet_create1);
        database = new Bdd(this);
        cdao = new CarnetDAO(database);

        layout_name = (EditText) findViewById(R.id.carnet_name);
        Button layout_saveButton = (Button) findViewById(R.id.carnet_saveButton);

        layout_saveButton.setOnClickListener(save_action);
    }

    private View.OnClickListener save_action = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = layout_name.getText().toString();
            Carnet carnet = new Carnet(name);

            cdao.insertRow(carnet);
            Intent carnet_listIntent = new Intent(Carnet_createActivity.this, Carnet_listActivity.class);
            startActivity(carnet_listIntent);
        }
    };

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}