package org.anotes.example.twitterflight.domain;

import java.util.List;

/**
 * User: gmc
 * Date: 27/05/2014
 */
public class SummaryInfo {

    private List<RangeInfo> brandSummaryList;
    private int productsNumber;

    public void setBrandSummaryList(List<RangeInfo> brandSummaryList) {
        this.brandSummaryList = brandSummaryList;
    }

    public List<RangeInfo> getBrandSummaryList() {
        return brandSummaryList;
    }

    public void setProductsNumber(int productsNumber) {
        this.productsNumber = productsNumber;
    }

    public int getProductsNumber() {
        return productsNumber;
    }
}
