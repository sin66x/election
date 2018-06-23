$(document).ready(function(){
    $("#clear").click(function() {
                    $("#name").val("");
                    $("#parent").val(0);
                    $("#id").val(0);

    });
    $('[id^="editBtn"]').click(function() {
            editId = $(this).val();
            $.ajax({
                type : "GET",
                url : "/categoryEdit",
                data : "editId=" + editId ,
                error: function(){
                						alert('error');
                },
                success : function(data) {
                    $("#name").val(data.name);
                    $("#parent").val(data.parent);
                    $("#id").val(data.id);
                }
            });
            return false;
        });
        $('[id^="removeBtn"]').click(function() {
                    removeId = $(this).val();
                    $.ajax({
                        type : "GET",
                        url : "/categoryRemove",
                        data : "removeId=" + removeId ,
                        error: function(){
                        		alert('error');
                        },
                        success : function(data) {
                                alert('Done');
                                location.reload();
                        }
                    });
                    return false;
                });
});