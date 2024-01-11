function terminalCredential() {
    let terminalID= $('#terminalId').val().trim();
    if (terminalID.length > 0) {
        this.terminalId = terminalID;
    }
    let terminalName = $('#terminalName').val().trim();
    if (terminalName.length > 0) {
        this.terminalName = terminalName;
    }

}

function addTerminal() {
    let addTerminalCredential = new terminalCredential();

    // Obține token-ul din local storage
    var token = localStorage.getItem('token');

    // Verificare dacă există un token
    if (!token) {
        alert("Token not found. Please log in.");
        return;
    }
    sendRequest("POST", "terminal", JSON.stringify(addTerminalCredential), token, addTerminalSuccessHandler, addTerminalErrorHandler);
}

function addTerminalSuccessHandler(respData) {
    $("#result").append("<br>" + JSON.stringify((respData)))
    alert("Operation succesuflly done!");
}

function addTerminalErrorHandler(status) {
    console.log(jqXHR.responseText)
    alert("Error adding terminal: " + status);
}

function deleteTerminal() {
    let deleteTerminalCredential = new terminalCredential();
    // Obține token-ul din local storage
    var token = localStorage.getItem('token');

    // Verificare dacă există un token
    if (!token) {
        alert("Token not found. Please log in.");
        return;
    }

    sendRequest("DELETE", "terminal/" + deleteTerminalCredential.id+"delete", null, token, deleteTerminalSuccessHandler, deleteTerminalErrorHandler);
}

function deleteTerminalSuccessHandler(respData) {
    $("#result").html("<br>Terminal deleted successfully.");
}

function deleteTerminalErrorHandler(status) {
    alert("Error deleting terminal: " + status);
}


