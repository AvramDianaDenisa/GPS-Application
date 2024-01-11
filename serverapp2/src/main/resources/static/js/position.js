function getPositions() {
    let criteria = new Criteria();

    //todo: replace with the token stored in the local storage at login
    let   token = localStorage.getItem('token');

    sendRequest("GET", "positions?" + $.param(criteria), null, token, getPositionsSuccessHandler, getPositionsErrorHandler);
}

function Criteria() {
    let deviceId = $('#deviceId').val().trim(); // select data from input and trim it
    if (deviceId.length > 0) {
        this.terminalId = deviceId;
    }

    let startDate = $('#startDate').val().trim(); // select data from input and trim it
    if (startDate.length > 0) {
        this.startDate = startDate;
    }

    let endDate = $('#endDate').val().trim(); // select data from input and trim it
    if (endDate.length > 0) {
        this.endDate = endDate;
    }
}

function getPositionsSuccessHandler(respData) {
    try {
        // Nu parsează respData aici, deoarece este deja un șir JSON valid
        localStorage.setItem('respData', JSON.stringify(respData));

        // Restul codului rămâne neschimbat
        // $("#result").append("<br>" + JSON.stringify(respData));
        // console.log(respData[0].latitude)
        // $("#result").text(respData); // appends the json to the 'result' div. see index.html
    } catch (error) {
        console.error("Eroare la parsarea JSON:", error);
    }
}

function getPositionsErrorHandler(status) {
    alert("err response: " + status); // popup on err.
}
