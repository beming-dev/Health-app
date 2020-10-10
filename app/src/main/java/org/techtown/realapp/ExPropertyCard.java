package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;

public class ExPropertyCard extends AppCompatActivity {
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_property);

        checkBox = findViewById(R.id.check_exercise);
    }
}
