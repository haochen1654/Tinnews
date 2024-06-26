package com.henry.tinnews.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.henry.tinnews.model.NewsResponse;
import com.henry.tinnews.network.NewsApi;
import com.henry.tinnews.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
  private final NewsApi newsApi;

  public NewsRepository(Context context) {
    newsApi = RetrofitClient.newInstance(context).create(NewsApi.class);
  }

  public LiveData<NewsResponse> getTopHeadlines(String country) {
    MutableLiveData<NewsResponse> topHeadlinesLiveData = new MutableLiveData<>();
    newsApi
        .getTopHeadlines(country)
        .enqueue(
            new Callback<NewsResponse>() {
              @Override
              public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                topHeadlinesLiveData.setValue(response.isSuccessful() ? response.body() : null);
              }

              @Override
              public void onFailure(Call<NewsResponse> call, Throwable t) {
                topHeadlinesLiveData.setValue(null);
              }
            });
    return topHeadlinesLiveData;
  }

  public LiveData<NewsResponse> searchNews(String query) {
    MutableLiveData<NewsResponse> everyThingLiveData = new MutableLiveData<>();
    newsApi
        .getEverything(query, 40)
        .enqueue(
            new Callback<NewsResponse>() {
              @Override
              public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                everyThingLiveData.setValue(response.isSuccessful() ? response.body() : null);
              }

              @Override
              public void onFailure(Call<NewsResponse> call, Throwable t) {
                everyThingLiveData.setValue(null);
              }
            });
    return everyThingLiveData;
  }
}
