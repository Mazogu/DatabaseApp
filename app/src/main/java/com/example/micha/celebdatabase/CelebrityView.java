package com.example.micha.celebdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CelebrityView extends AppCompatActivity {

    private TextView name;
    private TextView age;
    private TextView gender;
    private TextView industry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_view);

        Celebrity celebrity = getIntent().getParcelableExtra("Celebrity");
        name = findViewById(R.id.nameView);
        age = findViewById(R.id.ageView);
        gender = findViewById(R.id.genderView);
        industry = findViewById(R.id.industryView);

        name.setText(celebrity.getName());
        age.setText(celebrity.getAge());
        gender.setText(celebrity.getGender());
        industry.setText(celebrity.getIndustry());
    }
}
