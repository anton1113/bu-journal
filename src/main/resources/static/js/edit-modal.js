var editModal = document.getElementById("editModal");
var editButton = document.getElementById("editButton");
var closeEditModalSpan = document.getElementById("closeEditSpan");
var closeEditModalButton = document.getElementById("closeEditButton");

if (editButton != null) {
    editButton.onclick = function () {
        editModal.style.display = "block";
    }
}

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