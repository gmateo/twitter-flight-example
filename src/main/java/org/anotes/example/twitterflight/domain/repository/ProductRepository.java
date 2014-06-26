package org.anotes.example.twitterflight.domain.repository;

import org.anotes.example.twitterflight.application.service.PageInfo;
import org.anotes.example.twitterflight.domain.SummaryInfo;
import org.anotes.example.twitterflight.domain.entity.Product;
import org.anotes.example.twitterflight.domain.entity.ProductPriceHistory;
import org.anotes.example.twitterflight.domain.viewModel.ProductFlt;

import java.util.List;

/**
 * User: gmc
 * Date: 27/05/2014
 */
public interface ProductRepository {
    SummaryInfo createSummaryInfoFor(ProductFlt filter);

    List<Product> findProducts(ProductFlt filter, PageInfo pageInfo);

    List<ProductPriceHistory> getProductPricesHistoryFor(Long productGkey);
}
