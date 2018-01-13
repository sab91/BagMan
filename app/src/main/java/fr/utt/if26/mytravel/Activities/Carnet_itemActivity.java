package fr.utt.if26.mytravel.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.CarnetDAO;
import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.R;

public class Carnet_itemActivity extends MenuHeader {

    private Bdd database;
    private CarnetDAO cdao;
    private int id;
    private EditText layout_name;
    private Button layout_deleteButton;
    private Button layout_updateButton;
    private String current_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carnet_item1);
        database = new Bdd(this);
        current_email = getIntent().getStringExtra("EMAIL_ACCOUNT");
        cdao = new CarnetDAO(database);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        Carnet carnet = (Carnet) cdao.getRow(id);

        layout_name = (EditText) findViewById(R.id.carnet_itemTitle);

        layout_deleteButton = (Button) findViewById(R.id.carnet_deleteButton);
        layout_updateButton = (Button) findViewById(R.id.carnet_updateButton);

        layout_name.setText(carnet.getName());

        layout_deleteButton.setOnClickListener(new Carnet_itemActivity.Carnet_action_delete());
        layout_updateButton.setOnClickListener(new Carnet_itemActivity.Carnet_action_update());
    }

    private class Carnet_action_update implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String name = layout_name.getText().toString();

            Carnet carnet = new Carnet(name, current_email);

            cdao.updateRow(id, carnet);
            Intent carnet_listeIntent = new Intent(Carnet_itemActivity.this, Carnet_listActivity.class);
            startActivity(carnet_listeIntent);
        }
    }

    private class Carnet_action_delete implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Carnet carnet = (Carnet) cdao.getRow(id);
            AlertDialog diaBox = AskOption(carnet.getName());
            diaBox.show();
        }
    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }

    private AlertDialog AskOption(final String carnetTitle) {
        AlertDialog myQuitDialBox = new AlertDialog.Builder(this)
                .setTitle("Attention !")
                .setMessage("Voulez-vous vraiment supprimer la carnet "+carnetTitle+" ?")
                .setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cdao.deleteRow(id);
                        dialogInterface.dismiss();
                        Intent carnet_listIntent = new Intent(Carnet_itemActivity.this, Carnet_listActivity.class);
                        startActivity(carnet_listIntent);
                        Toast deleteToast = Toast.makeText(getApplicationContext(), "La carnet " + carnetTitle + " a été supprimée", Toast.LENGTH_SHORT);
                        deleteToast.show();
                    }
                })
                .setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();

        return myQuitDialBox;
    }
}
