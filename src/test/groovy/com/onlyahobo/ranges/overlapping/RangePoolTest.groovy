package com.onlyahobo.ranges.overlapping

import spock.lang.Specification

import static com.onlyahobo.ranges.overlapping.RangePoolFactory.*

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

class RangePoolTest extends Specification {

    def "testing multiple ranges for overlapping"() {
        when: "calling the #range.getOverlappingRangeCount() method"
        def result = rangePool.getOverlappingRangeCount()

        then: "overlapping range count should be as #expected"
        result == expected

        where:
        rangePool                                                                      || expected
        twoIdenticalOpenRanges()                                                       || 2
        twoOverlappingOpenRanges()                                                     || 2
        twoOverlappingAndOneNonOverlappingOpenRanges()                                 || 2
        fourOverlappingOpenRangesEachOfThemOverlappingWithOneRange()                   || 4
        twoOverlappingHalfOpenRanges()                                                 || 2
        twoNonOverlappingHalfOpenRanges()                                              || 0
        fiveOverlappingClosedRanges()                                                  || 5
        fiveNonOverlappingClosedRanges()                                               || 0
        twoIdenticalAndOneContainedRanges()                                            || 3
        twoSameIntervalRangesOneClosedSecondOpenAndThirdStartingFromWherePreviousEnd() || 3
    }

}