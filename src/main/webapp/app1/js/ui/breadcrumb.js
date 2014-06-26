'use strict';

define(
    [
        'flight/lib/component'
    ],
    function (defineComponent) {

        return defineComponent(breadcrumb);

        function breadcrumb() {

            this.defaultAttrs({
                //selectors
            });

            this.onClick = function (ev, data) {
                ev.preventDefault();
                var filterIdx = $(ev.target).attr("data-filter-idx");
                this.trigger("uiFilterChanged", {filter: "breadcrumbIdx", value: filterIdx})
            }


            this.renderItems = function (ev, data) {
                console.info("On Rendering Breadcrumb");
                var $breadcrumb = this.$node;
                $.ajax({
                    type: "GET",
                    url: "/getBreadcrumb1.htm",
                    success: function (data) {
                        $breadcrumb.html(data);
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
