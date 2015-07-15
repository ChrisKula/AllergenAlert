package com.itescia.rkouraichi_ckula.allergenalert.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itescia.rkouraichi_ckula.allergenalert.pojos.Article;
import com.itescia.rkouraichi_ckula.allergenalert.R;

import java.util.ArrayList;

/**
 * Created by Chris on 12/07/2015.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {

    private Context context;
    private int resource;
    private ArrayList<Article> data;

    public ArticleAdapter(Context context, ArrayList<Article> data) {
        super(context, R.layout.list_item_article, data);
        this.context = context;
        this.resource = R.layout.list_item_article;
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

        Article article = data.get(position);

        if (article != null) {
            TextView tt = (TextView) v.findViewById(R.id.article_text);
            tt.setText(article.toString());

            ImageView iv = (ImageView) v.findViewById(R.id.article_image);
//            iv.setImageResource(R.drawable.miel_pops_box);
        }

        // the view must be returned to our activity
        return v;

    }
}
