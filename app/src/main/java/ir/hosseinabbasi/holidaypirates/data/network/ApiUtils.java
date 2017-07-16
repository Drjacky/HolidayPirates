package ir.hosseinabbasi.holidaypirates.data.network;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abbasi on 7/15/2017.
 */

public class ApiUtils {

    public static ApiHelper getJsonPlaceHolderService() {
        return getClient(ApiEndPoint.ENDPOINT_JSONPLACEHOLDER).create(ApiHelper.class);
    }

    public static Retrofit getClient(String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        return retrofit;
    }
}
