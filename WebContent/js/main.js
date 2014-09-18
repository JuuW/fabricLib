$(document).ready(function() {

    console.log("Let's start :" + new Date().getMilliseconds());

    $("#nav-user").click(function() {
    	getNavPage("subpages/user.html");
    });
    $("#nav-fabric").click(function() {
    	getNavPage("subpages/fabric.html");
    });
});

function getNavPage(pageUrl) {
	$("#upper").html("");
	$("#middle").html("");
	$.ajax({
		type : "get",
		url : pageUrl,
		cache : false,
		success : function(data) {
			$("#upper").html(data);
		},
		error : function() {
			$("#upper").html("error");
		}
	});
}