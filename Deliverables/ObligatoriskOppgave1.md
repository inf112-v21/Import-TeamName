# Obligatorisk Oppgave 1


## Deloppgave 1: Organiser teamet
### Kartlegging av kompetanse:

Endre:
* Datateknologi 2.år
* Erfaring med Git/Github, OOP Java.

August
* Datateknologi 2 år
* Litt erfaring med Git / GitHub
* OOP, Java
* Litt erfaring med Game Engine Godot

Sondre
* IKT 3.år
* Brukt Git til flere prosjekter.
* Java OOP

Jakob
* Datatek 2.
* Tidligere studert fysikk
* Lite kodeerfaring

Eivind Mai
* Datatek 2.
* Litt erfaring med Git/Java

<br>

Viktig erfaring vi ikke har:
* Libgdx
* Networking/Multiplayer

<br>

### Roller:
Noe sånt kanskje? --> Vi har valgt å rullere på rollene igjennom semesteret, slik at alle får en variert erfaring.
* Sondre
* Eivind
* August
* Jakob: Utvikler og kundekontakt.
* Endre: Utvikler og referant.


## Deloppgave 2: Velg og tilpass en prosess for laget
### Vi har valgt: 

### Møter:
* Obligatorisk møte 1 gang i uken Onsdager 12:15 til 14:00 (Zoom).
* I tillegg planlegger vi for 2 små møter:
	* Mandag kl 15:00 - 16:00 (Discord)
	* Fredag kl 15:00 - 16:00 (Discord)
* Det skal skrives møtereferat til alle møter. (Lagres under Deliverbles/Møtereferat/)	
* Gruppen ble enige om at det skal holdes korte, men hyppige møter. Planen over er tentativ, og endres alt etter arbeidsmengde og ved behov. 

* Parprogrammering:
	 * Gruppen ble enige om at det skal holdes flere parprogrammerings-møter. 
	 * Hovedgrunnen er for å øke kompetansen til alle i gruppen.
	
### Kommunikasjon
* Discord
* Github Projekt Board


## Deloppgave 3: Få oversikt over forventet produkt

### Overordnet mål for applikasjonen: (Kort oppsummering)
NoeNoeNoeNoeNoeNoe<br>
NoeNoeNoeNoeNoe
### Eksempel på brukerhistorier i prioritert rekkefølge
<b>Brukerhistorie 1 - Visuell fremstilling</b>

-  Som bruker trenger jeg å se spillbrettet, slik at jeg kan spille roborally uten å måtte huske hvordan brettet ser ut.

		- Akseptansekriterier: 
			Brettet har forventet størrelse og mengde ruter.
			Antall ruter på brettet tilsvarer antall mulige posisjoner spilleren kan gå til.
		- Arbeidsoppgaver
			Lage fullstendig tiled kart
			Importere nevnte kart i spillmotor
			Koble spill logikk opp mot spillmotor/renderer.

<b>Brukerhistorie 2 - Brikke på brett</b>

- Som bruker trenger jeg å se min egen brikke på brettet, for å kunne se om brikken min står i fare.

		- Akseptansekriterier:
			Robot må være synlig på brettet.
			Når spillet startes, må bruker bli tildelt en robot brikke.
			Robot har forventet plassering i forhold til koordinater innad i spillet.
		- Arbeidsoppgaver:
	       		Lage grafisk fremstilling av robot.
			Splitte og korrekt angi grafisk fremstilling i spillmotoren
			Lage spill logikk som plasserer brikken i korrekt layer av spillebrettet

<b>Brukerhistorie 3 - Robot bevegelighet</b>

- Som bruker trenger jeg at roboten kan bevege seg, slik at jeg kan bytte posisjon på brettet.

  	- Akseptansekriterier:
            Roboten skal kunne flytte seg opp, høyre, venstre og ned
            Robotens brikke vises korrekt på ny plassering.
            Den forrige ruten viser ikke duplikat av roboten når den flytter seg.
  	- Arbeidsoppgaver:
            Spiller kan styre retningen roboten beveger seg i.
            Robotens gamle posisjon slettes
            Robotens nye posisjon legges til

<b>Brukerhistorie 4 - Fiender </b>

- Som bruker trenger jeg å se andre spilleres brikker, for å kunne planlegge mitt neste trekk.
  
		- Akseptansekriterier:
			- Andre spilleres roboter må være synlig på brettet 
			- Posisjonen til robotene må samsvare med posisjonsdata innad i spillet.
		- Arbeidsoppgaver:
            Lage grafisk fremstilling av robot.
            Splitte og korrekt angi grafisk fremstilling i spillmotoren
            Lage spill logikk som plasser brikkene i korrekt layer av spillebrettet



### Prioritert liste med krav til første iterasjon:
* 1 
* 2 