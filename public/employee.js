$(document).ready(function () {
    var urlParams = new URLSearchParams(window.location.search);
    var employeeId = urlParams.get('employeeId');
    console.log(employeeId)
    populateDepartmentDropdown();
    populateDropdown();
    getData(employeeId);
});

function getData(employeeId) {
    if (employeeId == null) {
        employeeId = document.getElementById("employeeSelect").value
    }
    $.ajax({
        url: "http://localhost:8082/employee/" + employeeId
    }).then(function (data, status, jqxhr) {
        console.log(document.getElementById("department"))
        console.log(data.departmentId)
        document.getElementById("id").value = data.employeeId;
        document.getElementById("firstname").value = data.firstName;
        document.getElementById("lastname").value = data.lastName;
        document.getElementById("department").value = data.departmentId;
        document.getElementById("salary").value = data.salary;
        document.getElementById("fulltime").value = data.fullTime;
        document.getElementById("employeeSelect").value = employeeId;
        console.log(jqxhr);
    });
}
function populateDepartmentDropdown() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8082/department",
        dataType: "json",
        success: function (responseData) {
            for (var key in responseData) {
                var option = "<option value=" + responseData[key].departmentId + ">" + responseData[key].departmentName + "</option>";
                $(option).appendTo('#department');
            }
        }
    });
}
function populateDropdown() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8082/employee",
        dataType: "json",
        success: function (responseData) {
            for (var key in responseData) {
                var option = "<option value=" + responseData[key].employeeId + ">" + responseData[key].employeeId + "</option>";
                $(option).appendTo('#employeeSelect');
            }
            document.getElementById("employeeSelect").value = 1;
        }
    });
}

function update() {
    var id = document.getElementById("id").value;
    console.log("update " + id)
}

function clearAllFields() {
    console.log("clearAllFields");
    document.getElementById("id").value = "";
    document.getElementById("firstname").value = "";
    document.getElementById("lastname").value = "";
    document.getElementById("department").value = 1;
    document.getElementById("salary").value = "";
    document.getElementById("fulltime").value = "";
}

function create() {
    var employee = createEmployee()
    console.log(employee)
    $.ajax({
        type: "POST",
        url: "http://localhost:8082/employee",
        data: employee,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            location.reload()
        },
        error: function (errMsg) {
            alert(errMsg);
        }
    });
    console.log("create " + id)
}
function createEmployee() {
    return  JSON.stringify({
        "employeeId": parseInt(document.getElementById("id").value),
        "departmentId": parseInt(document.getElementById("department").value),
        "salary":parseFloat(document.getElementById("salary").value) ,
        "fullTime":document.getElementById("fulltime").value ,
        "firstName":document.getElementById("firstname").value ,
        "lastName":document.getElementById("lastname").value ,
    });
}
function deleteEmployee() {
    var employee = createEmployee()
    console.log(employee)
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8082/employee",
        data: employee,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            location.reload()
        },
        error: function (errMsg) {
            alert(errMsg);
        }
    });
    console.log("delete " + id)
}