package com.player.movie.http;

import com.player.movie.api.Api;
import com.player.movie.entity.UserEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestService {

    @POST(Api.REGISTER)
    Call<UserEntity> register(@Body UserEntity user);

    @POST(Api.LOGIN)
    Call<UserEntity> login(@Body UserEntity user);

    @GET(Api.GETUSERDATA)
    Call<ResultEntity> getUserData();

    @GET(Api.GETCATEGORYLIST)
    Call<ResultEntity> getCategoryList(@Query("category")String category,@Query("classify")String classify);

    @GET(Api.GETALLCATEGORYLISTBYPAGENAME)
    Call<ResultEntity> getAllCategoryListByPageName(@Query("pageName")String pageName);


}
