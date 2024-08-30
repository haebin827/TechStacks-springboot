/*!
* Start Bootstrap - Simple Sidebar v6.0.6 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
// 
// Scripts
// 

document.addEventListener('DOMContentLoaded', function () {
    const sidebarToggle = document.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }
        sidebarToggle.addEventListener('click', function (e) {
            e.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

    document.querySelector("#logout").addEventListener("click", function (e) {
        e.preventDefault();
        e.stopPropagation();
        const formObj = document.createElement('form');
        formObj.action = "/user/logout";
        formObj.method = "post";
        document.body.appendChild(formObj);
        formObj.submit();
    });
});

/*document.querySelector("#myPageLink").addEventListener("click", function(e) {
    // Check if userLogin session is null
    var userLogin = /!*[[${session.userLogin}]]*!/ null;

    console.log("userLogin: " + userLogin);
    if (userLogin === null || userLogin === "") {
        alert("Login is required.");
        window.location.href = "/user/login";
    } else {
        window.location.href = "/myPage/home";
    }

    e.preventDefault(); // Prevent default anchor behavior
});*/

/*document.querySelector("#logoutPost").addEventListener("click", function(e) {
    e.preventDefault(); // Prevent default anchor behavior

    // Create a form dynamically
    var form = document.createElement("form");
    form.method = "POST";
    form.action = "/user/logout";

    // Append the form to the body and submit
    document.body.appendChild(form);
    form.submit();
});*/

document.querySelector("#csLibraryHeading").addEventListener("click", function() {
    window.location.href = "/home"; // Redirect to home page
});