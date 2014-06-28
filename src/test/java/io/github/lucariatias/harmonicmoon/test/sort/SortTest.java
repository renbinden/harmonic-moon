package io.github.lucariatias.harmonicmoon.test.sort;

import io.github.lucariatias.harmonicmoon.util.sort.Sorter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SortTest {

    @Test
    public void testAscendingSort() {
        List<IntegerSortableWrapper> list = new ArrayList<>();
        for (int i = 20; i > 0; i--) {
            list.add(new IntegerSortableWrapper(i));
        }
        Sorter<IntegerSortableWrapper> sorter = new Sorter<>(list);
        List<IntegerSortableWrapper> sorted = sorter.sortAscending();
        Assert.assertEquals(list.size(), sorted.size());
        for (int i = 0; i < sorted.size() - 1; i++) {
            Assert.assertTrue(sorted.get(i).getValue() <= sorted.get(i + 1).getValue());
        }
    }

    @Test
    public void testDescendingSort() {
        List<IntegerSortableWrapper> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new IntegerSortableWrapper(i));
        }
        Sorter<IntegerSortableWrapper> sorter = new Sorter<>(list);
        List<IntegerSortableWrapper> sorted = sorter.sortDescending();
        Assert.assertEquals(list.size(), sorted.size());
        for (int i = 0; i < sorted.size() - 1; i++) {
            Assert.assertTrue(sorted.get(i).getValue() >= sorted.get(i + 1).getValue());
        }
    }

}
