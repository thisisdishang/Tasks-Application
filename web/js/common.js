/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// 
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
