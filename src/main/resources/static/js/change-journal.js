function onChangeHandler(id, val) {
    if (role === 'STUDENT') {
        console.log("Nice try, " + username + ", =)");
        return;
    }

    console.log("attendanceId = " + id, "mark = " + val);
    console.log(window.location.origin)

    if (val == null || val === "") {
        const requestOptions = {
            method: 'DELETE',
            headers: {'Content-Type': 'text/plain'},
        };

        fetch(window.location.origin + '/attendances/' + id, requestOptions)
            .then(response => console.log(response.json()));
    } else {
        const requestOptions = {
            method: 'PUT',
            headers: {'Content-Type': 'text/plain'},
            body: val
        };
        fetch(window.location.origin + '/attendances/' + id, requestOptions)
            .then(response => console.log(response.json()));
    }
}

function onForbiddenChangeHandler() {
    console.log("Nice try, " + username + ", =)");
}