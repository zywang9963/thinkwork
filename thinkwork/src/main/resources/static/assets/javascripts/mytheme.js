(function() {
    $(document).ready(function() {

        $(".box .box-remove").click(function(e) {
            $(this).parents(".box").first().remove();
            return e.preventDefault();
        });
        $(".box .box-collapse").click(function(e) {
            var box;

            box = $(this).parents(".box").first();
            box.toggleClass("box-collapsed");
            return e.preventDefault();
        });
	});
}).call(this);