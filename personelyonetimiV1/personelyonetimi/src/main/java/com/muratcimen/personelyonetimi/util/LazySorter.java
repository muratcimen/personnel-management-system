package com.muratcimen.personelyonetimi.util;

import com.muratcimen.personelyonetimi.entity.Calisan;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

public class LazySorter implements Comparator<Calisan> {

    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Calisan calisan1, Calisan calisan2) {
        try {
            Object value1 = Calisan.class.getField(this.sortField).get(calisan1);
            Object value2 = Calisan.class.getField(this.sortField).get(calisan2);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
