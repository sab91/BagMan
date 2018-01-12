package fr.utt.if26.mytravel.Helpers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.R;

/**
 * Created by paf on 14/12/17.
 */

public class CarnetAdapter extends ArrayAdapter<Carnet> implements View.OnClickListener {

    public CarnetAdapter(Context ct, int ressource, ArrayList<Carnet> carnets) {
        super(ct, ressource, carnets);
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Carnet carnet = (Carnet) getItem(position);

        Log.e("====", "click ! ");
    }

    @Override
    public View getView(int position, View convertV, ViewGroup viewg) {
        View v = convertV;
        Carnet carnet = getItem(position);

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.row_item, null);
        }

        if (carnet != null) {
            TextView name_tv = (TextView) v.findViewById(R.id.row_title);
            TextView updatedAt_tv = (TextView) v.findViewById(R.id.row_updatedAt);

            if (name_tv != null) {
                name_tv.setText(carnet.getName());
            }


            if ( updatedAt_tv != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                String date = sdf.format(new Date(carnet.getUpdatedAt()));
                updatedAt_tv.setText(date);
            }
        }
        return v;
    }

}
