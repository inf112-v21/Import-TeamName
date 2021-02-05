<h3> Deloppgave 1: Organiser teamet </h3>



<h3> Deloppgave 2: Velg og tilpass en prosess for laget</h3>


<h3> Deloppgave 3: Få oversikt over forventet produkt </h3>

<h4> Overordnet mål for applikasjonen: (Kort oppsummering) </h4>

<h4> Eksempel på brukerhistorier i prioritert rekkefølge </h4>

* Som bruker trenger jeg å se spillbrettet, slik at jeg kan spille roborally uten å måtte huske hvordan brettet ser ut.
		- Akseptansekriterier:
            - Brettet har forventet størrelse og mengde ruter. </br>
            - Antall ruter på brettet tilsvarer antall mulige posisjoner spilleren kan gå til.
		Arbeidsoppgaver:
            **Lage fullstendig tiled kart
            **Importere nevnte kart i spillmotor
            **Koble spill logikk opp mot spillmotor/renderer.

* Som bruker trenger jeg å se min egen brikke på brettet, for å kunne se om brikken min står i fare.
        Akseptansekriterier:
            **Robot må være synlig på brettet.
            **Når spillet startes, må bruker bli tildelt en robot brikke.
            **Robot har forventet plassering i forhold til koordinater innad i spillet.
        Arbeidsoppgaver:
            **Lage grafisk fremstilling av robot.
            **Splitte og korrekt angi grafisk fremstilling i spillmotoren
            **Lage spill logikk som plasserer brikken i korrekt layer av spillebrettet
            
* Som bruker trenger jeg å se andre spilleres brikker, for å kunne planlegge mitt neste trekk.
		Akseptansekriterier:
            **Andre spilleres roboter må være synlig på brettet
            **Posisjonen til robotene må samsvare med posisjonsdata innad i spillet.
        Arbeidsoppgaver:
            **Lage grafisk fremstilling av robot.
            **Splitte og korrekt angi grafisk fremstilling i spillmotoren
            **Lage spill logikk som plasser brikkene i korrekt layer av spillebrettet

* Som bruker trenger jeg at roboten kan bevege seg, slik at jeg kan bytte posisjon på brettet.
	Akseptansekriterier:
            **Roboten skal kunne flytte seg opp, høyre, venstre og ned
            **Robotens brikke vises korrekt på ny plassering.
            **Den forrige ruten viser ikke duplikat av roboten når den flytter seg.
		Arbeidsoppgaver:
            **Spiller kan styre retningen roboten beveger seg i.
            **Robotens gamle posisjon slettes
            **Robotens nye posisjon legges til
