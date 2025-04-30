/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// ajax call to the server
// Asynchronous JavaScript and XML

function ajaxCall(method, url, data, isAsync, destination) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById(destination).innerHTML =
                    this.responseText;
        }
    };
    xhttp.open(method, url, isAsync);
    xhttp.setRequestHeader('content-type', 'application/x-www-form-urlencoded')
    xhttp.send(data);
}

var urls = getAllUrls();

function DashboardLoader() {
    ajaxCall('GET', urls.dashboard, '', true, 'load');
}

function TaskFormLoader() {
    ajaxCall('GET', urls.taskform, '', true, 'load');
}

function insertTask() {
    var result = validate_empty('NAME', 'NAME') &&
            validate_empty('EMAIL', 'EMAIL');

    if (result) {
        var taskForm = document.getElementById('taskForm');
        var data = getFormData(taskForm);

        var taskEndDate = document.getElementById("taskEndDate").value;
        data.taskEndDate = taskEndDate;

        ajaxCall('POST', urls.insertTasks, data, false, 'load');

        var status = document.getElementById('status').value;
        if (status > 0) {
            alert("Task allocated successfully");
            DashboardLoader();
        } else {
            alert("Error in task allocation, please try later!");
        }
    } else {
        alert('Not OK!');
    }
}

//function insertTask() {
//    // validation
//    var result = validate_empty('taskDescription', 'Task Description') && validate_empty('taskUsername', 'Task Username') && validate_empty('taskEndDate', 'Task End Date');
//
//// ajax call to insertTask with the data
//    if (result) {
//        var taskForm = document.getElementById('taskForm');
//        var data = getFormData(taskForm);
//
//        // ajax call to insertTask with the data
//        // blocking ajax call
//        ajaxCall('POST', urls.insertTasks, data, false, 'load');
//
//        var status = document.getElementById('status').value;
//
//        if (status > 0) {
//            alert("Task allocated successfully");
//            DashboardLoader();
//        } else {
//            alert("Error in task allocation, please try later!");
//        }
//    } else {
//        alert('Not OK!');
//    }
//}

function ViewTasks(params) {
    if (params === 'grid')
    {
        // ajaxCall('GET', urls.viewtasksGrid, '', true, 'load');
        mygrid = new dhtmlXGridObject("load");
        mygrid.setHeader('Sr. No,Task Id, Task Description, Task Status, Task Allocation Date, Task End Date');
        mygrid.setInitWidths('50, 100, 100, 100, 100, 100');
        mygrid.attachHeader(', ,#text_filter,#select_filter, ,');
        mygrid.setColTypes('ro,ro,ro,ro,ro,ro');
        mygrid.enableAutoWidth(true);
        mygrid.enableAutoHeight(true);
        mygrid.init();
        mygrid.post(urls.viewtasksGrid, '', '', "json");
        // Change header text color after grid is initialized
        setTimeout(() => {
            document.querySelectorAll(".hdr td").forEach(el => el.style.color = "black");
            document.querySelectorAll(".filters input, .filters select").forEach(el => el.style.color = "black");
        }, 500);

    } else {
        ajaxCall('GET', urls.viewtasks, '', true, 'load');
    }

}

//function DeleteTask(taskId) {
//    ajaxCall('POST', urls.deleteTask + "&TASK_ID=" + taskId, '', false, 'load');
//
//    var deleteStatus = document.getElementById('deleteStatus').value;
//
//    if (deleteStatus > 0) {
//        alert("Task deleted successfully");
//        DashboardLoader();
//    } else {
//        alert("Error in task deletion, please try later!");
//    }
//}

function UpdateTask(taskId) {
    ajaxCall('POST', urls.updateTask + "&TASK_ID=" + taskId, '', true, 'load');
}

//function updateData() {
//
//    var result = validate_empty('TASK_DESCRIPTION', 'Task Description') && validate_empty('USER_ID', 'Task Username') && validate_empty('TASK_END_DATE', 'Task End Date') && validate_empty('TASK_STATUS', 'Task Status');
//
//    if (result) {
//        var updateForm = document.getElementById('updateForm');
//        var data = getFormData(updateForm);
//
//        ajaxCall('POST', urls.updateData, data, false, 'load');
//
//        var status = document.getElementById('updateStatus').value;
//
//        if (status > 0) {
//            alert("Task update successfully");
//            DashboardLoader();
//        } else {
//            alert("Error in task updation, please try later!");
//        }
//    } else {
//        alert('Not OK!');
//    }
//}

function updateData() {
    var result = validate_empty('TASK_DESCRIPTION', 'Task Description') &&
            validate_empty('TASK_STATUS', 'Task Status');

    if (result) {
        var updateForm = document.getElementById('updateForm');

        updateHiddenDate();

        var data = getFormData(updateForm);
        data.TASK_END_DATE = document.getElementById("enddate").value; // ⬅️ important line

        var userRole = "<%= user.getROLE() %>";
        if (userRole === "Employee") {
            data.TASK_END_DATE = document.getElementById("TASK_END_DATE")?.value || document.getElementById("TASK_END_DATE_HIDDEN").value;
            data.USER_ID = document.getElementById("USER_ID")?.value || document.getElementById("USER_ID_HIDDEN").value;
        }

        ajaxCall('POST', urls.updateData, data, false, 'load');

        var status = document.getElementById('updateStatus').value;

        if (status > 0) {
            alert("Task updated successfully");
            DashboardLoader();
        } else {
            alert("Error in task update, please try later!");
        }
    } else {
        alert('Not OK!');
    }
}


function signin() {
    window.location.replace("task.fin?cmdAction=getTasks");
}

function DeleteTask(taskId) {
    var userRole = "<%= user.getROLE() %>";
    if (userRole === "Employee") {
        alert("You are not allowed to delete tasks.");
        return;
    }

    // Show confirmation dialog
    var confirmDelete = confirm("Are you sure you want to delete this task?");
    if (!confirmDelete) {
        return;
    }

    ajaxCall('POST', urls.deleteTask + "&TASK_ID=" + taskId, '', false, 'load');

    var deleteStatus = document.getElementById('deleteStatus').value;

    if (deleteStatus > 0) {
        alert("Task deleted successfully");
        DashboardLoader();
    } else {
        alert("Error in task deletion, please try later!");
    }
}

function updateHiddenDate() {
    var endDateInput = document.getElementById("taskEndDate");
    var hiddenEndDate = document.getElementById("enddate");

    if (endDateInput && hiddenEndDate) {
        hiddenEndDate.value = endDateInput.value;
    }
}

function importTasks() {
    var fileInput = document.getElementById("fileInput");
    if (fileInput.files.length === 0) {
        alert("Please select a file to upload.");
        return;
    }

    var formData = new FormData();
    formData.append("file", fileInput.files[0]);

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                alert("Tasks imported successfully!");
                ViewTasks();
            } else {
                alert("Error importing tasks. Please try again.");
            }
        }
    };

    xhttp.open("POST", urls.importTasks, true);
    xhttp.send(formData);
}