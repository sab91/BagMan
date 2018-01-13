package fr.utt.if26.mytravel.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.Model.Account;
import fr.utt.if26.mytravel.Model.Page;

/**
 * Created by sabri on 30/11/2017.
 */

public class AuthDAO extends DAO {
    /**
     * Constructeur
     *
     * @param db_pf
     */
    public AuthDAO(Bdd db_pf) {
        super(db_pf, Bdd.FeedAuth.MODEL_NAME);
    }

    @Override
    public ArrayList getList() {
        String[] projections = {"_id", "email", "mdp", "created_at", "updated_at"};
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

        ArrayList<Account> items = new ArrayList();
        while (c.moveToNext()) {
            items.add(this.itemToObject(c));
        }
        return items;
    }


    @Override
    public int insertRow(Object ob) {
        Account account = (Account) ob;
        ContentValues v = new ContentValues();
        v.put(Bdd.FeedAuth.EMAIL, account.getEmail());
        v.put(Bdd.FeedAuth.MDP, account.getPassword());
        v.put(Bdd.FeedAuth.CREATED_AT, account.getCreatedAt());
        v.put(Bdd.FeedAuth.UPDATED_AT, account.getUpdatedAt());

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
        String sql = "SELECT * FROM auth WHERE _id =? ";
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

    public ArrayList<Account> getRowByEmail(String mail) {
            String[] projections = { Bdd.FeedAuth._ID,
                    Bdd.FeedAuth.EMAIL,
                    Bdd.FeedAuth.MDP,
                    Bdd.FeedAuth.CREATED_AT,
                    Bdd.FeedAuth.UPDATED_AT };
            String sortOrder = projections[0] + " DESC";
            String whereClause = Bdd.FeedAuth.EMAIL + " = ?";
            String[] whereArg = { mail + "" };

            Cursor c = getDb().getReadableDatabase().query(
                    getModelName(),
                    projections,
                    whereClause,
                    whereArg,
                    null,
                    null,
                    sortOrder
            );
        ArrayList<Account> items = new ArrayList();
        while (c.moveToNext()) {
            items.add(this.itemToObject(c));
        }
        return items;
    }

    @Override
    public void updateRow(int id, Object ob) {
        Account account = (Account) ob;
        String filter = "_id="+id;
        ContentValues args = new ContentValues();
        args.put(Bdd.FeedAuth.EMAIL, account.getEmail());
        args.put(Bdd.FeedAuth.MDP, account.getPassword());
        args.put(Bdd.FeedAuth.UPDATED_AT, account.getUpdatedAt());

        try {
            getDb().getWritableDatabase().update(Bdd.FeedAuth.MODEL_NAME, args, filter, null);
        } catch (Exception e) {
            Log.i("====", e.getMessage());
        }
    }

    @Override
    public Account itemToObject(Cursor c_cf) {
        try {
            int itemId = c_cf.getInt(0);
            String itemEmail = c_cf.getString(1);
            String itemPassword = c_cf.getString(2);
            long itemCreatedAt = c_cf.getLong(3);
            long itemUpdatedAt = c_cf.getLong(4);
            Account account = new Account(itemId, itemEmail, itemPassword, itemCreatedAt,
                    itemUpdatedAt);
            return account;
        } catch(Exception e) {
            Log.i("Ex", e.getMessage());
            return null;
        }
    }

}