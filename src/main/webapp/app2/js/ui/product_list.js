'use strict';

define(
    [
        'flight/lib/component',
        'bower_components/mustache/mustache',
        'app2/js/templates'
    ],
    function (defineComponent, Mustache, templates) {
        return defineComponent(productList);

        function productList() {

            this.defaultAttrs({
                //selectors
                productListZone: "#product-list-zone",
                productListTitle: "#product-list-title"
            });

            this.onClick = function (ev) {
                var $productItem = $(ev.target).closest('.list-group-item');
                var productGkey = $productItem.attr("data-product-gkey");
                showProductInfo($productItem, productGkey);
            }

            function showProductInfo(obj, productGkey) {
                var $this = $(obj);
                var $collapse = $this.find('.collapse');
                var dataString = 'product=' + productGkey;
                var spanId = '#spn' + productGkey;
                var currentText = $(spanId).text();
                if (currentText == "Expand") {
                    $(spanId).text("Collapse");
                    $.ajax({
                        type: "GET",
                        url: "/getProductInfo.htm",
                        data: dataString,
                        success: function (data) {
                            $collapse.html(data);
                            $collapse.show();
                        }
                    });
                } else {
                    $(spanId).text("Expand")
                    $collapse.hide();
                }
                return false;
            }


            this.renderItems = function (ev, data) {
                console.info("On Rendering Products");
                var $productListTitle = this.select('productListTitle');
                var currentRangePage = getCurrentRangePage(data.pageInfo);
                var pageInfoData = {currentRangeStr: currentRangePage, productsNbr: data.pageInfo.productsNbr}
                var newTitleHtml = Mustache.render(templates.productsTitle, pageInfoData);
                $productListTitle.html(newTitleHtml);
                var $productList = this.select('productListZone');
                var productListHtml = Mustache.render(templates.products, data);
                $productList.html(productListHtml);
            }

            function getCurrentRangePage(pageInfo) {
                var rangeEnd = (pageInfo.currentPage + 1) * pageInfo.pageSize;
                return (pageInfo.currentPage * pageInfo.pageSize + 1) + " - " + Math.min(rangeEnd, pageInfo.productsNbr);
            }

            this.after('initialize', function () {
                this.on(document, 'pageDataChanged', this.renderItems);
                this.on(document, 'dataChanged', this.renderItems);
                this.on('click', this.onClick);
            });
        }
    }
);
