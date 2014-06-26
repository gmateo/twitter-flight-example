var ajaxLoader = {
    'defaultLoader': ['bar', 'moon', 'dots', 'pulser', 'commet', 'clock',
        '3dots', 'hlaf_spin', 'circle'
    ],
    'defaultTheme': ['apple', 'dark', 'grass', 'sky', 'love', 'girl',
        'chocolate', 'gold'
    ],
    'ALPTheme': {
        'apple': '#703642',
        'dark': '#000000',
        'grass': '#008000',
        'sky': '#1E90FF',
        'love': '#9370DB',
        'girl': '#FF69B4',
        'chocolate': '#8B4513',
        'gold': '#BDB82E'
    },
    'init': function (option) {
        if (typeof option === "undefined") {
            option = {};
        }
        currentFilePath = ajaxLoader.retrieveURL('jquery.ajaxloader');
        var loader = 'bar';
        var size = 0;
        var theme = ajaxLoader.ALPTheme.dark;
        if (option.loader != 'empty' && jQuery.inArray(option.loader, ajaxLoader.defaultLoader) > -1) {
            loader = option.loader;
        }
        if (option.theme != 'empty' && jQuery.inArray(option.theme, ajaxLoader.defaultTheme) > -1) {
            theme = ajaxLoader.ALPTheme[option.theme];
        }

        $("<link/>", {
            rel: "stylesheet",
            type: "text/css",
            href: currentFilePath + '/../css/' + loader + '.css'
        }).appendTo("head");

        $('body').append(
                '<div class="alert_popup_overlay" id="ALP_ajax-overlay"' + 'style="background: none repeat scroll 0 0 #00000">' + '<div class="ALP_content">' + '<div class="loader">Loading...</div>' + '</div></div>');

        $('.alert_popup_overlay').css({
            'background': 'none repeat scroll 0 0 ' + theme,
            'opacity': '0.6',
            'position': 'fixed',
            'bottom': '0',
            'left': '0',
            'right': '0',
            'top': '0',
            'z-index': '1111111',
        });
        $('.ALP_content').css({
            'left': '50%',
            'margin-left': '-200px',
            'position': 'fixed',
            'top': '30%',
            'padding-top': '114px',
            'width': '400px',
            'z-index': '11111111'
        });

        ajaxLoader.ALPAjaxSetup();
        $('#ALP_ajax-overlay').hide();
        $('.loader').css('margin', 'auto');
        if (option.size != 'empty' && option.size > 0 && option.size < 75) {
            $('.loader').css('font-size', option.size + 'px');
        }

    },
    'retrieveURL': function (filename) {
        var scripts = document.getElementsByTagName('script');
        if (scripts && scripts.length > 0) {
            for (var i in scripts) {
                if (scripts[i].src && scripts[i].src
                    .match(new RegExp(filename + '\\.js$'))) {
                    return scripts[i].src.replace(new RegExp('(.*)' + filename + '\\.js$'), '$1');
                }
            }
        }
    },
    'ALPAjaxSetup': function () {
        $(document).ajaxStart(function () {
            $('#ALP_ajax-overlay').show();
        });

        $(document).ajaxStop(function () {
            $('#ALP_ajax-overlay').hide();
        });
    },
}