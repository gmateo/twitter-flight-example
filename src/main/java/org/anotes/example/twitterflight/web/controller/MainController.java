package org.anotes.example.twitterflight.web.controller;

import org.anotes.example.twitterflight.application.service.ProductService;
import org.anotes.example.twitterflight.domain.SummaryInfo;
import org.anotes.example.twitterflight.domain.entity.Product;
import org.anotes.example.twitterflight.domain.entity.ProductPriceHistory;
import org.anotes.example.twitterflight.domain.viewModel.ProductFlt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: gmc
 * Date: 16/03/14
 */
@Controller
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    ProductService productService;
    @Autowired
    MainInfo mainInfo;

    @RequestMapping("/main")
    public ModelAndView main(HttpServletRequest request) {
        Map requestParams = request.getParameterMap();
        updateMainInfoBasedOn(requestParams);
        ProductFlt productFlt = mainInfo.getFilter();
        SummaryInfo summaryInfo = mainInfo.getSummaryInfo();
        List<Product> products = productService.findProducts(productFlt, mainInfo.getPageInfo());
        Map model = new HashMap<>();
        model.put("products", products);
        model.put("pageInfo", mainInfo.getPageInfo());
        model.put("filterList", productFlt.getFilterEntryList());
        model.put("brandList", summaryInfo.getBrandSummaryList());
        return new ModelAndView("main", model);
    }

    private void updateMainInfoBasedOn(Map requestParams) {
        String page = getRequestParameterValue(requestParams, "page");
        if (StringUtils.hasText(page)) {
            mainInfo.setCurrentPage(Integer.parseInt(page));
            return;
        }
        String filter = getRequestParameterValue(requestParams, "filter");
        if (StringUtils.hasText(filter)) {
            String value = getRequestParameterValue(requestParams, "value");
            mainInfo.updateFilter(filter, value);
        }
        SummaryInfo summaryInfo = productService.createSummaryInfoFor(mainInfo.getFilter());
        mainInfo.setSummaryInfo(summaryInfo);
    }

    public static String getRequestParameterValue(Map parameterMap, String paramName) {
        String[] values = (String[]) parameterMap.get(paramName);
        return values != null ? values[0] : null;
    }


    @RequestMapping("/getProductInfo")
    public ModelAndView getProductInfo(@RequestParam("product") Long productGkey) {
        logger.debug("Getting product info for:{}", productGkey);
        List<ProductPriceHistory> productPriceHistoryList = productService.getProductPricesHistoryFor(productGkey);
        Map model = new HashMap();
        model.put("productPriceHistoryList", productPriceHistoryList);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        model.put("displayDateFormatter", dateFormat);
        return new ModelAndView("productDetail", model);
    }

}