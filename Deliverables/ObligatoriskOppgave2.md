

# INF112 Oblig 2 - Import TeamName

<br>

## Deloppgave 1: Prosjekt og prosjektstruktur

<b>Hvordan fungerer rollene i teamet? Trenger dere andre roller?</b>
Gruppen erfarer at rollene har fungert bra og ser ikke nødvendigheten av et bytte hittil i prosjektarbeidet. En ny vurdering vil bli gjort ved behov.

<b>Erfaringer fra prosjektmetodikk:</b>
Gruppen har foretatt seg parprogrammering fra prosjektets start. Parprogrammering skjer i en mer uformell stand enn det som ofte skisseres i litteraturen (les “Driver/Navigator”), hvor gruppemedlemmene kommer med fortløpende innspill. Slik holder vi bedre kontroll på hva som gjenstår av arbeid. Prosjekttavlen og “issues” brukes hyppig. Gruppen erfarer at dette har fungert bra.

<b>Hva kan forbedres?</b>
Gruppen erfarer at det til tider kan være vanskelig å få oversikt over hva hver enkelt gruppemedlem skal foreta seg av arbeid. For å imøtekomme dette problemet vil gruppen i tiden fremover forsøke å holde bedre oversikt over gjøremål som ikke haster, men som gruppen samtidig vet den skal igjennom. Slike arbeidsoppgaver bør dokumenteres, for eksempel på tavle, slik at et ledig gruppemedlem kan ta på seg oppgaven ved leddigang. Et eksempel på en slik oppgave per nå, er å undersøke multiplayer og forsøke implementering f.eks i en egen git branch.

<b>Hvordan er gruppedynamikken?</b>
Stemningen er god i gruppen, og vi er fornøyd med fremgangen. MVP slik den er beskrevet i Deliverable 1 vil ikke kunne fullføres, men vi anser likevel at det totale arbeidet i prosjektet er kommet langt.

<b>Kommunikasjon:</b>
Gruppen ble tidlig enig om at fastsatte korte møter gir god mulighet for oppfølging på deloppgaver og oppnådde mål. Som nevnt i forrige deliverable, avholdes møtene på mandager, onsdag og fredager. Gruppen har erfart hittil at et slikt møte regime har fungert bra.

<br>

<b>Retrospektiv</b>
I hovedsak har gruppen valgt å forbeholde seg fleksibilitet og ikke bundet seg konkret til én enkelt prosjektmetodikk. Vi har imidlertid lent oss på to metodikker/prinsipper i prosjektarbeidet.


`Parprogrammering`
Gruppen erfarer at parprogrammering i løs forstand har fungert bra. I en typisk arbeidsøkt har gruppen delt seg inn i to/tre og jobbet på et delområde av prosjektet. Delområdene kan omfatte en logisk avgrenset del av prosjektet som implementeres i form av en pakke av klasser, f.eks board/screens.

`Kanban`
Kanban har fungert bra sammen med parprogrammering hvor parene har kontroll over hva det andre paret gjør gjennom prosjekttavlen. Gruppen har praktisert en modifisert kanban metodikk hvor vi har beholdt de mest sentrale elementene backlog, in-progress og done, men lagt til kolonner for hjelp og “Nice to have- not a priority”. Sistnevnte kolonne har vi implementert den siste uken for å få bedre oversikt over fremtidige arbeidsoppgaver som ikke vil bli umiddelbart prioritert. Hensikten er å ledige gruppe medlemmer et delprosjekt å sysselsette seg med.


<b>Arbeidsfordeling og antall commits:</b>
Gruppen opplever arbeidsfordelingen som så jevn det er realistisk å få til. Alle har forskjellige styrker og erfaringer, samt forskjellige nivåer innen programmering. Tidlig i prosjektet brukte alle medlemmene tid på å planlegge struktur og arve relasjon.
Mange timer er også lagt ned i research og planlegging før vi gikk igang med å programmere noe. Dette vises dårlig, og ofte ikke i det hele tatt, i commit historikken.

Vi har nådd utviklingsstadiet der en forandring, implementasjon eller tillegg i koden kan ta alt fra sekunder, til timer, alt etter hva som blir skrevet. Gruppen har lite erfaring med game-engine og GUI. Vi opplever det som utfordrende arbeid som krever svært mye lesing og googling, og resulterer i lite kode skrevet. Dette resulterer i ujevn commit statistikk, selv om mengden timer som er lagt inn i prosjektet er akseptabelt likt.

Tidlig i prosjektet var mye av arbeidet “grunt-work”, for eksmepel Tiles.java og TileManager.java. Samt oppretting av klasser og filer. Arbeid som er enkelt og gir godt utslag på statistikken. Det har blitt brukt ulik commit konvensjon, noen foretrekker små og hyppige, andre større og færre.

Vi anser bidraget av alle i gruppen som realistisk jevn og rettferdig, og mener at antall commits, og antall linjer endret, gir en feilaktig representasjon av den faktiske arbeidsfordelingen.


<b>Møtereferater</b>
Referater fra samtlige møter gruppen har gjennomført kan finnes i github repository under Deliverables/Møtereferater.


<b>Forbedringspunkter:</b>
Kartlegge>Kartlegge fremtidige arbeidsoppgaver i prosjekttavlen. Ikke utelukkende akutte arbeidsoppgaver
Være tydeligere på MVP krav og oppfølging av dem.
I større grad skille møtetid og arbeidstid

<br>


## Deloppgave 2: Krav

<b> Brukerhistorier: </b>

