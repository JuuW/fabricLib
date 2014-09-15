$(document).ready(function() {

    console.log("Let's start :" + new Date().getMilliseconds());

    search($("#submit"));
});

function search(btn) {
    btn.click(function() {
//         $.post("action/Search", $("#form1").serialize(),
//         function(data) {
//         $("#result").html(data);
//         }, "xml");

//         $.post("action/Search", {
//         username : $("#username").val(),
//         password : $("#password").val(),
//         lines : $("#lines").val()
//         },
//        
//         function(data) {
//        
//         alert("Data Loaded: " + data);
//         $("#result").html(data);
//        
//         });


        // var formParam = $("#form1").serialize();//序列化表格内容为字符串
         $.ajax({
         type:"post",
         url:"action/Search",
         cache:false,  
         data:{
             username : $("#username").val(),
             password : $("#password").val(),
             lines : $("#lines").val()
             },
         // cache:false,
         // dataType:'json',
         success:function(data){
         alert("Data Loaded: " + data);
         $("#result").html(data);
         } ,
         error:function(){$("#result").html("error");}
         });

//        var jqxhr = $.post("action/Search", $("#form1").serialize())
//            .done(function(data) {
//                $("#result").html(data);
//            })
//            .fail(function() {
//                alert("error");
//            })
//            .always(function() {
//                alert("finished");
//            });
//        jqxhr.always(function() {
//            alert("second finished");
//        });
    //	$.post("action/Search", )
//    	var jqxhr = $.post( "action/Search",$("#form1").serialize(), function() {
//    		
//    		  alert( "success" );
//    		})
//    		  .done(function() {
//    		    alert( "second success" );
//    		  })
//    		  .fail(function() {
//    		    alert( "error" );
//    		  })
//    		  .always(function() {
//    		    alert( "finished" );
//    		});
//    		 
//    		// Perform other work here ...
//    		 
//    		// Set another completion function for the request above
//    		jqxhr.always(function() {
//    		  alert( "second finished" );
//    		});




    });
}
