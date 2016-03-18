package com.example.inbar.todolistmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Inbar on 17/3/2016.
 */
public class AddNewTodoItemActivity extends Activity {
    public void onCreate(Bundle unused){
        super.onCreate(unused);
        setContentView(R.layout.addnew);

        Button okButton = (Button)findViewById(R.id.btnOK);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                EditText title = (EditText)findViewById(R.id.edtNewItem);
                DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);
                intent.putExtra("dueDate", getDateFromDatePicker(datePicker));
                intent.putExtra("title", title.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button cancelButton = (Button)findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }


    //return parsed Date object
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }


}
