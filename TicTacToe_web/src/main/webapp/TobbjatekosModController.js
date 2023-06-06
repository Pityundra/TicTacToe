let fields = [[], [], [], [], []];
let current_player = true;
let timer;
let result;

function initializeFields() {
    for(let i = 0; i < 5; i++) {
        for(let j = 0; j < 5; j++) {
            let button = document.createElement("button");
            button.innerText = "";
            button.disabled = false;
            document.getElementById("row" + (i+1)).appendChild(button);
            button.setAttribute("id", "button" + (i+1) + "_" + (j+1));
            button.setAttribute("class", "fields");
            button.setAttribute("onclick", "javascript: playerClick(this,this.id);");
            fields[i][j] = button;
        }
    }
}

function timerManager() {
    timer = setInterval(function () {
    let timerValue = parseInt(document.getElementById("timer").innerText);
    timerValue--;
    document.getElementById("timer").innerText = String(timerValue);
    if(timerValue === 0) {
        if(current_player) {
            player2Playing();
        } else {
            player1Playing();
        }
        current_player = !current_player;
        document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
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
    document.getElementById("p1").style.fontSize = "36px";
    document.getElementById("p1").style.color = "#f6546a";
    document.getElementById("p2").style.fontSize = "24px";
    document.getElementById("p2").style.color = "whitesmoke";
}

function player2Playing() {
    document.getElementById("p2").style.fontSize = "36px";
    document.getElementById("p2").style.color = "#f6546a";
    document.getElementById("p1").style.fontSize = "24px";
    document.getElementById("p1").style.color = "whitesmoke";
}

function startGame() {
    result = "";
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
    result = "";
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
        alert("Az elso jatekos nyert");
    } else if(winner === "O") {
        alert("A masodik jatekos nyert");
    } else {
        alert("Dontetlen lett");
    }
}

function jsToServlet() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8080/TicTacToe_web_war/result-servlet');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(encodeURI('postValue=' + result));
}

function playerClick(button, id) {
    if(current_player) {
        if(button.innerText === "") {
            button.innerText = "X";
            result += id + ";[X]";
        } else {
            player2Playing();
            current_player = !current_player;
            document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
            return;
        }
        if(allFieldsAreFilled()) {
            showWinner("|");
            result += "{Döntetlen}";
            jsToServlet();
            disableButtons();
            return;
        } else if(gameOver("X")) {
            showWinner("X");
            result += "{X-nyert}"
            jsToServlet();
            disableButtons();
            return;
        } else {
            player2Playing();
            current_player = !current_player;
            document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
        }
    } else {
        if(button.innerText === "") {
            button.innerText = "O";
            result += id + ";[O]";
        } else {
            player1Playing();
            current_player = !current_player;
            document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
            return;
        }
        if(allFieldsAreFilled()) {
            showWinner("|");
            result += "{Döntetlen}";
            jsToServlet();
            disableButtons();
            return;
        } else if(gameOver("O")) {
            showWinner("O");
            result += "{O-nyert}";
            jsToServlet();
            disableButtons();
            return;
        } else {
            player1Playing();
            current_player = !current_player;
            document.getElementById("timer").innerText = localStorage.getItem("timerChoiceValue");
        }
    }
}






