package com.example.diazapps.odometertracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<MyEntry> {

    public CustomAdapter(Context context, ArrayList<MyEntry> note) {
        super(context, R.layout.customrow, note);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // default -  return super.getView(position, convertView, parent);

        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.customrow, parent, false);


        TextView startOdo = (TextView) customView.findViewById(R.id.startOdo);
        TextView endOdo = (TextView) customView.findViewById(R.id.endOdo);
        TextView date = (TextView) customView.findViewById(R.id.date);
        TextView note = (TextView) customView.findViewById(R.id.note);

        String startOdo1 = String.valueOf(getItem(position).getStartOdo());
        String endOdo1 = String.valueOf(getItem(position).getEndOdo());
        String date1 = getItem(position).getDate();
        String note1 = getItem(position).getNote();


        startOdo.setText("Starting Odometer: " + startOdo1);
        endOdo.setText("Ending Odometer: " + endOdo1);
        date.setText("Date: " + date1);
        note.setText(note1);

        return customView;

    }
}