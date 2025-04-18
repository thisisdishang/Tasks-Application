<%-- 
    Document   : dashboard
    Created on : 10 Apr 2025, 4:36:24 pm
    Author     : disha
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="mx-auto max-w-7xl px-6 lg:px-8">
    <dl class="grid grid-cols-1 gap-x-8 gap-y-16 text-center lg:grid-cols-4">
        <div class="mx-auto flex max-w-xs flex-col gap-y-4 bg-blue-100 p-6 rounded-lg shadow-lg">
            <dt class="text-base font-medium text-blue-700">Tasks Allocated</dt>
            <dd class="order-first text-3xl font-semibold tracking-tight text-blue-900 sm:text-5xl">${dashboardDetails[0].nTasksAllocated}</dd>
        </div>

        <div class="mx-auto flex max-w-xs flex-col gap-y-4 bg-red-100 p-6 rounded-lg shadow-lg">
            <dt class="text-base font-medium text-red-700">Tasks Pending</dt>
            <dd class="order-first text-3xl font-semibold tracking-tight text-red-900 sm:text-5xl">${dashboardDetails[0].nTasksPending}</dd>
        </div>

        <div class="mx-auto flex max-w-xs flex-col gap-y-4 bg-yellow-100 p-6 rounded-lg shadow-lg">
            <dt class="text-base font-medium text-yellow-700">Tasks In Progress</dt>
            <dd class="order-first text-3xl font-semibold tracking-tight text-yellow-900 sm:text-5xl">${dashboardDetails[0].nTasksInProgress}</dd>
        </div>

        <div class="mx-auto flex max-w-xs flex-col gap-y-4 bg-green-100 p-6 rounded-lg shadow-lg">
            <dt class="text-base font-medium text-green-700">Tasks Completed</dt>
            <dd class="order-first text-3xl font-semibold tracking-tight text-green-900 sm:text-5xl">${dashboardDetails[0].nTasksCompleted}</dd>
        </div>
    </dl>
</div>
