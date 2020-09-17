package org.geek8080.datafetch;


import com.google.gson.JsonObject;
import org.geek8080.datafetch.model.CountryData;
import org.geek8080.datafetch.model.GlobalData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Defines all APIs we will use
 */

public interface CovidAPI {

	@GET("https://coronavirus-19-api.herokuapp.com/all")
	Call<GlobalData> getGlobalData();

	@GET("https://coronavirus-19-api.herokuapp.com/countries/{countryName}")
	Call<CountryData> getCountryData(@Path(value = "countryName") String countryName);
}
