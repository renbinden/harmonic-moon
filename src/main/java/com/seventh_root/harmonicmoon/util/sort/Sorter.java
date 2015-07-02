package com.seventh_root.harmonicmoon.util.sort;

import java.util.List;

public class Sorter<T extends Sortable> {

    private List<T> list;

    public Sorter(List<T> list) {
        this.list = list;
    }

    private int partitionDescending(int left, int right) {
        int i = left, j = right;
        T tmp;
        T pivot = list.get((left + right) / 2);
        while (i <= j) {
            while (list.get(i).getValue() > pivot.getValue()) i++;
            while (list.get(j).getValue() < pivot.getValue()) j--;
            if (i <= j) {
                tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    private void quickSortDescending(int left, int right) {
        int index = partitionDescending(left, right);
        if (left < index - 1) quickSortDescending(left, index - 1);
        if (index < right) quickSortDescending(index, right);
    }

    public List<T> sortDescending() {
        quickSortDescending(0, list.size() - 1);
        return list;
    }

    private int partitionAscending(int left, int right) {
        int i = left, j = right;
        T tmp;
        T pivot = list.get((left + right) / 2);
        while (i <= j) {
            while (list.get(i).getValue() < pivot.getValue()) i++;
            while (list.get(j).getValue() > pivot.getValue()) j--;
            if (i <= j) {
                tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    private void quickSortAscending(int left, int right) {
        int index = partitionAscending(left, right);
        if (left < index - 1) quickSortAscending(left, index - 1);
        if (index < right) quickSortAscending(index, right);
    }

    public List<T> sortAscending() {
        quickSortAscending(0, list.size() - 1);
        return list;
    }

}
