<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>



    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Twitter</title>

</head>
<script>
    $(document).ready(function () {
        setInterval(show,10000);
//        $("button").click(show);


        function show() {
            var latestId;
            latestId = $("#row").children("div").first().attr("id");
            var keyword = $("#keywords").val();
            $.getJSON("/update/"+keyword+"/"+latestId,function (data,status) {
                for(var i =data.length-1; i >=0 ; i--){

                    var newDiv = document.createElement("div");
                    newDiv.setAttribute("id",data[i].id);
                    newDiv.setAttribute("style","display:none");
                    var key = $("#keywords").val();
                    var word = data[i].text.match(new RegExp(keyword,"i"));

                    data[i].text = data[i].text.replace(new RegExp(keyword,"i"),"<span style=\"color: red; font-weight: bold\">"+word+"</span>");
                    $("#row").prepend(newDiv);
                }
                for(var i =data.length-1; i >=0 ; i--){
                    $("div#row div#"+data[i].id).html("<div class=\"\" style=\"background-color: lemonchiffon;border-style:solid;border-width: 2px;border-bottom-width: 1px;border-top-width: 1px\" >\n" +
                            "                        <div class=\"col-md-2\" style=\"margin-top: 5px ;margin-right: 0px;margin-bottom: 0px;margin-left: 0px\" >\n" +
                            "                            <img src=\""+data[i].profileImageUrl+"\" class=\"img-circle\">\n" +
                            "                        </div>\n" +
                            "                        <div  class=\"col-md-10\" style=\"float: left;margin: 0px \">\n" +
                            "                            <h6>"+data[i].userName+"(@"+data[i].screenName+")</h6>\n" +
                            "                            <p><small>"+data[i].createdAt+"</small>-<small>"+data[i].source+"</small></p>\n" +
                            "                            <p></p>\n" +
                            "                        </div>\n" +
                            "                        <div>\n" +
                            "                            <p>"+data[i].text+"</p>\n" +
                            "                        </div>\n" +
                            "\n" +
                            "                    </div>");
                    $("div#row div#"+data[i].id).delay((data.length-1-i)*1000).slideDown();
                }
            });

        }

    });
    function sub() {
        $.post("/changeKeywords",$("#search").serialize(),function (data) {
            $("#threadId").attr("value",data);
        });
        return false;
    }
</script>
<body >
<div class="panel panel-primary">
    <div class="panel-heading">
        <h1 class="panel-title text-center">Twitter采集展示</h1>
    </div>
    <div class="panel-body">

        <div class="panel panel-info col-md-4">
            <div class="panel-heading">
                <form role="form" class="form-inline" id="search" action="/changeKeywords" method="post" onsubmit="return sub()" >
                    <input id="keywords" class="form-control " style="width: 320px" name="keywords" value="China">
                    <input id="threadId" name="threadId" type="hidden" value="${threadId!'0'}">
                    <button class="btn btn-default" style="float: right" type="submit" >检索</button>
                </form>

            </div>
            <div class="panel-body">
                <div class="row" id="row">

                    <#list tweets as tweet>


                        <div id="${tweet.id}"  style="background-color: lemonchiffon;border-style:solid;border-width: 2px;border-bottom-width: 1px;border-top-width: 1px" >

                            <div class="col-md-2" style="margin-top: 5px ;margin-right: 0px;margin-bottom: 0px;margin-left: 0px" >
                                <img src="${tweet.profileImageUrl}" class="img-circle">
                            </div>
                            <div  class="col-md-10" style="float: left;margin: 0px ">
                                <h6>${tweet.userName}(@${tweet.screenName})</h6>
                                <p><small>${tweet.createdAt}</small>-<small>${tweet.source}</small></p>
                                <p></p>
                            </div>
                            <div>
                                <p>${tweet.text}</p>
                            </div>

                        </div>

                    </#list>
                </div>
            </div>
        </div>
        <div>
            <#include "index1.ftl"/>
        </div>
        <div>
            <#include "index2.ftl"/>
        </div>
    </div>
</div>

</body>
</html>