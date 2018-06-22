var data = {
               "provinces" : [
               	{
               	 "code" : "1" ,
               	 "name" : "Tehran",
               	 "branches" : [
               		{"code":"1","name":"branch1"},
               		{"code":"2","name":"branch2"},
               		{"code":"3","name":"branch3"},
               		{"code":"4","name":"branch4"}
               	 ]
               	},
               	{
               	 "code" : "4" ,
               	 "name" : "Khouzestan",
               	 "branches" : [
               		{"code":"1","name":"branch1"},
               		{"code":"2","name":"branch2"},
               		{"code":"3","name":"branch3"},
               		{"code":"4","name":"branch4"}
               	 ]
               	},
               	{
               	 "code" : "3" ,
               	 "name" : "Mazandaran",
               	 "branches" : [
               		{"code":"1","name":"branch1"},
               		{"code":"2","name":"branch2"},
               		{"code":"3","name":"branch3"},
               		{"code":"4","name":"branch4"}
               	 ]
               	}
               ]
               };

$(document).ready(function(){

    for (i=0;i<data["provinces"].length;i++){
        $("select#provinceCode").append( $("<option />").val(data["provinces"][i]["code"]).text(data["provinces"][i]["name"]) );
    }


    $("#provinceCode").change(function() {
        makeBranchOptions();
    });

     makeBranchOptions();
     $("#clear").click(function() {
                $("#id").val(0);
                $("#firstName").val("");
                $("#lastName").val("");
                $("#fatherName").val("");
                $("#candidateCode").val("");
                $("#birthdate").val("");
                $("#createdDate").val("");
                $("#personalCode").val("");
                $("#birthCity").val("");
                $("#employDate").val("");
                $("#provinceCode").val("");
                $("#branchCode").val("");
                $("#officialPosition").val("");
                $("#educationDegree").val("");
                $("#educationField").val("");
                $("#educationUni").val("");
                $("#internalHistory").val("");
                $("#externalHistory").val("");
                $("#externalHistory").val("");
                $("#isActive").val("true");


         });

    $('[id^="removeBtn"]').click(function() {
        removeId = $(this).val();
        $.ajax({
            type : "GET",
            url : "/candidateRemove",
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
            url : "/candidateEdit",
            data : "editId=" + editId ,
            error: function(){
                alert('error');
            },
            success : function(data) {
                makeBranchOptions();
                $("#id").val(data.id);
                $("#firstName").val(data.firstName);
                $("#lastName").val(data.lastName);
                $("#fatherName").val(data.fatherName);
                $("#candidateCode").val(data.candidateCode);
                $("#birthdate").val(data.birthdate);
                $("#createdDate").val(data.createdDate);
                $("#personalCode").val(data.personalCode);
                $("#birthCity").val(data.birthCity);
                $("#employDate").val(data.employDate);
                $("#provinceCode").val(data.provinceCode);
                $("#branchCode").val(data.branchCode);
                $("#officialPosition").val(data.officialPosition);
                $("#educationDegree").val(data.educationDegree);
                $("#educationField").val(data.educationField);
                $("#educationUni").val(data.educationUni);
                $("#internalHistory").val(data.internalHistory);
                $("#externalHistory").val(data.externalHistory);
                $("#externalHistory").val(data.externalHistory);
                $("#election").val(data.election);
                $("#isActive").val(data.isActive);

            }
        });
        return false;
    });
 });

 function makeBranchOptions(){
        for (i=0;i<data["provinces"].length;i++){
                if(data["provinces"][i]["code"]==$("#provinceCode").val()){
                    for (j=0;j<data["provinces"][i]["branches"].length;j++){
                        $("select#branchCode").append( $("<option />").val(data["provinces"][i]["branches"][j]["code"]).text(data["provinces"][i]["branches"][j]["name"]) );
                    }
                }
            }

 }