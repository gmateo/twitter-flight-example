'use strict';

define(
    [
        'flight/lib/component',
        'bower_components/mustache/mustache',
        'app2/js/templates'
    ],
    function (defineComponent, Mustache, templates) {

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
                var filterList = data.filterList;
                var last = filterList[filterList.length - 1];
                var breadcrumbActiveHtml = Mustache.render(templates.breadcrumbActive, last);
                var breadcrumbHtml = getBreadcrumbLinks(filterList);
                $breadcrumb.html(breadcrumbHtml + breadcrumbActiveHtml);
            }

            function getBreadcrumbLinks(fltList) {
                var breadcrumbHtml = "";
                if (fltList.length > 1) {
                    var list = fltList.slice(0, fltList.length - 1);
                    var filterIdx = 0;
                    var filterList = {
                        filterList: list,
                        'filterIdx': function () {
                            return function (text, render) {
                                return filterIdx++;
                            }
                        }
                    };
                    breadcrumbHtml = Mustache.render(templates.breadcrumb, filterList);
                }
                return breadcrumbHtml;
            }

            this.after('initialize', function () {
                this.on('click', this.onClick);
                this.on(document, 'breadcrumbChanged', this.renderItems);
            });
        }
    }
);
