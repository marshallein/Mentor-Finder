/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var roomId = [[${roomId}]];
var thisUserId = [[${userId}]];

function loadMsg(){
    
    var json = {
        roomId: roomId;
    }
    
    $.ajax({
		type:"get",
		url:"/msg/load",
		data: json,
		dataType:"json",
		success:function(data)
                {	
                    for(var i=0;i<data.length;i++)
                    {
                        var content = data[i].content;
			var sender = data[i].sender;
                        var time = data[i].time;
		
			var messageElement;
                        if (message.sender == thisUserId)
                        {
                            messageElement = '<div class="media w-50 ml-auto mb-3">'     
                        + '<div class="media-body">'
                        + '<div class="bg-primary rounded py-2 px-3 mb-2">'
                        + '<p class="text-small mb-0 text-white">'+ message.content +'</p>'
                        + '</div>'
                        + '<p class="small text-muted">'+ message.time +'</p>'
                        +  '</div>'
                        +  '</div>'                
                        }
                        else
                        {
                            messageElement=  '<div class="media w-50 mb-3"><img src="" alt="user" width="50" class="rounded-circle">' 
                        + '<div class="media-body ml-3">'
                        + '<div class="bg-light rounded py-2 px-3 mb-2">'
                        + '<p class="text-small mb-0 text-muted">' + message.content+'</p>'
                        + '</div>'
                        + '<p class="small text-muted">' + message.time +'</p>'
                        + '</div>'
                        +  '</div>'
                        +  '</div>'      
                        }
			$("#messageArea").append(messageElement);
                    }
		}
	});
}

$(document).ready(function() {
            window.addEventListener('loadMsg', connect, true);
       
})


