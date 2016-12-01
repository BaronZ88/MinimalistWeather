package me.baron.weatherstyle;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/11/30
 */
@Module
class AppModule {

    private Application application;

    AppModule(Application application) {

        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {

        return application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {

        return application.getApplicationContext();
    }
}
