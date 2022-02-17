<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--引入wangEditor-->
    <script src="/resources/wangEditor.min.js"></script>
</head>
<body>
    <div id="divEditor" style="width: 800px; height: 600px"></div>
    <script>
        var E = window.wangEditor;
        var editor = new E("#divEditor");//完成富文本编辑器初始化
        editor.create();
    </script>
</body>
</html>