$(document).ready(function() {

    console.log("Let's start :" + new Date().getMilliseconds());

    $(".nav_item").click(function(){navAddUser()});
    //$(".nav_item").mouseover(function(){navMouseover(this)});
});


function navAddUser() {
         $.ajax({
         type:"get",
         url:"subpages/addUser.html",
         cache:false,  
         success:function(data){
         $("#main_panel").html(data);
         } ,
         error:function(){$("#main_panel").html("error");}
         });
}

function navMouseover(obj) {
         $(obj).css({"background-color":"yellow","font-family":"blod"});
}

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
