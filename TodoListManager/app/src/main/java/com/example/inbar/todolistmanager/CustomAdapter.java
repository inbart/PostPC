package com.example.inbar.todolistmanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Inbar on 10/03/2016.
 */
public class CustomAdapter extends ArrayAdapter<Item> {

    private final Context context;
    private final ArrayList<Item> arrayList;

    public CustomAdapter(Context context, ArrayList<Item> items) {
        super(context, R.layout.listrow, items);
        this.context = context;
        this.arrayList = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listrow, parent, false);

        TextView titleView = (TextView) rowView.findViewById(R.id.txtTodoTitle);
        TextView dateView = (TextView) rowView.findViewById(R.id.txtTodoDueDate);

        Item item = arrayList.get(position);
        titleView.setText(item.getTitle());
        Date date = item.getDate();
        if(date != null) {
            dateView.setText(item.convertDateToString(date));
        }
        else{
            dateView.setText("No due date");
        }

        Date currentDate = new Date();
        Date noTimeCurDate = getZeroTimeDate(currentDate);
        Date noTimeDate = getZeroTimeDate(date);

        if(noTimeDate.compareTo(noTimeCurDate) < 0){
            titleView.setTextColor(Color.RED);
            dateView.setTextColor(Color.RED);
        }

        return rowView;
    }

    public static Date getZeroTimeDate(Date fecha) {
        Date res = fecha;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( fecha );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        res = calendar.getTime();
        return res;
    }


}
