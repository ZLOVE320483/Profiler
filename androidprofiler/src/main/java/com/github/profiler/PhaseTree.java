package com.github.profiler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlove on 2018/1/4.
 */

class PhaseTree {
    @NonNull
    Node root;

    PhaseTree(@NonNull Phase phase) {
        root = new Node(phase, false, null);
    }

    Node addTo(@NonNull Phase child, boolean isLeaf, @NonNull Node parent) {
        Node node = new Node(child, isLeaf, parent);
        if (parent.children == null) {
            throw new IllegalStateException("Cannot add child to leaf node " + parent);
        }
        parent.children.add(node);
        return node;
    }

    static class Node {
        @NonNull
        final Phase phase;

        @Nullable
        final Node parent;
        @Nullable
        final List<Node> children;
        final long timestamp;

        Node(@NonNull Phase phase, boolean isLeaf, @Nullable Node parent) {
            this.phase = phase;
            this.parent = parent;
            this.children = isLeaf ? null : new ArrayList<Node>();
            this.timestamp = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            final String parentPhase = parent != null ? parent.phase.name : null;
            return "Node{" +
                    "phase=" + phase +
                    ", parent=" + parentPhase +
                    ", children=" + children +
                    ", timestamp=" + timestamp +
                    '}';
        }

        long getEndTimestamp() {
            return children == null ? timestamp : children.get(children.size() - 1).timestamp;
        }
    }
}
