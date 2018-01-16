package com.example.micha.celebdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.micha.celebdatabase.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText industry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.age);
        industry = findViewById(R.id.age);



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
            case R.id.celebList:
                break;
        }
    }
}
