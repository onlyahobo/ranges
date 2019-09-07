package com.onlyahobo.ranges.overlapping;

class CannotSumRangesException extends RuntimeException {

    CannotSumRangesException(Range left, Range right) {
        super(String.format("Cannot sum ranges %s and %s as they don't overlap", left, right));
    }

}
