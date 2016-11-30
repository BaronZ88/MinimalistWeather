//package me.baron.weatherapi;
//
//import com.facebook.stetho.okhttp3.StethoInterceptor;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//
///**
// * @author 张磊 (baronzhang[at]anjuke[dot]com)
// *         2016/11/30
// */
//@Module
//public class ApiModule {
//
//    public ApiModule() {
//
//    }
//
//    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient() {
//
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        return new OkHttpClient().newBuilder()
//                .addInterceptor(httpLoggingInterceptor)
//                .addNetworkInterceptor(new StethoInterceptor())
//                .build();
//    }
//}
