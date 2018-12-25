<#include "/default/utils/ui.ftl"/>

<@layout "space">
    <div class="col-xs-12 col-md-8">
        <#--<textarea id="text1" style="width:100%; height:200px;"></textarea>-->
            <div>
                <input type="text" class="form-control" name="title" maxlength="128" id="title" placeholder="请输入标题">
            </div>
            <br>
        <div id="editor" style="background-color: white">
            <p></p>
        </div>
            <div class="col-xs-12 col-md-12">
                <div class="form-group">
                    <div class="text-center">
                        <button id="btn1" type="submit" class="btn btn-primary" style="padding-left: 30px; padding-right: 30px;">提交</button>
                    </div>
                </div>
            </div>

        <#--<button id="btn2">获取text</button>-->
        <#--<#include "/default/channel/editor/ueditor.ftl"/>-->
    </div>
<div class="col-xs-12 col-md-4">
    <div class="panel panel-default corner-radius help-box">
        <div class="panel-heading text-center">
            <h3 class="panel-title">标签(用逗号或空格分隔)</h3>
        </div>
        <div class="panel-body">
            <input type="hidden" name="tags" id="fieldTags" placeholder="添加相关标签，用逗号或空格分隔 (最多4个)">
            <ul id="tags"></ul>
        </div>
    </div>
</div>
    <script type="text/javascript" src="/dist/js/wangEditor.min.js"></script>
    <#--<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>-->
    <script src="../../../../../dist/js/jquery.form.min.js"></script>
    <script type="text/javascript">
        var E = window.wangEditor
        var editor = new E('#editor')
        editor.customConfig.uploadImgShowBase64 = true
        // 或者 var editor = new E( document.getElementById('editor') )
        // editor.customConfig.uploadImgServer = '/1/upload'
        // editor.customConfig.uploadFileName = 'myFileName';
        // editor.customConfig.uploadImgHooks = {
        //     customInsert: function (insertImg, result, editor) {
        //         console.log(result)
        //         var url =JSON.parse(result);
        //         insertImg(url.url);
        //     }
        // }
        //var title = $('#title').val();

        // editor.customConfig.onchange = function (html) {
        //     // 监控变化，同步更新到 textarea
        //     $text1.val(html)
        // }
        editor.create()
        // $text1.val(editor.txt.html())
        document.getElementById('btn1').addEventListener('click', function () {
            // 读取 html
            var content = editor.txt.html();
            $.ajax({
                type:"POST",
                url:"http://localhost:8088/1/test",
                dataType:"json",
                data: {"title":$('#title').val(),"content":content},
                success:function(data){
                if(data.success){
                    alert(data.msg);
                }else{
                    alert( data.msg);
                }
            },
            error:function(jqXHR){
                alert("发生错误："+ jqXHR.status);
            }
            });
        }, false)

        // document.getElementById('btn2').addEventListener('click', function () {
        //     // 读取 text
        //     alert(editor.txt.text())
        // }, false)
    </script>
</@layout>