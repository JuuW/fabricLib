$(document).ready(function() {

    console.log("Let's start :" + new Date().getMilliseconds());

    $("#nav-user").click(function() {
        navUser()
    });
    $("#nav-fabric").click(function() {
        navFabric()
    });
    //$(".nav_item").mouseover(function(){navMouseover(this)});
});


function navUser() {
    $.ajax({
        type: "get",
        url: "subpages/user.html",
        cache: false,
        success: function(data) {
            $("#upper").html(data);
        },
        error: function() {
            $("#upper").html("error");
        }
    });
}

function navFabric() {
    $.ajax({
        type: "get",
        url: "subpages/fabric.html",
        cache: false,
        success: function(data) {
            $("#upper").html(data);
        },
        error: function() {
            $("#upper").html("error");
        }
    });
}


function navMouseover(obj) {}
