package kg.geektech.weatherapp30;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp30.data.remote.RetrofitApi;
import kg.geektech.weatherapp30.databinding.ActivityMainBinding;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    //DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
    private ActivityMainBinding binding;

    @Inject
    RetrofitApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}