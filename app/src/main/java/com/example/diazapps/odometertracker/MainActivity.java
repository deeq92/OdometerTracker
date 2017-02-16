package com.example.diazapps.odometertracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Db db;
    static ArrayList<MyEntry> arrayList;
    static ArrayAdapter arrayAdapter;
    EditText odo;
    EditText miles;
    EditText notes;
    Calendar c;
    TabHost tabHost;

    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Add");
        host.addTab(spec);

        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("History");
        host.addTab(spec);


        db = new Db(this);

        odo = (EditText) findViewById(R.id.odometer);
        miles = (EditText) findViewById(R.id.milesDriven);
        ListView listView = (ListView) findViewById(R.id.listView);
        notes = (EditText) findViewById(R.id.notes);

        FrameLayout footerLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.listview_button,null);
        listView.addFooterView(footerLayout);

        TextView totalMiles = (TextView) findViewById(R.id.totalMiles);

        arrayList = db.getEntries();
        arrayAdapter = new CustomAdapter(this, arrayList);
        listView.setAdapter(arrayAdapter);

        int totalmiles = 0;
        for(MyEntry i : arrayList)
        {
            totalmiles += i.getMilesDriven();
        }
        totalMiles.setText("Total miles: " + totalmiles);

    }

    public void save(View view)
    {
        MyEntry entry = new MyEntry();
        entry.setEndOdo(Integer.valueOf(odo.getText().toString()));
        entry.setMilesDriven(Integer.valueOf(miles.getText().toString()));
        entry.setDate(sdf.format(new Date()));
        entry.setStartOdo(entry.getEndOdo() - entry.getMilesDriven());
        entry.setNote(String.valueOf(notes.getText()));

        db.addEntry(entry);
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void clear(View view)
    {
        db.clearDatabase();

        Toast.makeText(this, "Cleared!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void export(View view)
    {
        File outfile = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        + "/Odometer.txt"));

        try {
            FileOutputStream fileout = new FileOutputStream(outfile);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            for(MyEntry x : arrayList)
            {
                outputWriter.write(String.valueOf(x.getStartOdo()) + ", " + String.valueOf(x.getEndOdo()) + ", "
                        + x.getDate() + ", " + x.getNote() + "\n");
            }
            outputWriter.close();
            Toast.makeText(this, "Saved to: " + String.valueOf(outfile), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
