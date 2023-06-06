<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
<script src="MainPage.js"></script>
<div id="textDiv">
    <h1 id="mainText">Mennyi gondolkodasi ido legyen?</h1>
    <h4 id="choiceText">A gondolkodasi ido 3 es 10 masodperc kozozott allithatod be!</h4>
</div>
<div class="gamemode">
    <button class="gameModeButtons" disabled="true" id="pvpBtn" onclick="location.href='tobbjatekosmod.jsp'">Parpos jatek</button>
    <button class="gameModeButtons" disabled="true" id="pveBtn" onclick="location.href='egyjatekosmod.jsp'">Jatek egyedul</button>
</div>
<br>
<div id="timerTextDiv">
    <input type="text" id="timerText" name="timerText" maxlength="2" size="16" oninput="changeMainText()">
</div>
</body>
</html>
