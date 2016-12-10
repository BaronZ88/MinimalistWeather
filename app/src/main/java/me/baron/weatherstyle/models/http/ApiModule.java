//package me.baron.weatherstyle.model.http;
//
//import com.facebook.stetho.okhttp3.StethoInterceptor;
//
//import dagger.Module;
//import dagger.Provides;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//
///**
// * @author 张磊 (baronzhang[at]anjuke[dot]com)
// *         2016/12/1
// */
//@Module
//public class ApiModule {
//
//    @Provides
//    OkHttpClient provideOkHttpClient(){
//
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        return new OkHttpClient().newBuilder()
//                .addInterceptor(httpLoggingInterceptor)
//                .addNetworkInterceptor(new StethoInterceptor())
//                .build();
//    }
//
////    @Provides
////    Retrofit provideRetrofit(OkHttpClient client, Class){
////
////        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl(baseUrl)
////                .addConverterFactory(FastJsonConverterFactory.create())
////                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
////                .client(client)
////                .build();
////
////        return retrofit.create(clazz);
////    }
//}
