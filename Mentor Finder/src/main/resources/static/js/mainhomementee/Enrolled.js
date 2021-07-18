function getEnrolledList(reqId){
    var json = {
        reqId: parseInt(reqId,10)
    };
    $.ajax({
        type: "GET",
        url: "/enrolled/list",
        data: json,
        dataType: "json",
        success: function (data){
            var html =  '<div style=\"padding: 20px;overflow-y: scroll;max-height: 500px;box-shadow: 7px 7px 0px var(--secondary);border: 3px solid var(--gray) ;\">'
                    +   '<div id=\"listmentor\" style="padding: 10px;border: 1px solid var(--gray);margin-top: 10px;padding-bottom: 5px;\">';
            if (data["status"]){
                html += '<p>FINISH</p>';
                html +='<div>\n'
                            + '<p style=\"font-size: 10;display: inline-block;\">' + data["name"] + '</p>\n'
                            + '<div class=\"dropdown\" style=\"margin-left: 150px;display: inline-block;\"><button class=\"btn btn-primary dropdown-toggle\" aria-expanded=\"false\" data-toggle=\"dropdown\" type=\"button\">Action</button>\n'
                            +     '<div class=\"dropdown-menu\"><a class=\"dropdown-item\" href=\"/profile/'+ data["uid"] +'\">Profile</a></div>\n'
                            + '</div>\n'
                        + '</div>\n';
            }
            else {
                html += '<p>IN PROGRESS</p>';
                for (var i=0; i<data.length; i++){
                    if (data[i]["estatus"]=='NEW'){
                        html +='<div>\n'
                                + '<p style=\"font-size: 10;display: inline-block;\">' + data[i]["name"] + '</p>\n'
                                + '<div class=\"dropdown\" style=\"margin-left: 150px;display: inline-block;\"><button class=\"btn btn-primary dropdown-toggle\" aria-expanded=\"false\" data-toggle=\"dropdown\" type=\"button\">Action</button>\n'
                                +     '<div class=\"dropdown-menu\"><a class=\"dropdown-item\" href=\"/profile/'+ data[i]["uid"] +'\">Profile</a><a class=\"dropdown-item\" href=\"/enrolled/decide/'+ data[i]["enrId"] + '/ACCEPT\">Accept</a><a class=\"dropdown-item\" href=\"/enrolled/decide/'+ data[i]["enrId"] + '/REJECT\">Reject</a>'
                                +     '<a class=\"dropdown-item\" href=\"/roomcreate/'+ data[i]["uid"] +'\">Chat</a></div>\n'
                                + '</div>\n'
                            + '</div>\n';
                    }
                };
            }
            html += '</div>'
                +   '</div>';
            $(".modal-body").html(html);
        
        },
        error: function(e){
            console.log(e);
        }
    });
}
