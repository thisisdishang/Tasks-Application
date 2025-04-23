<%-- 
    Document   : ajax
    Created on : 10 Apr 2025, 4:36:07 pm
    Author     : disha
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.finlogic.task.model.TasksUser" %>
<%@ page import="com.finlogic.task.util.SessionUtil" %>
<%
    TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");
    if (user == null) {
        response.sendRedirect("welcome.jsp");
        return;
    }
    request.setAttribute("userRole", user.getROLE()); // Store role in request scope
%>

<%--<c:if test="${process eq 'taskform'}">
    <div class="w-ful">
        <p class="text-3xl text-gray-900 dark:text-black" align="center">Post Task</p>
        <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" id="taskForm">
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="taskDescription">
                    Task Description
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="taskDescription" name="taskDescription" type="text" placeholder="Task Description">
            </div>
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="taskUsername">
                    Allocated To
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="taskUsername" name="taskUsername" type="text" placeholder="Allocated To">
            </div> 
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="taskEndDate">
                    Task End Date
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="taskEndDate" name="taskEndDate" type="text" placeholder="Task End Date">
            </div>
            <div class="flex items-center justify-between">
                <button class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button" onclick="javascript:insertTask()">
                    Submit
                </button>  
                <button class="bg-orange-600 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="reset" onclick="">
                    Reset
                </button>
            </div> 
        </form>      
    </div>
</c:if>--%>

<c:if test="${process eq 'taskform'}">
    <div class="w-ful">
        <p class="text-3xl text-gray-900 dark:text-black" align="center">Post Task</p>
        <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" id="taskForm">
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="taskDescription">
                    Task Description
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" 
                       id="taskDescription" name="taskDescription" type="text" placeholder="Task Description">
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="taskUserId">
                    Allocated To
                </label>
                <select id="taskUserId" name="taskUserId"
                        class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight">
                    <option value="">Select User</option>
                    <c:forEach var="user" items="${usersList}">
                        <option value="${user.USER_ID}">${user.USERNAME}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="taskEndDate">
                    Task End Date
                </label>
                <input type="hidden" name="enddate" value="" id="enddate">
                <input type="date" id="taskEndDate" name="taskEndDate"
                       class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight" 
                       placeholder="Select Task End Date" onchange="updateHiddenDate()">
            </div>

            <div class="flex items-center justify-between">
                <button class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" 
                        type="button" onclick="javascript:insertTask()">
                    Submit
                </button>  
                <button class="bg-orange-600 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" 
                        type="reset">
                    Reset
                </button>
            </div> 
        </form>      
    </div>
</c:if>

<c:if test="${process eq 'insert'}">
    <input type="hidden" value="${status}" id="status"/>
</c:if>

<c:if test="${process eq 'viewtasks'}">
    <div class="p-6">
        <div class="grid grid-cols-2 gap-4 bg-white p-4 rounded-lg shadow-md">
            <form id="importForm" enctype="multipart/form-data" class="flex items-center space-x-2">
                <input type="file" name="file" id="fileInput" accept=".xls,.xlsx" required 
                       class="w-50 border border-gray-400 rounded-md p-1 text-gray-700 focus:ring-2 focus:ring-blue-500">
                <button type="button" onclick="importTasks()" 
                        class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-md shadow">
                    Import
                </button>
            </form>

            <button onclick="window.location.href = 'task.fin?cmdAction=exportTasks'" 
                    class="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded-md shadow justify-self-end">
                Export
            </button>
        </div>

        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            <c:forEach items="${data}" var="task">
                <div class="bg-white shadow-md rounded-lg p-6 border border-gray-200">
                    <h3 class="text-lg font-bold text-gray-900">${task.TASK_DESCRIPTION}</h3>
                    <p class="text-sm text-gray-600"><strong>Status:</strong> ${task.TASK_STATUS}</p>
                    <p class="text-sm text-gray-600"><strong>Allocation Date:</strong> ${task.TASK_ALLOCATION_DATE}</p>
                    <p class="text-sm text-gray-600"><strong>End Date:</strong> ${task.TASK_END_DATE}</p>   
                    <p class="text-sm text-gray-600"><strong>Allocated To:</strong> ${task.ALLOCATED_TO}</p> 

                    <div class="mt-4 flex gap-2">
                        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-4 rounded" 
                                onclick="javascript:UpdateTask('${task.TASK_ID}')">
                            Update
                        </button>

                        <c:if test="${userRole eq 'Admin'}">
                            <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-4 rounded" 
                                    onclick="javascript:DeleteTask('${task.TASK_ID}')">
                                Delete
                            </button>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test="${process eq 'viewtasksGrid'}">
    ${data}        
