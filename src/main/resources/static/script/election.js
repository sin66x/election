$(document).ready(function(){
     makeStartDate();
     makeEndDate();
     $("#clear").click(function() {
                $("#name").val("");
                $("#maxSelection").val(1);
                $("#category").val(0);
                $("#id").val(0);
         });

    $(".input_date").change(function() {
        makeStartDate();
        makeEndDate();
    });
    $('[id^="removeBtn"]').click(function() {
        removeId = $(this).val();
        $.ajax({
            type : "GET",
            url : "/electionRemove",
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
            url : "/electionEdit",
            data : "editId=" + editId ,
            error: function(){
                alert('error');
            },
            success : function(data) {
                $("#name").val(data.name);
                $("#category").val(data.category);
                $("#id").val(data.id);
                $("#maxSelection").val(data.maxSelection);

                $("#startYear").val(data.startDate.substring(0,4));
                $("#startMonth").val(data.startDate.substring(5,7));
                $("#startDay").val(data.startDate.substring(8,10));

                $("#endYear").val(data.endDate.substring(0,4));
                $("#endMonth").val(data.endDate.substring(5,7));
                $("#endDay").val(data.endDate.substring(8,10));
                makeStartDate();
                makeEndDate();
            }
        });
        return false;
    });
 });

 function makeStartDate(){
    $("#startDate").val($("#startYear").val()+"/"+$("#startMonth").val()+"/"+$("#startDay").val());
 }

  function makeEndDate(){
     $("#endDate").val($("#endYear").val()+"/"+$("#endMonth").val()+"/"+$("#endDay").val());
  }
