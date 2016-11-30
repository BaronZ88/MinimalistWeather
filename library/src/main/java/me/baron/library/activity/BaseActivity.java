package me.baron.library.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
