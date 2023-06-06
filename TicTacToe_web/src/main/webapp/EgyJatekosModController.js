let fields = [[], [], [], [], []];
let timer;

function initializeFields() {
    for(let i = 0; i < 5; i++) {
        for(let j = 0; j < 5; j++) {
            let button = document.createElement("button");
            button.innerText = "";
            button.disabled = false;
            document.getElementById("row" + (i+1)).appendChild(button);
            button.setAttribute("id", "button" + (i+1) + "_" + (j+1));
            button.setAttribute("class", "fields");
            button.setAttribute("onclick", "javascript: playerClick(this);");
            fields[i][j] = button;
            console.log(button + " hozzáadva!");
        }
    }
}

function timerManager() {
    timer = setInterval(function () {
        let timerValue = parseInt(document.getElementById("timer").innerText);
        timerValue--;
        document.getElementById("timer").innerText = String(timerValue);
        if(timerValue === 0) {
            document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
            botMakeStep();
            if(gameOver("O")) {
                showWinner("O");
                disableButtons();
                return;
            }
        }

    }, 1000);
}

function revealButtons() {
    for(let i = 0; i < 5; i++) {
        for(let j = 0; j < 5; j++) {
            fields[i][j].disabled = false;
        }
    }
}

function disableButtons() {
    for(let i = 0; i < 5; i++) {
        for(let j = 0; j < 5; j++) {
            fields[i][j].disabled = true;
        }
    }
}

function player1Playing() {
    document.getElementById("p1").style.fontSize = "24px";
    document.getElementById("p1").style.color = "red";
}

function startGame() {
    player1Playing();
    document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
    initializeFields();
    revealButtons();
    timerManager();
    document.getElementById("restartBtn").style.display = "block";
    document.getElementById("startBtn").disabled = true;
}

function clearButtons() {
    for(let i = 0; i < 5; i++) {
        for(let j = 0; j < 5; j++) {
            fields[i][j].innerText = "";
        }
    }
}

function restartGame() {
    clearInterval(timer);
    player1Playing();
    document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
    revealButtons();
    clearButtons();
    timerManager();
    document.getElementById("startBtn").disabled = true;
}


function gameOver(letter) {
    let szamlalo;
    for (let i = 0; i < 5; i++) {
        szamlalo = 0;
        for (let j = 0; j < 5; j++) {
            if(fields[i][j].innerText === letter) {
                szamlalo++;
                if(szamlalo === 5) {
                    return true;
                }
            }
        }
    }

    for (let i = 0; i < 5; i++) {
        szamlalo = 0;
        for (let j = 0; j < 5; j++) {
            if(fields[j][i].innerText === letter) {
                szamlalo++;
                if(szamlalo === 5) {
                    return true;
                }
            }
        }
    }

    szamlalo = 0;
    for (let i = 0; i < 5; i++) {
        if (fields[i][i].innerText === letter) {
            szamlalo++;
            if (szamlalo === 5) {
                return true;
            }
        }
    }

    if(((fields[0][4].innerText === "X") || (fields[0][4].innerText === "O")) && (fields[0][4].innerText === fields[1][3].innerText)
        && (fields[0][4].innerText === fields[2][2].innerText) && (fields[0][4].innerText === fields[3][1].innerText)
        && (fields[0][4]).innerText === fields[4][0].innerText) {
        return true;
    }

}

function allFieldsAreFilled() {
    for(let i = 0; i < 5; i++) {
        for(let j = 0; j < 5; j++) {
            if(fields[i][j].innerText === "") return false;
        }
    }
    return true;
}

function showWinner(winner) {
    clearInterval(timer);
    if(winner === "X") {
        alert("Az elso jatekos nyert")
    } else if(winner === "O") {
        alert("A gep gyozott")
    } else {
        alert("Dontetlen lett")
    }
}


function botMakeStep() {
    let available = false;
    let i;
    let j;

    while(!available) {
        i = Math.floor(Math.random() * 5);
        j = Math.floor(Math.random() * 5);
        if(fields[i][j].innerText === "") {
            fields[i][j].innerText = "O";
            available = true;
        }
    }

    clearInterval(timer);
    document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
    timerManager();
    player1Playing();
}

function playerClick(button) {
        if(button.innerText === "") {
            button.innerText = "X";
        } else {
            return;
        }
        if(allFieldsAreFilled()) {
            showWinner("|");
            disableButtons();
            return;
        } else if(gameOver("X")) {
            showWinner("X");
            disableButtons();
            return;
        } else {
            document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
            botMakeStep();
            if(gameOver("O")) {
                showWinner("O");
                disableButtons();
                return;
            }
        }
}






