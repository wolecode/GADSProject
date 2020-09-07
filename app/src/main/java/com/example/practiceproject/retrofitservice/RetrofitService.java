package com.example.practiceproject.retrofitservice;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static String baseUrl="https://gadsapi.herokuapp.com/";
    private static String baseUrlForm="https://docs.google.com/forms/d/e/";
    //https://www.googleapis.com/books/v1/volumes/?q=android
   // https://gadsapi.herokuapp.com/

    private static HttpLoggingInterceptor intercept= new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient okClient=new OkHttpClient.Builder()
                           .addInterceptor(intercept)
                           .build();

    public static Retrofit retrofit= new Retrofit.Builder()
                       .baseUrl(baseUrl)
                       .addConverterFactory(GsonConverterFactory.create())
                        .client(okClient)
                        .build();

    public static Retrofit retrofitForm=new Retrofit.Builder()
                                        .baseUrl(baseUrlForm)
                                         .addConverterFactory(GsonConverterFactory.create())
                                          .client(okClient)
                                           .build();

    public static <T> T getApiService(Class<T> apiService){
        return retrofit.create(apiService);
    }

    public static <T> T getFormApiService(Class<T> apiService){
        return retrofitForm.create(apiService);
    }
}
