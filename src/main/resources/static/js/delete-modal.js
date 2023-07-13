var deleteModal = document.getElementById("deleteModal");
var closeDeleteModalSpan = document.getElementById("closeDeleteSpan");
var closeDeleteModalButton = document.getElementById("closeDeleteButton");

if (closeDeleteModalSpan != null) {
    closeDeleteModalSpan.onclick = function () {
        deleteModal.style.display = "none";
    }
}

if (closeDeleteModalButton != null) {
    closeDeleteModalButton.onclick = function () {
        deleteModal.style.display = "none";
    }
}