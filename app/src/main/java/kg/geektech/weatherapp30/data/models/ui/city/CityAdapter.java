package kg.geektech.weatherapp30.data.models.ui.city;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.weatherapp30.common.OnItemClickListener;
import kg.geektech.weatherapp30.data.models.Weather;
import kg.geektech.weatherapp30.data.models.WeatherResponse;
import kg.geektech.weatherapp30.databinding.ItemCityBinding;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<WeatherResponse> weatherResponses = new ArrayList<>();

    public void setListener(OnItemClickListener<WeatherResponse> listener) {
        this.listener = listener;
    }

    private OnItemClickListener<WeatherResponse> listener;

    public void setWeatherResponses(List<WeatherResponse> weatherResponses) {
        this.weatherResponses = weatherResponses;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCityBinding binding = ItemCityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.onBind(weatherResponses.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(weatherResponses.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return weatherResponses.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {

        private final ItemCityBinding binding;

        public CityViewHolder(ItemCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(WeatherResponse weatherResponse) {
            binding.tvCityAp.setText(weatherResponse.getName());
        }
    }
}
