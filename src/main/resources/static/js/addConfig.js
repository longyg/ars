$(document).ready(function() {
    $("#srcAddBtnDiv").hide();
    $("#ifaceAddBtnDiv").hide();

   $("#adapIdSelect").change(function(e) {
       $("#srcAddBtnDiv").hide();
       $this = $(e.target);
       if ("" != $this.val()) {
           $.ajax({
               type: "post",
               url: "/getAdapVersions",
               data: {
                   adaptationId: $this.val()
               },
               success: function(data, status) {
                   $("#adapVersionSelect").html('<option value="">--Select--</option>');
                   var obj = eval(data);
                   $(obj).each(function (index) {
                       var val = obj[index];
                       $("#adapVersionSelect").append('<option value="' + val + '">' + val + '</option>');

                   });
               }
           });
       } else {
           $("#adapVersionSelect").html('<option value="">--Select--</option>');
       }
   });

   $("#adapVersionSelect").change(function(e) {
      $this = $(e.target);
      if ("" != $this.val()) {
            $("#srcAddBtnDiv").show();
      } else {
          $("#srcAddBtnDiv").hide();
      }
   });

   $("#srcAddBtn").click(function(e) {
       e.preventDefault();
       if ($("#adapIdSelect").val() == "") {
           alert("Please select Adaptation ID");
       }
       if ($("#adapVersionSelect").val() == "") {
           alert("Please select Adaptation Version");
       }

       $.ajax({
           type: "post",
           url: "/ars/addResource",
           data: {
               neTypeId: $("input[name='neTypeId']").val(),
               neRelId: $("input[name='neRelId']").val(),
               adaptationId: $("#adapIdSelect").val(),
               adaptationRelease: $("#adapVersionSelect").val()
           },
           success: function(data, status) {
               if (data.status == "ok") {
                   var adap = data.data;
                   $("#srcTableBody").append("<tr>\n" +
                       "                            <td>" + adap.id + "</td>\n" +
                       "                            <td>" + adap.release + "</td>\n" +
                       "                            <td>Delete</td>\n" +
                       "                          </tr>")
               }
           }
       });
   });

    $("#interfaceSelect").change(function(e) {
        $this = $(e.target);
        if ("" != $this.val()) {
            $("#ifaceAddBtnDiv").show();
        } else {
            $("#ifaceAddBtnDiv").hide();
        }
    });

    $("#ifaceAddBtn").click(function(e) {
        e.preventDefault();
        if ($("#interfaceSelect").val() == "") {
            alert("Please select interface");
        }
        $.ajax({
            type: "post",
            url: "/ars/addInterface",
            data: {
                neTypeId: $("input[name='neTypeId']").val(),
                neRelId: $("input[name='neRelId']").val(),
                interfaceId: $("#interfaceSelect").val()
            },
            success: function(data, status) {
                if (data.status == "ok") {
                    var iface = data.data;
                    $("#ifaceTableBody").append("<tr>\n" +
                        "                            <td>" + iface.name + "</td>\n" +
                        "                            <td>Delete</td>\n" +
                        "                          </tr>")
                }
            }
        });
    });
});