package org.anotes.example.twitterflight.web.controller;

import org.anotes.example.twitterflight.application.service.ProductService;
import org.anotes.example.twitterflight.domain.SummaryInfo;
import org.anotes.example.twitterflight.domain.entity.Product;
import org.anotes.example.twitterflight.domain.entity.ProductPriceHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: gmc
 * Date: 16/03/14
 */
@Controller
public class Main1Controller {
    final Logger logger = LoggerFactory.getLogger(Main1Controller.class);

    @Autowired
    ProductService productService;
    @Autowired
    MainInfo mainInfo;

    @RequestMapping("/main1")
    public ModelAndView main() {
        return new ModelAndView("main1");
    }


    @RequestMapping(value = "/changePage1", method = RequestMethod.POST)
    @ResponseBody
    public String changePage(@RequestParam("page") Integer page) {
        logger.info("Changing page to:{}", page);
        mainInfo.setCurrentPage(page);
        return "Ok";
    }

    @RequestMapping(value = "/changeFilter1", method = RequestMethod.POST)
    @ResponseBody
    public String changeFilter(@RequestParam("filter") String filter, @RequestParam("value") String value) {
        logger.info("Changing filter to:({}:{})", filter, value);
        mainInfo.updateFilter(filter, value);
        SummaryInfo summaryInfo = productService.createSummaryInfoFor(mainInfo.getFilter());
        mainInfo.setSummaryInfo(summaryInfo);
        return "" + mainInfo.getPageInfo().getTotalPages();
    }

    @RequestMapping("/getProductList1")
    public ModelAndView getProductList() {
        List<Product> products = productService.findProducts(mainInfo.getFilter(), mainInfo.getPageInfo());
        Map model = new HashMap<>();
        model.put("products", products);
        model.put("pageInfo", mainInfo.getPageInfo());
        return new ModelAndView("productList", model);
    }

    @RequestMapping("/getBrandList1")
    public ModelAndView getBrandList() {
        Map model = new HashMap<>();
        model.put("brandList", mainInfo.getSummaryInfo().getBrandSummaryList());
        return new ModelAndView("brandList", model);
    }

    @RequestMapping("/getBreadcrumb1")
    public ModelAndView getBreadcrumb() {
        Map model = new HashMap<>();
        model.put("filterList", mainInfo.getFilter().getFilterEntryList());
        return new ModelAndView("breadcrumb", model);
    }

    @RequestMapping("/getProductInfo1")
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