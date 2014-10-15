$(document).ready(function() {
    var b = new Barcode39;
    b.toBarcode();
    console.log("Let's start :" + new Date().getMilliseconds());

    $("#nav-user").click(function() {
    	getNavPage("subpages/user.html");
    });
    $("#nav-fabric").click(function() {
    	getNavPage("subpages/fabric.html");
    });
    
    var data  = [{"id":0,"password":"jaso","name":"jaso"},{"id":0,"password":"jaso","name":"jaso"},{"id":0,"password":"jaso","name":"jaso"},{"id":0,"password":"jaso","name":"jaso"},{"id":0,"password":"jaso","name":"jaso"},{"id":0,"password":"jaso","name":"jaso"}];
    $("#test_json_2_table").html(ConvertJsonToTable(data,"table_id2","display","Download"));
    $('#table_id2').DataTable();
    
    
    $("#table_idd tbody tr").click(function(){
    	var nTds = $("td", this);
		alert($(nTds[1]).text());
    	})

});


$(document).ready( function () {
//    $('#table_id').DataTable();
} );

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