$(function () {
    $.ajax({
        url:"/platformlist",
        type:"GET",
        dataType:"JSON",
        success:function (results) {
            for (var result of results){
                $("#platform_select").append("<option value='"+result.platform+"'>"+result.platform+"</option>")
            }
        }
    });

    $("#platform_select").change(function () {
        $.ajax({
            url:"/articlelist",
            type:"GET",
            dataType:"JSON",
            data:{"platform": $("#platform_select").val()},
            success:function (results) {
                for (var result of results){
                    $("#article_select").append("<option value='"+result.id+"'>"+result.name+"</option>")
                }
            }
        });
    });
});




$("#platform_input").click(function () {
    console.log("aaaaa");
});
