package com.example.cocktailapp;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class CockTailDeTail extends AppCompatActivity {

    Boolean like =false;
    int counter =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cock_tail_de_tail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ImageView cockTailDetail = findViewById(R.id.full_image);
        final ImageView likeImage = findViewById(R.id.like);
        final TextView likeCounter = findViewById(R.id.like_counter);
        likeCounter.setText("0");

        TextView nameImage = findViewById(R.id.name_of_cocktail);

        Intent intent = getIntent();
        String imageViewSquare = intent.getStringExtra("CockTailImage");
        String name = intent.getStringExtra("CockTailName");
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(name);

        Glide.with(getApplicationContext()).load(imageViewSquare).into(cockTailDetail);
        nameImage.setText(name);


        likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (like) {
                    likeImage.setImageResource(R.drawable.ic_action_like);
                    like = false;
                     decr(likeCounter);

                } else {
                    likeImage.setImageResource(R.drawable.ic_user_liked);
                    like = true;
                      inc(likeCounter);
                }
            }
        });
    }
        void inc(final TextView likeCounter){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    counter = counter + 1;
                     likeCounter.post(new Runnable() {

                            @Override
                            public void run() {

                                likeCounter.setText(" "+counter);

                            }
                        });

                    }
            }).start();

        }
    void decr(final TextView likeCounter){
        new Thread(new Runnable() {
            @Override
            public void run() {
                counter = counter-1;
                likeCounter.post(new Runnable() {

                    @Override
                    public void run() {

                        likeCounter.setText(" "+counter);

                    }
                });

            }
        }).start();

    }

    }



