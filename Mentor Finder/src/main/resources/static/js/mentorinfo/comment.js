function comment(mentorId, content){
    var json = {
        mentorId: mentorId,
        content: content
    };

    $.ajax({
        type: "POST",
        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        url: "/comment",
        data: json,
        dataType: "json",
        success: function(event){
            console.log("success");
        },
        error: function(e){
            console.log(e);
        }

    });
}

function getComment(){
    var json = {
        mentorId: $("#mentorId").val()
    };

    $.ajax({
        type: "GET",
        url: "/comment/get",
        data: json,
        dataType: "json",
        success: function(data){
            var html='<div style=\"overflow-y: auto;height: 277px;margin-right: 5px;\">';
            for (var i=0; i<data.length; i++){
                html += '<div style=\"padding: 20px;padding-top: 10px;margin: 7px;background: rgb(255,212,83);border-radius: 22px;border-bottom-right-radius: 0px;border-bottom-left-radius: 0px;border-top-left-radius: 14px;border-top-right-radius: 14px;border-bottom: 8px solid var(--secondary);margin-right: 40px;margin-left: 40px;"><img style="float: left;width: 70px;height: 70px;margin-right: 22px;margin-top: 0px;\">'
                    + '<p style=\"margin-bottom: 5px;\">'+ data[i]["from"] +'</p>'
                    + '<p style=\"margin-bottom: 5px;\">'+ data[i]["content"] +'</p>'
                    + '</div>';
            }
            html += '</div>';
            $(".comment-list").html(html);
        },
        error: function(e){
            console.log(e);
        }

    });
}
