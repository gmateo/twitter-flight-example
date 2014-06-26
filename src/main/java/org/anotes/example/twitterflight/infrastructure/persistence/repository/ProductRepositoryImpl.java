package org.anotes.example.twitterflight.infrastructure.persistence.repository;

import org.anotes.example.twitterflight.application.service.PageInfo;
import org.anotes.example.twitterflight.domain.BrandSummaryCreator;
import org.anotes.example.twitterflight.domain.SummaryInfo;
import org.anotes.example.twitterflight.domain.entity.Product;
import org.anotes.example.twitterflight.domain.entity.ProductPriceHistory;
import org.anotes.example.twitterflight.domain.repository.ProductRepository;
import org.anotes.example.twitterflight.domain.viewModel.ProductFlt;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

/**
 * User: gmc
 * Date: 27/05/2014
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> allProducts;
    private Map<Long, List<ProductPriceHistory>> productPricesHistory;

    @Override
    public SummaryInfo createSummaryInfoFor(ProductFlt filter) {
        List<Product> productList = getProducts(filter);
        SummaryInfo summaryInfo = new SummaryInfo();
        BrandSummaryCreator brandSummaryCreator = new BrandSummaryCreator();
        for (Product product : productList) {
            brandSummaryCreator.visit(product);
        }
        summaryInfo.setBrandSummaryList(brandSummaryCreator.getBrandRangeInfoList());
        summaryInfo.setProductsNumber(productList.size());
        return summaryInfo;
    }


    @Override
    public List<Product> findProducts(ProductFlt filter, PageInfo pageInfo) {
        List<Product> productList = getProducts(filter);
        return productList.subList(pageInfo.getFirstRow(), pageInfo.getLastRow() + 1);
    }

    @Override
    public List<ProductPriceHistory> getProductPricesHistoryFor(Long productGkey) {
        List<ProductPriceHistory> priceHistoryList = productPricesHistory.get(productGkey);
        if (priceHistoryList == null) {
            priceHistoryList = getProductPrices(productGkey);
            productPricesHistory.put(productGkey, priceHistoryList);
        }
        return priceHistoryList;
    }

    private List<ProductPriceHistory> getProductPrices(Long productGkey) {
        List<ProductPriceHistory> priceHistoryList = new ArrayList<>();
        int priceNumber = getPriceNumberFor(productGkey);
        Product product = getProductFor(productGkey);
        for (int i = 0; i < priceNumber; i++) {
            ProductPriceHistory priceHistory = getProductPriceHistory(getRandomPriceBasedOn(product.getPrice()));
            priceHistoryList.add(priceHistory);
        }
        ProductPriceHistory priceHistory = getProductPriceHistory(product.getPrice());
        priceHistoryList.add(priceHistory);
        return priceHistoryList;
    }

    private ProductPriceHistory getProductPriceHistory(BigDecimal price) {
        ProductPriceHistory priceHistory = new ProductPriceHistory();
        priceHistory.setPrice(price);
        priceHistory.setCreated(new Date());
        return priceHistory;
    }

    private Product getProductFor(Long productGkey) {
        int idx = allProducts.indexOf(Product.forSearching(productGkey));
        Product product = allProducts.get(idx);
        return product;
    }

    private BigDecimal getRandomPriceBasedOn(BigDecimal lastPrice) {
        Random r = new Random();
        return lastPrice.subtract(new BigDecimal(r.nextInt(49) + 1));
    }

    private int getPriceNumberFor(Long productGkey) {
        Random r = new Random();
        return r.nextInt(9) + 1;
    }

    private List<Product> getProducts(ProductFlt filter) {
        if (filter.isEmpty()) {
            return allProducts;
        }
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            String productName = product.getName().toLowerCase();
            String brand = product.getBrand().toLowerCase();
            if (productName.contains(filter.getName().toLowerCase()) &&
                    brand.contains(filter.getBrand().toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @PostConstruct
    private void init() {
        allProducts = new ArrayList<>();
        allProducts.add(new Product(1l, "Bose SoundLink Mini Bluetooth Speaker", "Bose", new BigDecimal(199), "41HFyQ9+arL._AA160_.jpg"));
        allProducts.add(new Product(2l, "Bose QuietComfort 15 Acoustic Noise Cancelling® Headphones", "Bose", new BigDecimal(299), "41iUE5cuOUL._AA160_.jpg"));
        allProducts.add(new Product(3l, "Bose® MIE2i Mobile Headset", "Bose", new BigDecimal(129), "41lHe5R8WmL._AA160_.jpg"));
        allProducts.add(new Product(4l, "Jawbone JAMBOX Wireless Bluetooth Speaker - Black Diamond", "Jawbone", new BigDecimal(95), "41ZjyLwUTyL._AA160_.jpg"));
        allProducts.add(new Product(5l, "Jawbone BIG JAMBOX Wireless Bluetooth Speaker - Graphite Hex - Retail Packaging", "Jawbone", new BigDecimal(249), "51NMU1yrR8L._AA160_.jpg"));
        allProducts.add(new Product(6l, "Ultimate Ears BOOM Wireless Bluetooth Speaker - Black", "Ultimate", new BigDecimal(175), "41zflXxcVZL._AA160_.jpg"));
        allProducts.add(new Product(7l, "Sennheiser RS120 On-Ear 926MHz Wireless RF Headphones with Charging Cradle", "Sennheiser", new BigDecimal(175), "41zflXxcVZL._AA160_.jpg"));
        allProducts.add(new Product(8l, "Sennheiser Momentum Headphone - Black", "Sennheiser", new BigDecimal(299), "41gk8-vs8nL._AA160_.jpg"));
        allProducts.add(new Product(9l, "Sennheiser HD-280 PRO Headphones", "Sennheiser", new BigDecimal(83), "413dm8SG1nL._AA160_.jpg"));
        allProducts.add(new Product(10l, "Sony MDRZX100/BLK ZX Series Stereo Headphones", "Sony", new BigDecimal(115), "41fjcWxTzUL._AA160_.jpg"));
        allProducts.add(new Product(11l, "Sony ICF-S10MK2 Pocket AM/FM Radio, Silver", "Sony", new BigDecimal(215), "51H0TDSW1JL._AA160_.jpg"));
        productPricesHistory = new HashMap<>();
    }

}
