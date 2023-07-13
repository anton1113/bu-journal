var editModal = document.getElementById("editModal");
var closeEditModalSpan = document.getElementById("closeEditSpan");
var closeEditModalButton = document.getElementById("closeEditButton");

if (closeEditModalSpan != null) {
    closeEditModalSpan.onclick = function () {
        editModal.style.display = "none";
    }
}

if (closeEditModalButton != null) {
    closeEditModalButton.onclick = function () {
        editModal.style.display = "none";
    }
}