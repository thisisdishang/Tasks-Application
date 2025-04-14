<%-- 
    Document   : welcome
    Created on : 10 Apr 2025, 4:36:15 pm
    Author     : disha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome - Tasks Application</title>
        <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
        <!--<script src="https://cdn.tailwindcss.com"></script>-->
        <link rel="stylesheet" href="css/mycss.css"/>
    </head>
    <body>
        <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
            <div class="sm:mx-auto sm:w-full sm:max-w-sm">
                <img class="mx-auto h-23 w-auto" src="https://www.njtechnologies.in/img/nj-technologies-logo.png">
                <h2 class="mt-7 text-center text-2xl/9 font-bold tracking-tight text-gray-900">Welcome To Tasks Application</h2>
            </div>

            <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                <c:if test="${not empty error}">
                    <p class="error-message">${error}</p>
                </c:if>

                <form class="space-y-6" action="login.fin" method="POST">
                    <div>
                        <label for="username" class="block text-sm/6 font-medium text-gray-900">Username</label>
                        <div class="mt-2">
                            <input type="text" name="username" id="username" autocomplete="username" required class="block w-full rounded-md border border-gray-400 bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-blue-600 sm:text-sm/6">
                        </div>
                    </div>

                    <div>
                        <label for="password" class="block text-sm/6 font-medium text-gray-900">Password</label>                          
                        <div class="mt-2">
                            <input type="password" name="password" id="password" autocomplete="current-password" required class="block w-full rounded-md border border-gray-400  bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-blue-600 sm:text-sm/6">
                        </div>
                    </div>

                    <div>
                        <label for="role" class="block text-sm/6 font-medium text-gray-900">Select Role</label>
                        <div class="mt-2">
                            <select name="role" id="role" class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300">
                                <option value="">Select Role</option>
                                <option value="Admin">Admin</option>
                                <option value="Employee">Employee</option>
                            </select>
                        </div>
                    </div>

                    <div>
                        <button type="submit" class="flex w-full justify-center rounded-md bg-blue-600 px-3 py-1.5 text-sm/6 font-semibold text-white shadow-xs hover:bg-blue-500 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue-600">
                            Sign in
                        </button>
                    </div>
                </form>                
            </div>
        </div>
    </body>
</html>
