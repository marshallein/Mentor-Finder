
function nextPage(total_page){
    var current_page = parseInt($("#current-page").text(),10);
    if (current_page===total_page) return;
    $("#current-page").html(current_page+1);
    $($(".request-group")[current_page-1]).hide();
    $($(".request-group")[current_page]).show();
    window.scrollTo(0,460);
}

function prevPage(){
    var current_page = parseInt($("#current-page").text(),10);
    if (current_page===1) return;
    $("#current-page").html(current_page-1);
    $($(".request-group")[current_page-1]).hide();
    $($(".request-group")[current_page-2]).show();
    window.scrollTo(0,460);
}