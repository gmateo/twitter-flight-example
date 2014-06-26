'use strict';

define(
    [
        'flight/lib/component'
    ],
    function (defineComponent) {

        return defineComponent(dataProvider);

        function dataProvider() {

            this.defaultAttrs({
                //selectors
            });

            this.onUiPageDataChanged = function (ev, data) {
                console.info("Changing page to:" + data.page);
                var dataString = 'page=' + data.page;
                $.ajax({
                    dataType: "json",
                    type: "POST",
                    url: "/changePage2.htm",
                    data: dataString,
                    success: function (data) {
                        console.info("ChangPage response:" + data);
                        $(document).trigger("pageDataChanged", {pageInfo: data.pageInfo, productList: data.productList})
                    }
                });
            };

            this.onUiFilterChanged = function (ev, data) {
                console.info("Changing filter:" + data.filter + " value:" + data.value);
                var dataString = 'filter=' + data.filter + "&value=" + data.value;
                $.ajax({
                    dataType: "json",
                    type: "POST",
                    url: "/changeFilter2.htm",
                    data: dataString,
                    success: function (data) {
                        console.info("ChangeFilter response. New number of pages:" + data.totalPages);
                        $(document).trigger("dataChanged", { resultSize: data.totalPages, pageInfo: data.pageInfo, productList: data.productList});
                        $(document).trigger("brandDataChanged", {brandList: data.brandSummaryList});
                        $(document).trigger("breadcrumbChanged", {filterList: data.filterEntryList});
                    }
                });
            }

            this.after('initialize', function () {
                this.on('uiPageDataChanged', this.onUiPageDataChanged);
                this.on('uiFilterChanged', this.onUiFilterChanged);
            });
        }
    }
);

