$(document).ready(function(){
    $(".addCandidate").click(function() {
        if ($(this).html()=='+'){
            if (isBelowMax())
                $(this).html('-');
            else
                alert('Enough');
        }
        else
            $(this).html('+');
    });
    $("#vote").click(function(){
        var votes = concatVotes();
        var electionId = $("#electionId").val();
        $.ajax({
                url : "/voteSubmit",
                type: "GET",
                data: "votes="+votes+"&"+"electionId="+electionId,
                error: function(){
                    alert('error');
                },
                success : function() {
                    alert('Done');
                    window.location.href="../";
                }
            });
            return false;
    });
});

function concatVotes(){
    var votes="";
    $('.addCandidate').each(function(i, obj) {
           if(obj.innerHTML=='-'){
            votes+=$(this).val()+"-";
            }
        });
    return votes;
}

function isBelowMax(){
    var cnt = 0;
    $('.addCandidate').each(function(i, obj) {
       if(obj.innerHTML=='-'){
        cnt++;
        }
    });
    if (cnt>=$("#maxSelection").val())
        return false;
    else
        return true;
}