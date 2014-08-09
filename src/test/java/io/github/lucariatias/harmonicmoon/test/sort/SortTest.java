package io.github.lucariatias.harmonicmoon.test.sort;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import io.github.lucariatias.harmonicmoon.util.sort.Sorter;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static com.insightfullogic.lambdabehave.Suite.describe;

@RunWith(JunitSuiteRunner.class)
public class SortTest {{

    List<IntegerSortableWrapper> list = new ArrayList<>();

    describe("a descending list", it -> {

        it.isSetupWith(() -> {
            for (int i = 20; i > 0; i--) {
                list.add(new IntegerSortableWrapper(i));
            }
        });

        it.should("be in ascending order when sorted", (expect) -> {
            Sorter<IntegerSortableWrapper> sorter = new Sorter<>(list);
            List<IntegerSortableWrapper> sorted = sorter.sortAscending();
            expect.that(list.size()).is(sorted.size());
            for (int i = 0; i < sorted.size() - 1; i++) {
                expect.that(sorted.get(i).getValue()).isLessThanOrEqualTo(sorted.get(i + 1).getValue());
            }
        });

    });

    describe("an ascending list", it -> {

        it.isSetupWith(() -> {
            for (int i = 0; i < 20; i++) {
                list.add(new IntegerSortableWrapper(i));
            }
        });

        it.should("be in descending order when sorted", (expect) -> {
            Sorter<IntegerSortableWrapper> sorter = new Sorter<>(list);
            List<IntegerSortableWrapper> sorted = sorter.sortDescending();
            expect.that(list.size()).is(sorted.size());
            for (int i = 0; i < sorted.size() - 1; i++) {
                expect.that(sorted.get(i).getValue()).isGreaterThanOrEqualTo(sorted.get(i + 1).getValue());
            }
        });

    });

}}
