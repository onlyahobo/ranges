package com.onlyahobo.ranges.overlapping;

import static com.google.common.base.Preconditions.checkArgument;

class Range implements Comparable<Range> {

    private final int from;

    private final int to;

    private final boolean leftOpen;

    private final boolean rightOpen;

    Range(int from, int to, boolean leftOpen, boolean rightOpen) {
        checkArgument(from != to, String.format("Illegal single-value interval passed: %s-%s", from, to));
        if (from > to) {
            this.to = from;
            this.from = to;
        } else {
            this.from = from;
            this.to = to;
        }
        this.leftOpen = leftOpen;
        this.rightOpen = rightOpen;
    }

    boolean overlapWithRangeStartingFurther(Range o) {
        return this.to > o.from || this.shareCommonBorderPoint(o);
    }

    private boolean shareCommonBorderPoint(Range o) {
        return (this.from == o.to && leftClosed() && o.rightClosed()) || (this.to == o.from && rightClosed() && o.leftClosed());
    }

    private boolean leftClosed() {
        return !leftOpen;
    }

    private boolean rightClosed() {
        return !rightOpen;
    }

    @Override public int compareTo(final Range o) {
        final int result = Integer.compare(to, o.to);
        return result != 0 ? result : Boolean.compare(rightClosed(), o.rightClosed());
    }
}
