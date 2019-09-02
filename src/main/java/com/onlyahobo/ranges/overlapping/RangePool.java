package com.onlyahobo.ranges.overlapping;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableList;

class RangePool {

    private final List<Range> ranges;

    RangePool(Range... ranges) {
        this.ranges = stream(ranges).sorted().collect(toUnmodifiableList());
    }

    /**
     * @return number of ranges that are the sums of given (overlapping) input ranges, i.e. for
     * [ (1, 5), (3, 10) ] returns 1
     * [ <1, 5>, <5, 10> ] returns 1
     * [ <1, 5), <5, 10) ] returns 2
     * [ <1, 5), <5, 10), (20, 30>, <30, 31> ] returns 3
     * {@link} http://matematyka.pisz.pl/strona/5.html (sum)
     */
    int getSummedRangeCount() {
        return ranges.stream().collect(new SummedOverlappingRangeCountCollector());
    }

}
