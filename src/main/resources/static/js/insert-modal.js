var insertModal = document.getElementById("insertModal");
var insertButton = document.getElementById("insertButton");
var closeInsertModalSpan = document.getElementById("closeInsertSpan");
var closeInsertModalButton = document.getElementById("closeInsertButton");

if (insertButton != null) {
    insertButton.onclick = function () {
        insertModal.style.display = "block";
    }
}

if (closeInsertModalSpan != null) {
    closeInsertModalSpan.onclick = function () {
        insertModal.style.display = "none";
    }
}

if (closeInsertModalButton != null) {
    closeInsertModalButton.onclick = function () {
        insertModal.style.display = "none";
    }
}