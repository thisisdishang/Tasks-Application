/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.controller;

import com.finlogic.task.model.Tasks;
import com.finlogic.task.model.TasksFormBean;
import com.finlogic.task.model.TasksUser;
import com.finlogic.task.service.TaskService;
import com.finlogic.task.service.UserService;
import com.finlogic.task.util.SessionUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author disha
 */

/*
Controller ==> Service ==> Repository

Dependency Injection

TaskController (TaskServiceImpl: Dependency) or IoC
 */
@Controller
@RequestMapping("task.fin")
// Annotation provide extra data to the jvm environment.
public class TaskController {

    // Spring help us in creating, managing objects.
    // Spring also help us in wiring object whenever it is required
    // field injection
    // @Autowired
    private final TaskService taskService;
    private final UserService userService;
    private final Tasks tasks;

    // constructor injection
    public TaskController(TaskService taskService, Tasks tasks, UserService userService) {
        this.taskService = taskService;
        this.tasks = tasks;
        this.userService = userService;
    }

//    @RequestMapping(params = "cmdAction=getTasks")
//    public ModelAndView getTasks() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("tasks");
//        modelAndView.addObject("tasksList", taskService.getTasks());
//        return modelAndView;
//    }
    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(params = "cmdAction=getTasks")
    public ModelAndView getTasks(HttpServletRequest request) {
        if (SessionUtil.getSessionAttribute(request, "loggedInUser") == null) {
            return new ModelAndView("redirect:welcome.jsp");
        }
        modelAndView.setViewName("tasks");
        return modelAndView;
    }

//    @RequestMapping(params = "cmdAction=getDashboardDetails")
//    public ModelAndView getDashboard() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("dashboard");
//        modelAndView.addObject("dashboardDetails", taskService.getDashboardDetails());
//        return modelAndView;
//    }
    @RequestMapping(params = "cmdAction=getDashboardDetails")
    public ModelAndView getDashboard(HttpServletRequest request) {
        modelAndView.setViewName("dashboard");

        TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");
        if (user == null) {
            return new ModelAndView("redirect:welcome.jsp");
        }

        if ("Employee".equals(user.getROLE())) {
            modelAndView.addObject("dashboardDetails", taskService.getUserDashboardDetails(user.getUSER_ID()));
        } else {
            modelAndView.addObject("dashboardDetails", taskService.getDashboardDetails());
        }

        return modelAndView;
    }

//    @RequestMapping(params = "cmdAction=insertTasks", method = RequestMethod.POST)
//    public ModelAndView insertTasks(TasksFormBean tasksFormBean) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("ajax");
//        modelAndView.addObject("process", "insert");
//        modelAndView.addObject("status", taskService.insertTask(tasksFormBean));
//        return modelAndView;
//    }
    @RequestMapping(params = "cmdAction=getTaskForm")
    public ModelAndView getTaskForm(HttpServletRequest request) {
        TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");
        if (user == null) {
            return new ModelAndView("redirect:welcome.jsp");
        }
        modelAndView.setViewName("ajax");
        modelAndView.addObject("process", "taskform");
        modelAndView.addObject("usersList", userService.getAllUsers());
        return modelAndView;
    }

//    @RequestMapping(params = "cmdAction=viewTasks")
//    public ModelAndView viewTasks() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("ajax");
//        modelAndView.addObject("process", "viewtasks");
//        modelAndView.addObject("data", taskService.viewAllTask());
//        return modelAndView;
//    }
    @RequestMapping(params = "cmdAction=viewTasks")
    public ModelAndView viewTasks(HttpServletRequest request) {
        modelAndView.setViewName("ajax");
        modelAndView.addObject("process", "viewtasks");

        TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");
        if (user == null) {
            modelAndView.setViewName("welcome");
            return modelAndView;
        }

        if ("Employee".equals(user.getROLE())) {
            modelAndView.addObject("data", taskService.viewTasksByUser(user.getUSER_ID()));
        } else {
            modelAndView.addObject("data", taskService.viewAllTask());
        }

        return modelAndView;
    }

//    @RequestMapping(params = "cmdAction=viewTasksGrid")
//    public ModelAndView viewTasksGrid() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("ajax");
//        modelAndView.addObject("process", "viewtasksGrid");
//        modelAndView.addObject("data", taskService.viewAllTaskGrid());
//        return modelAndView;
//    }
    @RequestMapping(params = "cmdAction=viewTasksGrid")
    public ModelAndView viewTasksGrid(HttpServletRequest request) {
        modelAndView.setViewName("ajax");
        modelAndView.addObject("process", "viewtasksGrid");

        TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");
        if (user == null) {
            return new ModelAndView("redirect:welcome.jsp");
        }

        if ("Employee".equals(user.getROLE())) {
            modelAndView.addObject("data", taskService.viewTasksByUserGrid(user.getUSER_ID()));
        } else {
            modelAndView.addObject("data", taskService.viewAllTaskGrid());
        }

        return modelAndView;
    }

//    @RequestMapping(params = "cmdAction=deleteTask", method = RequestMethod.POST)
//    public ModelAndView deleteTask(Tasks tasks) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("ajax");
//        modelAndView.addObject("process", "delete");
//        modelAndView.addObject("deleteStatus", taskService.deleteTask(tasks));
//        return modelAndView;
//    }
//    @RequestMapping(params = "cmdAction=viewTask", method = RequestMethod.POST)
//    public ModelAndView viewTask(Tasks tasks) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("ajax");
//        modelAndView.addObject("process", "viewtask");
//        modelAndView.addObject("data", taskService.viewTask(tasks));
//        return modelAndView;
//    }
    @RequestMapping(params = "cmdAction=viewTask", method = RequestMethod.POST)
    public ModelAndView viewTask(Tasks tasks, HttpServletRequest request) {
        modelAndView.setViewName("ajax");

        TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");
        if (user == null) {
            modelAndView.setViewName("welcome");
            return modelAndView;
        }

        modelAndView.addObject("process", "viewtask");
        modelAndView.addObject("data", taskService.viewTask(tasks));
        modelAndView.addObject("usersList", userService.getAllUsers());
        modelAndView.addObject("userRole", user.getROLE());

        return modelAndView;
    }

//    @RequestMapping(params = "cmdAction=updateTask", method = RequestMethod.POST)
//    public ModelAndView updateTask(Tasks tasks) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("ajax");
//        modelAndView.addObject("process", "updateTask");
//        modelAndView.addObject("updateStatus", taskService.updateTask(tasks));
//        return modelAndView;
//    }
    @RequestMapping(params = "cmdAction=updateTask", method = RequestMethod.POST)
    public ModelAndView updateTask(HttpServletRequest request, Tasks tasks) {
        modelAndView.setViewName("ajax");

        TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");

        if (user == null) {
            modelAndView.setViewName("welcome");
            return modelAndView;
        }

        Tasks existingTask = taskService.getTaskById(tasks.getTASK_ID());

        if (existingTask == null) {
            modelAndView.addObject("updateStatus", "error");
            return modelAndView;
        }

        if ("Employee".equals(user.getROLE())) {
            tasks.setTASK_END_DATE(existingTask.getTASK_END_DATE());
            tasks.setUSER_ID(existingTask.getUSER_ID());
        }

        modelAndView.addObject("process", "updateTask");
        modelAndView.addObject("updateStatus", taskService.updateTask(tasks));

        return modelAndView;
    }

