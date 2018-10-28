$(function () {
    $.ajax({
        url: "/platformlist",
        type: "GET",
        dataType: "JSON",
        success: function (results) {
            for (var result of results) {
                $("#platform_select").append("<option value='" + result.platform + "'>" + result.platform + "</option>")
            }
        }
    });

    $("#platform_select").change(function () {
        $.ajax({
            url: "/articlelist",
            type: "GET",
            dataType: "JSON",
            data: {"platform": $("#platform_select").val()},
            success: function (results) {
                for (var result of results) {
                    $("#article_select").append("<option value='" + result.name + "'>" + result.name + "</option>")
                }
            }
        });
    });

    $("#submit").click(function () {
        console.log($("#file").prop("files"));
        var platform_select = $("#platform_select").val();
        var platform_input = $("#platform_input").val();
        var article_select = $("#article_select").val();
        var article_input = $("#article_input").val();

        var platform;
        var article;

        if (platform_select == null || platform_select == "") {
            if (platform_input.trim() == null || platform_input.trim() == "") {
                return;
            } else {
                platform = platform_input.trim();
            }
        } else {
            if (platform_input.trim() == null || platform_input.trim() == "") {
                platform = platform_select;
            } else {
                return;
            }
        }
        if (article_select == null || article_select == "") {
            if (article_input.trim() == null || article_input.trim() == "") {
                return;
            } else {
                article = article_input.trim();
            }
        } else {
            if (article_input.trim() == null || article_input.trim() == "") {
                article = article_select;
            } else {
                return;
            }
        }

        // var form = "<form enctype=\"multipart/form-data\" action=\"/uploadfile\" method=\"post\">" +
        //     "<input type='text' value='" + platform + "' name='platform' />" +
        //     "<input type='text' value='" + article + "' name='name' />" +
        //     "<input type='file' value='" + $("#file").prop("files")[0] + "' name='file'/>" +
        //     "</form>";
        var form = new FormData();
        form.append("file",$("#file").prop("files")[0]);
        form.append("platform",platform);
        form.append("name",article);
        $.ajax({
            url: "/uploadfile",
            type: "POST",
            dataType: "text",
            data: form,
            contentType:false,
            processData:false,
            success: function (results) {
                $("body").append("<a>文件路径为：http://fdfs.zmuchi.cn/"+results+"</a><br>");
            }
        })
        // $(form).appendTo($("body")).submit().remove();
    })

});

