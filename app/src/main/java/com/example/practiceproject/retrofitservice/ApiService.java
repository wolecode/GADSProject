package com.example.practiceproject.retrofitservice;

import com.example.practiceproject.learners_iq.IQLeaders;
import com.example.practiceproject.learners.LearningLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("api/hours")
    public Call<List<LearningLeaders>> getLearningLeaders();

    @GET("api/skilliq")
    public Call<List<IQLeaders>>getIQLeaders();

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    public Call<Void>submitForm(@Field("entry.1877115667") String firstName,
                                @Field("entry.2006916086") String LastName,
                                @Field("entry.1824927963") String email,
                                @Field("entry.284483984") String projectLink);

}
