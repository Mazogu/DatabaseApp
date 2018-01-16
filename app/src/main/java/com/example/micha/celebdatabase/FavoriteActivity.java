package com.example.micha.celebdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.micha.celebdatabase.data.DatabaseContract;
import com.example.micha.celebdatabase.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        list = findViewById(R.id.favoriteList);
        DatabaseHelper db = new DatabaseHelper(this);
        final List<Celebrity> celeb = db.getCelebrityList(DatabaseContract.Action.GET_FAV);
        ArrayAdapter<Celebrity> adapter = new ArrayAdapter<Celebrity>(this,android.R.layout.simple_expandable_list_item_1,celeb);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),CelebrityView.class);
                intent.putExtra("Celebrity", celeb.get(i));
                startActivity(intent);
            }
        });


    }

    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
