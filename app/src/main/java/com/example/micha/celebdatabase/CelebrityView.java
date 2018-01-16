package com.example.micha.celebdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.micha.celebdatabase.data.DatabaseHelper;

import org.w3c.dom.Text;

public class CelebrityView extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText industry;
    boolean favoriteToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_view);

        Celebrity celebrity = getIntent().getParcelableExtra("Celebrity");
        checkFavorite(celebrity);

        name = findViewById(R.id.nameView);
        age = findViewById(R.id.ageView);
        gender = findViewById(R.id.genderView);
        industry = findViewById(R.id.industryView);

        name.setText(celebrity.getName());
        age.setText(celebrity.getAge());
        gender.setText(celebrity.getGender());
        industry.setText(celebrity.getIndustry());
    }

    private void checkFavorite(Celebrity entry) {
        switch (entry.getFavorite()){
            case "0":
                favoriteToggle = false;
                break;
            case "1":
                favoriteToggle = true;
                break;
        }
    }

    private String setFavorite() {
        if(favoriteToggle){
            return "1" ;
        }
        else{
            return"0";
        }
    }

    public void viewButton(View view) {
        switch (view.getId()) {
            case R.id.update:
                final Celebrity celebrity = new Celebrity(name.getText().toString(),age.getText().toString(),gender.getText().toString(),
                        industry.getText().toString(),setFavorite());

                DatabaseHelper db = new DatabaseHelper(this);
                //Toast.makeText(this,celebrity.getFavorite(),Toast.LENGTH_SHORT).show();
                db.update(celebrity);
                break;
            case R.id.favoriteBtn:
                favoriteToggle = !favoriteToggle;
                Toast.makeText(this,Boolean.toString(favoriteToggle),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
