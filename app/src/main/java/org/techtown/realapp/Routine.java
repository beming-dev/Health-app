package org.techtown.realapp;

import androidx.appcompat.app.AppCompatActivity;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.Toast;

        import java.util.ArrayList;

public class Routine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine);
    }
}
