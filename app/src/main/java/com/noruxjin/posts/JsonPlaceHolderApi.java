package com.noruxjin.posts;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {


    @GET("posts")
    Call<List<Post>> getpost(@Query("userId") Integer userId,
                             @Query("_sort") String sort,
                             @Query("_order") String order);

    @GET("posts")
    Call<List<Post>> getpost(@QueryMap Map<String,String> parameters);

    @GET("comments?postId")
    Call<List<Comment>> getComment(@Query("postId") Integer [] postId,
                                   @Query("_sort") String sort,
                                   @Query("_order") String order);

    @GET
    Call<List<Comment>> getComment (@Url String url);


}
