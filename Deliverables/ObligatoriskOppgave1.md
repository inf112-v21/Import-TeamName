# Obligatorisk Oppgave 1


## Deloppgave 1: Organiser teamet

### Roller:
* Sondre
* Eivind
* August
* Jakob
* Endre


## Deloppgave 2: Velg og tilpass en prosess for laget
### Vi har valgt: 

## Deloppgave 3: Få oversikt over forventet produkt

### Overordnet mål for applikasjonen: (Kort oppsummering)
NoeNoeNoeNoeNoeNoe<br>
NoeNoeNoeNoeNoe
### Eksempel på brukerhistorier i prioritert rekkefølge
<b>Brukerhistorie 1</b>

-  Som bruker trenger jeg å se spillbrettet, slik at jeg kan spille roborally uten å måtte huske hvordan brettet ser ut.

		- Akseptansekriterier: 
			Brettet har forventet størrelse og mengde ruter.
			Antall ruter på brettet tilsvarer antall mulige posisjoner spilleren kan gå til.
		- Arbeidsoppgaver
			Lage fullstendig tiled kart
			Importere nevnte kart i spillmotor
			Koble spill logikk opp mot spillmotor/renderer.

<b>Brukerhistorie 2</b>

- Som bruker trenger jeg å se min egen brikke på brettet, for å kunne se om brikken min står i fare.

		- Akseptansekriterier:
			Robot må være synlig på brettet.
			Når spillet startes, må bruker bli tildelt en robot brikke.
			Robot har forventet plassering i forhold til koordinater innad i spillet.
		- Arbeidsoppgaver:
	       		Lage grafisk fremstilling av robot.
			Splitte og korrekt angi grafisk fremstilling i spillmotoren
			Lage spill logikk som plasserer brikken i korrekt layer av spillebrettet

<b>Brukerhistorie 3</b>

- Som bruker trenger jeg å se andre spilleres brikker, for å kunne planlegge mitt neste trekk.
  
		- Akseptansekriterier:
			- Andre spilleres roboter må være synlig på brettet 
			- Posisjonen til robotene må samsvare med posisjonsdata innad i spillet.
		- Arbeidsoppgaver:
            Lage grafisk fremstilling av robot.
            Splitte og korrekt angi grafisk fremstilling i spillmotoren
            Lage spill logikk som plasser brikkene i korrekt layer av spillebrettet

<b>Brukerhistorie 4</b>

- Som bruker trenger jeg at roboten kan bevege seg, slik at jeg kan bytte posisjon på brettet.
		
		- Akseptansekriterier:
            Roboten skal kunne flytte seg opp, høyre, venstre og ned
            Robotens brikke vises korrekt på ny plassering.
            Den forrige ruten viser ikke duplikat av roboten når den flytter seg.
		- Arbeidsoppgaver:
            Spiller kan styre retningen roboten beveger seg i.
            Robotens gamle posisjon slettes
            Robotens nye posisjon legges til

### Prioritert liste med krav til første iterasjon:
