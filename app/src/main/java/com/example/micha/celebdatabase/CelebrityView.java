package com.example.micha.celebdatabase;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_view);

        Celebrity celebrity = getIntent().getParcelableExtra("Celebrity");

        name = findViewById(R.id.nameView);
        age = findViewById(R.id.ageView);
        gender = findViewById(R.id.genderView);
        industry = findViewById(R.id.industryView);
        imageButton = findViewById(R.id.favoriteBtn);

        checkFavorite(celebrity);
        name.setText(celebrity.getName());
        age.setText(celebrity.getAge());
        gender.setText(celebrity.getGender());
        industry.setText(celebrity.getIndustry());
    }

    private void checkFavorite(Celebrity entry) {
        switch (entry.getFavorite()){
            case "0":
                favoriteToggle = false;
                imageButton.setImageDrawable(getResources().getDrawable(R.drawable.empty_heart));
                break;
            case "1":
                imageButton.setImageDrawable(getResources().getDrawable(R.drawable.heart_full));
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

        DatabaseHelper db = new DatabaseHelper(this);
        final Celebrity celebrity = new Celebrity(name.getText().toString(),age.getText().toString(),gender.getText().toString(),
                industry.getText().toString(),setFavorite());
        switch (view.getId()) {
            case R.id.update:
                //Toast.makeText(this,celebrity.getFavorite(),Toast.LENGTH_SHORT).show();
                db.update(celebrity);
                goBack();
                break;
            case R.id.favoriteBtn:
                favoriteToggle = !favoriteToggle;
                if(favoriteToggle){
                    imageButton.setImageDrawable(getResources().getDrawable(R.drawable.heart_full));
                }
                else{
                    imageButton.setImageDrawable(getResources().getDrawable(R.drawable.empty_heart));
                }
                Toast.makeText(this,Boolean.toString(favoriteToggle),Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                int deleted = db.delete(celebrity);
                if(deleted > 0){
                    goBack();
                }
                break;
            case R.id.viewFavorites:
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void goBack() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
