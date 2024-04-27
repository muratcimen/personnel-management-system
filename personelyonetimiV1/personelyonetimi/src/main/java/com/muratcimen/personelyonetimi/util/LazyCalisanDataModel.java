package com.muratcimen.personelyonetimi.util;

import com.muratcimen.personelyonetimi.entity.Calisan;
import org.apache.commons.collections.ComparatorUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;

import javax.faces.context.FacesContext;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.stream.Collectors;


public class LazyCalisanDataModel extends LazyDataModel<Calisan> {

    private static final long serialVersionUID = 1L;

    private List<Calisan> datasource;

    public LazyCalisanDataModel(List<Calisan> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Calisan getRowData(String rowKey) {
        for (Calisan calisan : datasource) {
            if (calisan.getCalisanID() == Integer.parseInt(rowKey)) {
                return calisan;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(Calisan calisan) {
        return String.valueOf(calisan.getCalisanID());
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return (int) datasource.stream()
                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .count();
    }

    @Override
    public List<Calisan> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        // apply offset & filters
        List<Calisan> calisanlar;
        calisanlar = datasource.stream()
                .skip(offset)
                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .limit(pageSize)
                .collect(Collectors.toList());
        if (!sortBy.isEmpty()) {
            List<Comparator<Calisan>> comparators = sortBy.values().stream()
                    .map(o -> new LazySorter(o.getField(), o.getOrder()))
                    .collect(Collectors.toList());
            Comparator<Calisan> cp = ComparatorUtils.chainedComparator(comparators); // from apache
            calisanlar.sort(cp);
        }
        return calisanlar;
    }

    public static final Object getPropertyValueViaReflection(Object o, String field)
            throws ReflectiveOperationException, IllegalArgumentException, IntrospectionException {
        return new PropertyDescriptor(field, o.getClass()).getReadMethod().invoke(o);
    }

    private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
        boolean matching = true;

        for (FilterMeta filter : filterBy) {
            FilterConstraint constraint = filter.getConstraint();
            Object filterValue = filter.getFilterValue();

            try {
                Object columnValue = String.valueOf(getPropertyValueViaReflection(o, filter.getField()));
                matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
            } catch (ReflectiveOperationException | IntrospectionException e) {
                matching = false;
            }

            if (!matching) {
                break;
            }
        }
        return matching;
    }
}
