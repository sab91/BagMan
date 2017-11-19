package fr.utt.if26.mytravel.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

import fr.utt.if26.mytravel.Bdd;
import fr.utt.if26.mytravel.Page;

/**
 * Created by paf on 10/11/17.
 */

/**
 * DAO pour la classe Page
 * Interactions de base avec la bdd, + conversion des resultats en objets
 */
public class PageDAO extends DAO implements BaseColumns{

    /**
     * Constructeur
     * @param bd_pf la base de donnée à laquelle il faut se connecter
     */
    public PageDAO(Bdd bd_pf) {
        super(bd_pf, "page");
    }

    public int insertRow(Object ob) {
        Page page = (Page) ob;
        ContentValues v = new ContentValues();
        v.put(Bdd.FeedPage.TITLE, page.getTitle());
        v.put(Bdd.FeedPage.CONTENT, page.getContent());
        v.put(Bdd.FeedPage.SUMMARY, page.getSummary());

        try {
            int id = (int) getDb().getWritableDatabase().insert(getModelName(), null, v);
            return id;
        } catch (Exception e) {
            Log.i("Ex", e.getMessage());
            return -1;
        }

    }

    public void deleteRow(Object ob) {
        Page page = (Page) ob;
        String select = "_id LIKE ?";
        String[] args = { page.getId()+"" };
        getDb().getWritableDatabase().delete(getModelName(), select, args);
    }

    public Page getRow(int id) {
        String sql = "SELECT * FROM page WHERE _id =? ";
        SQLiteDatabase db = getDb().getReadableDatabase();
        Cursor c = null;

        try {
            c = db.rawQuery(sql, new String[] {id+""});
            if (c != null)
                c.moveToFirst();
        } catch (Exception e) {
            Log.i("Ex", e.getMessage());
        }
        return this.itemToObject(c);
    }

    public ArrayList getList() {
        String[] projections = {"_id", "title", "content", "summary"};
        String sortOrder = projections[0] + " DESC";
        Cursor c = getDb().getReadableDatabase().query(
                getModelName(),
                projections, //Nullable pour avoir toutes les colonnes
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Page> items = new ArrayList();
        while (c.moveToNext()) {
            items.add(this.itemToObject(c));
        }
        return items;
    }

    public Page itemToObject(Cursor c_pf) {
        try {
            int itemId = c_pf.getInt(0);
            String itemTitle = c_pf.getString(1);
            String itemContent = c_pf.getString(2);
            String itemSummary = c_pf.getString(3);
            Page page = new Page(itemId, itemTitle, itemContent, itemSummary);
            return page;
        } catch(Exception e) {
            Log.i("Ex", e.getMessage());
            return null;
        }
    }

    public void updateRow(int id, Object ob) {
        Page page = (Page) ob;
        // Méthode à l'ancienne, faute d'avoir reussi à faire fonctionner l'autre ^^
        String sql = "UPDATE " + Bdd.FeedPage.MODEL_NAME + " SET " +
                Bdd.FeedPage.TITLE + "='" + page.getTitle() + "', " +
                Bdd.FeedPage.CONTENT + "='" + page.getContent() + "', " +
                Bdd.FeedPage.SUMMARY + "='" + page.getSummary() + "' " +
                "WHERE _id = " + id;
        try {
            getDb().getDatabase().execSQL(sql);
        } catch (Exception e) {
            Log.i("====", e.getMessage());
        }
    }

}
