package com.onlyahobo.ranges.overlapping

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

import static com.onlyahobo.ranges.overlapping.RangePoolFactory.*

class RangePoolTest extends Specification {

    @Unroll
    def "Sum: testing the number of ranges with overlapping ones being summed"() {
        when: "calling the #range.getRangeCountWithOverlappingSummed() method"
        def result = rangePool.getRangeCountWithOverlappingSummed()

        then: "the result range count should be as #expected"
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
        getTwoNonOverlappingAndOneOverlappingThemBoth()                                || 1
        getTwoNonOverlappingAndOneOverlappingThemBoth2()                               || 1
    }

    @Unroll
    @Ignore
    def "Intersection: testing the number of ranges with overlapping ones being intersected"() {
        when: "calling the #range.getRangeCountWithOverlappingIntersected() method"
        def result = rangePool.getRangeCountWithOverlappingIntersected()

        then: "the result range count should be as #expected"
        result == expected

        where:
        rangePool                                        || expected
        getTwoNonOverlappingAndOneOverlappingThemBoth()  || 2
        getTwoNonOverlappingAndOneOverlappingThemBoth2() || 2
    }

}