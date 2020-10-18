package org.techtown.realapp;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import java.util.ArrayList;

public class ExkindSel extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExkindSelAdapter.MyData> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_kind_sel);
        mRecyclerView = (RecyclerView) findViewById(R.id.routine_recycler);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        int requestCode = intent.getExtras().getInt("requestCode");

        myDataset = new ArrayList<>();
        mAdapter = new ExkindSelAdapter(myDataset,requestCode);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new ExkindSelAdapter.MyData(getString(R.string.upper), R.drawable.btn));
        myDataset.add(new ExkindSelAdapter.MyData(getString(R.string.lower), R.drawable.btn));
        myDataset.add(new ExkindSelAdapter.MyData(getString(R.string.diet), R.drawable.btn));
        myDataset.add(new ExkindSelAdapter.MyData(getString(R.string.core), R.drawable.btn));

        Button add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}