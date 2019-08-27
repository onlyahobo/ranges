package com.onlyahobo.ranges.overlapping;

import java.util.List;
import java.util.function.Function;

class RangePool {

    private final List<Range> ranges;

    private final Function<Range, Boolean> overlappingRangeCountFunction;

    RangePool(Range... ranges) {
        this.ranges = List.of(ranges);
        this.overlappingRangeCountFunction = new CountOverlappingRangeFunction(this.ranges);
    }

    long getOverlappingRangeCount() {
        return ranges.stream().map(overlappingRangeCountFunction).filter(Boolean.TRUE::equals).count();
    }

}
