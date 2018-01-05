package com.github.profiler;

import android.support.annotation.NonNull;

/**
 * Created by zlove on 2018/1/4.
 */

class PhaseTreeCollector {
    private static final String TAIL_NODE_NAME = "~tail";

    @NonNull
    PhaseTree tree;
    @NonNull
    PhaseTree.Node current;

    PhaseTreeCollector(@NonNull Phase phase) {
        this.tree = new PhaseTree(phase);
        this.current = this.tree.root;
    }

    /**
     * 添加叶子步骤.
     */
    synchronized void split(@NonNull Phase phase) {
        tree.addTo(phase, true, current);
    }

    /**
     * 添加枝步骤，并进入子分支.
     */
    synchronized void into(@NonNull Phase phase) {
        current = tree.addTo(phase, false, current);
    }

    /**
     * 退回上级分支.
     */
    synchronized void out() {
        if (current.parent == null) {
            return;
        }
        tree.addTo(new Phase(TAIL_NODE_NAME), true, current);
        current = current.parent;
    }
}
