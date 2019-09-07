package com.onlyahobo.ranges.overlapping;

class RangePoolFactory {

    private static final Range RANGE1_5_OPEN = new Range(1, 5, true, true);

    private static final Range RANGE4_8_OPEN = new Range(8, 4, true, true);

    private static final Range RANGE8_9_OPEN = new Range(8, 9, true, true);

    private static final Range RANGE8_10_OPEN = new Range(10, 8, true, true);

    private static final Range RANGE1_5_LEFT_OPEN_RIGHT_CLOSED = new Range(1, 5, true, false);

    private static final Range RANGE5_10_LEFT_CLOSED_RIGHT_OPEN = new Range(5, 10, false, true);

    private static final Range RANGE5_10_LEFT_OPEN_RIGHT_CLOSED = new Range(5, 10, true, false);

    static RangePool twoIdenticalOpenRanges() {
        return new RangePool(RANGE1_5_OPEN, RANGE1_5_OPEN);
    }

    static RangePool twoOverlappingOpenRanges() {
        return new RangePool(RANGE4_8_OPEN, RANGE1_5_OPEN);
    }

    static RangePool twoOverlappingAndOneNonOverlappingOpenRanges() {
        return new RangePool(RANGE4_8_OPEN, RANGE8_9_OPEN, RANGE1_5_OPEN);
    }

    static RangePool fourOverlappingOpenRangesEachOfThemOverlappingWithOneRange() {
        return new RangePool(RANGE8_10_OPEN, RANGE1_5_OPEN, RANGE4_8_OPEN, RANGE8_9_OPEN);
    }

    static RangePool twoOverlappingHalfOpenRanges() {
        return new RangePool(RANGE1_5_LEFT_OPEN_RIGHT_CLOSED, RANGE5_10_LEFT_CLOSED_RIGHT_OPEN);
    }

    static RangePool twoNonOverlappingHalfOpenRanges() {
        return new RangePool(RANGE1_5_LEFT_OPEN_RIGHT_CLOSED, RANGE5_10_LEFT_OPEN_RIGHT_CLOSED);
    }

    static RangePool fiveOverlappingClosedRanges() {
        final var RANGE1_5_CLOSED = new Range(1, 5, false, false);
        final var RANGE5_10_CLOSED = new Range(5, 10, false, false);
        final var RANGE10_15_CLOSED = new Range(10, 15, false, false);
        final var RANGE15_20_CLOSED = new Range(15, 20, false, false);
        final var RANGE20_25_CLOSED = new Range(25, 20, false, false);

        return new RangePool(RANGE1_5_CLOSED, RANGE5_10_CLOSED, RANGE10_15_CLOSED, RANGE15_20_CLOSED, RANGE20_25_CLOSED);
    }

    static RangePool fiveNonOverlappingClosedRanges() {
        final var RANGE15_20_LEFT_OPEN_RIGHT_CLOSED = new Range(10, 15, true, false);
        final var RANGE10_15_LEFT_OPEN_RIGHT_CLOSED = new Range(15, 20, true, false);
        final var RANGE20_25_LEFT_OPEN_RIGHT_CLOSED = new Range(25, 20, true, false);

        return new RangePool(RANGE1_5_LEFT_OPEN_RIGHT_CLOSED, RANGE5_10_LEFT_OPEN_RIGHT_CLOSED, RANGE15_20_LEFT_OPEN_RIGHT_CLOSED,
            RANGE10_15_LEFT_OPEN_RIGHT_CLOSED, RANGE20_25_LEFT_OPEN_RIGHT_CLOSED);
    }

    static RangePool twoIdenticalAndOneContainedRanges() {
        final var RANGE1_20_OPEN = new Range(1, 20, true, true);
        final var RANGE10_15_OPEN = new Range(11, 15, true, true);

        return new RangePool(RANGE1_20_OPEN, RANGE1_20_OPEN, RANGE10_15_OPEN);
    }

    static RangePool twoSameIntervalRangesOneClosedSecondOpenAndThirdStartingFromWherePreviousEnd() {
        final var RANGE16_17_CLOSED = new Range(16, 17, false, false);
        final var RANGE16_17_OPEN = new Range(16, 17, true, true);
        final var RANGE17_20_CLOSED = new Range(17, 20, false, false);

        return new RangePool(RANGE16_17_CLOSED, RANGE16_17_OPEN, RANGE17_20_CLOSED);
    }

    static RangePool twoNonOverlappingRangesAndAPairOfOverlappingRanges() {
        final var RANGE_1_5_LEFT_CLOSED = new Range(1, 5, false, true);
        final var RANGE_5_10_LEFT_CLOSED = new Range(5, 10, false, true);
        final var RANGE_20_30_RIGHT_CLOSED = new Range(25, 30, true, false);
        final var RANGE_30_31_CLOSED = new Range(30, 31,false, false);

        return new RangePool(RANGE_1_5_LEFT_CLOSED, RANGE_5_10_LEFT_CLOSED, RANGE_20_30_RIGHT_CLOSED, RANGE_30_31_CLOSED);
    }

    //This one won't work with sorting by Range.to since <1,5) and <5,7> don't overlap with each other yet <4,10) overlaps with each of them
    static RangePool getTwoNonOverlappingAndOneOverlappingThemBoth() {
        final var RANGE_1_5_LEFT_CLOSED = new Range(1, 5, false, true);
        final var RANGE_5_7_CLOSED = new Range(5, 7, false, false);
        final var RANGE_4_10_LEFT_CLOSED = new Range(4, 10, false, true);

        return new RangePool(RANGE_1_5_LEFT_CLOSED, RANGE_4_10_LEFT_CLOSED, RANGE_5_7_CLOSED);
    }

    //This one won't work with sorting by Range.to since <20,40> and <60,80> don't overlap with each other yet <1,100> overlaps with each of them
    static RangePool getTwoNonOverlappingAndOneOverlappingThemBoth2() {
        final var RANGE_1_100_CLOSED = new Range(1, 100, false, false);
        final var RANGE_20_40_CLOSED = new Range(20, 40, false, false);
        final var RANGE_60_80_CLOSED = new Range(60, 80, false, false);

        return new RangePool(RANGE_1_100_CLOSED, RANGE_20_40_CLOSED, RANGE_60_80_CLOSED);
    }

}
