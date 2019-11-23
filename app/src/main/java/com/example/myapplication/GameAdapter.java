package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameAdapter extends ArrayAdapter{
    private ArrayList<GameModel> data;
    private Context mContext;

    public GameAdapter(Context c, ArrayList<GameModel> data){
        super(c,R.layout.single_listview_item,data);
        this.data=data;
        this.mContext=c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v ;
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            v= LayoutInflater.from(mContext).inflate(R.layout.single_listview_item,null);

            holder.tvTitle=v.findViewById(R.id.tvTitle);
            holder.tvRating=v.findViewById(R.id.tvRating);
            holder.tvPrice=v.findViewById(R.id.tvPrice);

            v.setTag(holder);

        }else {
            holder=(ViewHolder)convertView.getTag();
            v=convertView;
        }
        GameModel model=(GameModel)getItem(position);

        holder.tvTitle.setText(model.getTitle());
        holder.tvRating.setText(model.getRating());
        holder.tvPrice.setText(model.getPrice());

        return v;
    }

    class ViewHolder{
        TextView tvTitle,tvRating,tvPrice;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return data.get(position);

    }
}
