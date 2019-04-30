var categorys;
$(function(){
    if($("#level1")) {
        // 加载所有分类
        loadAllCategorys();
        // 选择一级分类事件
        $("#level1").change(selectFirstLevel);
        // 选择二级分类事件
        $("#level2").change(selectSecondLevel);
    }
    if($("#detail")) {
        showImages();
    }
});

function loadAllCategorys() {
    $.post(
        "/category/all",
        function(data){
            categorys = data;
            for(var i=0;i<categorys.length;i++) {
                var category = categorys[i];
                // 初始化一级分类
                if(!category.parentId) {
                    $("#level1").append("<option value='" + category.id + "'>" + category.name + "</option>");
                }
            }
        }
    );
}

function selectFirstLevel() {
    var id = $(this).val();
    $("#level2").html('<option value="">二级分类</option>');
    $("#level3").html('<option value="">三级分类</option>');
    for(var i=0;i<categorys.length;i++) {
        var category = categorys[i];
        // 初始化二级分类
        if(category.parentId == id) {
            $("#level2").append("<option value='" + category.id + "'>" + category.name + "</option>");
        }
    }
}

function selectSecondLevel() {
    var id = $(this).val();
    $("#level3").html('<option value="">三级分类</option>');
    for(var i=0;i<categorys.length;i++) {
        var category = categorys[i];
        // 初始化三级分类
        if(category.parentId == id) {
            $("#level3").append("<option value='" + category.id + "'>" + category.name + "</option>");
        }
    }
}

function showImages() {
    var path = $("#path").val();
    if(path) {
        var names = path.split(",");
        for(var i=0;i<names.length;i++) {
            $("#detail").append("<p><img src='/item/img?name="+names[i]+"'></p>");
        }
    }
}