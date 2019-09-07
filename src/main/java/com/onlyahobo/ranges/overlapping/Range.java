package com.onlyahobo.ranges.overlapping;

import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.Comparator;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Comparator.comparingInt;

@Getter
class Range implements Comparable<Range> {

    private static final Comparator<Range> LOWER_STARTING = comparingInt(Range::getFrom).thenComparing(Range::isLeftOpen);

    private static final Comparator<Range> FURTHER_CLOSING = comparingInt(Range::getTo).thenComparing(Range::rightClosed);

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
        checkArgument(from != to || (leftClosed() && rightClosed()), String.format("Illegal range: %s", this));
    }

    static Range sum(Range left, Range right) {
        if (!left.overlapWith(right)) {
            throw new CannotSumRangesException(left, right);
        }
        final var lowerStartingRange = left.getLowerStarting(right);
        final var furtherClosingRange = left.getFurtherClosing(right);
        return new Range(lowerStartingRange.getFrom(), furtherClosingRange.getTo(), lowerStartingRange.isLeftOpen(), furtherClosingRange.isRightOpen());
    }

    static Range intersection(Range left, Range right) {
        if (!left.overlapWith(right)) {
            throw new CannotIntersectRangesException(left, right);
        }
        final var furtherStartingRange = left.getFurtherStarting(right);
        final var lowerClosingRange = left.getLowerClosing(right);
        return new Range(furtherStartingRange.getFrom(), lowerClosingRange.getTo(), furtherStartingRange.isLeftOpen(), lowerClosingRange.isRightOpen());
    }

    boolean overlapWith(Range other) {
        return (this.from < other.to && this.to > other.from) || this.shareCommonBorderPoint(other);
    }

    private Range getLowerStarting(Range other) {
        return LOWER_STARTING.compare(this, other) <= 0 ? this : other;
    }

    private Range getFurtherStarting(Range other) {
        return LOWER_STARTING.compare(this, other) <= 0 ? other : this;
    }

    private Range getLowerClosing(Range other) {
        return FURTHER_CLOSING.compare(this, other) <= 0 ? this : other;
    }

    private Range getFurtherClosing(Range other) {
        return FURTHER_CLOSING.compare(this, other) <= 0 ? other : this;
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
        return LOWER_STARTING.compare(this, other);
    }

    @Override public String toString() {
        return (leftOpen ? "(" : "<") + from + ", " + to + (rightOpen ? ")" : ">");
    }

}
