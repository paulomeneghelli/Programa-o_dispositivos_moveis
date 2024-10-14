package com.example.planetas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlanetaAdapter extends ArrayAdapter {

    Context mContext;
    int mResouseXMl;
    List<Planeta> mListPlaneta;

    public PlanetaAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context;
        mResouseXMl = resource;
        mListPlaneta = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view  = layoutInflater.inflate(mResouseXMl, parent, false);

        TextView tv = view.findViewById(R.id.textView); // indo no xml
        ImageView imageView = view.findViewById(R.id.imageView);

        Planeta planeta = mListPlaneta.get(position);
        tv.setText(planeta.nome);
        imageView.setImageResource(planeta.foto);
        return view;
    }


}
