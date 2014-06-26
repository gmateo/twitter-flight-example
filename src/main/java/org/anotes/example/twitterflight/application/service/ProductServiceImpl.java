package org.anotes.example.twitterflight.application.service;

import org.anotes.example.twitterflight.domain.SummaryInfo;
import org.anotes.example.twitterflight.domain.entity.Product;
import org.anotes.example.twitterflight.domain.entity.ProductPriceHistory;
import org.anotes.example.twitterflight.domain.repository.ProductRepository;
import org.anotes.example.twitterflight.domain.viewModel.ProductFlt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: gmc
 * Date: 21/05/2014
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findProducts(ProductFlt filter, PageInfo pageInfo) {
        return productRepository.findProducts(filter, pageInfo);
    }

    @Override
    public SummaryInfo createSummaryInfoFor(ProductFlt filter) {
        return productRepository.createSummaryInfoFor(filter);
    }

    @Override
    public List<ProductPriceHistory> getProductPricesHistoryFor(Long productGkey) {
        return productRepository.getProductPricesHistoryFor(productGkey);
    }
}
