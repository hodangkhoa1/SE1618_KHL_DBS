function setActiveMenuBarHistory() {
    const navLink = document.querySelectorAll("a.all");
    navLink.forEach((item) => {
        item.classList.remove("active");
    });
    navLink.forEach((item) => {
        if (item.getAttribute("href") === window.location.pathname) {
            item.classList.add("active");
        }
    });
}