package com.example.inbar.todolistmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.ContextMenu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import android.view.ContextMenu.ContextMenuInfo;

import com.parse.Parse;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> arrayList = new ArrayList<Item>();
    private CustomAdapter adapter;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        ListView listView = (ListView)findViewById(R.id.listv);
        adapter = new CustomAdapter(this, arrayList);
        listView.setAdapter(adapter);
        db.setList(arrayList);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registerForContextMenu(listView);

    }

        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
            if(view.getId() == R.id.listv){
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
                Item item = arrayList.get(info.position);
                menu.setHeaderTitle(item.getTitle());
                menu.add("Delete Item");
                String[] arr = item.getTitle().split(" ");
                if(arr[0].compareTo("Call") == 0){
                    menu.add(item.getTitle());
                }
            }
        }

        public boolean onContextItemSelected(MenuItem item){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            String[] arr2 = item.getTitle().toString().split(" ");
            if(item.getTitle() == "Delete Item"){
                db.deleteItem(arrayList.get(info.position).getId());
                arrayList.remove(info.position);
                adapter.notifyDataSetChanged();
            }

            else if(item.getTitle() == arrayList.get(info.position).getTitle()){
                Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + arr2[1]));
                startActivity(dial);
            }
            return true;
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menuItemAdd) {
            Intent intent = new Intent(this, AddNewTodoItemActivity.class);
            startActivityForResult(intent, 1);
        }
        return true;
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == 1) {
           if (resultCode == RESULT_OK) {
               Bundle b = data.getExtras();
               if (b != null) {
                   String title = (String)b.get("title");
                   Date date = (Date)b.get("dueDate");
                   if(!title.matches("")){
                       Item it = new Item(title, date);
                       int id = db.addItem(it);
                       it.setId(id);
                       arrayList.add(it);
                       adapter.notifyDataSetChanged();
                   }
               }
           }
       }
   }
}
