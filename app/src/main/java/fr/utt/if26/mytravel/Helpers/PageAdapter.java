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

import fr.utt.if26.mytravel.Model.Page;
import fr.utt.if26.mytravel.R;

/**
 * Created by paf on 14/12/17.
 */

public class PageAdapter extends ArrayAdapter<Page> implements View.OnClickListener {

    public PageAdapter(Context ct, int ressource, ArrayList<Page> pages) {
        super(ct, ressource, pages);
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Page page = (Page) getItem(position);

        Log.e("====", "click ! ");
    }

    @Override
    public View getView(int position, View convertV, ViewGroup viewg) {
        View v = convertV;
        Page page = getItem(position);

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.row_item, null);
        }

        if (page != null) {
            TextView title_tv = (TextView) v.findViewById(R.id.row_title);
            TextView summary_tv = (TextView) v.findViewById(R.id.row_summary);
            TextView updatedAt_tv = (TextView) v.findViewById(R.id.row_updatedAt);

            if (title_tv != null) {
                title_tv.setText(page.getTitle());
            }

            if (summary_tv != null) {
                summary_tv.setText(page.getSummary());
            }

            if ( updatedAt_tv != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                String date = sdf.format(new Date(page.getUpdatedAt()));
                updatedAt_tv.setText(date);
            }
        }
        return v;
    }

}
