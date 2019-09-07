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

class SummedOverlappingRangeCountCollector implements Collector<Range, List<Range>, Integer> {

    @Override public Supplier<List<Range>> supplier() {
        return LinkedList::new;
    }

    @Override public BiConsumer<List<Range>, Range> accumulator() {
        return (resultList, next) -> {
            final var lastResultListIndex = resultList.size() - 1;
            if (!resultList.isEmpty() && previousOverlapsWithNext(resultList.get(lastResultListIndex), next)) {
                resultList.set(lastResultListIndex, Range.sum(resultList.get(lastResultListIndex), next));
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

    private boolean previousOverlapsWithNext(Range previous, Range next) {
        return previous.overlapWith(next);
    }

}
