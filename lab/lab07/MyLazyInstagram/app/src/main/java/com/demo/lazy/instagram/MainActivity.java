package com.demo.lazy.instagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.lazy.instagram.api.LazyInstragramAPI;
import com.demo.lazy.instagram.api.UserProfile;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private Holder.PostAdapter postAdapter = new Holder.PostAdapter(this);

    private String user = "nature";
    private String mode = "grid";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        postAdapter.setLayout(R.layout.item_grid);
        refresh(user, mode);
    }

    public void refresh(String user, String mode){
        getUserProfile(user);
        if(mode.equals("grid"))
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.android:
                user = "android";
                refresh(user, mode);
                return true;
            case R.id.nature:
                user = "nature";
                refresh(user, mode);
                return true;
            case R.id.cartoon:
                user = "cartoon";
                refresh(user, mode);
                return true;
            case R.id.switch_mode:
                if(mode.equals("grid")){
                    mode = "list";
                    postAdapter.setLayout(R.layout.item_list);
                }else {
                    mode = "grid";
                    postAdapter.setLayout(R.layout.item_grid);
                }
                refresh(user, mode);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUserProfile(String name) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstragramAPI.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LazyInstragramAPI api = retrofit.create(LazyInstragramAPI.class);
        api.getProfile(name).enqueue(new retrofit2.Callback<UserProfile>() {
            @Override
            public void onResponse(retrofit2.Call<UserProfile> call, retrofit2.Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    UserProfile result = response.body();
                    TextView textUser = findViewById(R.id.textUser);
                    TextView textPost = findViewById((R.id.textPost));
                    TextView textFollower = findViewById(R.id.textFollower);
                    TextView textFollowing = findViewById(R.id.textFollwing);
                    TextView textBio = findViewById(R.id.textBio);
                    ImageView imageProfile =  findViewById(R.id.imageProfile) ;
                    textUser.setText("@"+result.getUser());
                    textPost.setText("Post\n"+Integer.toString(result.getPost()));
                    textFollower.setText("Follwer\n"+Integer.toString(result.getFollower()));
                    textFollowing.setText("Following\n"+Integer.toString(result.getFollowing()));
                    textBio.setText(result.getBio());
                    postAdapter.setData(result.getPosts());
                    recyclerView.setAdapter(postAdapter);
                    Glide.with(MainActivity.this).load(result.getUrlProfile()).into(imageProfile);
                }
            }
            @Override
            public void onFailure(retrofit2.Call<UserProfile> call, Throwable t) {

            }
        });
    }

}
