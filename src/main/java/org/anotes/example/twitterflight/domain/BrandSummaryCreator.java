package org.anotes.example.twitterflight.domain;


import org.anotes.example.twitterflight.domain.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 27/05/2014
 */
public class BrandSummaryCreator implements Visitor {
    private List<RangeInfo> rangeInfoList = new ArrayList<>();

    @Override
    public void visit(Product product) {
        String brand = product.getBrand();
        RangeInfo rangeInfo = getRangeInfoFor(brand);
        rangeInfo.incrementNbr();
    }

    private RangeInfo getRangeInfoFor(String brand) {
        for (RangeInfo rangeInfo : rangeInfoList) {
            if (rangeInfo.getDescription().equals(brand)) {
                return rangeInfo;
            }
        }
        RangeInfo rangeInfo = new RangeInfo(rangeInfoList.size(), brand);
        rangeInfoList.add(rangeInfo);
        return rangeInfo;
    }

    public List<RangeInfo> getBrandRangeInfoList() {
        return rangeInfoList;
    }
}
