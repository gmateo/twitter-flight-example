'use strict';

define(
    [
        'flight/lib/component'
    ],
    function (defineComponent) {

        return defineComponent(paginator);

        function paginator() {
            this.defaultAttrs({
                options: {
                    bootstrapMajorVersion: 3,
                    currentPage: 1,
                    totalPages: 2,
                    numberOfPages: 10
                }
            });

            this.onPageChanged = function (ev, lastPage, currentPage) {
                this.trigger('uiPageDataChanged', {page: (currentPage - 1)});
            };

            this.onDataChanged = function (ev, data) {
                var pagesNumber = data.resultSize;
                var $paginator = $('#paginator');
                if (pagesNumber > 1) {
                    $paginator.show();
                    this.attr.options.currentPage = 1;
                    this.attr.options.totalPages = pagesNumber;
                    $paginator.bootstrapPaginator(this.attr.options);
                } else {
                    $paginator.hide();
                }
            };

            this.after('initialize', function () {
                this.on('page-changed', this.onPageChanged);
                this.on(document, 'dataChanged', this.onDataChanged);
            });
        }
    }
);
