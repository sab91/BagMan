package fr.utt.if26.mytravel.Helpers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import fr.utt.if26.mytravel.Activities.Carnet_listActivity;
import fr.utt.if26.mytravel.Activities.MainActivity;
import fr.utt.if26.mytravel.R;


/**
 * Niveau d'abstraction permettant d'avoir le meme menu dans toutes les activités
 *  !! Voir comment garder que le header et faire changer le contenu (actuellement le header est rechargé à chaque fois)
 *
 */
public abstract class MenuHeader extends AppCompatActivity {

    private String current_email;
    /**
     * Création du menu, lien avec le xml
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Attribution des taches en fonction de ce qui est selectionné
     * TODO : Trouver un moyen de ne pas executer d'intents lorsqu'on est deja sur la page demandée
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        current_email = getIntent().getStringExtra("EMAIL_ACCOUNT");

        switch(item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(MenuHeader.this, Carnet_listActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.disconnect:
                Toast toast = Toast.makeText(getApplicationContext(), "Disconnection...", Toast
                        .LENGTH_SHORT);
                toast.show();
                Intent disconnectIntent = new Intent(MenuHeader.this, MainActivity.class);
                Bundle extras = new Bundle();
                extras.putString("EMAIL_ACCOUNT", current_email);
                disconnectIntent.putExtras(extras);
                startActivity(disconnectIntent);
                return true;
            default: return false;
        }
    }
}
