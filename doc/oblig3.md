 # Rapport – innlevering 3
**Team:** 
* Team: *Skadedyrkontrollørene_inc* (Gruppe 1): 
    * *Jonathan Christensen - Developer/Git*
    * *Axel Lundeby - Developer/Grafikk*
    * *Theodor Nissen-Meyer - Lead Developer*
    * *Jacob Foss - Developer/Testansvarlig*


## Referat 11.03.24

**Tilstede:** Alle

- Vi er fornøyde med at vi fikk til store deler av MVPen. Fremover blir det å fullføre MVP og fikse funksjonalitet.
- Vi skal også se på hvordan å lage path på et bedre vis.
- Må fikse grafikk og lyd.
- Se på tester, og hvordan disse skal skrives.

## Referat 18.03.24

**Avslutning av andre sprint**  

**Tilstede:** Alle

- **Hva har blitt gjort siden forrige møte?**
  - Mer funksjonalitet er implementert. Vi får byttet mellom vanlig katt og fryse-katt.
  - Er i oppstartsfasen av å lage grafikk.
  - Rottene går ikke på path, skal fikse dette opp mot ny bane (grafikk) - fokuset er ikke her.

- **Hva skal vi gjøre til neste møte?**
  - Litt problematikk med prosjektilene, de avhenger av hverandres vektor(?).
  - Matematikken er ikke helt på topp, og vi forstår ikke helt hva som skjer, men vi er på saken.
  - Det er en rotasjonsbug, som gjør at kattene roterer litt feil. Vi må finne ut av dette.

### Påskeferie 22.03.24-03.04.24

## Referat 08.04.24

**Nærmer oss release candidate**

**Tilstede:** Alle

- **Hva har skjedd siden forrige møte?**
  - Rotasjonsbugen er løst.
  - Problemet med prosjektilet er ikke løst.

- **Hva skal vi gjøre til neste møte?**
  - Se på tilbakemeldingsskjemaet vi har fått fra Oblig 2, og lage en TO DO for å snevre inn hva som mangler for å oppnå full score.
  - Få inn deler av TO DO i kanban-boardet også.
  - Vårt fokus skal ligge på testing frem til innlevering.
  - Vi skal også få dokumentert public-metoder, samt oppdatere pom-filen.
  - Grafikk er 90% ferdig, men usikkert om det rekker å bli implementert til innlevering.

**TO DO:**
- **TEAMET**
  - Beskrivelse av prosjektmetodikk
  - Project board, issues etc. er oppdatert
- **Produktleveranse**
  - pom.xml / build.gradle er oppdatert med korrekt prosjektnavn, main-klassenavn etc.
- **Kodekvalitet**
  - Public metoder dokumentert
- **Testing**
  - Test coverage, Minimum 75% test coverage
  - Tester er automatiske, Automatiske tester kan kjøres «hodeløst»
  - Tester som faktisk kan finne feil
- **Konkrete krav**
  - Spillet har forside/hjelpeside
  - Lyd koblet til hendelse
  - Objektfabrikker, Objekter som modifiserer oppførsel (powerups, etc.)
  - Abstrakte objektfabrikker

## Referat 10.04.24

**Nærmer oss release candidate - statusmøte**

**Tilstede:** Alle

