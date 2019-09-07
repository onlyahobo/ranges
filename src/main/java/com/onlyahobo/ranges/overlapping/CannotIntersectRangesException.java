package com.onlyahobo.ranges.overlapping;

class CannotIntersectRangesException extends RuntimeException {

    CannotIntersectRangesException(Range left, Range right) {
        super(String.format("Cannot intersect ranges %s and %s as they don't overlap", left, right));
    }

}
