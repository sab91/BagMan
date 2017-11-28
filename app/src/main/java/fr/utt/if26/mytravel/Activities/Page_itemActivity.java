package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.Model.Page;
import fr.utt.if26.mytravel.R;

public class Page_itemActivity extends AppCompatActivity {
    private Bdd database;
    private PageDAO pdao;
    private int id;
    private EditText layout_title;
    private EditText layout_summary;
    private EditText layout_content;
    private Button layout_deleteButton;
    private Button layout_updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_item);
        database = new Bdd(this);
        pdao = new PageDAO(database);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        Page page = pdao.getRow(id);

        layout_title = (EditText) findViewById(R.id.page_itemTitle);
        layout_summary = (EditText) findViewById(R.id.page_itemSummary);
        layout_content = (EditText) findViewById(R.id.page_itemContent);
        layout_deleteButton = (Button) findViewById(R.id.page_deleteButton);
        layout_updateButton = (Button) findViewById(R.id.page_updateButton);

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
            Page page = new Page(title, content, summary);

            pdao.updateRow(id, page);
            Intent page_listeIntent = new Intent(Page_itemActivity.this, Page_listActivity.class);
            startActivity(page_listeIntent);
        }
    }

    private class Page_action_delete implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            pdao.deleteRow(id);
            Intent page_listIntent = new Intent(Page_itemActivity.this, Page_listActivity.class);
            startActivity(page_listIntent);
        }
    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}
