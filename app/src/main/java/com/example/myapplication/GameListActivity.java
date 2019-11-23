package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GameListActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<GameModel> data=new ArrayList<>();
    private OkHttpClient okHttpClient=new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        getDataFromApi();
    }

    private void findViews(){
        listview=findViewById(R.id.game_list);
        GameAdapter adapter=new GameAdapter(this,data);
        listview.setAdapter(adapter);
    }
    private void setListener(){
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameModel selectedItem=(GameModel)listview.getAdapter().getItem(position);
                Intent i=new Intent(GameListActivity.this,GameDetailActivity.class);
                i.putExtra("title",selectedItem.getTitle());
                i.putExtra("rating",selectedItem.getRating());
                i.putExtra("price",selectedItem.getPrice());
                i.putExtra("description",selectedItem.getDescription());
                startActivity(i);
            }
        });
    }
    private void initialize(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                findViews();
                setListener();
            }
        });
    }
    private void getDataFromApi(){
        Request request=new Request.Builder().url("https://api.myjson.com/bins/11792e").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    JSONObject dataObject = new JSONObject(response.body().string());
                    JSONArray dataArray=dataObject.getJSONArray("data");
                    for(int i=0;i<dataArray.length();i++){
                        JSONObject singleObject=dataArray.getJSONObject(i);
                        GameModel model=new GameModel();
                        model.setTitle(singleObject.getString("name"));
                        model.setRating(singleObject.getString("rating"));
                        model.setPrice(singleObject.getString("price"));
                        model.setDescription(singleObject.getString("description"));

                        data.add(model);
                        initialize();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
