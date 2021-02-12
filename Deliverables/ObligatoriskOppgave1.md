# Obligatorisk Oppgave 1


## Deloppgave 1: Organiser teamet
### Kartlegging av kompetanse:
Teamet består av fire fra datateknologi og en fra IKT. 
Dermed har alle jevn erfaring opp mot java, og stort sett har alle vært innom samme emner. 
Git har vært brukt av alle, i blant annet INF101 og INF102, og alle i gruppen er kjent med konseptet. 
Vi ser derimot et behov for å ta en grunnleggende gjennomgang slik at alle har en felles forståelse for git opp mot prosjektet. 
Ingen i teamet har hatt jobb/sommerjobb relevant til faget, så prosjektarbeid og metodikk er nytt for alle. 
Flere av medlemmene har hatt relevante prosjekter på fritiden, og har litt utvidet erfaring i visse områder, men det varierer hvor relevant diverse teknologier er for prosjektet.
<br>

### Roller:
Vi har valgt å rullere på rollene igjennom semesteret, slik at alle får en variert erfaring.
* Sondre: Utvikler og kravsjekker
* Eivind: Utvikler og playtester
* August: Utvikler og game master
* Jakob: Utvikler og kundekontakt
* Endre: Utvikler og referant

### Begrunnelse for roller:
<b>Kravsjekker:</b><br>
Ansvarlig for at gruppen oppfyller alle krav i henhold til skriftlige og kode innleveringerer. Alle var enige om at vi burde tilegne en ansvaret for å dobbeltsjekke produktene før vi leverer til kunden. Dette innebærer en sjekk før levering, men også, en løpende oversikt gjennom innleverings perioden.

<b>Utvikler:</b><br>
Vi har valgt at alle i gruppen skal ha rollen utvikler. Vi ser et behov for å dele ut visse kritiske roller, men også å beholde smidighet innad i gruppen. Som utvikler skal man bistå andre medlemmer, og tilegne seg oversikt over prosjektet.

<b>Kundekontakt:</b><br>
Gruppen har besluttet å inkludere rollen kundekontakt. Vi ser et behov for å ha et knutepunkt mellom kunden og gruppen. Her tenker vi at det vil være enklere for kunden å ikke trenge å forholde seg til flere medlemmer fra samme team, samt det vil spare oss tid og forvirring når en har oversikt. Dette forhindrer at flere utviklere bruker tid på å spørre om samme sak. God kontakt med kunden er essensielt for å løse problemer og til slutt levere produktet kunden forventer. Kundekontakten har ansvaret for å videreformidle spørsmål og forespørsler fra teamet til kunden.

<b>Gamemaster:</b><br>
Vi tenker det er gunstig å gi ansvaret for spilleregler til en av medlemmene. Her er ansvaret å ha kontroll på spillereglene, samt bistå andre gruppemedlemmene når det skulle oppstå tvil. Siden det er en stor mengde regler å forholde seg til, vil vi spare tid ved at ikke alle må umiddelbart lese seg opp på hele spillet.

<b>Playtester:</b><br>
Ansvaret for å oppdage, dokumentere og informere teamet om feilaktig oppførsel i spillet.



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
        Lengde for små møter er satt til maks 1 time. Ytterliggere møter planlegges ved behov.
		* Mandag 15:00
		* Fredag 15:15


<p> 
    Arbeidet gruppen utfører skal følge en Kanban basert prosjekttavle for oppfølging og delegering av arbeidsoppgaver. <br>
    Gruppen har valgt å ikke følge en streng metodikk, da ingen gruppemedlemmer har erfaring med prosjektmetodikker. 
    Ved behov vil gruppen revurdere hvordan vi arbeider, og hvorvidt en spesifikk prosjektmetodikk skal velges. 
    Ved konsultasjon med gruppeleder har gruppen besluttet å forsøke parprogrammering ved behov. Særlig nyttig kan parprogrammering være dersom det er sprikende kunnskaper blandt gruppemedlemmene på et gitt område av prosjektet.
    <br> Ved behov vil arbeidsmetodikk endres etter innlevering 1. </br>

</p>




## Deloppgave 3: Få oversikt over forventet produkt

### Overordnet mål for applikasjonen: (Kort oppsummering)

Applikasjonen skal la flere personer spille RoboRally i samtid fra forskjellige enheter og lokasjoner. Spillet skal gjengi beskrivelsen og dokumentasjonen fra guiden vi har fått utdelt.


### Eksempel på brukerhistorier i prioritert rekkefølge

<b>Brukerhistorie 1 - Visuell fremstilling</b>

-  Som bruker trenger jeg å se spillbrettet, slik at jeg kan spille RoboRally uten å måtte huske hvordan brettet ser ut.
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
	Roboten skal kunne flytte seg opp, høyre, venstre, og ned.
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

### MVP-krav:

1. Vise spillebrett på skjermen
2. Vise brikke på spillebrett
3. Robot bevegelighet
4. Robot dør når den går over et hull (sprite endres)
5. Robot vinner ved å besøke flagg (sprite endres)
   

<br> Et klassediagram over forventede klasser i arbeidet fremover etter obligatorisk oppgave 1, er lagt til i Deliverables\UML mappen. </br>

## Deloppgave 4: 


Vi har gjennom første del av prosjektet avholdt hyppige og korte møter. I den faste gruppetimen har gruppen arbeidet sammen, skrevet dokumentasjon og forklart kode til andre gruppemedlemmer. Vi har så langt erfart at dette har vært en god måte å jobbe på.

Etter å ha arbeidet med prosjektet i en uke, har vi erfart at fordeling og delegering av arbeid kan uttrykkes tydeligere. Gruppen har blitt enig om å bruke github sitt issue system til å delegere arbeidsoppgaver gruppen har blitt enig på hvert møte. Prosjekttavlen vil bli brukt for å holde oversikt over prosjektet, hva som er gjort, hva som er igang og hva som skal gjøres.

Det er enda tidlig i prosjektet, og etter hvert som prosjektet går videre vil vi i større grad kunne gjøre rede for styrker og svakheter i gruppearbeidet.

#### Positive og negative erfrainger 
    Positivt:
        Hyppige møter fungerer
        Prosjektet går fremover
    Til forbering:
        Delegering av arbeidsoppgaver. I større grad bruke Issues i Github til å markere arbeidsfordeling.
        Hyppigere og mer presise commits i git.


<h4> Gjeldende commit for innlevering 1 </h4>
Deliverable 1 under releases
