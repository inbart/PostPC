package com.example.inbar.todolistmanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Inbar on 10/03/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    private int[] colors = new int[] {Color.RED , Color.BLUE};

    public CustomAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.listrow, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(getContext());
        View customView = inf.inflate(R.layout.listrow, parent, false);
        String item = getItem(position);
        TextView infText = (TextView) customView.findViewById(R.id.listrow);
        infText.setText(item);
        infText.setBackgroundColor(colors[position%colors.length]);
        return customView;
    }


}
