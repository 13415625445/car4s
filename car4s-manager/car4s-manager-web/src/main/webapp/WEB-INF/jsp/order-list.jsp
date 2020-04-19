<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="预约信息列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/order/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:200">预约ID</th>
        <th data-options="field:'personName',width:200">预约人</th>
        <th data-options="field:'phone',width:200">预约人手机号</th>
        <th data-options="field:'title',width:500">预约车辆</th>
    </tr>
    </thead>
</table>
<script>

    function getSelectionsIds(){
        var itemList = $("#itemList");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        console.log(sels);
        for(var i in sels){
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

    var toolbar = [
  {
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
            var ids = getSelectionsIds();
            if(ids.length == 0){
                $.messager.alert('提示','未选中信息!');
                return ;
            }
            $.messager.confirm('确认','确定删除ID为 '+ids+' 的预约信息吗？',function(r){
                if (r){
                    var params = {"ids":ids};
                    $.post("/order/delete",params, function(data){
                        if(data.status == 200){
                            $.messager.alert('提示','删除预约信息!',undefined,function(){
                                $("#itemList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }];
</script>