package com.onlyahobo.ranges.overlapping;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.IntPredicate;

@RequiredArgsConstructor
class OverlapRangePredicate implements IntPredicate {

    private final List<Range> ranges;

    @Override public boolean test(final int value) {
        return currentOverlapsWithPrevious(value) || currentOverlapsWithNext(value);
    }

    private boolean currentOverlapsWithPrevious(int i) {
        return i > 0 && ranges.get(i - 1).overlapWithRangeStartingFurther(ranges.get(i));
    }

    private boolean currentOverlapsWithNext(int i) {
        return i < ranges.size() - 1 && ranges.get(i).overlapWithRangeStartingFurther(ranges.get(i + 1));
    }

}
