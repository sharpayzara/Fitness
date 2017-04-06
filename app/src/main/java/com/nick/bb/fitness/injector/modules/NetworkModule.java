package com.nick.bb.fitness.injector.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nick.bb.fitness.AndroidApplication;
import com.nick.bb.fitness.api.interceptor.LoggingInterceptor;
import com.nick.bb.fitness.injector.scope.PerApplication;
import com.nick.bb.fitness.repository.Repository;
import com.nick.bb.fitness.repository.impl.RepositoryImpl;
import com.nick.bb.fitness.util.Constants;
import com.nick.bb.fitness.util.FileUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sharpay on 17-3-22.
 */
@Module
public class NetworkModule {
    private final AndroidApplication mApplication;

    public NetworkModule(AndroidApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @PerApplication
    Repository provideRepository(@Named("user") Retrofit userRepos) {
        return new RepositoryImpl(mApplication, userRepos);
    }

    @Provides
    @Named("user")
    @PerApplication
    Retrofit provideUserRetrofit(){
        String endpointUrl = Constants.BASE_API_URL_USER;
        Gson gson = new GsonBuilder().create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(new Cache(FileUtil.getHttpCacheDir(mApplication), Constants.HTTP_CACHE_SIZE))
                .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
//        OkHttpClient newClient = client.newBuilder().addInterceptor(loggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpointUrl)
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
