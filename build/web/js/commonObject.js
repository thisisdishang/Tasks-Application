/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
commonUrls = {
    dashboard: "task.fin?cmdAction=getDashboardDetails",
    taskform: "task.fin?cmdAction=getTaskForm",
    insertTasks: "task.fin?cmdAction=insertTasks",
    viewtasks: "task.fin?cmdAction=viewTasks",
    viewtasksGrid: "task.fin?cmdAction=viewTasksGrid",
    deleteTask: "task.fin?cmdAction=deleteTask",
    updateTask: "task.fin?cmdAction=viewTask",
    updateData: "task.fin?cmdAction=updateTask",
    importTasks: "task.fin?cmdAction=importTasks"
};

function getAllUrls() {
    return commonUrls;
}
