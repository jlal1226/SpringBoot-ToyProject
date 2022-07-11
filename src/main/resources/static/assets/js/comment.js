function inputData() {
    var data=$("#commentForm").serialize();
    $.ajax({
        url: "/comment/write",
        data: commentRequestDto,
        type:"POST",
        cache:false
    }).done(function (fragment) {
        $("#list").replaceWith(fragment);
    });
}