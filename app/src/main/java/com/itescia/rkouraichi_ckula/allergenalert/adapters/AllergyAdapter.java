package com.itescia.rkouraichi_ckula.allergenalert.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.itescia.rkouraichi_ckula.allergenalert.R;
import com.itescia.rkouraichi_ckula.allergenalert.pojos.Allergy;

import java.util.ArrayList;

/**
 * Created by Chris on 13/07/2015.
 */
public class AllergyAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private ArrayList<Allergy> data;

    public AllergyAdapter(Context context, ArrayList<Allergy> data) {
        super(context, R.layout.list_item_allergy, data);
        this.context = context;
        this.resource = R.layout.list_item_allergy;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, null);
        }

        Allergy allergy = data.get(position);

        if (allergy != null) {
            CheckBox cb = (CheckBox) v.findViewById(R.id.allergy_checked);
            cb.setChecked(allergy.isAllergicTo());

            TextView tt = (TextView) v.findViewById(R.id.allergy_name);
            tt.setText(allergy.getName());

            tt = (TextView) v.findViewById(R.id.allergy_description);
            tt.setText(allergy.getDescription());
        }

        // the view must be returned to our activity
        return v;
    }
}