package fr.utt.if26.mytravel.DAO;

import android.content.ContentValues;
import android.database.Cursor;
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
    public void deleteRow(int id) {

    }

    @Override
    public Object getRow(int id) {
        return null;
    }

    @Override
    public void updateRow(int id, Object ob) {

    }

    @Override
    public Carnet itemToObject(Cursor c_pf) {
        try {
            int itemId = c_pf.getInt(0);
            String itemName = c_pf.getString(1);
            long itemCreatedAt = c_pf.getLong(2);
            long itemUpdatedAt = c_pf.getLong(3);
            Carnet carnet = new Carnet(itemId, itemName, itemCreatedAt,
                    itemUpdatedAt);
            return carnet;
        } catch(Exception e) {
            Log.i("Ex", e.getMessage());
            return null;
        }
    }
}
