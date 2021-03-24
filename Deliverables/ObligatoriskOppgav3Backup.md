
# INF112 Oblig 3 - Import TeamName




## Deloppgave 1: Team og prosjekt
<b>Referat</b>
Referat fra hvert møte gruppen har holdt finnes i repository under Deliverables/Møtereferat

<b>Hvordan fungerer rollene i teamet? Trenger dere andre roller?</b>

Teamet har beholdt rollene fra forrige Deliverable og erfarer at rollene slik de er nå fungerer godt. En ny vurdering vil bli gjort ved behov

<b>Erfaringer fra prosjektmetodikk:</b>
Gruppen har drevet med parprogrammering ved flere av møtene som har blitt holdt. Som ved forrige deliverable har vi utført parprogrammering i en mer uformell stil, hvor alle gruppens medlemmer deltar og kommer med innspill. Prosjekttavlen


<b>Retrospektiv</b>
For å få fortgang i utviklingsprosessen og komme i mål med MVP har vi i noe grad forsøkt paralell utvikling på ulike branches.
Komponenter i spillet som manglet fra forrige MVP (multiplayer, kortutdeling og utførelse av kort sine handline) ble utviklet selvstendig og deretter merged sammen med
hovedprosjektet.

<b>Hva kan forbedres?</b>
Iløpet av prosjektet har kodebasen stadig blitt utvidet og det er lettere å miste oversikt. Gruppen har erfart at komponenter av kodebasen en selv ikke har jobbet med, kan være vanskelig å få oversikt over.
Til neste deliverable vil gruppen tilstrebe å sette av mer tid på å få oppdatert gruppemedlemmer på kodebasens helhet, ikke bare enkeltkomponenten gruppemedlemmet selv har jobbet på.

Prosjekttavlen fungerer fint til å holde oversikt over den totale arbeidsmengden, og hver delkomponent, men gruppen har erfart at det er vanskeligere å holde oversikt over hva hver enkelt gruppemedlem skal ha gjort mellom hvert møte. For å imøtekomme dette problemet vil vi i størst mulig grad plassere enkeltmedlemmer på noen av arbeidsoppgavene som finnes på prosjekttavlen.


<b>Forbedringspunkter</b>
1. Oversikt over alle komponenter i kodebasen
2. Deloppgaver til alle gruppens medlemmer


<b>Forklar>Forklar kort hvordan dere har prioritert oppgavene fremover</b>. Legg ved skjermdump av project board ved innlevering

[Bilder av projectBoard]("assets/Images/projectBoard.png")

<b>Gruppedynamikk og kommunikasjon</b>
Gruppen møtes ved faste møter tre ganger uken. Møtene avholdes fast på en egen discord kanal for å forenkle kommunikasjon.



## Deloppgave 2: Krav

*Hvilke krav har vi prioritert? Hva har vi gjort siden forrige innlevering? Møtt MVP?*

### <b> Brukerhistorier: </b>

### <b>Brukerhistorie 3.1 - Valg av kort </b>
* "Som bruker trenger jeg å kunne velge opptil 5 kort fra min hånd"

<b>Akseptansekriterier:</b>
- Å kunne velge riktig antall kort
- Kortene lagres i valgt rekkefølge
- Å kunne legge til kort til register
- Å kunne fjerne kort fra register

<b> Arbeidsoppgaver: </b>
- Lage et register som kortene lagres i
- En måte å velge kort

### <b>Brukerhistorie 3.2 - Grafisk framstilling av kort</b>

<b>Akseptansekriterier:</b>
- Å kunne se hvilke kort man har i hånden sin
- Å kunne se hvilke kort man har valgt
  <b> Arbeidsoppgaver: </b>
- Lage en grafisk framstilling av kortene i hånden
- Valgte kort blir markert


### <b>Brukerhistorie 3.3 - Multiplayer </b>

<b>Akseptansekriterier:</b>

<b> Arbeidsoppgaver: </b>

### <b>Brukerhistorie 3.4 - </b>

<b>Akseptansekriterier:</b>

<b> Arbeidsoppgaver: </b>



<b> Fra forrige Deliverable ble følgende MVP innfridd.</b>

    1. Vise et spillebrett
    2. Vise brikke på spillebrett
    3. Flytte brikke (vha taster e.l. for testing)
    4. Robot besøker flagg
    5. Robot vinner ved å besøke flagg
    7. Dele ut kort
    9. Bevege robot ut fra valgte kort 
<b> MVP slik prosjektet er nå </b> 

    1. Vise et spillebrett
    2. Vise brikke på spillebrett
    3. Flytte brikke (vha taster e.l. for testing)
    4. Robot besøker flagg
    5. Robot vinner ved å besøke flagg
    6. Spille fra flere maskiner**
    7. Dele ut kort
    8. Velge 5 kort
    9. Bevege robot ut fra valgte kort
** En spiller kan tilkoble seg og se spillbrettet





## Deloppgave 3: Produktleveranse og kodekvalitet

*Dokumentasjon på hvordan spillet bygges og spilles finnes i repositoriets readme fil.*

<b> Operativsystem</b>
Prosjektet kan kjøres på Mac, Linux og Windows. Hver build sjekkes automatisk på Mac og Linux av Travis. Ettersom Travis foreløpig ikke støtter Windows builds, tester vi builds manuelt på Windows. 

