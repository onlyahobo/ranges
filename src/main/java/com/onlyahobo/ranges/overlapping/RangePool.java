package com.onlyahobo.ranges.overlapping;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableList;

class RangePool {

    private final List<Range> ranges;

    RangePool(Range... ranges) {
        this.ranges = stream(ranges).collect(toUnmodifiableList());
    }

    /**
     * @return number of separate ranges, the overlapping ones having been summed, i.e. for
     * [ (1, 5), (3, 10) ] returns [ (1, 10) ] thus 1
     * [ <1, 5>, <5, 10> ] returns [ <1, 10> ] thus 1
     * [ <1, 5), <5, 10) ] returns [ <1, 5), <5, 10) ] thus 2
     * [ <1, 5), <5, 10), (20, 30>, <30, 31> ] returns [ <1, 5), <5, 10), (20, 31> ] thus 3
     * [ <1, 100>, <20, 40>, <60, 80> ] returns [ <1, 100> ] thus 1
     * {@link} http://matematyka.pisz.pl/strona/5.html (sum)
     * {@link} https://www.geeksforgeeks.org/merging-intervals/
     */
    int getRangeCountWithOverlappingSummed() {
        return ranges.stream().sorted().collect(new SummedOverlappingRangeCountCollector());
    }

    /**
     * @return number of separate ranges, the overlapping ones having been intersected, i.e. for
     * [ (1, 5), (3, 10) ] returns <3, 5> thus 1
     * [ <1, 5>, <5, 10> ] returns [ <5, 5> ] thus 1
     * [ <1, 5), <5, 10) ] returns [ <1, 5), <5, 10) ] thus 2
     * [ <1, 5), <5, 10), (20, 30>, <30, 31> ] returns [ <1, 5), <5, 10), <30, 30> ] thus 3
     * [ <1, 100>, <20, 40>, <60, 80> ] returns [ <20, 40>, <60, 80> ] thus 2
     * {@link} http://matematyka.pisz.pl/strona/5.html (część wspólna)
     */
    int getRangeCountWithOverlappingIntersected() {
        throw new UnsupportedOperationException("To be implemented");
    }

}
