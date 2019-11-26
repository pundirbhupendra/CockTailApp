package com.example.cocktailapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;


import com.example.cocktailapp.View.ImageViewSquare;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

      private List<CockTailModal> cockTailModalList;
      private Context context;
      HomeListAdapter(Context applicationContext, List<CockTailModal> cockTailModalList){
                this.cockTailModalList=cockTailModalList;
                this.context =applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

         final CockTailModal cockTailModal=cockTailModalList.get(position);

         holder.name.setText(cockTailModal.getDrinkName());

        Glide.with(holder.imageViewSquare.getContext()).load(cockTailModal.getDrinkImage()).into(holder.imageViewSquare);

       holder.imageViewSquare.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent= new Intent(context,CockTailDeTail.class);
               intent.putExtra("CockTailImage",cockTailModal.getDrinkImage());
               intent.putExtra("CockTailName",cockTailModal.getDrinkName());
               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return cockTailModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageViewSquare imageViewSquare;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSquare =itemView.findViewById(R.id.cocktail_cover_image);
            name =itemView.findViewById(R.id.cocktail_name);
        }
    }
}
