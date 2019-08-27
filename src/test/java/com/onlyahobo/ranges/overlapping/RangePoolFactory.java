package com.onlyahobo.ranges.overlapping;

class RangePoolFactory {

    private static final Range RANGE1_5_OPEN = new Range(1, 5, true, true);
    private static final Range RANGE4_8_OPEN = new Range(8, 4, true, true);
    private static final Range RANGE8_9_OPEN = new Range(8, 9, true, true);
    private static final Range RANGE8_10_OPEN = new Range(10, 8, true, true);
    private static final Range RANGE1_5_LEFT_OPEN_RIGHT_CLOSED = new Range(1, 5, true, false);
    private static final Range RANGE5_10_LEFT_CLOSED_RIGHT_OPEN = new Range(5, 10, false, true);
    private static final Range RANGE5_10_LEFT_OPEN_RIGHT_CLOSED = new Range(5, 10, true, false);

    static RangePool getTwoIdenticalOpenRanges() {
        return new RangePool(RANGE1_5_OPEN, RANGE1_5_OPEN);
    }

    static RangePool getTwoOverlappingOpenRanges() {
        return new RangePool(RANGE4_8_OPEN, RANGE1_5_OPEN);
    }

    static RangePool getTwoOverlappingAndOneNonOverlappingOpenRanges() {
        return new RangePool(RANGE4_8_OPEN, RANGE8_9_OPEN, RANGE1_5_OPEN);
    }

    static RangePool getFourOverlappingOpenRangesEachOfThemOverlappingWithOneRange() {
        return new RangePool(RANGE4_8_OPEN, RANGE8_9_OPEN, RANGE8_10_OPEN, RANGE1_5_OPEN);
    }

    static RangePool getTwoOverlappingHalfOpenRanges() {
        return new RangePool(RANGE1_5_LEFT_OPEN_RIGHT_CLOSED, RANGE5_10_LEFT_CLOSED_RIGHT_OPEN);
    }

    static RangePool getTwoNonOverlappingHalfOpenRanges() {
        return new RangePool(RANGE1_5_LEFT_OPEN_RIGHT_CLOSED, RANGE5_10_LEFT_OPEN_RIGHT_CLOSED);
    }

    static RangePool getFiveOverlappingClosedRanges() {
        final Range RANGE1_5_CLOSED = new Range(1, 5, false, false);
        final Range RANGE5_10_CLOSED = new Range(5, 10, false, false);
        final Range RANGE10_15_CLOSED = new Range(10, 15, false, false);
        final Range RANGE15_20_CLOSED = new Range(15, 20, false, false);
        final Range RANGE20_25_CLOSED = new Range(25, 20, false, false);

        return new RangePool(RANGE1_5_CLOSED, RANGE5_10_CLOSED, RANGE10_15_CLOSED, RANGE15_20_CLOSED, RANGE20_25_CLOSED);
    }

    static RangePool getFiveNonOverlappingClosedRanges() {
        final Range RANGE15_20_LEFT_OPEN_RIGHT_CLOSED = new Range(10, 15, true, false);
        final Range RANGE10_15_LEFT_OPEN_RIGHT_CLOSED = new Range(15, 20, true, false);
        final Range RANGE20_25_LEFT_OPEN_RIGHT_CLOSED = new Range(25, 20, true, false);

        return new RangePool(RANGE1_5_LEFT_OPEN_RIGHT_CLOSED, RANGE5_10_LEFT_OPEN_RIGHT_CLOSED, RANGE15_20_LEFT_OPEN_RIGHT_CLOSED,
            RANGE10_15_LEFT_OPEN_RIGHT_CLOSED, RANGE20_25_LEFT_OPEN_RIGHT_CLOSED);
    }

}
