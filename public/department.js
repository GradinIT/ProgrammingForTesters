$(document).ready(function () {
    var urlParams = new URLSearchParams(window.location.search);
    var departmentId = urlParams.get('departmentId');
    var employeeId = urlParams.get('employeeId');
    console.log(departmentId)
    getData(departmentId,employeeId)
});

function getData(departmentId,employeeId) {
    $.ajax({
        url: "http://localhost:8082/department/"+departmentId
    }).then(function (data, status, jqxhr) {
        document.getElementById("id").value = data.departmentId;
        document.getElementById("name").value = data.departmentName;
        document.getElementById("employee").getAttributeNode("href").value= "http://localhost:8082/employee.html?employeeId="+employeeId;
        document.getElementById("employee").textContent= "Employee "+employeeId;
        console.log(jqxhr);
    });
}