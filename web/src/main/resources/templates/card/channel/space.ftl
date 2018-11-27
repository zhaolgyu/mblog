<#include "/default/utils/ui.ftl"/>

<@layout "space">
    <div>
        <textarea id="text1" style="width:100%; height:200px;"></textarea>
        <div id="editor">
            <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
        </div>
        <button id="btn1">获取html</button>
        <button id="btn2">获取text</button>
        <#include "/default/channel/editor/ueditor.ftl"/>
    </div>
    <script type="text/javascript" src="E:\Pro\mblog\web\src\main\resources\static\dist\js\wangEditor.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        var E = window.wangEditor
        var editor = new E('#editor')
        // 或者 var editor = new E( document.getElementById('editor') )
        var $text1 = $('#text1')
        editor.customConfig.onchange = function (html) {
            // 监控变化，同步更新到 textarea
            $text1.val(html)
        }
        editor.create()
        $text1.val(editor.txt.html())
        document.getElementById('btn1').addEventListener('click', function () {
            // 读取 html
            alert(editor.txt.html())
        }, false)

        document.getElementById('btn2').addEventListener('click', function () {
            // 读取 text
            alert(editor.txt.text())
        }, false)
    </script>
</@layout>