package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<ModelClass> listItems;
    private Context context;

    public MyAdapter(List<ModelClass> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.ViewHolder holder, int position) {
        ModelClass modelClass=listItems.get(position);
        holder.txthead.setText(modelClass.getHead());
        holder.txtdesc.setText(modelClass.getDesc());

     //Picasso.with(context).load(listItems.);
        Picasso.with(context).load(modelClass.getImageUrl()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txthead;
        public  TextView txtdesc;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txthead=itemView.findViewById(R.id.textheading);
            txtdesc=itemView.findViewById(R.id.textdescription);
            imageView=itemView.findViewById(R.id.imageview);
        }
    }
}
