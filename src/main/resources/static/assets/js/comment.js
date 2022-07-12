function inputData() {
    var data = $("#commentForm").serialize();
/*    $.ajax({
        url: "/comment/write"
        , data: data
        , type: "POST"
        , error : function(error) {
            alert("Error!");
        }
        , success : function(data) {
            alert("success!");
        }
        , complete : function() {
            alert("complete!");
        }).done(function (fragment) {
                 alert(fragment);
                 $('#list').replaceWith(fragment);
        });*/

    $.ajax({
        url: "/comment/write"
        , data: data
        , type: "POST"
    }).done(function (fragment) {
        alert(fragment);
        $('#list').replaceWith(fragment);
    });

}