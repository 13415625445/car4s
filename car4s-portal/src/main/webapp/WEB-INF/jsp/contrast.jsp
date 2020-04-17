<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>车辆对比</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/summernote.min.css">
    <link rel="stylesheet" href="/css/nav.css">
    <link rel="stylesheet" href="/css/good.css">
    <link rel="stylesheet" href="/css/list.css">
    <link href="/css/taotao.css" rel="stylesheet"/>


</head>

<body>

<!-- 页面内容 -->
<div class="content">
    <div class="container-fluid">

        <div class="row">
            <div class="car-list col-md-2">
                <table class="table table-hover" border="1">
                    <c:forEach items="${list}" var="item" varStatus="status">
                    <tr class="item-show">
                        <td style="visibility: hidden">${item.id}</td>
                        <td>${item.title}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="car-image col-md-offset-1 col-md-3" id="car-image-0">
                <div class="container-fluid">
                    <div class="row">
                        <h3 id="title1"></h3>
                    </div>
                    <div class="row">
                        <img id="image" src="" alt="" style="width: 600px;height: 300px;">
                    </div>

                    <div class="row">
                        <table class="table" id="table1">

                        </table>
                    </div>

                </div>
            </div>

            <div class="car-image col-md-offset-2 col-md-3" id="car-image-1">
                <div class="container-fluid">
                    <div class="row">
                        <h3 id="title2"></h3>
                    </div>
                    <div class="row">
                        <img id="image2" src="" alt="" style="width: 600px;height: 300px;">
                    </div>

                    <div class="row">
                        <table id="table2" class="table">
                        </table>
                    </div>

                </div>
        </div>
    </div>
    </div>
</div>
<!-- 页面内容 -->







<!-- js -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function(){

        $(".car-image").slideUp();

        var page = 0;

        $(".car-list table tr td").click(function(){
            text = $(this).text()
            if(page%2==0){
                setDisplay(page,text);
                page = 1;
            }
            else
            {
                setDisplay(page,text);
                page = 0;
            }
        })

        function setDisplay(site,name){
            $("#car-image-"+site).slideDown();
            $("#car-image-"+site+" h3").text(name);

        }

        // $.ajax({
        //     //请求方式
        //     type : "POST",
        //     //请求的媒体类型
        //     dataType: "json",
        //     //请求地址
        //     url : "/contrast.html",
        //     //数据，json字符串
        //     data : JSON.stringify({id:itemId}),
        //     //请求成功
        //     success : function(result) {
        //         $('#title1').text(result['item']['title']);
        //     },
        //     //请求失败，包含具体的错误信息
        //     error : function(e){
        //         console.log(e.status);
        //         console.log(e.responseText);
        //     }
        // });

        function showDive0(element){
            var id = $(element).find('td:eq(0)').text();
            $.ajax({
                //请求方式
                type : "POST",
                //请求的媒体类型
                dataType: "json",
                //请求地址
                url : "/contrast/item/"+id+".html",
                //请求成功
                success : function(result) {
                    console.log(result);
                    $('#title2').text(result['item']['title']);
                    $('#image2').attr('src',result['item']['image']);


                    for(var i=0;i<result['jsonList'].length;i++){
                        var list = result['jsonList'][i];
                        var str = "<tr><th>"+list['group']+"</th></tr>";
                        $('#table2').append(str);
                        for(var j = 0;j<list['params'].length;j++){
                            console.log(list['params'][j]);
                            var list1 = list['params'][j];
                            var str1 = "<tr><td>"+list1['k']+"</td><td>"+list1["v"]+"</td></tr>"
                            $('#table2').append(str1);
                        }
                    }
                },
                //请求失败，包含具体的错误信息
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }

        function showDive1(element){
            var id = $(element).find('td:eq(0)').text();
            $.ajax({
                //请求方式
                type : "POST",
                //请求的媒体类型
                dataType: "json",
                //请求地址
                url : "/contrast/item/"+id+".html",
                //请求成功
                success : function(result) {
                    console.log(result);
                    $('#title1').text(result['item']['title']);
                    $('#image').attr('src',result['item']['image']);


                    for(var i=0;i<result['jsonList'].length;i++){
                        var list = result['jsonList'][i];
                        var str = "<tr><th>"+list['group']+"</th></tr>";
                        $('#table1').append(str);
                        for(var j = 0;j<list['params'].length;j++){
                            console.log(list['params'][j]);
                            var list1 = list['params'][j];
                            var str1 = "<tr><td>"+list1['k']+"</td><td>"+list1["v"]+"</td></tr>"
                            $('#table1').append(str1);
                        }
                    }
                },
                //请求失败，包含具体的错误信息
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }

        $('.item-show').click(function () {
            if(page == 0) {
                showDive0(this);
            } else {
                showDive1(this);
            }
        });

    });
</script>
<!-- js -->
</body>
</html>