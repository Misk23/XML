# XML


- Članovi tima:
	- Mihajlo Jovković, SW10-2015
	- Slaven Garić, SW47-2015


-Pokretanje projekta:
	- Pokretanje bekenda se vrši u Intellj radnom okruzenju kao spring aplikacija(port 8081)
	- Frontend je rađen u Angularu i on se pokreće iz terminala u Visual Studio Code okruženju komandom "ng serve"
	- Za bazu je korištena ExistDB baza, nju je potrebno prvo skinuti i prebaciti exist.war file u Apache TomCat folder i onda se pozicionirati u bin folder Apacha-a i startovati startup.bat fajl u terminalu, zatim idemo na localhost:8080/exist
	-Za skladištenje rdf grafova za pretrage po više parametara treba skinuti Apache Jenu i pokrenuti fuseki-server.bat fajl u terminalu i zatim idemo na localhost:3030