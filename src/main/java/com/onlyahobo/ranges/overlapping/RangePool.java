package com.onlyahobo.ranges.overlapping;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableList;

class RangePool {

    private final List<Range> ranges;

    private final IntPredicate overlapRangePredicate;

    RangePool(Range... ranges) {
        this.ranges = stream(ranges).sorted().collect(toUnmodifiableList());
        this.overlapRangePredicate = new OverlapRangePredicate(this.ranges);
    }

    long getOverlappingRangeCount() {
        return IntStream.range(0, ranges.size()).filter(overlapRangePredicate).count();
    }

}
