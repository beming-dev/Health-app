package org.techtown.realapp;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import java.util.ArrayList;

public class MyActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyAdapter.MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.routine_recycler);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new MyAdapter.MyData(getString(R.string.upper), R.drawable.btn));
        myDataset.add(new MyAdapter.MyData(getString(R.string.lower), R.drawable.btn));
        myDataset.add(new MyAdapter.MyData("다이어트", R.drawable.btn));
        myDataset.add(new MyAdapter.MyData("코어", R.drawable.btn));

        Button add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}