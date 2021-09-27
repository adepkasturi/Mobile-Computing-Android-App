package com.example.mca1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {
    Spinner spin;
    Button upload;
    RatingBar ratings;
    TextView ack;
    TextView ratingValue;
    long rowid;
    SQLiteDatabase dbwrite;
    Map column_map;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbwrite = dbHelper.getWritableDatabase();
        rowid = intent.getLongExtra(MainActivity.EXTRA_MESSAGE, 0);
        upload = (Button) findViewById(R.id.uploadsymptoms);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRatingValues();
            }
        });

        spin = (Spinner) findViewById(R.id.symptomlist);
        ratings = (RatingBar) findViewById(R.id.ratingBar);
        ack = (TextView) findViewById(R.id.successmsg);

        column_map=new HashMap();
        String sym[] = {"Nausea", "Headache", "Diarrhea", "Sore Throat", "Fever", "Muscle Ache", "Loss of Smell or Taste", "Cough", "Shortness of Breath", "Feeling Tired"};
        String symdb[] = {"Nausea", "Headache", "Diarrhea", "Sore_Throat", "Fever", "Muscle_Ache", "Loss_of_Smell_Taste", "Cough", "Shortness_of_Breath", "Feeling_Tired"};

        for(int i = 0; i < 10 ; i++){
            column_map.put(sym[i], symdb[i]);
        }
    }

    public void getRatingValues() {
        String sym = spin.getSelectedItem().toString();
        float rat = ratings.getRating();
        ratings.setRating(0.0f);
        ContentValues values = new ContentValues();
        String selection = DataModel.AppTable._ID + " = ?";
        String[] selectionArgs = { Long.toString(rowid) };
        values.put((String)column_map.get(sym), rat);
        int count = dbwrite.update(DataModel.AppTable.TABLE_NAME, values, selection, selectionArgs);
    }
}