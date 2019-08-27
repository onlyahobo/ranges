package com.onlyahobo.ranges.overlapping;

class Range {

    private final int from;

    private final int to;

    private final boolean leftOpen;

    private final boolean rightOpen;

    Range(int from, int to, boolean leftOpen, boolean rightOpen) {
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

    boolean overlapWith(Range otherRange) {
        return overlapWithNotConsideringRangeClosedness(otherRange) || overlapWithBecauseOfRangeClosedness(otherRange);
    }

    private boolean overlapWithNotConsideringRangeClosedness(Range otherRange) {
        return this.from < otherRange.to && this.to > otherRange.from;
    }

    private boolean overlapWithBecauseOfRangeClosedness(Range otherRange) {
        return (this.from == otherRange.to && leftClosed() && otherRange.rightClosed())
            || (this.to == otherRange.from && rightClosed() && otherRange.leftClosed());
    }

    private boolean leftClosed() {
        return !leftOpen;
    }

    private boolean rightClosed() {
        return !rightOpen;
    }

}
