package com.onlyahobo.ranges.overlapping

import spock.lang.Specification

class RangePoolTest extends Specification {

    def "testing multiple ranges for overlapping"() {
        when: "calling the #range.getOverlappingRangeCount() method"
        def result = rangePool.getOverlappingRangeCount()

        then: "overlapping range count should be as #expected"
        result == expected

        where:
        rangePool                                                                        || expected
        RangePoolFactory.getTwoIdenticalOpenRanges()                                     || 2
        RangePoolFactory.getTwoOverlappingOpenRanges()                                   || 2
        RangePoolFactory.getTwoOverlappingAndOneNonOverlappingOpenRanges()               || 2
        RangePoolFactory.getFourOverlappingOpenRangesEachOfThemOverlappingWithOneRange() || 4
        RangePoolFactory.getTwoOverlappingHalfOpenRanges()                               || 2
        RangePoolFactory.getTwoNonOverlappingHalfOpenRanges()                            || 0
        RangePoolFactory.getFiveOverlappingClosedRanges()                                || 5
        RangePoolFactory.getFiveNonOverlappingClosedRanges()                             || 0

    }

}
