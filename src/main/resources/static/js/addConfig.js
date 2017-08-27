$(document).ready(function() {
   $("#adapIdSelect").change(function(e) {
       $this = $(e.target);
        alert($this.val());
       if ("" != $this.val()) {
           $.ajax({
               type: "post",
               url: "/getAdapVersions",
               data: {'adaptationId': $this.val()},
               success: function(data, status) {
                   var obj = eval(data);
                   $(obj).each(function (index) {
                       var val = obj[index];
                       $("#adapVersionSelect").append('<option value="' + val + '">' + val + '</option>');

                   });
               }
           });
       }
   });
});