Brukerhistorie #2.1 - Vegger og kollisjon

    “Som brikke, må jeg ikke kunne gå gjennom vegger, slik at jeg ikke bryter spillereglene.”
        Akseptansekriterier:
            Spillere kan ikke gå gjennom vegg objekter. (Wall, Laser, Pushers)
            Spesifisert: En spiller på brettet står sør for en nord-vegg plassert på f.eks (1,1)= (x.y).
            (Nord-vegg, at veggen ligger grafisk nord inne i cellen sin.)
            Spilleren står dermed på (0, 1), og når spilleren prøver å gå inn i cellen (1,1), forlater spilleren (1,0) og inn på (1,1).
            Dersom spilleren prøver å forlate (1,1) i retningen nord,skal spilleren ikke kunne forlate cellen (1,1) pga. nord-veggen i samme celle.

<b> Arbeidsoppgaver: </b>

        Implementere en metode for å sjekke om en actor kan forlate sin egen tile i gitt retning.
        Implementere en metode for å sjekke om en actor kan gå inn på en annen tile i gitt retning.
        Implementere en metode som bruker metodene over, og sjekker om en actor kan beaavege seg i gitt         retning. 


<b> Brukerhistorie #2.2 - Må ikke krasje? </b>


<b>Akseptansekriterier:</b>

<b>Arbeidsoppgaver: </b>

<b>Brukerhistorie #2.3 - JUnit tester </b>
“Som utvikler trenger jeg automatiske tester som gir meg tilbakemelding om kodebasen fortsatt fungerer etter jeg har gjort endringer i den.”

<b>Akseptansekriterier: </b>
Hver test, tester bare for 1 egenskap.
Test navnene kommuniserer tydelig intensjonen til testen.
Tester dekker kodebasen der det er mulig.
Arbeidsoppgaver:
Skrive JUnit tester som dekker forretningslogikken.

<b>Brukerhistorie #2.4 - Manuelle tester </b>
“Som utvikler trenger jeg manuelle tester/prosedyrer for GUI og andre elementer som er vanskelige å teste/forsikre seg om fungerer ved automatisk sjekk..”

Akseptansekriterier:
Testene er presise/beskriver/tester bare 1 ting hver.
Presiserer forventet oppførsel.
Arbeidsoppgaver:
Skrive manuelle tester for GUI

Brukerhistorie #2.5 - Kort
“Som bruker trenger jeg å få utdelt kort, så jeg kan bruke de til å programmere roboten min.”
Akseptansekriterier:
Spillet gir ut riktig antall kort til hver spiller
Hver spiller har en egen lagringsplass (hånd)  til sine kort
Ha en kortstokk som alle kort trekkes fra
Arbeidsoppgaver:
Implementere lagringsplass til spiller
Skrive metode som deler ut kort
Lage en kortstokk

Brukerhistorie #2.6 - Program sheet
“Som bruker trenger jeg et programming sheet, så jeg kan programmere roboten min.”
Akseptansekriterier:
Spillet gir deg mulighet til å endre riktig antall kort
Hver spiller har en egen programming sheet
Spiller kan velge kort fra sin hånd
Arbeidsoppgaver:
Skrive funksjon som fryser kort i programming sheet
Implementere lagringsplass til programming sheet
Fjerne valgte kort fra hånd


<br>

<b> Har dere gjort justeringer på kravene som er med i MVP? Forklar i så fall hvorfor. Hvis det er gjort endringer i rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?  </b>

Fra forrige Deliverable så MPV kravene slik ut:

        1. Vise et spillebrett
        2. Vise brikke på spillebrett
        3. Flytte brikke (vha taster e.l. for testing)
        4. Robot besøker flagg
        5. Robot vinner ved å besøke flagg
        6. Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere)
        7. Dele ut kort
        8. Velge 5 kort
        9. Bevege robot ut fra valgte kort

Første prioritet for gruppen var å refaktorere kode for å innfri Single Responsibility Principle, og generelt gjøre koden mer håndterlig og lettere å jobbe med. Vi brøt opp adferd som har med spillere i actor pakken med interfaces, abstrakte klasser og klasser. Håndteringen av elementer på spillebrettet som f.eks vegger, håndteres av map pakken. Bevegelse av spiller gjøres nå i cards pakken. En svakhet med forrige Deliverable var at vi skrev for få tester for å kontrollere forretningslogikken. Dette har vi imøtekommet ved å skrive utfyllende tester.

<b> Ved denne deliverable har vi innfridd følgende MVP: </b> 

	    1. Vise et spillebrett
        2. Vise brikke på spillebrett
        3. Flytte brikke (vha taster e.l. for testing)
        4. Robot besøker flagg
        5. Robot vinner ved å besøke flagg
        7. Dele ut kort
        8. Bevege robot ut fra valgte kort 

<b> Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. </b>

En oversikt over hvilke krav som er innfridd i denne iterasjonen er gitt i avsnittet over.

I henhold til de prioriterte kravene gitt i første iterasjon, har vi ikke kommet helt i mål etter en streng tolkning av kravene. Slik vi ser det har vi laget et godt fundament for videreutvikling, og har unngått stor refaktorering som ville vært resultatet dersom vi utelukkende skulle prioritert å innfri MVP kravene. Gruppen anser at på lang sikt vil gruppen være bedre tjent med en slik metodikk enn et snevert fokus på MVP poeng.












## Deloppgave 3

Bygging, testing og kjøring er beskrevet i readme.md.

<b>Klassediagram</b>
Et oppdatert klassediagram per Deliverable 2 finnes i Deliverables/UML





