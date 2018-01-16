package com.example.micha.celebdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.micha.celebdatabase.data.DatabaseContract;
import com.example.micha.celebdatabase.data.DatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText industry;
    private ListView celebListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        industry = findViewById(R.id.industry);
        celebListView = findViewById(R.id.celebList);



    }

    public void btnCeleb(View view) {
        String cName, cAge, cGender, cIndustry;
        DatabaseHelper database = new DatabaseHelper(this);
        cName = name.getText().toString();
        cAge = age.getText().toString();
        cGender = gender.getText().toString();
        cIndustry = industry.getText().toString();
        final Celebrity celebrity = new Celebrity(cName, cAge, cGender, cIndustry);

        switch(view.getId()){
            case R.id.addCeleb:
                long row = database.saveCeleb(celebrity);
                Toast.makeText(this,"Saved at row: "+row,Toast.LENGTH_SHORT).show();
                break;
            case R.id.getList:
                final List<Celebrity> list = database.getCelebrityList(DatabaseContract.Action.GET_ALL);

                ArrayAdapter<Celebrity> adapter = new ArrayAdapter<Celebrity>(this,android.R.layout.simple_expandable_list_item_1,list);
                celebListView.setAdapter(adapter);
                celebListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getApplicationContext(),CelebrityView.class);
                        intent.putExtra("Celebrity", list.get(i));
                        startActivity(intent);
                    }
                });
                break;
        }
    }
}
