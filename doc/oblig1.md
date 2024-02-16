# Rapport – Innlevering 1, *Return of the rats*


### Oppgave A1: Konsept

Checkliste fra oppskriften.
 - Vi har opprettet et repo på gitt. 
 - Vi har lagt til alle i teamet (som developers). Gruppeleder Tobias Innlegen og foreleser Anya Helene Bagge er lagt til som maintainers (ettersom det ikke fungerte å legge til gruppe)
 - Vi har forket prosjektet til Anya
 - README er oppdatert, og vil holdes oppdatert gjennom semesteret.


#### Team: Skadedyrkontrollørene_inc (Gruppe 1)

- Jonathan Christensen - Developer/Git
- Axel Lundeby - Developer/Grafikk 
- Theodor Nissen-Meyer - Lead Developer
- Jacob Foss - Developer/Testansvarlig


### Oppgave A2: Konsept

#### Spillmekanikker

Inspirert av [BloonsTD](https://no.wikipedia.org/wiki/Bloons_Tower_Defense), består *Skadedyrkontrollørene* av følgende spillmekanikker:

- **Forsvarere som kan plasseres:** Spillere kan utplassere ulike kattetyper langs ruten til rotter for å forsvare byen. Hver katt har unike evner som prosjektilangrep, nærkamp, eller spesialeffekter som å fryse eller bremse ned fiender.

- **Todimensjonal byverden:** En spillverden med horisontale og vertikale flater som representerer rottenes rute gjennom byen. Katter kan plasseres strategisk rundt denne stien.

- **Fiender langs en fast rute:** Rottene invaderer byen langs en forhåndsbestemt rute, og kattene brukes for å stoppe deres fremmarsj.

- **Poengsamling:** Spillere tjener poeng ved å eliminere rotter og kan bruke disse til å kjøpe eller oppgradere forsvarere.

- **Utfordringer i spillet:** Vanskelighetsgraden øker med introduksjon av raskere og sterkere rotter, samt spesielle typer som krever strategiske valg for å bekjempes.

- **Byen bygget opp av blokker:** Spillverdenen er inndelt i et rutenett der hver blokk enten kan huse en forsvarer eller fungerer som del av rottenes rute.

- **Oppgraderbare forsvarere:** Forsvarerne kan oppgraderes med poeng tjent for å effektivt håndtere stadig tøffere rottebølger.

#### Spillets Mål

Målet er å beskytte byen mot en rotteinvasjon ved å strategisk plassere og oppgradere katter langs invasjonsrutene. Spillerne må balansere ressursbruk for å takle de varierte truslene rottene utgjør.

### Oppgave A3.0: Velg og tilpass en prosess for teamet

#### Valg av Prosjektmetodikk

Vi har valgt å bruke Kanban og Scrum for prosjektstyring. Kanban hjelper oss å se og styre arbeidsflyten, og Scrum bruker vi for å strukturerer arbeidet vårt i korte perioder kalt sprinter. Denne kombinasjonen gir oss fleksibiliteten til å tilpasse oss endringer raskt og holder oss på sporet mot våre mål.

#### Teamdiskusjon og Metodevalg

Gjennom teamdiskusjoner har vi identifisert følgende metodiske elementer som essensielle for vårt prosjekt:

- Ukentlige stand-up møter for å sikre kontinuerlig kommunikasjon og problemidentifisering.
- Iterative utviklingssykluser (Sprints) fra Scrum for strukturert progresjon.
- Kanban-tavle for visuell sporing av oppgaver og arbeidsflyt.
- Regelmessige code reviews for å opprettholde høye standarder i kodebasen.
- Alltid minst en på teamet som går igjennom koden før vi merger med main

Vi vil involvere vår gruppeleder i denne diskusjonen for å identifisere mulige utfordringer og motta veiledning på beste praksiser.

#### Viktige Prosesselementer

Vår prosess vil omfatte:

- **Møter:** Ukentlige sprint planleggingsmøter, statusmøter, og stand-ups.
- **Kommunikasjon:** Discord for daglig kommunikasjon, og Git for å spore endringer og versjonskontroll.
- **Arbeidsfordeling:** God og hyppig kommunikasjon og flytende tildeling av arbeidsoppgaver fra Kanban-tavle.
- **Deling og oppbevaring av dokumenter:** Oppbevaring av kode/dokumentasjon vil foregå på Git.

#### Organisering av Teamet

Vi vil organisere vårt arbeid med følgende plan:

- **Møter:** Ukentlige stand-ups kl 09:00 (mandager), sprint planlegging hver andre mandag, og retrospektiv hver andre fredag.
- **Kommunikasjon mellom møter:** Discord kanal innad i teamet.
- **Arbeidsfordeling:** Roller vil bli tildelt basert på kompetanse og interesseområder, med mulighet for rotasjon for å fremme tverrfaglig læring.
- **Oppfølging av arbeid:** Ukesoppsummering og regelmessige gruppetimer med gruppeleder for å sikre at alle er på riktig spor.

Vi vil forsøke å forbedre oss og tilpasse vår prosess gjennom hele prosjektet basert på tilbakemeldinger og læringspunkter fra hver sprint.

#### Konklusjon

Dette dokumentet vil tjene som en levende guide gjennom utviklingen av Skadedyrkontrollørene. Det vil bli revidert og oppdatert for å reflektere vårt voksende forståelse av prosjektet og teamdynamikken. 

Vi bruker Scrum, og Kanban. Stand-up en gang i uken (mandag 09:00), retro annenhver fredag og planlegging av sprint annenhver mandag. Vi kommuniserer gjennom discord (her er også gruppeleder inkludert). Tar flittig i bruk Git, hvor alle endringer må gjennom review hos en annen på teamet. 


## Oppgave A3.1: Få oversikt over forventet produkt

#### Mål for Applikasjonen

Utvikle et spill som er engasjerende med et begrenset sett av funksjoner som definerer kjernen i spillopplevelsen. Dette inkluderer grunnleggende spillmekanikker som:

- **Spillerbevegelse** gjennom plassering og valg av katter langs stien.
- **Interaksjon med spillverdenen,** der spillerne må tenke strategisk om hvor de plasserer kattene for optimalt forsvar.
- **Poengsystem** som belønner spillere basert på antall rotter forhindret i å krysse stien.
- **Fiender i form av rotter** med ulike egenskaper eller hastigheter, som krever at spilleren tilpasser forsvaret etter hvert som spillet utvikler seg.
- **Klart definerte mål** for hvert nivå, som kan inkludere å stoppe et visst antall rotter, overleve gjennom bølger av angrep, eller oppnå en spesifikk score.

#### MVP Funksjoner

1. **Basis Spillområde**
   - En sti for rottebevegelse fra start til slutt.
   - Enkel bakgrunnsgrafikk for kattutplassering.

2. **Katt- og Rotte-Enheter**
   - **Katt-Enheter:** Minst to oppgraderbare kattetyper (f.eks., korthårs- og langhårskatter).
   - **Rotte-Enheter:** Minst én rotte som følger stien mot et mål.

3. **Enkel Spillopplevelse**
   - Funksjoner for å starte, akselerere, pause, og restarte spillet.
   - Penger opptjent per drept rotte for kjøp av flere katter.
   - Livstelling for spillerens gjenværende liv.

4. **Grunnleggende Brukergrensesnitt (UI)**
   - Enkel meny for spillstart.
   - Skjermdisplay for poeng og spillerliv.
   - Kjøpsmeny for flere katter.

### Brukerhistorier

#### Brukerhistorie 1: Se en sti hvor rotter kan bevege seg
Som en spiller ønsker jeg å kunne se en sti hvor rotter kan bevege seg fra start til slutt, slik at jeg vet hvor jeg skal plassere mine katter for å forsvare området.
- En tydelig definert sti er synlig på spillets skjerm.
- Stien har klart markerte start- og sluttpunkter.
- Bakgrunnsgrafikken indikerer hvor katter kan plasseres for forsvar.

#### Brukerhistorie 2: Plassere katter langs stien
Som en spiller ønsker jeg å kunne plassere katter langs stien, slik at jeg kan stoppe rotter fra å nå slutten av stien.
- Spilleren kan velge katter fra en meny og plassere dem langs stien.
- Plasserte katter vises tydelig på skjermen.
- Katter kan ikke plasseres utenfor den definerte stien.

#### Brukerhistorie 3: Velge mellom ulike kattetyper
Som en spiller ønsker jeg å velge mellom minst to typer katter, slik at jeg kan anvende ulike strategier.
- Minst to forskjellige kattetyper er tilgjengelige for spilleren.
- Hver kattetype har unike egenskaper eller angrepsmetoder.
- Spilleren kan enkelt velge hvilken kattetype de ønsker å plassere.

#### Brukerhistorie 4: Se rotter bevege seg langs stien
Som en spiller ønsker jeg å se rotter bevege seg langs stien mot målet, slik at jeg kan planlegge hvor jeg skal plassere mine forsvarsbrikker.
- Rotter vises tydelig mens de beveger seg langs stien.
- Bevegelsesmønsteret til rottene er forutsigbart nok til at spilleren kan planlegge forsvar.
- Rotter når et definert mål hvis de ikke stoppes.

#### Brukerhistorie 5: Kontrollere spillopplevelsen
Som en spiller ønsker jeg å kunne starte, akselerere, pause, og restarte spillet, slik at jeg har full kontroll over spillopplevelsen.
- Det er tydelige kontroller for å starte, akselerere spillets hastighet, pause, og restarte spillet.
- Disse kontrollene er enkelt tilgjengelige og intuitive å bruke.

#### Brukerhistorie 6: Tjene og bruke penger
Som en spiller ønsker jeg å tjene penger for hver rotte jeg dreper, slik at jeg kan kjøpe flere forsvarsbrikker/katter for å forbedre mitt forsvar.
- Spilleren tjener penger for hver drepte rotte.
- Opptjente penger kan brukes i spillet til å kjøpe nye katter eller oppgradere eksisterende.


#### Brukerhistorier i prioritert rekkefølge

1. Som en spiller ønsker jeg å se en sti hvor rotter kan bevege seg fra start til slutt, for å vite hvor jeg skal plassere mine forsvarere.

2. Som en spiller ønsker jeg å kunne plassere forsvarere langs stien for å stoppe rotter fra å nå målet.

3. Som en spiller ønsker jeg å velge mellom ulike forsvarertyper for å anvende diverse strategier.

4. Som en spiller ønsker jeg å se rotter bevege seg mot målet for å planlegge forsvarsstrategier.

5. Som en spiller, ønsker jeg å kunne starte, akselerere, pause, og restarte spillet, slik at jeg har full kontroll over spillopplevelsen.

6. Som en spiller, ønsker jeg å tjene penger for hver rotte jeg dreper, slik at jeg kan kjøpe flere forsvarsbrikker/katter for å forbedre mitt forsvar.

### Oppgave A4: Progresjonsrapport

Vi har dedikert tid til å forbedre vår forståelse av libGDX, og vi har besluttet å bruke dette rammeverket. Arkitekturen til spillet har blitt diskutert, og vi har implementert noen nøkkelklasser, som for eksempel en abstrakt `Rotte`-klasse, samt et model-view-controller-oppsett. Fremover vil prosessen involvere mer intensiv programmering nå som vi har en bedre oversikt og forståelse av både prosjektet og rammeverket.

### Oppgave A5: Oppsummering

#### Kort oppsummering av diskusjonen

Vi erkjenner viktigheten av effektiv tidsstyring og prioritering, spesielt gitt utfordringene vi har møtt med tidspress fra andre emner. Her er en oppsummering av vår diskusjon:

- **Hva som gikk bra:**
  - Etableringen av kommunikasjonskanaler og motivasjonen innad i teamet er stor.
  - Begynnelsen på å bruke Kanban-tavlen for å få en oversikt over oppgavene virker som en god strategi.

- **Hva som ikke fungerte helt som forventet:**
  - Tidspress fra andre emner har begrenset tiden tilgjengelig for prosjektet, noe som påvirket vår fremdrift. Vi må derfor allokere mer tid til prosjektet.

#### Vurdering og justering for neste iterasjon

Basert på retrospektivet, anerkjenner vi behovet for å være mer realistiske i vår planlegging og prioritering av oppgaver for den neste fasen av prosjektet. Vi vil fokusere på MVPen og justere vårt arbeidsomfang til å passe bedre med tiden vi realistisk kan dedikere til prosjektet.

#### Konklusjon

Diskusjonen har vært verdifullt for å identifisere både styrker og områder for forbedring i vårt prosjekt. Ved å endre vår strategi og innføre nye arbeidsmetoder, er vi optimistiske for bedre resultater i den kommende perioden.
