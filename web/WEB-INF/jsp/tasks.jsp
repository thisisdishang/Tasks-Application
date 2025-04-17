<%-- 
    Document   : tasks
    Created on : 10 Apr 2025, 4:37:20 pm
    Author     : disha
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tasks Application</title>
        <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
        <!--<script src="https://cdn.tailwindcss.com"></script>-->
        <script src="js/commonObject.js"></script>
        <script src="js/common.js"></script>
        <script src="js/validate.js"></script>     
        <script src="https://test.njindiaonline.in/finlibrary/resource/ajax.js"></script>
        <script src="https://test.njindiaonline.in/finlibrary/dhtmlxSuite4/codebase/dhtmlx.js"></script>
        <link rel="stylesheet" href="https://test.njindiaonline.in/finlibrary/dhtmlxSuite4/codebase/dhtmlx.css"/>
        <link rel="stylesheet" href="css/mycss.css"/>
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <script>
            window.history.forward();
        </script>   
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const loadDiv = document.getElementById("load");

                const adjustSize = () => {
                    requestAnimationFrame(() => {
                        loadDiv.style.height = "auto";
                        loadDiv.style.width = "auto";
                        let newHeight = loadDiv.scrollHeight;
                        let newWidth = loadDiv.scrollWidth;
                        loadDiv.style.height = ${newHeight}px;
                        loadDiv.style.width = ${newWidth}px;
                    });
                };

                const observer = new MutationObserver(adjustSize);
                observer.observe(loadDiv, {childList: true, subtree: true});

                window.addEventListener("resize", adjustSize);
            });
        </script>
    </head>
    <body class="h-screen flex flex-col items-center justify-center bg-gray-100">
        <%@ page import="com.finlogic.task.model.TasksUser" %>
        <%@ page import="com.finlogic.task.util.SessionUtil" %>
        <%
            TasksUser user = (TasksUser) SessionUtil.getSessionAttribute(request, "loggedInUser");
            if (user == null) {
                response.sendRedirect("welcome.jsp");
                return;
            }
        %>
        <header class="bg-blue-600 w-full shadow-md">
            <nav class="mx-auto flex max-w-7xl items-center justify-center p-6 lg:px-8" aria-label="Global">             
                <div class="flex gap-x-12">
                    <a href="#" class="text-sm font-semibold text-white hover:text-gray-200" onclick="javascript:DashboardLoader()">Dashboard</a>

                    <% if ("Admin".equals(user.getROLE())) { %>
                    <a href="#" id="postTaskBtn" class="text-sm font-semibold text-white hover:text-gray-200" onclick="javascript:TaskFormLoader()">Post Tasks</a>
                    <% }%>

                    <a href="#" class="text-sm font-semibold text-white hover:text-gray-200" onclick="javascript:ViewTasks()">View Tasks</a>
                    <a href="#" class="text-sm font-semibold text-white hover:text-gray-200" onclick="javascript:ViewTasks('grid')">View Tasks - Grid</a>
                </div>                


                <div class="relative ml-auto flex items-center">
                    <button id="userMenuBtn" class="focus:outline-none">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNzXYh-X4wxX1jfbPywa8HWoNGDnx1Tlo0-g&s" 
                             alt="User Icon" class="w-8 h-8 rounded-full border-2 border-black">
                    </button>
                    <div id="userMenu" class="absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg border border-gray-200 hidden">
                        <div class="px-4 py-2 text-gray-900 text-sm">👤 <%= user.getUSERNAME()%></div>
                        <hr class="border-gray-300">
                        <a href="task.fin?cmdAction=logout" class="block px-4 py-2 text-sm text-red-600 hover:bg-gray-100">🚪 Logout</a>
                    </div>
                </div>
            </nav>            
        </header>

        <div class="flex flex-col items-center justify-center flex-grow w-full">            
            <div id="load" class="bg-white shadow-md rounded-lg w-full max-w-4xl min-h-[100px] p-4 transition-all duration-300" align="center">
                <h1 class="text-xl font-bold text-gray-600 text-center">Welcome to Tasks Application</h1>                
            </div>        
        </div>

        <footer class="bg-gray-700 text-white w-full py-4 text-center mt-auto">
            <p class="text-sm">&copy; 2025 NJ Technologies, NJ Group. All Rights Reserved.</p>
        </footer>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const userMenuBtn = document.getElementById("userMenuBtn");
                const userMenu = document.getElementById("userMenu");

                userMenuBtn.addEventListener("click", function (event) {
                    event.stopPropagation();
                    userMenu.classList.toggle("hidden");
                });

                document.addEventListener("click", function () {
                    userMenu.classList.add("hidden");
                });

                userMenu.addEventListener("click", function (event) {
                    event.stopPropagation();
                });
            });
        </script>
    </body>
</html>
