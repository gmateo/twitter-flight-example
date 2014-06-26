package org.anotes.example.twitterflight.web.controller;

import org.anotes.example.twitterflight.application.service.PageInfo;
import org.anotes.example.twitterflight.domain.RangeInfo;
import org.anotes.example.twitterflight.domain.entity.Product;
import org.anotes.example.twitterflight.domain.viewModel.FilterEntry;

import java.util.List;

/**
 * User: gmc
 * Date: 08/06/2014
 */
public class Main2Json {
    private int totalPages;
    private List<RangeInfo> brandSummaryList;
    private List<Product> productList;
    private List<FilterEntry> filterEntryList;
    private PageInfo pageInfo;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<RangeInfo> getBrandSummaryList() {
        return brandSummaryList;
    }

    public void setBrandSummaryList(List<RangeInfo> brandSummaryList) {
        this.brandSummaryList = brandSummaryList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setFilterEntryList(List<FilterEntry> filterEntryList) {
        this.filterEntryList = filterEntryList;
    }

    public List<FilterEntry> getFilterEntryList() {
        return filterEntryList;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }
}
