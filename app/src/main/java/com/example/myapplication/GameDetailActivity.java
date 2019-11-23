package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameDetailActivity extends AppCompatActivity {
    private TextView tvTitle,tvPrice,tvRating,tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        findViews();
        initialize();
    }
    private void findViews(){
        tvTitle=findViewById(R.id.tvTitle);
        tvRating=findViewById(R.id.tvRating);
        tvPrice=findViewById(R.id.tvPrice);
        tvDescription=findViewById(R.id.tvDescription);
    }
    private void initialize(){
        Bundle bundle=getIntent().getExtras();

        tvTitle.setText(bundle.getString("title"));
        tvPrice.setText(bundle.getString("price"));
        tvRating.setText(bundle.getString("rating"));
        tvDescription.setText(bundle.getString("description"));
    }
}
