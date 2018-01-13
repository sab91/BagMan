package fr.utt.if26.mytravel.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.Model.Page;
import fr.utt.if26.mytravel.R;

public class Page_itemActivity extends MenuHeader {
    private Bdd database;
    private PageDAO pdao;
    private int id;
    private EditText layout_title;
    private EditText layout_summary;
    private EditText layout_content;
    private Page page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_item);
        database = new Bdd(this);
        pdao = new PageDAO(database);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("PAGE_ID");
        page = pdao.getRow(id);

        layout_title = (EditText) findViewById(R.id.page_itemTitle);
        layout_summary = (EditText) findViewById(R.id.page_itemSummary);
        layout_content = (EditText) findViewById(R.id.page_itemContent);
        Button layout_deleteButton = (Button) findViewById(R.id.page_deleteButton);
        Button layout_updateButton = (Button) findViewById(R.id.page_updateButton);

        layout_title.setText(page.getTitle());
        layout_summary.setText(page.getSummary());
        layout_content.setText(page.getContent());

        layout_deleteButton.setOnClickListener(new Page_itemActivity.Page_action_delete());
        layout_updateButton.setOnClickListener(new Page_itemActivity.Page_action_update());
    }

    private class Page_action_update implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String title = layout_title.getText().toString();
            String summary = layout_summary.getText().toString();
            String content = layout_content.getText().toString();
            Page new_page = new Page(title, content, summary, page.getCarnet_id());

            pdao.updateRow(id, new_page);
            Intent page_listeIntent = new Intent(Page_itemActivity.this, Page_listActivity.class);
            startActivity(page_listeIntent);
        }
    }

    private class Page_action_delete implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AlertDialog diaBox = AskOption(pdao.getRow(id).getTitle());
            diaBox.show();
        }
    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }

    private AlertDialog AskOption(final String pageTitle) {
        return new AlertDialog.Builder(this)
                .setTitle("Attention !")
                .setMessage("Voulez-vous vraiment supprimer la page "+pageTitle+" ?")
                .setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pdao.deleteRow(id);
                        dialogInterface.dismiss();
                        Intent page_listIntent = new Intent(Page_itemActivity.this, Page_listActivity.class);
                        Bundle extras = new Bundle();
                        extras.putInt("carnet_id", page.getCarnet_id());
                        page_listIntent.putExtras(extras);
                        startActivity(page_listIntent);
                        Toast deleteToast = Toast.makeText(getApplicationContext(), "La page " + pageTitle + " a été supprimée", Toast.LENGTH_SHORT);
                        deleteToast.show();
                    }
                })
                .setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
    }
}