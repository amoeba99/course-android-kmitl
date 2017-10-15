package com.demo.lazy.instagram;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

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

        private ArrayList<String> dataImg = new ArrayList<>();
        private ArrayList<Integer> dataComment = new ArrayList<>();
        private ArrayList<Integer> dataLikes = new ArrayList<>();
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
            Glide.with(context).load(dataImg.get(position).toString()).into(holder.image);
            holder.comment.setText("comment: "+Integer.toString(dataComment.get(position)));
            holder.likes.setText("likes: "+Integer.toString(dataLikes.get(position)));
        }

        @Override
        public int getItemCount() {
            if (dataImg != null)
                return dataImg.size();
            return 0;
        }


        public ArrayList<String> getData() {
            return dataImg;
        }

        public void setData(ArrayList<String> data) {
            this.dataImg = data;
        }

        public void addData(String url, int comment, int likes) {
            dataImg.add(url);
            dataComment.add(comment);
            dataLikes.add(likes);
        }

        public void clearList() {
            dataImg.clear();
            dataComment.clear();
            dataLikes.clear();

        }

        public ArrayList<String> getDataImg() {
            return dataImg;
        }

        public void setDataImg(ArrayList<String> dataImg) {
            this.dataImg = dataImg;
        }

        public ArrayList<Integer> getDataComment() {
            return dataComment;
        }

        public void setDataComment(ArrayList<Integer> dataComment) {
            this.dataComment = dataComment;
        }

        public ArrayList<Integer> getDataLikes() {
            return dataLikes;
        }

        public void setDataLikes(ArrayList<Integer> dataLikes) {
            this.dataLikes = dataLikes;
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
