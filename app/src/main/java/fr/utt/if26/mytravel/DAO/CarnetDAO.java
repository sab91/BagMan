package fr.utt.if26.mytravel.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.Model.Page;

/**
 * Created by sabri on 30/11/2017.
 */

public class CarnetDAO extends DAO {
    /**
     * Constructeur
     *
     * @param db_pf
     */
    public CarnetDAO(Bdd db_pf) {
        super(db_pf, Bdd.FeedCarnet.MODEL_NAME);
    }

    @Override
    public ArrayList getList() {
        String[] projections = {"_id", "name", "created_at", "updated_at"};
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

        ArrayList<Carnet> items = new ArrayList();
        while (c.moveToNext()) {
            items.add(this.itemToObject(c));
        }
        return items;
    }

    /**
     * TODO: Observer/Observable pour mettre à jour liste de page d'un objet carnet ?
     * @param carnet
     * @param page
     * @return
     */
    public int addPage(Carnet carnet, Page page) {
        ContentValues newValues = new ContentValues();
        String whereClause = Bdd.FeedPage._ID + " = " + page.getId();
        newValues.put(Bdd.FeedPage.CARNET, carnet.getId());

        try {
            getDb().getWritableDatabase().update(Bdd.FeedPage.MODEL_NAME, newValues, whereClause, null);
            carnet.getPages().add(page);
            return 0;

        } catch(Exception e) {
            Log.e("===", "Probleme d'ajout de id_carnet à page");
            return -1;
        }
    }

    public ArrayList<Page> getPageList(int carnet_id) {
        String[] projections = { Bdd.FeedPage._ID,
                Bdd.FeedPage.TITLE,
                Bdd.FeedPage.CONTENT,
                Bdd.FeedPage.SUMMARY,
                Bdd.FeedPage.CREATED_AT,
                Bdd.FeedPage.UPDATED_AT,
                Bdd.FeedPage.CARNET };
        String sortOrder = projections[0] + " DESC";
        String whereClause = Bdd.FeedPage.CARNET + " = " + carnet_id;

        Cursor c = getDb().getReadableDatabase().query(
                Bdd.FeedPage.MODEL_NAME,
                projections,
                whereClause,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Page> items = new ArrayList();
        while (c.moveToNext()) {
            items.add(this.itemToPage(c));
        }
        return items;
    }

    @Override
    public int insertRow(Object ob) {
        Carnet carnet = (Carnet) ob;
        ContentValues v = new ContentValues();
        v.put(Bdd.FeedCarnet.NAME, carnet.getName());
        v.put(Bdd.FeedCarnet.CREATED_AT, carnet.getCreatedAt());
        v.put(Bdd.FeedCarnet.UPDATED_AT, carnet.getUpdatedAt());

        try {
            int id = (int) getDb().getWritableDatabase().insert(getModelName(), null, v);
            return id;
        } catch (Exception e) {
            Log.i("Ex", e.getMessage());
            return -1;
        }
    }

    @Override
    public void deleteRow(int id_cf) {
        String select = "_id LIKE ?";
        String[] args = { id_cf+"" };
        getDb().getWritableDatabase().delete(getModelName(), select, args);
    }

    @Override
    public Object getRow(int id) {
        String sql = "SELECT * FROM carnet WHERE _id =? ";
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

    @Override
    public void updateRow(int id, Object ob) {
        Carnet carnet = (Carnet) ob;
        String filter = "_id="+id;
        ContentValues args = new ContentValues();
        args.put(Bdd.FeedCarnet.NAME, carnet.getName());
        args.put(Bdd.FeedCarnet.UPDATED_AT, carnet.getUpdatedAt());

        try {
            getDb().getWritableDatabase().update(Bdd.FeedCarnet.MODEL_NAME, args, filter, null);
        } catch (Exception e) {
            Log.i("====", e.getMessage());
        }
    }

    @Override
    public Carnet itemToObject(Cursor c_cf) {
        try {
            int itemId = c_cf.getInt(0);
            String itemName = c_cf.getString(1);
            long itemCreatedAt = c_cf.getLong(2);
            long itemUpdatedAt = c_cf.getLong(3);
            Carnet carnet = new Carnet(itemId, itemName, itemCreatedAt,
                    itemUpdatedAt);
            return carnet;
        } catch(Exception e) {
            Log.i("Ex", e.getMessage());
            return null;
        }
    }

    public Page itemToPage(Cursor c_pf) {
        try {
            int itemId = c_pf.getInt(0);
            String itemTitle = c_pf.getString(1);
            String itemContent = c_pf.getString(2);
            String itemSummary = c_pf.getString(3);
            long itemCreatedAt = c_pf.getLong(4);
            long itemUpdatedAt = c_pf.getLong(5);
            int carnet_id = c_pf.getInt(6);
            Page page = new Page(itemId, itemTitle, itemContent, itemSummary, itemCreatedAt, itemUpdatedAt, carnet_id);
            return page;
        } catch(Exception e) {
            Log.i("Ex", e.getMessage());
            return null;
        }
    }
}