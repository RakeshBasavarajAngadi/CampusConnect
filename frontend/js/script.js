/* =========================================================
   REVA CampusConnect - script.js
========================================================= */

document.addEventListener("DOMContentLoaded", function () {

    /* =====================================================
       NAVBAR NAVIGATION
    ===================================================== */

    const navLinks = document.querySelectorAll(".nav-links a");

    navLinks.forEach(function (link) {

        const text = link.textContent.trim().toLowerCase();

        if (text === "home") {
            link.href = "index.html";
        }
        else if (text === "campus") {
            link.href = "campus.html";
        }
        else if (text === "hostels") {
            link.href = "hostels.html";
        }
        else if (text === "mess") {
            link.href = "mess.html";
        }
        else if (text === "departments") {
            link.href = "departments.html";
        }
        else if (text === "events") {
            link.href = "events.html";
        }
        else if (text === "notices") {
            link.href = "notices.html";
        }
        else if (text === "contact") {
            link.href = "contact.html";
        }

    });


    /* =====================================================
       QUICK ACCESS LINKS
    ===================================================== */

    const quickCards = document.querySelectorAll(".quick-card");

    quickCards.forEach(function (card) {

        const heading = card.querySelector("h3");
        const link = card.querySelector("a");

        if (!heading || !link) {
            return;
        }

        const name = heading.textContent.trim().toLowerCase();

        if (name === "hostels") {
            link.href = "hostels.html";
        }
        else if (name === "mess menu") {
            link.href = "mess.html";
        }
        else if (name === "campus map") {
            link.href = "campus.html";
        }
        else if (name === "departments") {
            link.href = "departments.html";
        }
        else if (name === "notices") {
            link.href = "notices.html";
        }
        else if (name === "events") {
            link.href = "events.html";
        }

    });


    /* =====================================================
       EXPLORE CAMPUS
    ===================================================== */

    const exploreBtn = document.getElementById("exploreBtn");

    if (exploreBtn) {

        exploreBtn.addEventListener("click", function () {

            const quickAccess =
                document.getElementById("quick-access");

            if (quickAccess) {

                quickAccess.scrollIntoView({
                    behavior: "smooth"
                });

            }

        });

    }


    /* =====================================================
       SCROLL REVEAL
    ===================================================== */

    const revealElements = document.querySelectorAll(
        ".quick-card, " +
        ".stat-box, " +
        ".place-card, " +
        ".announcement-card, " +
        ".campus-card, " +
        ".hostel-stat, " +
        ".residence-card, " +
        ".facility-card, " +
        ".mess-info-card, " +
        ".food-option-card, " +
        ".department-card, " +
        ".contact-card"
    );


    revealElements.forEach(function (element) {
        element.classList.add("reveal");
    });


    if ("IntersectionObserver" in window) {

        const observer = new IntersectionObserver(
            function (entries) {

                entries.forEach(function (entry) {

                    if (entry.isIntersecting) {

                        entry.target.classList.add("show");

                        observer.unobserve(entry.target);

                    }

                });

            },
            {
                threshold: 0.12
            }
        );


        revealElements.forEach(function (element) {
            observer.observe(element);
        });

    }
    else {

        revealElements.forEach(function (element) {
            element.classList.add("show");
        });

    }


    /* =====================================================
       SEARCH
    ===================================================== */

    const searchInput =
        document.getElementById("searchInput");

    if (searchInput) {

        searchInput.addEventListener("input", function () {

            const value =
                this.value.toLowerCase().trim();

            const elements = document.querySelectorAll(
                ".quick-card, " +
                ".place-card, " +
                ".announcement-card, " +
                ".campus-card, " +
                ".hostel-stat, " +
                ".residence-card, " +
                ".facility-card, " +
                ".faq-item, " +
                ".mess-info-card, " +
                ".food-option-card, " +
                ".department-card, " +
                ".contact-card"
            );


            elements.forEach(function (element) {

                const text =
                    element.textContent.toLowerCase();

                if (text.includes(value)) {
                    element.style.display = "";
                }
                else {
                    element.style.display = "none";
                }

            });

        });

    }


    /* =====================================================
       DEPARTMENT SEARCH
    ===================================================== */

    const departmentSearch =
        document.getElementById("departmentSearch");

    if (departmentSearch) {

        const cards =
            document.querySelectorAll(".department-card");


        departmentSearch.addEventListener("input", function () {

            const value =
                this.value.toLowerCase().trim();


            cards.forEach(function (card) {

                const text =
                    card.textContent.toLowerCase();


                if (text.includes(value)) {
                    card.style.display = "";
                }
                else {
                    card.style.display = "none";
                }

            });

        });

    }


    /* =====================================================
       FAQ ACCORDION
    ===================================================== */

    const faqQuestions =
        document.querySelectorAll(".faq-question");

    faqQuestions.forEach(function (question) {

        question.addEventListener("click", function () {

            const current =
                this.closest(".faq-item");

            if (!current) {
                return;
            }


            const allItems =
                document.querySelectorAll(".faq-item");


            allItems.forEach(function (item) {

                if (item !== current) {
                    item.classList.remove("open");
                }

            });


            current.classList.toggle("open");

        });

    });


    /* =====================================================
       CAMPUS MAP
    ===================================================== */

    const mapButton =
        document.getElementById("mapButton");

    if (mapButton) {

        mapButton.addEventListener("click", function () {

            alert(
                "Interactive campus map will be available soon."
            );

        });

    }


    /* =====================================================
       CAMPUS FACILITY BUTTONS
    ===================================================== */

    const campusButtons =
        document.querySelectorAll(".campus-btn");

    campusButtons.forEach(function (button) {

        button.addEventListener("click", function () {

            const card =
                this.closest(".campus-card");

            if (!card) {
                return;
            }


            const title =
                card.querySelector("h3");

            if (title) {

                alert(
                    title.textContent +
                    " information will be available soon."
                );

            }

        });

    });


    /* =====================================================
       VIEW ALL NOTICES
    ===================================================== */

    const viewAllBtn =
        document.getElementById("viewAllBtn");

    if (viewAllBtn) {

        viewAllBtn.addEventListener("click", function () {

            window.location.href = "notices.html";

        });

    }


    /* =====================================================
       HOSTEL MESS BUTTON
    ===================================================== */

    const messButton =
        document.querySelector(".mess-button");

    if (messButton) {

        messButton.addEventListener("click", function (event) {

            const href =
                this.getAttribute("href");


            if (!href || href === "#") {

                event.preventDefault();

                window.location.href = "mess.html";

            }

        });

    }


    /* =====================================================
       LOGIN - CONNECT FRONTEND TO SPRING BOOT
    ===================================================== */

    const loginButtons =
        document.querySelectorAll(".login-btn");

    loginButtons.forEach(function (button) {

        button.addEventListener("click", async function (event) {

            event.preventDefault();

            const email =
                prompt("Enter your student email:");

            if (!email) {
                return;
            }

            const password =
                prompt("Enter your password:");

            if (!password) {
                return;
            }

            try {

                const response = await fetch(
                    "http://localhost:8080/api/login",
                    {
                        method: "POST",

                        headers: {
                            "Content-Type": "application/json"
                        },

                        body: JSON.stringify({
                            email: email,
                            password: password
                        })
                    }
                );


                const message =
                    await response.text();


                if (response.ok) {

                    alert("✅ " + message);

                }
                else {

                    alert("❌ " + message);

                }

            }
            catch (error) {

                console.error("Login error:", error);

                alert(
                    "❌ Cannot connect to CampusConnect backend.\n\n" +
                    "Make sure Spring Boot is running on localhost:8080."
                );

            }

        });

    });

});