<b> Testing </b>
Automatisk tester gjøres gjennom Junit, og manuelle tester er beskrevet i Deliverables/ManualTests. 

### <b> Klassediagram </b>
Et oppdatert klassediagram over hele projektet finnes under Deliverables/UML.

På grunn av størrelsen på prosjektet og mengden filer har vi valgt å splitte UML-en opp i flere biter for mer presis forklaring.

Hvert diagram inneholder en viktig del.

<b>Ting på brettet</b>
[Ting på brettet](UML/UMLOfTilesOnGameboard.png)

Dette er alle klassene for det som kan eksistere på spillbrettet. Alt arver fra den abstrakte klassen `SimpleObject.java` som implementerer interfacet `IObjects.java`. Dens hoved egenskap er å holde styr på posisjonen til en brikke på brettet.

`DockingBays`, `Gear`, `Pit`, `Conveyor`, `RepairSite`, `Flag`, og `SimpleRobot` arver direkte herifra.

`Lasers`, `Walls`, og `Pushers` er brikker som også har vegg og da kollisjons logikk. De arver fra `IWall` (Som også arver fra `IObject`), som har tilleggsfunksjonene `isPassableFromDirection()` som gir svar om det er en vegg på brikken i gitt rettning.

Til slutt har vi `actors`, som er brikkene som eksiterer i `Player layer` i alle kart. Dette er brikker som beveger seg, styres av mennesker eller AI. Alle `actors` arver fra `IActor`, holder styr på rettningen til brikken. `SimpleRobot` arver fra `IActor`. Den inneholder alle metodene en robot skal ha, mye av dens informasjon er lagret i `ProgramSheet.java`.

<b>GameBoard - Samle informasjon av brett</b>
[Hva inneholder et brett](UML/UMLGameBoard.png)

Dette er klassen under mappen `Maps` i prosjektet. `Board.java` er hovedklassen, den looper igjennom alle lagene på et kart, lager instanser av objektene den finner, og legger dem til i de relevante listene. Eks, alle "vegg" type brikker blir lagt til i listen `HashMap<> mapCollidables`.

`Tiles.java` er en enum som inneholder alle id-ene til brikkene et kart kan bestå av. `TileManager.java` tar inn en slik id, og retunerer et objekt/instans av dens relevante klasse. Eks, får den id=23, returnerer den en instans av `Wall.java` med informasjonen som gjør den til en brikke med vegg i Øst.


I tillegg har `Board.java` metoder for å spørre hva som er på en gitt posisjon, og om man kan gå fra en posisjon til en nabo, med hensyn til vegger.

<b>GameLoop - spill loopen</b>
[Hva inneholder et brett](UML/UMLGameLoop.png)

Her styres spill loopen som kjører selve spill logikken.


<b>Screens - Skjermene til spillet</b>
[Skjermene](UML/UMLScreens.png)

Her er alle skjermene i spillet. `Titlescreen` er hovedmenyen, `Multiplayer` er undermenyen som styer spill over nett, og `GameScreen` er skjermen med spillet.

I samme package ligger `cardsUI`, denne mappen inneholder skjermene som viser `dashboard/meny` som hver spiller skal ha. Her er logikken for å vise kort og knapper visuelt.
- `CardUI` viser kortene spilleren kan velge mellom.
- `SelectedCards` viser kortene som spiller har valgt. Disse kortene er robotns register. og blir utført i CompleteRegisterPhase.

`WinScreen` er skjermen som vises når en spiller vinner spillet.


<b>Mappen `AssetManager` og `Buttons` - Visuelle resursser i spillet</b>
[Skjermene](UML/UMLAssets.png)

`Assets.java` er klassen som laster inn alle textures og visuelle resursser og gjør dem tilgjengelige for de andre klassene. Dette gjøres kun her i prosjektet, slik at Single Responsibility er innfrid.

`Buttons.java` inneholder foreløpig knappen som brukes til starten av spillet.


<b>Cards - Spillets forskjellige kort</b>
[Skjermene](UML/UMLCards.png)

Alle kort utvider den abstrakte klassen`SimpleProgramCard.java` som implementerer interfacet `IProgramCard.java`. `SimpleProgramCard` inneholder alle generelle egenskaper kort trenger. Det er foreløpig implementert 2 forskjellige kort, `MovementCard.java` og `RotationCard.java`.

`CardType.java` er en enum som inneholder alle mulige kort typer.

`Register.java` og `CardHand.java` er klasser som hver enkelt robot instansierer i sin individuelle `ProgramSheet`. `CardHand` er kortene roboten får utdelt i starten av hver runde. Spilleren velger så 5 av dem, og disse sendes til `Register`. Når spillet skal eksekvere de valgte kortene til spillerene. Henter ` CompleteRegisterPhase.java` ut kortene fra robotenes`Register`.


<br>




<b>Arbeidsfordeling</b>

Gruppen har hatt fokus på å gjevne ut kodebidrag med hensyn på mengden linjer og commits.

<b> Bugs/uønsket adferd </b>
En oversikt over kjente feil finnes på hovesiden/Readme. 














