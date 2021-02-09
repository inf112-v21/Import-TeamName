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


## Deloppgave 2: Velg og tilpass en prosess for laget


Gruppen er blitt enig om at prosjektet foretas uten teamleder, men med selvstendige utviklere hvor alle har en egen støtterolle i tillegg til å være utvikler. I forkant av prosjektet blir gruppen enige om møtehyppighet, kommunikasjonsmidler og hvordan vi ønsker å arbeide med prosjektet for å oppnå best mulig resultat. 

## Kommunikasjon mellom møter:
	* Discord:
		*All kommunikasjon foregår på en egen discord kanal. Her holdes møter på tre
		faste ukentlige tidspunkter
	* Google Doc:
		* Google docs muliggjør samskriving og enkel formidling av notater, arbeidskrav og dokumentasjon til prosjektet.
	* Github:
		* Prosjektet oppbevares som et git repository på github. Issue tracking og prosjektets Kanban tavle er tilgjengelig for gruppens medlemmer.

## Møtehyppighet:
	Gruppen er enig om følgende møtetidspunkter:
	* Obligator møte onsdag 12:15- 14:00 (Holdes på Zoom)
	* Små møter (tentative ved behov) (Holdes på discord):
		* Mandag 15:00
		* Fredag 15:15


Arbeidet gruppen utfører skal følge en Kanban basert prosjekttavle for oppfølging og delegering av arbeidsoppgaver. Gruppen har valgt å ikke følge en streng metodikk, da ingen gruppemedlemmer har erfaring med prosjektmetodikker. Ved behov vil gruppen revurdere hvordan vi arbeider, og hvorvidt en spesifikk prosjektmetodikk skal velges. Ved konsultasjon med gruppeleder har gruppen besluttet å forsøke parprogrammering ved behov. Særlig nyttig kan parprogrammering være dersom det er sprikende kunnskaper blandt gruppemedlemmene på et gitt område av prosjektet. 





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
