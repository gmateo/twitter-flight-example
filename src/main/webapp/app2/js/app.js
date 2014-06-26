/*global define */
'use strict';

define([
    './data/data_provider',
    './ui/product_list',
    './ui/brand_list',
    './ui/breadcrumb',
    './ui/search_box',
    './ui/paginator'
], function (DataProviderData, ProductListUI, BrandListUI, BreadcrumbUI, SearchBoxUI, PaginatorUI) {
    var initialize = function () {
        DataProviderData.attachTo(document);
        ProductListUI.attachTo('#product-zone');
        BrandListUI.attachTo('#brand-zone');
        BreadcrumbUI.attachTo('#breadcrumb-zone');
        SearchBoxUI.attachTo('#search-zone');
        PaginatorUI.attachTo('#paginator-zone');
    };
    return {
        initialize: initialize
    };
});
