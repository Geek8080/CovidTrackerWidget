package org.geek8080.datafetch;

import org.geek8080.datafetch.model.CountryData;
import org.geek8080.datafetch.model.CovidDataModel;
import org.geek8080.datafetch.model.GlobalData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.CompletableFuture;

public class DataProviderService {
	public CovidDataModel getData(String CountryName){
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://coronavirus-19-api.herokuapp.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		CovidAPI covidAPI = retrofit.create(CovidAPI.class);

		CompletableFuture<GlobalData> callback1 = new CompletableFuture<>();
		covidAPI.getGlobalData().enqueue(new Callback<GlobalData>() {
			@Override
			public void onResponse(Call<GlobalData> call, Response<GlobalData> response) {
				callback1.complete(response.body());
			}

			@Override
			public void onFailure(Call<GlobalData> call, Throwable t) {
				callback1.completeExceptionally(t);
			}
		});

		CompletableFuture<CountryData> callback2 = new CompletableFuture<>();
		covidAPI.getCountryData("India").enqueue(new Callback<CountryData>() {
			@Override
			public void onResponse(Call<CountryData> call, Response<CountryData> response) {
				callback2.complete(response.body());
			}

			@Override
			public void onFailure(Call<CountryData> call, Throwable t) {
				callback2.completeExceptionally(t);
			}
		});

		GlobalData globalData = callback1.join();
		CountryData countryData = callback2.join();

		return new CovidDataModel(globalData, countryData);
	}
}
