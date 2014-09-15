$(document).ready(function() {

    console.log("Let's start :" + new Date().getMilliseconds());

    search($("#submit"));
});

function search(btn) {
    btn.click(function() {
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
    });
}
