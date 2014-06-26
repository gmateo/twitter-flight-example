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
                    type: "POST",
                    url: "/changePage1.htm",
                    data: dataString,
                    success: function (data) {
                        console.info("ChangPage response:" + data);
                        $(document).trigger("pageDataChanged")
                    }
                });
            };

            this.onUiFilterChanged = function (ev, data) {
                console.info("Changing filter:" + data.filter + " value:" + data.value);
                var dataString = 'filter=' + data.filter + "&value=" + data.value;
                $.ajax({
                    type: "POST",
                    url: "/changeFilter1.htm",
                    data: dataString,
                    success: function (data) {
                        console.info("ChangeFilter response. New number of pages:" + data);
                        $(document).trigger("dataChanged", { resultSize: data })
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

