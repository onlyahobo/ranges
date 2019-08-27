package com.onlyahobo.ranges.overlapping;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
class CountOverlappingRangeFunction implements Function<Range, Boolean> {

    private final List<Range> ranges;

    @Override public Boolean apply(Range range) {
        return calculateOverlappingRangeCount(range) > 0;
    }

    private long calculateOverlappingRangeCount(Range range) {
        return ranges.stream().filter(range::overlapWith).count() - 1;
    }
}
