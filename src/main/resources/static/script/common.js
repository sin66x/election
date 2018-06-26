$(document).ready(function(){

    if (window.location.href.indexOf("election") >= 0)
    {
        $("#admin_header_election").toggleClass("active");
        $("#admin_header_category").toggleClass("");
        $("#admin_header_home").toggleClass("");
        $("#admin_header_user").toggleClass("");
    }
    else if (window.location.href.indexOf("category") >= 0)
    {
        $("#admin_header_election").toggleClass("");
        $("#admin_header_category").toggleClass("active");
        $("#admin_header_home").toggleClass("");
        $("#admin_header_user").toggleClass("");
    }
    else if (window.location.href.indexOf("candidate") >= 0)
    {
        $("#admin_header_election").toggleClass("active");
        $("#admin_header_category").toggleClass("");
        $("#admin_header_home").toggleClass("");
        $("#admin_header_user").toggleClass("");
    }
    else if (window.location.href.indexOf("user") >= 0)
    {
        $("#admin_header_election").toggleClass("");
        $("#admin_header_category").toggleClass("");
        $("#admin_header_home").toggleClass("");
        $("#admin_header_user").toggleClass("active");
    }
    else {
        $("#admin_header_election").toggleClass("");
        $("#admin_header_category").toggleClass("");
        $("#admin_header_home").toggleClass("active");
        $("#admin_header_user").toggleClass("");
    }
});

function filterTable(endingColumns) {
  var input, filter, table, tr, td, i;
  var shouldShow =[];
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  for (i = 1; i < tr.length; i++) {
  	for(j=0;j<tr[i].getElementsByTagName("td").length-endingColumns;j++){
    	cellValue = tr[i].getElementsByTagName("td")[j].innerHTML.toUpperCase();
        if (cellValue.indexOf(filter)>-1)
        {
        	shouldShow.push(i);
            j=tr[i].getElementsByTagName("td").length;
        }
    }
  }

  for (i = 1; i < tr.length; i++) {
  	tr[i].style.display = "none";
  }

  for (i = 0; i < shouldShow.length; i++) {
  	tr[shouldShow[i]].style.display = "";
  }
}