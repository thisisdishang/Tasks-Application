/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
//function validate_empty(elementId, labelText) {
//    var elementValue = document.getElementById(elementId).value;
//
//    if (elementValue.trim() === '') {
//        alert(labelText + " cannot be left empty!");
//        document.getElementById(elementId).focus();
//        return false;
//    }
//    return true;
//}

function validate_empty(fieldId, fieldName) {
    var field = document.getElementById(fieldId);

    if (field && field.value.trim() === "") {
        alert(fieldName + " cannot be empty!");
        return false;
    }

    return true;
}


