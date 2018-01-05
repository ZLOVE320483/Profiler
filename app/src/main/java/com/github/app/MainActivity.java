package com.github.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.profiler.Profiler;
import com.github.profiler.Profilers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Profiler profiler = Profilers.startup();
        profiler.into("MainActivity");
        profiler.into("onCreate");
        super.onCreate(savedInstanceState);
        profiler.split("superOnCreate");
        profiler.split("setActivityCreate");
        setContentView(R.layout.activity_main);
        profiler.split("setContentView");
        profiler.dump();
    }
}
