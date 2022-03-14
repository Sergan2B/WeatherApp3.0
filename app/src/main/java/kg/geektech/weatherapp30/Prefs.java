package kg.geektech.weatherapp30;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void  saveCity(String city) {
        preferences.edit().putString("city", city).apply();
    }

    public String getCity() {
        return  preferences.getString("city", "Moscow");
    }
}
