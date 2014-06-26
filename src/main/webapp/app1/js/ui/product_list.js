'use strict';

define(
    [
        'flight/lib/component'
    ],
    function (defineComponent) {

        return defineComponent(productList);

        function productList() {

            this.defaultAttrs({
                //selectors
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
                        url: "/getProductInfo1.htm",
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
                var $productList = this.$node;
                $.ajax({
                    type: "GET",
                    url: "/getProductList1.htm",
                    success: function (data) {
                        $productList.html(data);
                    }
                });
            }

            this.after('initialize', function () {
                this.on(document, 'pageDataChanged', this.renderItems);
                this.on(document, 'dataChanged', this.renderItems);
                this.on('click', this.onClick);
            });
        }
    }
);