- **Hva har skjedd siden forrige møte?**
  - Kanban-boardet er gjort offentlig, her er linken: [https://trello.com/b/YKJUgETU](https://trello.com/b/YKJUgETU).
  - POM-filen er oppdatert.
  - Dokumentasjon er delvis ferdigstilt, Tester er in the making, blir merget til fredag.
  - Det vi har av grafikk er implementert.
  - Vi har tatt bort det som er unnagjort fra TO DO.

- **Hva skal vi gjøre til neste møte?**
  - Bruke kanban for å snevre inn TO Do enda mer.
  - Sørge for at Readme er oppdatert, og at doc er fullført.

**TO DO:**

- **TEAMET**
  - Beskrivelse av prosjektmetodikk
- **Testing**
  - Test coverage, Minimum 75% test coverage
  - Tester er automatiske, Automatiske tester kan kjøres «hodeløst»
  - Tester som faktisk kan finne feil
- **Konkrete krav**
  - Spillet har forside/hjelpeside
  - Lyd koblet til hendelse
  - Objektfabrikker, Objekter som modifiserer oppførsel (powerups, etc.)
  - Abstrakte objektfabrikker

## Teamroller og Dynamikk

Vår team fungerer bra slik rollene er satt i dag, og har ikke hatt behov for endringer. Gruppestrukturen er fortsatt som følger:

- **Lead Developer (Theodor):** Har det overordnede ansvaret for kodebasen, innsikt i prosjektets tekniske aspekter, og bistår andre utviklere ved behov.
- **Developer:** Alle teammedlemmer har denne rollen og bidrar med utviklingen av programvaren.
- **Git Ansvarlig (Jonathan):** Spesialisert i bruk av Git, denne rollen innebærer å opprettholde repoet, og sikre at det er oppdatert.
- **Grafikk (Axel):** Ansvarlig for å sikre at spillets grafikk stemmer overens med de ønskede visuelle målene.
- **Testansvarlig (Jacob):** Fokuserer på utvikling og implementering av tester for å sikre at koden fungerer som forventet.

**Gruppedynamikk:**

Gruppedynamikken er god, og vi har ikke hatt noen problemer innad i teamet som har krevd endring av struktur. Vi kommuniserer kontinuerlig og holder hverandre oppdatert om prosjektets status, hovedsakelig fordi vi tilbringer mye tid sammen på lesesalen. Dette har ført til at de ukentlige standup-møtene ikke føles like nødvendige, men de tjener fortsatt en viktig funksjon for å oppsummere og planlegge for kommende uke. Bruken av et kanban-board har også bidratt positivt til å holde oversikt over arbeidsflyt og fremgang. Spesielt nå opp mot sluttprodukt har det vært enda mer relevant å strukturere oppgaver som må løses ved hjelp av kanban. KanbanBoardet finner man her: https://trello.com/b/YKJUgETU


**Forbedringspunkter:**

- **Oppgradering av katter:** I dag er oppdatering litt uoversiktlig. Rette opp i dette, og sørge for en ryddig og forståelig løsning.
- **Prosjektiler:** Få prosjektil til å fungere slik vi ønsker.
- **Lyd:** Implementere lyd. Hva slags lyder skal man bruke til de ulike aspektene av spillet. Vi vet at vi vil ha lyd ved katteangrep. 




## Oppdatering på prosjektstatus: Prioriterte krav og fremdrift

## Introduksjon
Her er statusen på hvor langt vi har kommet i utviklingsprosessen, og de stegene vi har tatt siden vår siste oppdatering. Vårt fokus har vært sentrert rundt utviklingen av brukerhistorier og Minimum Viable Product (MVP). Vi er nå nær ved å fullføre MVPen, og her presenterer vi en detaljert gjennomgang av vår progresjon. Vi har endret MVPen litt; vi har inkludert at vi må ta med lyd i spillet.

## Utførte funksjoner på MVPen:
- En "Rat Factory"-klasse, designet for å generere forskjellige rotter i bølger basert på spillerens nivå.
- Implementert en definert sti for rottebevegelse som strekker seg fra startpunkt til mål.
- Utviklet interaktive knapper som opereres med musen for å starte, akselerere, pause, og restarte spillet.
- Innført opptjening av penger for hver drepte rotte, som videre kan brukes for kjøp av flere katter og oppgraderinger.
- Livstelling for å spore spillerens gjenværende liv.
- Utviklet en enkel meny for spillstart.
- En kjøpsmeny for å kjøpe flere katter.

## MVP-funksjonaliteter med manglende funksjonaliteter:
- Mangler funksjonalitet for å oppgradere hver enkelt katt hver for seg, ikke alle.
- Implementere en definert sti for rottebevegelse som strekker seg fra startpunkt til mål.(Dette har vi egentlig fått til, men vi ønsker å gjøre det på en bedre måte med Spline, rottene går derfor nå på stien til det gamle kartet).
- Lyd, mangler funksjoner som lager en lyd når hendelser i spillet forekommer.

## Fremtidige prioriteter og fokusområder:
- Fullføre oppgraderingsmulighetene for hver spesifikk katt.
- Tester: Utvikling av ytterligere tester for bedre kvalitetssikring..
- Prosjektiler: Implementering av prosjektiler.
- Lyd: Innføring av lydeffekter for å forbedre spillopplevelsen.

### Brukerhistorie for oppgraderbare katter:
Som en spiller ønsker jeg å kunne oppgradere spesifikke katter som allerede er plassert på spillbrettet slik at deres rekkevidde, styrke, og firerate kan forbedres. Dette vil gjøre det mulig for meg å håndtere stadig vanskeligere bølger av angripende rotter mer effektivt.

#### Akseptansekriterier:
- Spilleren skal kunne velge spesifikke katter som allerede er plassert på kartet for oppgradering.

#### Arbeidsoppgaver for oppgradering:
1. Fastsett en unik kostnadsstruktur for hver oppgraderingstype (rekkevidde, styrke, firerate) for hver unike kattetype.
2. Utvikle en funksjon som sjekker om spilleren har nok penger til å utføre ønsket oppgradering før transaksjonen godkjennes.
3. Oppdater spillerens lommebok umiddelbart etter at en oppgradering er gjennomført på en spesifikk katt.
4. Når en katt er oppgradert, skal kattens bilde endres for å reflektere oppgraderingen visuelt.
5. En katt kan maks oppgraderes 3 ganger for hver oppgraderingskategori.

### Brukerhistorie for katter med unike lydeffekter:
Som en spiller ønsker jeg at mine katter skal lage unike lydeffekter når de angriper. Dette vil gjøre det lettere for meg å identifisere hvilke katter som angriper og hvordan de utvikler seg gjennom spillet.

#### Akseptansekriterier for lydeffekter:
- Spilleren skal kunne høre en unik lydeffekt for hver spesifikke katt når de angriper.

#### Arbeidsoppgaver for lydeffekter:
8. Identifiser og design unike lydeffekter for hver kattetype.
9. Implementer en funksjon som spiller av den riktige lydeffekten når en katt angriper.
10. Optimaliser lydeffektenes volum og varighet.

### Brukerhistorie for prosjektilsystem:
Som en spiller ønsker jeg at mine katter skal kunne skyte prosjektiler mot angripende rotter. Det vil da bli lettere for meg å se hvilke rotter kattene mine angriper.

#### Akseptansekriterier for prosjektiler:
- Spilleren skal kunne se at kattene skyter prosjektiler mot angripende rotter automatisk når rotter kommer innenfor rekkevidde.

#### Arbeidsoppgaver for prosjektilsystem:
12. Designe og implementere en prosjektilklasse.
13. Implementere en skytefunksjon for kattene.
14. Lage unike grafiske representasjoner for hvert prosjektil.

## Prioritering av oppgavene i synkende rekkefølge:
1. Katte oppgradering
2. Fikse prosjektiler
3. Lage tester
4. Lyd

## Oversikt over kjente bugs:

### 1. Spillet Krasjer på Wave 6
- **Problemstilling:** Spillet krasjer når spillerne når Wave 6.
- **Potensiell løsning:** Vi mistenker at dette har noe med RatFactory klassen å gjøre.

### 2. Feil med Prosjektilmekanikken
- Vi opplever også bugs med prosjektilene våre. Det virker som alle prosjektilene er avhengig av hverandre.





# Produkt og kode

## Oppdateringer siden sist

### Vi har gjort følgende forbedringer og tillegg til spillet siden Oblig 2:

- **POM fil:** Vi har oppdatert POM-filen med korrekt prosjektnavn, main-klassenavn etc.
- **Testing:** Vi har jobbet med å øke test coverage.
- **RatFactory:** Vi har opprettet en ratFactory klasse som genererer rotter i bølger basert på spillerens nivå.
- **Dokumentasjon:** 
  - Vi har oppdatert README.md
  - *Kode Dokumentasjon:* Vi har dokumentert public metoder og laget interface til nødvendige klasser.
- **Knapper:** Alle knapper er nå interaktive og kan brukes basert på hvor mye pengene spilleren har.
  - *Menyknapper:* Vi har laget interaktive knapper som brukes for å starte, akselerere, pause, og restarte spillet.
  - *Kjøpsmeny:* Vi har laget en kjøpsmeny for å kjøpe flere katter.
  - *Oppgraderingsknapper:* Vi har laget oppgraderingsknapper for å oppgradere kattenes range, damage og firerate.
  - *Startmeny:* Vi har laget en enkel knapp på startmeny for å starte spillet.
- **Rotter:** 
  - Rottene roterer nå basert på hvilken retning de går.
  - Forskjellige rotter har mulighet til å bli spawnet basert på spillerens nivå.
  - Når du dreper en rotte får du penger og poeng, i tillegg til at en mynt blir synlig på skjermen der rotta døde.
  - Du får forskjellig poeng basert på hvilken rotte du dreper.
- **Liv:** Vi har lagt til slik at man ikke kan ha negative liv.
- **Penger:**
  - Spilleren kan nå ikke ha negative penger.

- **Map:**
  - Det er lagt inn et eget nytt map med en annen sti for rotter.
    - Derfor går ikke rotter på stien til det nye kartet.
  
    

## Klassediagram
![Klassediagram](/src/main/resources/uml_oblig3.png)
