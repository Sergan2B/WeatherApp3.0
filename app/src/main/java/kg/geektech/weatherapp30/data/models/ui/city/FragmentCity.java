package kg.geektech.weatherapp30.data.models.ui.city;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp30.base.BaseFragment;
import kg.geektech.weatherapp30.databinding.FragmentCitySearchBinding;

@AndroidEntryPoint
public class FragmentCity extends BaseFragment {

    private FragmentCitySearchBinding binding;
    private CityAdapter adapter;
    private FragmentCityDirections.ActionFragmentCityToFragmentWeatherInformation action;
    private FragmentCityArgs args;
    private CityViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CityAdapter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void callRequest() {

    }

    @Override
    public void setupObverse() {
    }

    @Override
    protected void setupListeners() {
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Prefs prefs = new Prefs(requireContext());
                prefs.saveCity(binding.citySearch.getText().toString());
                binding.citySearch.setText(args.getCityName());*/

                action = FragmentCityDirections.actionFragmentCityToFragmentWeatherInformation();
                action.setCityName(binding.citySearch.getText().toString());
                controller.navigate(action);
            }
        });
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void setupViews() {
    }

    @Override
    protected ViewBinding bind() {
        return FragmentCitySearchBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCitySearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}