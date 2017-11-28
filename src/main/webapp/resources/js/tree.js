(function($) {
    var defaults = {
        autoParentDetection: true,
        autoActiveDetection: true,
        itemsUniqueClasses: true,
        parentClass: 'parent',
        activeClass: 'active',
        selectedClass: 'selected',
        expandClass: 'opened',
        collapseClass: 'closed',
        slideEffect: true
    };

    var methods = {
        init: function(params) {
            var options = $.extend({}, defaults, params);

            var items = this.find('li');

            $.each(items, function(num, item) {
                item = $(item);

                if (options.autoParentDetection) {
                    if (item.has('ul')[0]) {
                        item.addClass(options.parentClass);
                    }
                }

                if (options.itemsUniqueClasses) {
                    item.addClass('item-' + num);
                }

            });

            var parents = this.find('.' + options.parentClass);

            $.each(parents, function(num, parent) {
                parent = $(parent);

                parent.addClass(options.collapseClass);

                if (parent.has('.' + options.selectedClass)[0]) {
                    parent.removeClass(options.collapseClass).addClass(options.expandClass);

                    if (options.autoActiveDetection) {
                        parent.addClass(options.activeClass);
                    }
                }

                if (parent.hasClass(options.selectedClass)) {
                    parent.removeClass(options.activeClass).removeClass(options.collapseClass).addClass(options.expandClass);
                }
            });

            $('.' + options.collapseClass + ' > ul', this).hide();

            $('.' + options.parentClass + ' > a', this).click(function(e) {
          
                    var item = $(this).parent('li');
                    var content = $(this).parent('li').children('ul');

                    item.toggleClass(options.expandClass).toggleClass(options.collapseClass);

                    if (options.slideEffect) {
                        content.slideToggle();
                    } else {
                        content.toggle();
                    }

                    e.preventDefault();
                
            });
        }
    };

    $.fn.ntm = function(method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method "' + method + '" was not found in the jQuery.ntm plugin');
        }
    };
})(jQuery);
