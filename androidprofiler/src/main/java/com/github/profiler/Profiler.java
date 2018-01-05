package com.github.profiler;

import android.support.annotation.NonNull;

/**
 * Created by zlove on 2018/1/5.
 */

public final class Profiler {
    private final String name;
    private final PhaseTreeDumper dumper;
    private PhaseTreeCollector collector;

    public Profiler(@NonNull String name) {
        this.name = name;
        this.collector = new PhaseTreeCollector(new Phase(name));
        this.dumper = new PhaseTreeDumper(name, 3, "!!! ", 64);
    }

    public void split(@NonNull String label) {
        collector.split(new Phase(label));
    }

    public void into(@NonNull String label) {
        collector.into(new Phase(label));
    }

    public void out() {
        collector.out();
    }

    public void dump() {
        dumper.dump(collector.tree);
    }

    public void reset() {
        this.collector = new PhaseTreeCollector(new Phase(name));
    }
}
