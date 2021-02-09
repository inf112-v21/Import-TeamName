# Obligatorisk Oppgave 1


## Deloppgave 1: Organiser teamet
### Kartlegging av kompetanse:

<br>

### Roller:
Noe sånt kanskje? --> Vi har valgt å rullere på rollene igjennom semesteret, slik at alle får en variert erfaring.
* Sondre: Utvikler og kravsjekker
* Eivind: Utvikler og playtester
* August: Utvikler og game master
* Jakob: Utvikler og kundekontakt
* Endre: Utvikler og referant

###Begrunnelse for roller: <br>
Kravsjekker: <br>
Ansvarlig for at gruppen oppfyller alle krav i henhold til skriftlige og kode innleveringerer. Alle var enige om at vi burde tilegne en ansvaret for å dobbeltsjekke produktene før vi leverer til kunden. Dette innebærer en sjekk før levering, men også, en løpende oversikt gjennom innleverings perioden.

Utvikler:<br>
Vi har valgt at alle i gruppen skal ha rollen utvikler. Vi ser et behov for å dele ut visse kritiske roller, men også å beholde smidighet innad i gruppen. Som utvikler skal man bistå andre medlemmer, og tilegne seg oversikt over prosjektet.

Kundekontakt:<br>
Gruppen har besluttet å inkludere rollen kundekontakt. Vi ser et behov for å ha et knutepunkt mellom kunden og gruppen. Her tenker vi at det vil være enklere for kunden å ikke trenge å forholde seg til flere medlemmer fra samme team, samt det vil spare oss tid og forvirring når en har oversikt. Dette forhindrer at flere utviklere bruker tid på å spørre om samme sak. God kontakt med kunden er essensielt for å løse problemer og til slutt levere produktet kunden forventer. Kundekontakten har ansvaret for å videreformidle spørsmål og forespørsler fra teamet til kunden.

Gamemaster:<br>
Vi tenker det er gunstig å gi ansvaret for spilleregler til en av medlemmene. Her er ansvaret å ha kontroll på spillereglene, samt bistå andre gruppemedlemmene når det skulle oppstå tvil. Siden det er en stor mengde regler å forholde seg til, vil vi spare tid ved at ikke alle må umiddelbart lese seg opp på hele spillet.

Playtester:<br>
Ansvaret for å oppdage, dokumentere og informere teamet om feilaktig oppførsel i spillet.


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
```
- Akseptansekriterier: 
	Brettet har forventet størrelse og mengde ruter.
	Antall ruter på brettet tilsvarer antall mulige posisjoner spilleren kan gå til.
- Arbeidsoppgaver
	Lage fullstendig tiled kart
	Importere nevnte kart i spillmotor
	Koble spill logikk opp mot spillmotor/renderer.
```

<b>Brukerhistorie 2 - Brikke på brett</b>

- Som bruker trenger jeg å se min egen brikke på brettet, for å kunne se om brikken min står i fare.
```
- Akseptansekriterier:
	Robot må være synlig på brettet.
	Når spillet startes, må bruker bli tildelt en robot brikke.
	Robot har forventet plassering i forhold til koordinater innad i spillet.
- Arbeidsoppgaver:
	Lage grafisk fremstilling av robot.
	Splitte og korrekt angi grafisk fremstilling i spillmotoren
	Lage spill logikk som plasserer brikken i korrekt layer av spillebrettet
```

<b>Brukerhistorie 3 - Robot bevegelighet</b>

- Som bruker trenger jeg at roboten kan bevege seg, slik at jeg kan bytte posisjon på brettet.
```
- Akseptansekriterier:
	Roboten skal kunne flytte seg opp, høyre, venstre og ned
	Robotens brikke vises korrekt på ny plassering.
	Den forrige ruten viser ikke duplikat av roboten når den flytter seg.
- Arbeidsoppgaver:
	Spiller kan styre retningen roboten beveger seg i.
	Robotens gamle posisjon slettes
	Robotens nye posisjon legges til
```

<b>Brukerhistorie 4 - Fiender </b>

- Som bruker trenger jeg å se andre spilleres brikker, for å kunne planlegge mitt neste trekk.
```
- Akseptansekriterier:
	Andre spilleres roboter må være synlig på brettet 
	Posisjonen til robotene må samsvare med posisjonsdata innad i spillet.
- Arbeidsoppgaver:
	Lage grafisk fremstilling av robot.
	Splitte og korrekt angi grafisk fremstilling i spillmotoren
	Lage spill logikk som plasser brikkene i korrekt layer av spillebrettet
```


### Prioritert liste med krav til første iterasjon:
* 1 
* 2 


<h4> Gjeldende commit for innlevering 1 </h4>
