package com.correct.score.network;


import com.correct.score.model.DatewiseResponse;
import com.correct.score.footballtips.category.CategoryListResponse;
import com.correct.score.footballtips.ListResponse;
import com.correct.score.footballtips.details.DetailsReponse;
import com.correct.score.oldtips.OldTipsResponse;
import com.correct.score.todaytips.TodayTipsResponse;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @GET("api/category-details/{id}")
    Call<DetailsReponse> getTipsDetails(
            @Path("id")int id
            );

    @GET("api/categories")
    Call<CategoryListResponse> getGameCatResponse();
    @GET("api/today-tips")
    Call<TodayTipsResponse> todayResponse();

    @GET("api/old-tips")
    Call<OldTipsResponse> OldResponse();
    @POST("api/date-wise-tips")
    @FormUrlEncoded
    Call<DatewiseResponse> DateResponse(
            @Field("date") String date
    );

}
