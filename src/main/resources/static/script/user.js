$(document).ready(function(){
    $("#clear").click(function() {
                    $("#username").val("");
                    $("#password").val("");
                    $("#id").val(0);
                    $("#isActive").val("true");

    });

    $('[id^="removeBtn"]').click(function() {
                removeId = $(this).val();
                $.ajax({
                    type : "GET",
                    url : "/userRemove",
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
    $('[id^="editBtn"]').click(function() {
                editId = $(this).val();
                $.ajax({
                    type : "GET",
                    url : "/userEdit",
                    data : "editId=" + editId ,
                    error: function(){
                            alert('error');
                    },
                    success : function(data) {
                        $("#id").val(data.id);
                        $("#username").val(data.username);
                        $("#role").val(data.role);
                        $("#isActive").val(data.isActive);
                    }
                });
                return false;
            });
});