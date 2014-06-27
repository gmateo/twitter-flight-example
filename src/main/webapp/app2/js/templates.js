'use strict';

define(
    function () {
        var brands =
            '{{#brandList}}\
             <div class="panel-collapse collapse in">\
                <ul class="list-group">\
                    <li class="list-group-item">\
                        <a href="#" data-brand-id="{{id}}">\
                            {{description}}\
                        </a>\
                        <span class="badge" >\
                            {{nbr}}\
                        </span>\
                    </li>\
                </ul>\
            </div>\
            {{/brandList}}';

        var breadcrumb =
            '{{#filterList}}\
            <li>\
                <a href="#" data-filter-idx="{{filterIdx}}">\
                {{value}}\
                </a>\
            </li>\
            {{/filterList}}';
        var breadcrumbActive =
            '<li class="active">\
               <span>\
                   {{value}}\
                </span>\
            </li>';

        var productsTitle = '&nbsp;&nbsp;&nbsp;&nbsp;Showing\
            <span>{{currentRangeStr}}</span> de\
            <span>{{productsNbr}}</span>';

        var products =
            '<div class="list-group">\
            {{#productList}}\
                <div class="list-group-item media-hover" data-product-gkey="{{gkey}}">\
                <div class="media">\
                    <a class="pull-left" href="#">\
                        <img class="media-object" data-src="holder.js/64x64" alt="64x64"\
                             style="width: 64px; height: 64px;"\
                             src="/img/{{imageUrl}}"/>\
                    </a>\
                    <div class="media-body" style="text-align:left ">\
                        <h4 class="media-heading" style="text-align:left" >\
                            {{name}}\
                        </h4>\
                        <div>\
                            <ul class="list-inline">\
                                <li>\
                                    Price:\
                                    <strong>{{price}}</strong>\
                                </li>\
                                <li>\
                                    Brand:<strong>{{brand}}</strong>\
                                </li>\
                            </ul>\
                        </div>\
                        <div class="container-fluid">\
                            <div class="row">\
                                <div class="col-sm-8">\
                                    <a class="pull-left cursorpointer">\
                                        <span id="spn{{gkey}}">Expand</span>\
                                    </a>\
                                </div>\
                                <div class="col-sm-4">\
                                </div>\
                            </div>\
                        </div>\
                        <p class="collapse"></p>\
                    </div>\
                </div>\
            </div>\
            {{/productList}}\
            </div>';

        return {
            brands: brands,
            breadcrumb: breadcrumb,
            breadcrumbActive: breadcrumbActive,
            productsTitle: productsTitle,
            products: products
        }
    }
);
