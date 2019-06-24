package com.noruxjin.posts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.postTxtViewId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //getPosts ();
        //getComments();
        createPost();

    }

    private void getComments (){

        //Call<List<Comment>> call = jsonPlaceHolderApi.getComment(new Integer[] {5,6,7}, "id","desc");
        Call<List<Comment>> call = jsonPlaceHolderApi.getComment("comments?postId=7");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()){

                    result.setText("Code: " + response.code());

                }

                List <Comment> comments = response.body();

                for (Comment comment : comments){

                    String content = "";

                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "E-mail: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    result.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                result.setText(t.getMessage());
                return;

            }
        });
    }

    private void getPosts (){

        Map<String,String> parameters = new HashMap<>();

        parameters.put("userId","7");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Post>> call = jsonPlaceHolderApi.getpost(parameters);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){

                    result.setText("Code: " + response.code());
                    return;

                }

                List <Post> posts = response.body();

                for (Post post :posts){

                    String content = "";

                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Post ID: " + post.getId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    result.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                result.setText(t.getMessage());

            }
        });

    }

    private void createPost(){

        //Post post = new Post(27, "Brok's laugh", "yohohohohoho");

        Map<String,String> fields = new HashMap<>();
        fields.put("userId", "32");
        fields.put("title", "boooooooooh");
        fields.put("body", "yohohohohohoho");

        Call<Post> call = jsonPlaceHolderApi.creatPost(fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()){

                    result.setText("Code: " + response.code());
                    return;

                }

                Post postResponse = response.body();



                    String content = "";

                    content += "Code: " + response.code() + "\n";
                    content += "User ID: " + postResponse.getUserId() + "\n";
                    content += "Post ID: " + postResponse.getId() + "\n";
                    content += "Title: " + postResponse.getTitle() + "\n";
                    content += "Text: " + postResponse.getText() + "\n\n";

                    result.setText(content);



            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                result.setText(t.getMessage());

            }
        });

    }

}