    @RequestMapping(params = "cmdAction=welcome")
    public ModelAndView welcome() {
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    @RequestMapping(params = "cmdAction=logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return "redirect:task.fin?cmdAction=welcome";
    }

    @RequestMapping(params = "cmdAction=insertTasks", method = RequestMethod.POST)
    public ModelAndView insertTasks(@RequestParam("enddate") String taskEndDate, HttpServletRequest request, TasksFormBean tasksFormBean) {
        TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");
        modelAndView.setViewName("ajax");

        tasksFormBean.setTaskEndDate(taskEndDate);

        if (user == null || "Employee".equals(user.getROLE())) {
            modelAndView.addObject("process", "insert");
            modelAndView.addObject("status", "error");
            return modelAndView;
        }

        modelAndView.addObject("process", "insert");
        modelAndView.addObject("status", taskService.insertTask(tasksFormBean));
        return modelAndView;
    }

    @RequestMapping(params = "cmdAction=deleteTask", method = RequestMethod.POST)
    public ModelAndView deleteTask(HttpServletRequest request, Tasks tasks) {
        TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");
        modelAndView.setViewName("ajax");

        if (user == null || "Employee".equals(user.getROLE())) {
            modelAndView.addObject("process", "delete");
            modelAndView.addObject("deleteStatus", "error");
            modelAndView.addObject("message", "Employees are not allowed to delete tasks.");
            return modelAndView;
        }

        modelAndView.addObject("process", "delete");
        modelAndView.addObject("deleteStatus", taskService.deleteTask(tasks));
        return modelAndView;
    }

    @RequestMapping(params = "cmdAction=exportTasks")
    public void exportToExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {

        TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");

        byte[] export;
        if (user != null && "Employee".equals(user.getROLE())) {
            export = taskService.exportToExcel(user.getUSER_ID());
        } else {
            export = taskService.exportToExcel(null);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=tasks.xlsx");
        response.getOutputStream().write(export);
    }

    @RequestMapping(params = "cmdAction=importTasks", method = RequestMethod.POST)
    @ResponseBody
    public String importTasks(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty. Please select a file.";
        }

        try {
            taskService.importFromExcel(file);
            return "success"; // Return success response
        } catch (Exception e) {
            return "Error importing tasks: " + e.getMessage();
        }
    }

}
