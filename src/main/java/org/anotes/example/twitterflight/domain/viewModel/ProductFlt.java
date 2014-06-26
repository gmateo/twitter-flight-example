package org.anotes.example.twitterflight.domain.viewModel;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 21/05/2014
 */
public class ProductFlt {
    private String name = "";
    private String brand = "";
    private List<FilterEntry> filterEntryList;

    public ProductFlt() {
        filterEntryList = new ArrayList<>();
        filterEntryList.add(new FilterEntry("", "All"));
    }

    public boolean isEmpty() {
        return !StringUtils.hasText(name) && !StringUtils.hasText(brand);
    }

    public List<FilterEntry> getFilterEntryList() {
        return filterEntryList;
    }

    public void addFilterEntry(String filter, String value) {
        FilterEntry filterEntry = new FilterEntry(filter, value);
        int idx = filterEntryList.indexOf(filterEntry);
        boolean filterCurrentlyUsed = idx != -1;
        if (filterCurrentlyUsed) {
            applyFilterEntriesUntil(idx - 1);
        }
        updateFilters(filterEntry);
        if (StringUtils.hasText(value)) {
            filterEntryList.add(filterEntry);
        }
    }

    public void applyFilterEntriesUntil(int filterEntryIdxMax) {
        cleanFilters();
        filterEntryList = filterEntryList.subList(0, filterEntryIdxMax + 1);
        for (int i = 1; i <= filterEntryIdxMax; i++) {
            FilterEntry filterEntry = filterEntryList.get(i);
            updateFilters(filterEntry);
        }
    }

    private void updateFilters(FilterEntry filterEntry) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(this);
        beanWrapper.setPropertyValue(filterEntry.getName(), filterEntry.getValue());
    }

    private void cleanFilters() {
        name = "";
        brand = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


}
