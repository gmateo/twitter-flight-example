'use strict';
require.config({
    baseUrl: '',
    paths: {
        jquery: '/bower_components/jquery/jquery.min',
        bootstrap: '/bower_components/bootstrap/js/bootstrap.min',
        bootstrapPaginator: '/bower_components/bootstrap/js/bootstrap-paginator.min',
        es5shim: '/bower_components/es5-shim/es5-shim',
        es5sham: '/bower_components/es5-shim/es5-sham',
        text: '/bower_components/requirejs-text/text',
        flight: '/bower_components/flight',
        nprogress: '/bower_components/nprogress/nprogress'
    },
    shim: {
        'bootstrap': {
            deps: ['jquery']
        },
        'nprogress': {
            deps: ['jquery']
        },
        'bootstrapPaginator': {
            deps: ['jquery', 'bootstrap']
        },
        'app2/js/app': {
            deps: ['jquery', 'es5shim', 'es5sham', 'bootstrap', 'bootstrapPaginator', 'nprogress']
        }
    }
});

require(['app2/js/app', 'nprogress'], function (App, NProgress) {
    App.initialize();
    $(window).load(function () {
        NProgress.done();
    });

    $(document).ready(function () {
        NProgress.start();
    });


    $(document).ajaxStart(function () {
        NProgress.start();
    });

    $(document).ajaxComplete(function () {
        NProgress.done();
    });

});




