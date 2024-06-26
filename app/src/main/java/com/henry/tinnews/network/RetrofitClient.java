package com.henry.tinnews.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
  private static final String API_KEY = "5739ca347c374f838cbba0a6adcbf8e0";
  private static final String BASE_URL = "https://newsapi.org/v2/";

  public static Retrofit newInstance(Context context) {
    OkHttpClient okHttpClient =
        new OkHttpClient.Builder().addInterceptor(new HeaderInterceptor()).build();
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build();
  }

  private static class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Request original = chain.request();
      Request request = original.newBuilder().header("X-Api-Key", API_KEY).build();
      return chain.proceed(request);
    }
  }
}
