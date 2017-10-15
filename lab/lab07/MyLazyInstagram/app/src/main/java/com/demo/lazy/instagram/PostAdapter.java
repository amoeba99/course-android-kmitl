package com.demo.lazy.instagram;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.lazy.instagram.model.Posts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amoeba on 10/6/2017.
 */

class Holder extends RecyclerView.ViewHolder {


    private ImageView image;
    private TextView comment;
    private TextView likes;
    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
        comment = itemView.findViewById(R.id.comment);
        likes = itemView.findViewById(R.id.likes);
    }

    public static class PostAdapter extends RecyclerView.Adapter<Holder> {

        private List<Posts> data;
        private Context context;
        private int layout;

        public PostAdapter(Context context) {
            this.context = context;
        }


        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, null, false);
            Holder holder = new Holder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
                Glide.with(context).load(this.data.get(position).getUrl()).into(holder.image);
                holder.comment.setText("comment: " + Integer.toString(this.data.get(position).getComment()));
                holder.likes.setText("likes: " + Integer.toString(this.data.get(position).getLike()));
        }

        @Override
        public int getItemCount() {
            if (this.data != null)
                return data.size();
            return 0;
        }

        public List<Posts> getData() {
            return data;
        }

        public void setData(List<Posts> data) {
            this.data = data;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public int getLayout() {
            return layout;
        }

        public void setLayout(int layout) {
            this.layout = layout;
        }
    }


}
