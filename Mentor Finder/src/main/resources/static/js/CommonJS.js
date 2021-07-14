function getOneNotification(notId){
    $.ajax({
        type: "GET",
        url: "/notify/"+notId,
        dataType: "json",
        success: function (data){
            var read = data["read"];
            var unread = data["unread"];
            var total = data["all"];
            var html = "";
            for (var i in data["noti"]){
                if (i["type"]==="0"){

                }
                else if (i["type"]==="1"){

                }
                else if (i["type"]==="2"){

                }
                else if (i["type"]==="3"){

                }
                else if (i["type"]==="4"){

                }
                else if (i["type"]==="5"){

                }
                else if (i["type"]==="6"){

                }
                else if (i["type"]==="7"){

                }
            }
        },
        error: function(e){
            console.log(e);
        }
    });
}

                                    
function getAllNotification(){
    console.log("called");
    $.ajax({
        type: "GET",
        url: "/notify/all",
        dataType: "json",
        success: function (data){
            console.log(data);
            console.log("success");
            var read = data["read"];
            var unread = data["unread"];
            var total = data["all"];
            var noti = JSON.parse(data["noti"].replace('\\', ''));
            console.log(noti);
            var html = "";
            if (total==="0"){
                html += '<a class=\"dropdown-item\" href=\"#\" style=\"background-color: #ffdacc\">'
                    + '<div class=\"notify_icon\"><i class=\"far fa-bell-slash\" style=\"font-size: 22px;color: var(--blue);margin-right: 11px;\"></i></div>'
                    + '<div class=\"notify_item\">'
                    + '<p style=\"margin-bottom: 7px;\">You don\'t have any notification</p>'
                    + '</div>'
                    + '</a>';
            }
            for (var i in noti){
                if (i.status==="true"){
                    if (i.type==="0"){
                        html += '<a class=\"dropdown-item\" href=\"#\" style=\"background-color: #ffdacc\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-plus-square\" style=\"font-size: 22px;color: var(--green);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Request ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Created!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="1"){
                        html += '<a class=\"dropdown-item\" href=\"#\" style=\"background-color: #ffdacc\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-minus-square\" style=\"font-size: 22px;color: var(--pink);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Request ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Deleted!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="2"){
                        html += '<a class=\"dropdown-item\" href=\"#\" style=\"background-color: #ffdacc\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-edit\" style=\"font-size: 22px;color: var(--blue);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Request ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Edited!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="3"){
                        html += '<a class=\"dropdown-item\" href=\"#\" style=\"background-color: #ffdacc\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-check-square\" style=\"font-size: 22px;color: var(--green);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Enrollment ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Accepted!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="4"){
                        html += '<a class=\"dropdown-item\" href=\"#\" style=\"background-color: #ffdacc\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-times\" style=\"font-size: 22px;color: var(--pink);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Enrollment ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp;Rejected!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="5"){
                        html += '<a class=\"dropdown-item\" href=\"#\" style=\"background-color: #ffdacc\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-edit\" style=\"font-size: 22px;color: var(--blue);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Your :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Edited!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="6"){
                        html += '<a class=\"dropdown-item\" href=\"#\" style=\"background-color: #ffdacc\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-edit\" style=\"font-size: 22px;color: var(--blue);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Your :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Edited!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="7"){
                        html += '<a class=\"dropdown-item\" href=\"#\" style="background-color: #ffdacc">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-plus-square\" style=\"font-size: 22px;color: var(--green);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Request ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; have new enrollment!</p>'
                            + '</div>'
                            + '</a>';
                    }
                }
                else {
                    if (i.type==="0"){
                        html += '<a class=\"dropdown-item\" href=\"#\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-plus-square\" style=\"font-size: 22px;color: var(--green);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Request ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Created!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="1"){
                        html += '<a class=\"dropdown-item\" href=\"#\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-minus-square\" style=\"font-size: 22px;color: var(--pink);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Request ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Deleted!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="2"){
                        html += '<a class=\"dropdown-item\" href=\"#\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-edit\" style=\"font-size: 22px;color: var(--blue);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Request ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Edited!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="3"){
                        html += '<a class=\"dropdown-item\" href=\"#\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-check-square\" style=\"font-size: 22px;color: var(--green);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Enrollment ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Accepted!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="4"){
                        html += '<a class=\"dropdown-item\" href=\"#\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-times\" style=\"font-size: 22px;color: var(--pink);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Enrollment ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp;Rejected!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="5"){
                        html += '<a class=\"dropdown-item\" href=\"#\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-edit\" style=\"font-size: 22px;color: var(--blue);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Your :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Edited!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="6"){
                        html += '<a class=\"dropdown-item\" href=\"#\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-edit\" style=\"font-size: 22px;color: var(--blue);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Your :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; Edited!</p>'
                            + '</div>'
                            + '</a>';
                    }
                    else if (i.type==="7"){
                        html += '<a class=\"dropdown-item\" href=\"#\">'
                            + '<div class=\"notify_icon\"><i class=\"far fa-plus-square\" style=\"font-size: 22px;color: var(--green);margin-right: 11px;\"></i></div>'
                            + '<div class=\"notify_item\">'
                            + '<p style=\"margin-bottom: 7px;\">Request ID :&nbsp;<span style=\"color: var(--yellow);\"><strong>'+i.content+'</strong></span>&nbsp; have new enrollment!</p>'
                            + '</div>'
                            + '</a>';
                    }
                }
            }
            console.log(html);
            $("#noti-item").html(html);
            if (!(unread==="0")) {
                $("#noti-unread").html(unread);
            }
        },
        error: function(e){
            console.log(e);
        }
    });
}

function updateNotificationStatus(){
    $.ajax({
        type: "POST",
        url: "/notify/update",
        dataType: "json",
        success: function(e){
            console.log(e);
        },
        error: function(e){
            console.log(e);
        }
    });
}