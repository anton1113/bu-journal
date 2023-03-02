var modal = document.getElementById("insertModal");
var button = document.getElementById("insertButton");
var closeSpan = document.getElementById("closeInsertSpan");
var closeButton = document.getElementById("closeInsertButton");

button.onclick = function () {
    modal.style.display = "block";
}
closeSpan.onclick = function () {
    modal.style.display = "none";
}

closeButton.onclick = function () {
    modal.style.display = "none";
}