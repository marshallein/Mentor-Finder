function getEnrolledList(reqId){
    console.log("step 1");
    var json = {
        reqId: parseInt(reqId,10)
    };
    console.log("step 2");
    $.ajax({
        type: "GET",
        url: "/enrolled/list",
        data: json,
        dataType: "json",
        success: function (data){
            var html = "";
            for (var i=0; i<data.length; i++){
                html +='<div>\n'
                        + '<p style=\"font-size: 10;display: inline-block;\">' + data[i]["name"] + '</p>\n'
                        + '<div class=\"dropdown\" style=\"margin-left: 120px;display: inline-block;\"><button class=\"btn btn-primary dropdown-toggle\" aria-expanded=\"false\" data-toggle=\"dropdown\" type=\"button\">Action</button>\n'
                        +     '<div class=\"dropdown-menu\"><a class=\"dropdown-item\" href=\"/profile/'+ data[i]["uid"] +'\">Profile</a><a class=\"dropdown-item\" href=\"#\">Accept</a><a class=\"dropdown-item\" href=\"#\">Reject</a></div>\n'
                        + '</div>\n'
                    + '</div>\n';
            };
            console.log(html);
            $("#listmentor").html(html);
        
        },
        error: function(e){
            console.log(e);
        }
    });
}
