package fr.utt.if26.mytravel.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.Model.Carnet;

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
}
