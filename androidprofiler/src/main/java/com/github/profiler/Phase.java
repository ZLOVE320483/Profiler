package com.github.profiler;

import android.support.annotation.NonNull;

/**
 * Created by zlove on 2018/1/4.
 */

class Phase {
    @NonNull
    final String name;

    Phase(@NonNull final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Phase phase = (Phase) o;

        return name.equals(phase.name);
    }

    @Override
    public String toString() {
        return "Phase{" +
                "name='" + name + '\'' +
                '}';
    }
}
