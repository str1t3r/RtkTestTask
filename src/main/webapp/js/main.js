$(document).ready(function() {

   $('#datatable').on('change', '[data-action=change_color]', function() {
       var selected = $(this).find(":selected");
       var color = $(selected).data('color');
       var action = $(selected).val();
       if (action === "bg") {
           $(this).parent().parent().css("background", color);
       } else {
           $(this).parent().parent().css('color', color);
       }
   });
   
   $('#load_more').click(function(e) {
       e.preventDefault();
       var offset = $(this).data('offset');
       $.ajax({
           type: 'GET',
           url: './dataServlet',
           data: {
               'offset' : offset
           },
           success: function(data) {
               var parent = $('#datatable tbody tr:last-child');
               $.each(data, function(index, value) {    
                    var prot = parent.clone();
                    prot.removeAttr("style");
                    prot.find("td:first-child").text(value.colorNumber);
                    prot.find("td:nth-child(2)").text(value.name);
                    prot.find("option").data("color", value.name.replace(/\s/g,''));
                    $('#datatable tbody tr:last-child').after(prot);

                    $('#load_more').data('offset', offset + data.length);
            });
           },
           dataType: 'json'
       });
   });
});


