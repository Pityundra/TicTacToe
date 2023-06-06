**********************Amőba***********************
Játék célja:
	Gyűjts össze 5 szimbolumot egymás melett
	vizszintesen, függőlegesen vagy átlósan
	az ellenfelednél gyorsabban. Ellenfeled 
	lehet egy másik játékos vagy gép. 

	A játék megkezdése előtt be állíthatod, 
	hogy mennyi gondolkodási időtök legyen
	3 és 10 másodperc között.

*******************Információk********************
A többjátékos mód során a lépések és a győztes 
eltárolódik az \TicTacToe_asztali mappán belüli
results nevezetű adatbázisban SQLite segítségével.

jdk-11 -et használ az alkalmazás és IntelliJ IDEA-
ban készült.

Asztali alkalmazás:

  Megnyitásáshoz a feljesztő környezetben a 
  TicTacToe_asztali mappát kell kiválasztani.

  Átírandók:
	Ha nincs be égetve a jdk útvonalata, akkor 
	az alábbi sort kell átírni a pom.xml-ben:

	<executable>"D:\IntelliJ IDEA 2020.1\jdk-11.0.7_windows-x64_bin\jdk-11.0.7\bin\java.exe"</executable>

	Ha be van égetve akkor egyszerúen törölni 
	kel a felső sort!

  Futtatáshoz:
	mvn clean compiler:compile resources:resources javafx:run

Webes alkalmazás:

  Megnyitásáshoz a feljesztő környezetben a 
  TicTacToe_web mappát kell kiválasztani.

  Átirndók:
	Ha nem a 8080-as portott szeretnéd használni
	akkor a Tobbjatekosmod.js-en belül a 
	jsToServlet()-metódosun belül írd át az xhr.open
	útvonalát.

	A resources mappában található a db.properties 
	fájlban át kell írni az útvonalat, úgy hogy az a
	asztali alkalmazásban található results.db-re
	muttasson. Az alábbi módon: 

	db_conn_str=jdbc:sqlite:{meghajtó}:/../../TicTacToe_asztali/results.db

  Futtatáshoz:
	Tomcat Server/Local konfegurációt kell alkalmazni
	Tomcat 9 használatával
