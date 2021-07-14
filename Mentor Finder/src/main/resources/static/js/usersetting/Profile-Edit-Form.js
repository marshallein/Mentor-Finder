file(function(){
    function readURL(input) {

        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.avatar-bg').css({
                    'background':'url('+e.target.result+')',
                    'background-size':'cover',
                    'background-position': '50% 50%'
                });
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
    
    function toggleAlert(clasz, display){
        $(".alert")
            .removeClass("display")
            .removeClass("alert-info")
            .removeClass("alert-success")
            .removeClass("alert-danger")
            .addClass(clasz);
        if(display){
            $(".alert").addClass("display");
            $(".alert").css({"z-index": "1"});
        }
        if(clasz === "alert-success"){
            $(".alert > span").text('Profile saved');
        }else if(clasz === "alert-danger"){
            $(".alert > span").text('Profile reset');
        }
    }

    $("input.form-control[name=avatar]").change(function(){
        readURL(this);
    });
    
    $('#profile').on('reset', function(){
        var inst = $('#profile');

        $(inst).find("button[type = reset]").addClass("loading").prop("disabled", true);
        toggleAlert("alert-danger",true);
        
        setTimeout(function(){
            $(inst).find("button[type = reset]").removeClass("loading").prop("disabled", false);
            toggleAlert("alert-danger");
        },1000);
    });
    
    $('#profile').on('submit', function (e) {
        e.preventDefault();
        var form = new FormData($("form#profile-form")[0]);
        var json = {
            fullname: $("#fullname").val(),
            school: $("#school").val(),
            email: $("#email").val(),
            address: $("#address").val(),
            telephone: $("#telephone").val(),
            dob: $("#dob").val()
        };
        $.ajax({
            type: "POST",
            url: "/profile/update",
            data: form,
            dataType: "json",
            contentType: false,
            processData: false,
            success: function(){
                $("#fullname").attr("value", json["fullname"]);
                $("#school").attr("value", json["school"]);
                $("#email").attr("value", json["email"]);
                $("#address").attr("value", json["address"]);
                $("#telephone").attr("value", json["telephone"]);
                $("#dob").attr("value", json["dob"]);
                
                var inst = $('#profile');
                
                $(inst).find("button[type = submit]").addClass("loading").prop("disabled", true);
                toggleAlert("alert-success", true);

                setTimeout(function(){
                    $(inst).find("button[type = submit]").removeClass("loading").prop("disabled", false);
                    toggleAlert("alert-success");
                },1000);

            },
            error: function(e){
                console.log(e);
            }
        });
    });
})();