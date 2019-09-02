package com.onlyahobo.ranges.overlapping

import spock.lang.Specification

import static com.onlyahobo.ranges.overlapping.RangePoolFactory.*

class RangePoolTest extends Specification {

    def "testing the number of summed overlapping ranges"() {
        when: "calling the #range.getSummedRangeCount() method"
        def result = rangePool.getSummedRangeCount()

        then: "summed overlapping range count should be as #expected"
        result == expected

        where:
        rangePool                                                                      || expected
        twoIdenticalOpenRanges()                                                       || 1
        twoOverlappingOpenRanges()                                                     || 1
        twoOverlappingAndOneNonOverlappingOpenRanges()                                 || 2
        fourOverlappingOpenRangesEachOfThemOverlappingWithOneRange()                   || 2
        twoOverlappingHalfOpenRanges()                                                 || 1
        twoNonOverlappingHalfOpenRanges()                                              || 2
        fiveOverlappingClosedRanges()                                                  || 1
        fiveNonOverlappingClosedRanges()                                               || 5
        twoIdenticalAndOneContainedRanges()                                            || 1
        twoSameIntervalRangesOneClosedSecondOpenAndThirdStartingFromWherePreviousEnd() || 1
        twoNonOverlappingRangesAndAPairOfOverlappingRanges()                           || 3
    }

}