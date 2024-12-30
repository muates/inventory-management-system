$(document).ready(function() {
    $('#loginTitle').on('click', function() {
        $('#loginForm').collapse('toggle');
        $('#registerForm').collapse('hide');
    });

    $('#registerTitle').on('click', function() {
        $('#registerForm').collapse('toggle');
        $('#loginForm').collapse('hide');
    });
});