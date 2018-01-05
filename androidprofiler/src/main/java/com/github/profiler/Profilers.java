package com.github.profiler;

import android.support.annotation.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zlove on 2018/1/5.
 */

public final class Profilers {
    private static Map<String, Profiler> sProfilerMap = new ConcurrentHashMap<>();
    private static final String SUFFIX = "Profiler";

    public static Profiler get(@NonNull final String name) {
        final String fullName = name + SUFFIX;
        Profiler profiler = sProfilerMap.get(fullName);
        if (profiler == null) {
            profiler = new Profiler(fullName);
            sProfilerMap.put(fullName, profiler);
        }
        return profiler;
    }

    public static Profiler startup() {
        return get("startup");
    }
}
