'use strict';

define(
    [
        'flight/lib/component'
    ],
    function (defineComponent) {

        return defineComponent(brandList);

        function brandList() {

            this.defaultAttrs({
            });

            this.onClick = function (ev, data) {
                ev.preventDefault();
                var $anchor = $(ev.target).closest('.list-group-item').find('a');
                var brandId = $anchor.attr("data-brand-id");
                this.trigger("uiFilterChanged", {filter: "brand", value: brandId})
            };

            this.renderItems = function (ev, data) {
                console.info("On Rendering Brands");
                var $brandList = this.$node;
                $.ajax({
                    type: "GET",
                    url: "/getBrandList1.htm",
                    success: function (data) {
                        $brandList.html(data);
                    }
                });
            }

            this.after('initialize', function () {
                this.on('click', this.onClick);
                this.on(document, 'dataChanged', this.renderItems);
            });
        }
    }
);
