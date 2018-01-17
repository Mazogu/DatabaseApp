package com.example.micha.celebdatabase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        String filename = "savedEntries";
        OutputStream out;
        InputStream in;
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

            case R.id.savePref:
                try{
                    out = openFileOutput(filename, Context.MODE_PRIVATE);
                    out.write((cName+"\n").getBytes());
                    out.write((cAge+"\n").getBytes());
                    out.write((cGender+"\n").getBytes());
                    out.write((cIndustry+"\n").getBytes());
                    out.close();
                }
                catch (IOException e){
                    Toast.makeText(this,"This didn't work",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.loadPref:
                try{
                    File input = new File(getFilesDir().getAbsolutePath(),filename);
                    StringBuilder text = new StringBuilder();
                    String line;
                    BufferedReader read = new BufferedReader(new FileReader(input));
                    int i = 0;
                    while((line = read.readLine()) != null){
                        switch (i){
                            case 0:
                                name.setText(line);
                                break;
                            case 1:
                                age.setText(line);
                                break;
                            case 2:
                                gender.setText(line);
                                break;
                            case 3:
                                industry.setText(line);
                                break;
                        }
                        i++;
                    }
                    read.close();
                }
                catch (IOException e){
                    Toast.makeText(this,"Something broke",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
