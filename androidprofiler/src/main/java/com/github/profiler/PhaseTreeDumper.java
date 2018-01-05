package com.github.profiler;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by zlove on 2018/1/5.
 */

class PhaseTreeDumper {

    private final long slowThreshold;
    private final String slowPrefix;
    @NonNull
    private final String logTag;
    private final int indentSpaceCount;

    PhaseTreeDumper(@NonNull String logTag, int indentSpaceCount, String slowPrefix, long slowThreshold) {
        this.logTag = logTag;
        this.slowThreshold = slowThreshold;
        this.indentSpaceCount = indentSpaceCount;
        this.slowPrefix = slowPrefix;
    }

    void dump(@NonNull PhaseTree tree) {
        final long end = System.currentTimeMillis();
        final long start = tree.root.timestamp;
        final long total = end - start;

        dump(tree.root, total, start, total, 0);
    }

    private void dump(@NonNull PhaseTree.Node node, long totalDuration, long start, long phaseDuration, int indentLevel) {
        // format: [indent] [name]: [duration], [percent]
        final int logLevel = phaseDuration > slowThreshold ? Log.WARN : Log.DEBUG;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel * indentSpaceCount; i++) {
            sb.append(' ');
        }
        if (phaseDuration > slowThreshold) {
            sb.append(slowPrefix);
        }
        sb.append(node.phase.name);
        sb.append(": ");
        sb.append(phaseDuration);
        if (totalDuration > 0) {
            final long percent = phaseDuration * 100 / totalDuration;
            if (percent > 0) {
                sb.append(", ").append(percent).append('%');
            }
        }
        Log.println(logLevel, logTag, sb.toString());

        if (node.children != null) {
            for (PhaseTree.Node child : node.children) {
                final long end = child.getEndTimestamp();
                long childDuration = end - start;
                dump(child, totalDuration, start, childDuration, indentLevel + 1);
                start = end;
            }
        }
    }
}
