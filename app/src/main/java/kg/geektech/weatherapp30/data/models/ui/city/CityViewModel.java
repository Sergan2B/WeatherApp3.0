package kg.geektech.weatherapp30.data.models.ui.city;

import androidx.lifecycle.LiveData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp30.common.Resource;
import kg.geektech.weatherapp30.data.models.WeatherResponse;
import kg.geektech.weatherapp30.data.repositories.MainRepository;


public class CityViewModel {

    private MainRepository mainRepository;
    public LiveData<Resource<WeatherResponse>> liveData;

    @Inject
    public CityViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }
}
