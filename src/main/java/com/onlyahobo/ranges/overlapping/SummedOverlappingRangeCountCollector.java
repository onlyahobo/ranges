package com.onlyahobo.ranges.overlapping;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.Comparator.comparing;

class SummedOverlappingRangeCountCollector implements Collector<Range, List<Range>, Integer> {

    @Override public Supplier<List<Range>> supplier() {
        return LinkedList::new;
    }

    @Override public BiConsumer<List<Range>, Range> accumulator() {
        return (resultList, next) -> {
            final int lastResultListIndex = resultList.size() - 1;

            if (!resultList.isEmpty() && previousOverlapsWithNext(resultList.get(lastResultListIndex), next)) {
                final Range lowerStartingRange = resolveLowerStartingRange(resultList.get(lastResultListIndex), next);
                final Range mergedRange = new Range(lowerStartingRange.getFrom(), next.getTo(), lowerStartingRange.isLeftOpen(), next.isRightOpen());
                resultList.set(lastResultListIndex, mergedRange);
            } else {
                resultList.add(next);
            }
        };
    }

    @Override public BinaryOperator<List<Range>> combiner() {
        return (left, right) -> {
            throw new UnsupportedOperationException("Parallel processing not supported");
        };
    }

    @Override public Function<List<Range>, Integer> finisher() {
        return List::size;
    }

    @Override public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    private Range resolveLowerStartingRange(final Range previous, final Range next) {
        return comparing(Range::getFrom).thenComparing(Range::isLeftOpen).compare(previous, next) <= 0 ? previous : next;
    }

    private boolean previousOverlapsWithNext(final Range previous, final Range next) {
        return previous.overlapWithRangeStartingFurther(next);
    }

}
