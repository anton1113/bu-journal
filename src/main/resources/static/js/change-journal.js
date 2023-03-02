function onChangeHandler(id, val) {
    console.log(id, val);
    console.log(window.location.origin)

    const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'text/plain' },
        body: val
    };
    fetch(window.location.origin + '/attendances/' + id, requestOptions)
        .then(response => console.log(response.json()));
}