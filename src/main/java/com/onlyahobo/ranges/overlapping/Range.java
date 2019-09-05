package com.onlyahobo.ranges.overlapping;

import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.Comparator;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

@Getter
class Range implements Comparable<Range> {

    private static final Comparator<Range> FURTHER_CLOSING = comparingInt(Range::getTo).thenComparing(Range::rightClosed);

    private static final Comparator<Range> LOWER_STARTING = comparing(Range::getFrom).thenComparing(Range::isLeftOpen);

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

    static Range sumAndGet(Range left, Range right) {
        final var lowerStartingRange = left.getLowerStarting(right);
        return new Range(lowerStartingRange.getFrom(), right.getTo(), lowerStartingRange.isLeftOpen(), right.isRightOpen());
    }

    boolean overlapWithRangeStartingFurther(Range other) {
        return this.to > other.from || this.shareCommonBorderPoint(other);
    }

    private Range getLowerStarting(Range other) {
        return Range.LOWER_STARTING.compare(this, other) <= 0 ? this : other;
    }

    private boolean shareCommonBorderPoint(Range other) {
        return (this.from == other.to && leftClosed() && other.rightClosed()) || (this.to == other.from && rightClosed() && other.leftClosed());
    }

    private boolean leftClosed() {
        return !leftOpen;
    }

    private boolean rightClosed() {
        return !rightOpen;
    }

    @Override public int compareTo(@Nonnull Range other) {
        return FURTHER_CLOSING.compare(this, other);
    }
}
