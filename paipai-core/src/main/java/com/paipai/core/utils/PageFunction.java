package com.paipai.core.utils;

import java.util.List;
import java.util.Objects;

@FunctionalInterface
public interface PageFunction<T> {

    void accept(List<T> ts);

    default void apply(List<T> ts, int pageSize) {
        Objects.requireNonNull(ts);
        int totalSize = ts.size();
        int pageNum = (totalSize + pageSize - 1) / pageSize;
        for (int i = 0; i < pageNum; i++) {
            int start = i * pageSize;
            int end = start + pageSize;
            if (end > totalSize) {
                end = totalSize;
            }
            this.accept(ts.subList(start, end));
        }
    }
}
