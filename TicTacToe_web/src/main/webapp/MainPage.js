let timerChoice;

function changeMainText() {
    timerChoice = parseInt(document.getElementById("timerText").value);
    if ((timerChoice >= 3) && (timerChoice <= 10)) {
        document.getElementById("mainText").innerText = "Kezdheted a jatekot!";
        document.getElementById("mainText").style.color = "#c6e2ff";
        revealGMButtons();
        localStorage.setItem("timerChoiceValue", String(timerChoice));
    } else {
        document.getElementById("mainText").innerText = "Nem megfelelo ertek";
        document.getElementById("mainText").style.color = "#f6546a";
        disableGMButtons();
    }
}

function revealGMButtons() {
    document.getElementById("pvpBtn").disabled = false;
    document.getElementById("pveBtn").disabled = false;
    document.getElementById("pvpBtn").style.border = "2px #c6e2ff solid";
    document.getElementById("pveBtn").style.border = "2px #c6e2ff solid";
}

function disableGMButtons() {
    document.getElementById("pvpBtn").disabled = true;
    document.getElementById("pveBtn").disabled = true;
    document.getElementById("pvpBtn").style.border = "2px #f6546a solid";
    document.getElementById("pveBtn").style.border = "2px #f6546a solid";
}
