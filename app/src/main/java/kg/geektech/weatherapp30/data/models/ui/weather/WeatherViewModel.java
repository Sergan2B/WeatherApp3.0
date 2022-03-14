package kg.geektech.weatherapp30.data.models.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp30.common.Resource;
import kg.geektech.weatherapp30.data.models.WeatherResponse;
import kg.geektech.weatherapp30.data.repositories.MainRepository;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private MainRepository repository;
    public LiveData<Resource<WeatherResponse>> liveData;

    @Inject
    public WeatherViewModel(MainRepository repository) {
        this.repository = repository;
    }


    public void getWeather(String city) {
        liveData = repository.getWeather(city);
    }
}
