(function () {
    if (!sessionStorage.getItem("earthAdminState")) {
        window.location.href = './404.html'
    }
})();