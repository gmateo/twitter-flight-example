package org.anotes.example.twitterflight.web.controller;

import org.anotes.example.twitterflight.application.service.ProductService;
import org.anotes.example.twitterflight.domain.SummaryInfo;
import org.anotes.example.twitterflight.domain.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User: gmc
 * Date: 16/03/14
 */
@Controller
public class Main2Controller {
    final Logger logger = LoggerFactory.getLogger(Main2Controller.class);

    @Autowired
    ProductService productService;
    @Autowired
    MainInfo mainInfo;

    @RequestMapping("/main2")
    public ModelAndView main() {
        return new ModelAndView("main2");
    }


    @RequestMapping(value = "/changePage2", method = RequestMethod.POST)
    @ResponseBody
    public Main2Json changePage(@RequestParam("page") Integer page) {
        logger.info("Changing page to:{}", page);
        mainInfo.setCurrentPage(page);
        List<Product> products = productService.findProducts(mainInfo.getFilter(), mainInfo.getPageInfo());
        Main2Json main2Json = new Main2Json();
        main2Json.setProductList(products);
        main2Json.setPageInfo(mainInfo.getPageInfo());
        return main2Json;
    }

    @RequestMapping(value = "/changeFilter2", method = RequestMethod.POST)
    @ResponseBody
    public Main2Json changeFilter(@RequestParam("filter") String filter, @RequestParam("value") String value) {
        logger.info("Changing filter to:({}:{})", filter, value);
        mainInfo.updateFilter(filter, value);
        SummaryInfo summaryInfo = productService.createSummaryInfoFor(mainInfo.getFilter());
        mainInfo.setSummaryInfo(summaryInfo);
        List<Product> products = productService.findProducts(mainInfo.getFilter(), mainInfo.getPageInfo());
        Main2Json main2Json = new Main2Json();
        main2Json.setTotalPages(mainInfo.getPageInfo().getTotalPages());
        main2Json.setBrandSummaryList(summaryInfo.getBrandSummaryList());
        main2Json.setProductList(products);
        main2Json.setPageInfo(mainInfo.getPageInfo());
        main2Json.setFilterEntryList(mainInfo.getFilter().getFilterEntryList());
        return main2Json;
    }

}