</c:if>

<c:if test="${process eq 'delete'}">
    <input type="hidden" value="${deleteStatus}" id="deleteStatus"/>
</c:if>

<c:if test="${process eq 'viewtask'}">
    <c:forEach var="task" items="${data}" varStatus="loop">
        <c:if test="${loop.index == 0}">
            <div class="w-full">
                <p class="text-3xl text-gray-900 dark:text-black text-center">Update Tasks</p>
                <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" id="updateForm">
                    <input type="hidden" id="TASK_ID" name="TASK_ID" value="${task.TASK_ID}">

                    <div class="mb-4">
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="TASK_DESCRIPTION">
                            Task Description
                        </label>
                        <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" 
                               id="TASK_DESCRIPTION" name="TASK_DESCRIPTION" type="text" placeholder="Task Description" value="${task.TASK_DESCRIPTION}">
                    </div>

                    <c:if test="${userRole ne 'Employee'}">
                        <div class="mb-4">
                            <label class="block text-gray-700 text-sm font-bold mb-2" for="USER_ID">
                                Allocated To
                            </label>
                            <select id="USER_ID" name="USER_ID"
                                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                                <c:forEach var="user" items="${usersList}">
                                    <option value="${user.USER_ID}" ${user.USER_ID == task.USER_ID ? 'selected' : ''}>
                                        ${user.USERNAME}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>


                    <c:if test="${userRole eq 'Employee'}">               
                        <input type="hidden" id="USER_ID_HIDDEN" name="USER_ID_HIDDEN" value="${task.USER_ID}">
                    </c:if>

                    <c:if test="${userRole ne 'Employee'}">
                        <div class="mb-4">
                            <label class="block text-gray-700 text-sm font-bold mb-2" for="TASK_END_DATE">
                                Task End Date
                            </label>
                            <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" 
                                   id="TASK_END_DATE" name="TASK_END_DATE" type="text" placeholder="Task End Date" value="${task.TASK_END_DATE}">
                        </div>
                    </c:if>

                    <c:if test="${userRole eq 'Employee'}">
                        <input type="hidden" id="TASK_END_DATE_HIDDEN" name="TASK_END_DATE_HIDDEN" value="${task.TASK_END_DATE}">
                    </c:if>

                    <div class="mb-4">
                        <label class="block text-gray-700 text-sm font-bold mb-2" for="TASK_STATUS">
                            Task Status
                        </label>
                        <select id="TASK_STATUS" name="TASK_STATUS"
                                class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                            <option value="PENDING" ${task.TASK_STATUS == 'PENDING' ? 'selected' : ''}>PENDING</option>
                            <option value="INPROGRESS" ${task.TASK_STATUS == 'INPROGRESS' ? 'selected' : ''}>INPROGRESS</option>
                            <option value="COMPLETED" ${task.TASK_STATUS == 'COMPLETED' ? 'selected' : ''}>COMPLETED</option>
                        </select>
                    </div>

                    <div align="center">
                        <button class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" 
                                type="button" onclick="javascript:updateData()">
                            Update
                        </button>                
                    </div> 
                </form>      
            </div>
        </c:if>
    </c:forEach>
</c:if>

<c:if test="${process eq 'updateTask'}">
    <input type="hidden" value="${updateStatus}" id="updateStatus"/>
</c:if>
