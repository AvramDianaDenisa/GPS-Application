function loginAccont() {
    let login = new Login();
    sendRequest("POST", "login", login , loginSuccessHandler, loginErrorHandler);
}

function Login() {
    let username = $('#username').val().trim();
    let password = $('#password').val().trim();

    if (username.length === 0 || password.length === 0) {
        alert("Please enter both username and password.");
        return;
    }

     let data = {
        username: username,
        password: password
    };

    //sendRequest("POST", "login",login, loginSuccessHandler, loginErrorHandler);
}

function loginSuccessHandler(respData) {
    if (respData.token) {
        localStorage.setItem('token', respData.token)
        window.location.href = 'index.html';

    } else {
        alert("Token missing");
    }

}

function loginErrorHandler(status) {
    alert("Login failed. " + status);
}

function sendRequest(type, resource, data, successHandler, errHandler) {
    $.ajax({
        type: type,
        url: "http://localhost:8082/" + resource,
        data: JSON.stringify(data),  // Adaugă datele de autentificare și convertește-le într-un format JSON
        dataType: "json",
        accepts: "application/json",
        contentType: "application/json",

        success: function (data, status, jqXHR) {
            successHandler(data);
        },

        error: function (jqXHR, status) {
            let error = status + ": " + jqXHR.status;
            errHandler(error);
        }
    });
}


// function login() {
//     let username = $('#user').val().trim();
//     let password = $('#password').val().trim();
//
//     if (username.length === 0 || password.length === 0) {
//         alert("Please enter both username and password.");
//         return;
//     }
//
//     let data = {
//         username: username,
//         password: password
//     };
//
//     sendRequest("POST", "login", JSON.stringify(data), null, loginSuccessHandler, loginErrorHandler);
// }
//
// function loginSuccessHandler(respData) {
//     // Salvare token în localStorage
//     localStorage.setItem('token', respData.token);
//
//     // Redirecționare către pagina principală după login
//     goToPage('index.html');
// }
//
// function loginErrorHandler(status) {
//     alert("Login failed. " + status);
// }


