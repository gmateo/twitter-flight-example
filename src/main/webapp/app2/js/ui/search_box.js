'use strict';

define(
    [
        'flight/lib/component'
    ],
    function (defineComponent) {

        return defineComponent(searchBox);

        function searchBox() {
            this.defaultAttrs({
                txtSelector: '#txtSearch',
                btnSelector: '#btnSearch'
            });

            this.searchOnClick = function (e) {
                e.preventDefault();
                this.executeSearch();
            };

            this.executeSearch = function () {
                var $txtSearch = this.select('txtSelector');
                var textToSearch = $txtSearch.val().trim();
                if (!textToSearch) {
                    return;
                }
                $txtSearch.val('');
                this.trigger("uiFilterChanged", {filter: "name", value: textToSearch})
            }

            this.after('initialize', function () {
                this.on('click', {
                    btnSelector: this.searchOnClick
                });
                this.trigger("uiFilterChanged", {filter: "name", value: ""})
            });
        }
    }
);
