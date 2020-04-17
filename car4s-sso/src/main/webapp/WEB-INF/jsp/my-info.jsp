<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
    <title>个人信息</title>
    <link type="text/css" rel="stylesheet" href="/css/regist.personal.css"/>
    <link type="text/css" rel="stylesheet" href="/css/passport.base.css"/>
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
<div  id="logo">
    <div>
        <a href="http://localhost:8082">
            <img src="/images/logo.jpg" alt="少强4S店" width="170" height="150"/>
        </a>
    </div>
</div>

<div class="w" id="regist">
    <div class="mt">
        <ul class="tab">
            <li class="curr">个人信息</li>
        </ul>
        <div class="extra">
        </div>
    </div>
    <div class="mc">
        <form id="personRegForm" method="post" onsubmit="return false;">
            <div>
                <input name="id" value="${user.id}" style="visibility:hidden"/>
            </div>
            <div class="form" onselectstart="return false;">
                <div class="item" id="select-regName">
                    <span class="label"><b class="ftx04">*</b>用户名：</span>
                    <div class="fl item-ifo">
                        <div class="o-intelligent-regName">
                            <input type="text" id="regName" value="${user.username}" disabled="disabled" name="username" class="text" tabindex="1" autoComplete="off"
                                   onpaste="return false;"
                                   value=""
                                   onfocus="if(this.value=='') this.value='';this.style.color='#333'"
                                   onblur="if(this.value=='') {this.value='';this.style.color='#999999'}"/>
                            <i class="i-name"></i>
                            <ul id="intelligent-regName" class="hide"></ul>
                            <label id="regName_succeed" class="blank"></label>
                            <label id="regName_error" class="hide"></label>
                        </div>
                    </div>
                </div>
                <div id="o-password">
                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>密码：</span>
                        <style>
                            .input-disabled{
                                display: none;
                            }
                        </style>
                        <div class="fl item-ifo">
                            <input id="pwd" type="password" name="password" class="text password-input input-disabled" tabindex="2"
                                   onpaste="return  false" autocomplete="off"/>
                            <i class="i-pass"></i>
                            <label id="pwd_succeed" class="blank"></label>
                            <label id="pwd_error"></label>
                        </div>
                    </div>

                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>确认密码：</span>

                        <div class="fl item-ifo">
                            <input type="password"  id="pwdRepeat" name="pwdRepeat" class="text password-input input-disabled" tabindex="2"
                                   onpaste="return  false" autocomplete="off"/>
                            <i class="i-pass"></i>
                            <label id="pwdRepeat_succeed" class="blank"></label>
                            <label id="pwdRepeat_error"></label>
                        </div>
                    </div>
                    <div class="item" id="dfavoriteModel">
                        <span class="label"><b class="ftx04">*</b>喜好：</span>
                        <div class="fl item-ifo">
                            <select id="favorite_model" disabled="disabled" class="easyui-combobox" name="favoriteModel"   style="height:40px;width:270px;">
                                <option value="两厢" class="foption" foption="${user.favoriteModel}">两厢</option>
                                <option value="三厢"  class="foption" foption="${user.favoriteModel}"> 三厢</option>
                                <option value="SUV"  class="foption" foption="${user.favoriteModel}">SUV</option>
                                <option value="新能源" class="foption" foption="${user.favoriteModel}"> 新能源</option>

                            </select>
                            <script>
                                $(".foption").each(function(){

                                    text = $(this).attr("foption")

                                    if(text==$(this).text())
                                    {
                                        $(this).attr("selected","selected");
                                    }

                                })
                            </script>
                        </div>
                    </div>
                    <div class="item" id="dphone">
                        <span class="label"><b class="ftx04">*</b>手机号码：</span>

                        <div class="fl item-ifo">
                            <input type="text" disabled="disabled" id="phone" maxlength="11" name="phone" value="${user.phone}"
                                   class="text" tabindex="4"
                                   autocomplete="off" /> <i class="i-phone"></i> <label
                                id="phone_succeed" class="blank"></label> <label
                                id="phone_error"></label>
                        </div>
                    </div>

                    <div class="item" id="demail">
                        <span class="label"><b class="ftx04">*</b>邮箱：</span>

                        <div class="fl item-ifo">
                            <input type="text" disabled="disabled" id="email" name="email" value="${user.email}"
                                   class="text"
                                   autocomplete="off" /><label
                                id="email_succeed" class="blank"></label> <label
                                id="email_error"></label>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <span class="label">&nbsp;</span>
                    <input type="button" class="btn" style="background-color:red;" id="registsubmit" value="修改" tabindex="8"
                           clstag="regist|keycount|personalreg|07"/>
                </div>

                <script>
                    var v_btn_re_in = 0
                    $("#registsubmit").click(function(){

                    if(v_btn_re_in==0)
                    {
                        $(this).attr("value","保存")
                        v_btn_re_in = 1
                        $(".password-input").removeClass("input-disabled")
                        $("#email").removeAttr("disabled");
                        $("#phone").removeAttr("disabled");
                        $("#regName").removeAttr("disabled");
                        $("#favorite_model").removeAttr("disabled");
                        $("#email").removeAttr("value");
                        $("#phone").removeAttr("value");
                        $("#regName").removeAttr("value");
                        $("#pwd").removeAttr("value");
                    }
                    else
                    {
                        //密码检查
                        if ($("#pwd").val() != $("#pwdRepeat").val()) {
                            alert("确认密码和密码不一致，请重新输入！");
                            $("#pwdRepeat").select();
                            $("#pwdRepeat").focus();
                            return false;
                        }
                        console.log($("#personRegForm").serialize());
                        $.post("http://localhost:8083/user/update",$("#personRegForm").serialize(), function(data){
                            console.log(data.status);
                            if(data.status == 200){
                                alert('用户修改成功！需要重新登录');
                                return location.href = "http://localhost:8083/page/login";
                            } else {
                                alert("修改失败！");
                            }
                        });

                        $(".password-input").addClass("input-disabled")
                        $("#email").attr("disabled","disabled");
                        $("#phone").attr("disabled","disabled");
                        $("#regName").attr("disabled","disabled");
                        $("#favorite_model").attr("disabled","disabled");

                        $(this).attr("value","修改")
                        v_btn_re_in = 0;
                    }
                    })
                </script>
            </div>
        </form>
    </div>
</div>
</body>
</html>
