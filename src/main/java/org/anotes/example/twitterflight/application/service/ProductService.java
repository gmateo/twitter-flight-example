package org.anotes.example.twitterflight.application.service;

import org.anotes.example.twitterflight.domain.SummaryInfo;
import org.anotes.example.twitterflight.domain.entity.Product;
import org.anotes.example.twitterflight.domain.entity.ProductPriceHistory;
import org.anotes.example.twitterflight.domain.viewModel.ProductFlt;

import java.util.List;

/**
 * User: gmc
 * Date: 21/05/2014
 */
public interface ProductService {
    public List<Product> findProducts(ProductFlt filter, PageInfo pageInfo);

    SummaryInfo createSummaryInfoFor(ProductFlt filter);

    List<ProductPriceHistory> getProductPricesHistoryFor(Long productGkey);
}
