'use strict';

define(
    [
        'flight/lib/component',
        'bower_components/mustache/mustache',
        'app2/js/templates'
    ],
    function (defineComponent, Mustache, templates) {

        return defineComponent(brandList);

        function brandList() {

            this.defaultAttrs({
                divBrand: '#brand-list-zone'
            });

            this.onClick = function (ev, data) {
                ev.preventDefault();
                var $anchor = $(ev.target).closest('.list-group-item').find('a');
                var brandId = $anchor.attr("data-brand-id");
                this.trigger("uiFilterChanged", {filter: "brand", value: brandId})
            };

            this.renderItems = function (ev, data) {
                console.info("On Rendering Brands:" + data);
                var brandHtml = Mustache.render(templates.brands, data);
                var $brand = this.select('divBrand');
                $brand.html(brandHtml);
            };

            this.after('initialize', function () {
                this.on('click', this.onClick);
                this.on(document, 'brandDataChanged', this.renderItems);
            });
        }
    }
);
