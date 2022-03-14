package kg.geektech.weatherapp30.data.repositories;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import kg.geektech.weatherapp30.common.Resource;
import kg.geektech.weatherapp30.data.models.WeatherResponse;
import kg.geektech.weatherapp30.data.models.ui.city.CityAdapter;
import kg.geektech.weatherapp30.data.models.ui.city.CityViewModel;
import kg.geektech.weatherapp30.data.models.ui.city.FragmentCity;
import kg.geektech.weatherapp30.data.remote.RetrofitApi;
import kg.geektech.weatherapp30.data.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private RetrofitApi api;
    private RetrofitClient client;

    @Inject
    public MainRepository(RetrofitApi api, RetrofitClient client) {
        this.api = api;
        this.client = client;
    }

    public MutableLiveData<Resource<WeatherResponse>> getWeather(String city) {
        MutableLiveData<Resource<WeatherResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather(city, "a5f0ece2d473d3a6319e91cef81147cb", "metric", "ru").
                enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }
}
