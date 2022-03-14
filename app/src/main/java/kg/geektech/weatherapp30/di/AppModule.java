package kg.geektech.weatherapp30.di;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.weatherapp30.data.remote.RetrofitApi;
import kg.geektech.weatherapp30.data.remote.RetrofitClient;
import kg.geektech.weatherapp30.data.repositories.MainRepository;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    public static OkHttpClient provide() {
        return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    public static RetrofitApi provideApi(Retrofit retrofit) {
        return retrofit.create(RetrofitApi.class);
    }

    @Provides
    public static MainRepository provideMainRepository(RetrofitApi api, RetrofitClient client) {
        return new MainRepository(api, client);
    }

    @Provides
    @Singleton
    public static RetrofitClient provideRetrofitClient() {
        return new RetrofitClient();
    }
}
