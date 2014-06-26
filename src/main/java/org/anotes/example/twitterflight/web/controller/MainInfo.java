package org.anotes.example.twitterflight.web.controller;

import org.anotes.example.twitterflight.application.service.PageInfo;
import org.anotes.example.twitterflight.domain.RangeInfo;
import org.anotes.example.twitterflight.domain.SummaryInfo;
import org.anotes.example.twitterflight.domain.viewModel.ProductFlt;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * User: gmc
 * Date: 25/05/2014
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MainInfo implements Serializable {
    private static final String BRAND = "brand";
    private static final String BREADCRUMB_IDX = "breadcrumbIdx";

    private PageInfo pageInfo;
    private ProductFlt filter;
    private SummaryInfo summaryInfo;

    public void updateFilter(String filter, String value) {
        if (BREADCRUMB_IDX.equals(filter)) {
            getFilter().applyFilterEntriesUntil(Integer.parseInt(value));
            return;
        }
        if (BRAND.equals(filter)) {
            RangeInfo rangeInfoSearch = RangeInfo.forSearching(Integer.parseInt(value));
            List<RangeInfo> brandSummaryList = summaryInfo.getBrandSummaryList();
            int idx = brandSummaryList.indexOf(rangeInfoSearch);
            RangeInfo brand = brandSummaryList.get(idx);
            value = brand.getDescription();
        }
        getFilter().addFilterEntry(filter, value);
    }

    public void setSummaryInfo(SummaryInfo summaryInfo) {
        this.summaryInfo = summaryInfo;
        pageInfo = new PageInfo();
        pageInfo.setProductsNbr(summaryInfo.getProductsNumber());
    }

    public SummaryInfo getSummaryInfo() {
        return summaryInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setCurrentPage(Integer page) {
        pageInfo.setCurrentPage(page);
    }

    public void setFilter(ProductFlt filter) {
        this.filter = filter;
    }

    public ProductFlt getFilter() {
        if (filter == null) {
            filter = new ProductFlt();
        }
        return filter;
    }
}
