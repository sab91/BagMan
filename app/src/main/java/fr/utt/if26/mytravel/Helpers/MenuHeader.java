package fr.utt.if26.mytravel.Helpers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import fr.utt.if26.mytravel.Activities.MainActivity;
import fr.utt.if26.mytravel.R;


/**
 * Niveau d'abstraction permettant d'avoir le meme menu dans toutes les activités
 *  !! Voir comment garder que le header et faire changer le contenu (actuellement le header est rechargé à chaque fois)
 *
 */
public abstract class MenuHeader extends AppCompatActivity {

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
        switch(item.getItemId()) {
            case R.id.main:
                Intent targetIntent = new Intent(MenuHeader.this, MainActivity.class);
                startActivity(targetIntent);
                return true;
            case R.id.listCarnet:
                Log.e("====", "liste ");
                return true;
            case R.id.newCarnet:
                Log.e("====", "new");
                return true;
            default: return false;
        }
    }
}
