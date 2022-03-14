package kg.geektech.weatherapp30.data.models.ui.weather;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp30.Prefs;
import kg.geektech.weatherapp30.R;
import kg.geektech.weatherapp30.base.BaseFragment;
import kg.geektech.weatherapp30.common.Resource;
import kg.geektech.weatherapp30.data.models.WeatherResponse;
import kg.geektech.weatherapp30.databinding.FragmentWeatherInformationBinding;

@AndroidEntryPoint
public class FragmentWeatherInformation extends BaseFragment<FragmentWeatherInformationBinding> {

    private FragmentWeatherInformationBinding binding;
    private FragmentWeatherInformationArgs args;
    private WeatherViewModel viewModel;

    public FragmentWeatherInformation() {
    }

    protected NavController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherInformationBinding.inflate(inflater, container, false);
        bind();
        controller = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setupListeners() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigateUp();
                controller.navigate(R.id.fragmentCity);
            }
        });
    }

    @Override
    protected void initViewModel() {
        // viewModel.getWeather(args.getCityName());
    }

    @Override
    protected void callRequest() {
        viewModel.getWeather(args.getCityName());
    }

    @Override
    protected void setupViews() {
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        args = FragmentWeatherInformationArgs.fromBundle(getArguments());
        String ars = args.getCityName();
        binding.textView.setText(ars);

        Prefs prefs = new Prefs(requireContext());
        String city = prefs.getCity();
        //binding.textView.setText(city);
    }

    @Override
    protected FragmentWeatherInformationBinding bind() {
        return FragmentWeatherInformationBinding.inflate(getLayoutInflater());

    }

    @Override
    protected void setupObverse() {
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<WeatherResponse>>() {

            @Override
            public void onChanged(Resource<WeatherResponse> weatherResponseResource) {
                switch (weatherResponseResource.status) {
                    case LOADING: {
                        break;
                    }
                    case SUCCESS: {
                        setupUi(weatherResponseResource.data);
                        Toast.makeText(requireContext(), "aleeee", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case ERROR: {
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }

            private void setupUi(WeatherResponse weather) {
                bindingTvText(weather);
                glideIv(weather);
            }

            private void glideIv(WeatherResponse weather) {
                Glide.with(requireActivity())
                        .load("https://openweathermap.org/img/wn/" +
                                weather.getWeather().get(0).getIcon() + "@2x.png")
                        .into(binding.imageView24);

            }

            @SuppressLint("SetTextI18n")
            private void bindingTvText(WeatherResponse weather) {
                binding.tvTemp.setText(weather.getMain().getTemp().toString());
                binding.tvSunset.setText(getData(weather.getSys().getSunset(), "hh:mm"));
                binding.tvSunrise.setText(getData(weather.getSys().getSunrise(), "hh:mm"));
                binding.tvHighestTemp.setText(weather.getMain().getTempMax().toString());
                binding.tvLowestTemp.setText(weather.getMain().getTempMin().toString());
                binding.tvPressure.setText(weather.getMain().getPressure().toString());
                binding.tvHumidity.setText(weather.getMain().getHumidity().toString());
                binding.tvWind.setText(weather.getWind().getSpeed().toString());
                binding.tvSunny.setText(weather.getWeather().get(0).getDescription());
                binding.tvTime.setText(getData(weather.getTimezone(), "dd:mm:yyyy"));
                //binding.tvTime.setText(DateFormat.getDateTimeInstance().format(new Date(millis)) + "   ");
            }

            public String getData(Integer milliSeconds, String dateFormat) {
                SimpleDateFormat formatter = new SimpleDateFormat();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milliSeconds);
                return formatter.format(calendar.getTime());
            }
        });
    }
}


//JSONVIEWER