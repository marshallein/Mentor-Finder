(function(){
    function toggleAlert(clasz, display){
        $(".alert")
            .removeClass("display")
            .removeClass("alert-info")
            .removeClass("alert-success")
            .removeClass("alert-danger")
            .addClass(clasz);
        if(display){
            $(".alert").addClass("display");
        }
        if(clasz === "alert-success"){
            $(".alert > span").text('Password changed!');
        }else if(clasz === "alert-danger"){
            $(".alert > span").text('Password changed fail!');
        }
    }
    
    $('#profile').on('submit', function (e) {
        e.preventDefault();
        var json = {
            old_password: $("#old-pass").val(),
            new_password: $("#new-pass").val(),
            re_new_password: $("#re-new-pass").val()
            
        };
        $.ajax({
            type: "POST",
            contentType : "application/x-www-form-urlencoded; charset=UTF-8",
            url: "/profile/update/password",
            data: json,
            dataType: "json",
            success: function(data){
                if (data["status"]) {
                    var inst = $('#profile');

                    $(inst).find("button[type = submit]").addClass("loading").prop("disabled", true);
                    toggleAlert("alert-success",true);

                    setTimeout(function(){
                        $(inst).find("button[type = submit]").removeClass("loading").prop("disabled", false);
                        toggleAlert("alert-success");
                    },1000);
                }
                else {
                    var inst = $('#profile');

                    $(inst).find("button[type = submit]").addClass("loading").prop("disabled", true);
                    toggleAlert("alert-danger",true);

                    setTimeout(function(){
                        $(inst).find("button[type = submit]").removeClass("loading").prop("disabled", false);
                        toggleAlert("alert-danger");
                    },1000);
                    return false;
                }
            },
            error: function(e){
                console.log(e);
            }

        });
        
    });
    
    $('#profile').delegate('form', 'reset', function (e) {
        var inst = this;
        var formData = new FormData($(this)[0]);

        $(inst).find("button[type = reset]").addClass("loading").prop("disabled", true);
        toggleAlert("alert-danger",true);
        
        setTimeout(function(){
            $(inst).find("button[type = reset]").removeClass("loading").prop("disabled", false);
            toggleAlert("alert-danger");
        },1000);
        
        return false;
    });
})();