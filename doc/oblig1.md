# Rapport – Innlevering 1

## Team: Skadedyrkontrollørene_inc (Gruppe 1)
- Jonathan Christensen
- Axel Lundeby
- Theodor Nissen-Meyer
- Jacob Foss
  
<br>

## Oppgave A1: 

<br>


## Oppgave A2: Konsept

<br>

#### **Spillmekanikker:**

[Bæsh](bloons.jpeg)

Inspirert av [BloonsTD](https://no.wikipedia.org/wiki/Bloons_Tower_Defense), består *Skadedyrkontrollørene* av følgende spillmekanikker:


- **Forsvarere som kan plasseres:** Spillere kan utplassere ulike kattetyper langs ruten til rotter for å forsvare byen. Hver katt har unike evner som prosjektilangrep, nærkamp, eller spesialeffekter som å fryse eller bremse ned fiender.

- **Todimensjonal byverden:** En spillverden med horisontale og vertikale flater som representerer rottenes rute gjennom byen. Katter kan plasseres strategisk rundt denne stien.

- **Fiender langs en fast rute:** Rottene invaderer byen langs en forhåndsbestemt rute, og kattene brukes for å stoppe deres fremmarsj.

- **Poengsamling:** Spillere tjener poeng ved å eliminere rotter og kan bruke disse til å kjøpe eller oppgradere forsvarere.

- **Utfordringer i spillet:** Vanskelighetsgraden øker med introduksjon av raskere og sterkere rotter, samt spesielle typer som krever strategiske valg for å bekjempes.

- **Byen bygget opp av blokker:** Spillverdenen er inndelt i et rutenett der hver blokk enten kan huse en forsvarer eller fungerer som del av rottenes rute.

- **Oppgraderbare forsvarere:** Forsvarerne kan oppgraderes med poeng tjent for å effektivt håndtere stadig tøffere rottebølger.

#### **Spillets Mål:**

Målet er å beskytte byen mot en rotteinvasjon ved å strategisk plassere og oppgradere katter langs invasjonsrutene. Spillerne må balansere ressursbruk for å takle de varierte truslene rottene utgjør.

<br>

## Oppgave A3.0: Velg og tilpass en prosess for teamet

<br>

### **Valg av Prosjektmetodikk**

Vi har valgt å bruke Kanban og Scrum for prosjektstyring. Kanban hjelper oss å se og styre arbeidsflyten, mens Scrum strukturerer arbeidet vårt i korte perioder kalt sprinter. Denne kombinasjonen gir oss fleksibiliteten til å tilpasse oss endringer raskt og holder oss på sporet mot våre mål.

### **Teamdiskusjon og Metodevalg**

Gjennom teamdiskusjoner har vi identifisert følgende metodiske elementer som essensielle for vårt prosjekt:

- Ukentlige stand-up møter for å sikre kontinuerlig kommunikasjon og problemidentifisering.
- Iterative utviklingssykluser (Sprints) fra Scrum for strukturert progresjon.
- Kanban-tavle for visuell sporing av oppgaver og arbeidsflyt.
- Regelmessige code reviews for å opprettholde høye standarder i kodebasen.
- Alltid minst en på teamet som går igjennom koden før vi «merger»

Vi vil involvere vår gruppeleder i denne diskusjonen for å identifisere mulige utfordringer og motta veiledning på beste praksiser.

### **Viktige Prosesselementer**

Vår prosess vil omfatte:

- **Møter:** Ukentlige sprint planleggingsmøter, statusmøter, og stand-ups.
- **Kommunikasjon:** Discord for daglig kommunikasjon, og Git for å spore endringer og versjonskontroll.
- **Arbeidsfordeling:** God og hyppig kommunikasjon og flytende tildeling av arbeidsoppgaver fra Kanban tavle.
- **Deling og oppbevaring av dokumenter:** Oppbevaring av kode vil foregå på Git.

### **Organisering av Teamet**

Vi vil organisere vårt arbeid med følgende plan:

- **Møter:** Ukentlige stand-ups kl 09:00, sprint planlegging hver andre mandag, og retrospektiv hver andre fredag.
- **Kommunikasjon mellom møter:** Discord kanal innad i teamet.
- **Arbeidsfordeling:** Roller vil bli tildelt basert på kompetanse og interesseområder, med mulighet for rotasjon for å fremme tverrfaglig læring.
- **Oppfølging av arbeid:** Ukesoppsummering og regelmessige gruppetimer med gruppeleder for å sikre at alle er på riktig spor.

Vi vil forsøke å forbedre oss og tilpasse vår prosess gjennom hele prosjektet basert på tilbakemeldinger og læringspunkter fra hver sprint.

### **Konklusjon**

Dette dokumentet vil tjene som en levende guide gjennom utviklingen av Skadedyrkontrollørene. Det vil bli revidert og oppdatert for å reflektere vårt voksende forståelse av prosjektet og teamdynamikken.

<br>

## Oppgave A3.1: Velg og tilpass en prosess for teamet

<br>

#### **Mål for Applikasjonen**

- Utvikle et spill som er engasjerende med et begrenset sett av funksjoner som definerer kjernen i spillopplevelsen.
 **Inkludere grunnleggende spillmekanikker** som omfatter:
  - Spillerbevegelse gjennom plassering og valg av katter langs stien.
  - Interaksjon med spillverdenen, der spillerne må tenke strategisk om hvor de plasserer kattene for optimal forsvar.
  - Poengsystem som belønner spillere basert på antall rotter forhindret i å krysse stien.
  - Fiender i form av rotter med ulike egenskaper eller hastigheter, som krever at spilleren tilpasser forsvaret etter hvert som spillet utvikler seg.
  - Klart definerte mål for hvert nivå, som kan inkludere å stoppe et visst antall rotter, overleve gjennom bølger av angrep, eller oppnå en spesifikk score.


#### **MVP Funksjoner**

1. **Basis Spillområde**
   - En sti for rottebevegelse fra start til slutt.
   - Enkel bakgrunnsgrafikk for kattutplassering.

2. **Katt- og Rotte-Enheter**
   - **Katt-Enheter:** Minst to oppgraderbare kattetyper (f.eks. korthårs- og langhårskatter).
   - **Rotte-Enheter:** Minst én rotte som følger stien mot et mål.

3. **Enkel Spillopplevelse**
   - Funksjoner for å starte, akselerere, pause, og restarte spillet.
   - Penger opptjent per drept rotte for kjøp av flere katter.
   - Livstelling for spillerens gjenværende liv.

4. **Grunnleggende Brukergrensesnitt (UI)**
   - Enkel meny for spillstart.
   - Skjermdisplay for poeng og spillerliv.
   - Kjøpsmeny for flere katter.

<br>

### **Brukerhistorier:**


### Brukerhistorie 1: *Se en sti hvor rotter kan bevege seg*
**Beskrivelse:** Som en spiller ønsker jeg å kunne se en sti hvor rotter kan bevege seg fra start til slutt, slik at jeg vet hvor jeg skal plassere mine katter for å forsvare området.

**Akseptansekriterier:**
- En tydelig definert sti er synlig på spillets skjerm.
- Stien har klart markerte start- og sluttpunkter.
- Bakgrunnsgrafikken indikerer hvor katter kan plasseres for forsvar.

<br>

### Brukerhistorie 2: *Plassere katter langs stien*
**Beskrivelse:** Som en spiller ønsker jeg å kunne plassere katter langs stien, slik at jeg kan stoppe rotter fra å nå slutten av stien.

**Akseptansekriterier:**
- Spilleren kan velge katter fra en meny og plassere dem langs stien.
- Plasserte katter vises tydelig på skjermen.
- Katter kan ikke plasseres utenfor den definerte stien.

<br>

### Brukerhistorie 3: *Velge mellom ulike kattetyper*
**Beskrivelse:** Som en spiller ønsker jeg å velge mellom minst to typer katter, slik at jeg kan anvende ulike strategier.

**Akseptansekriterier:**
- Minst to forskjellige kattetyper er tilgjengelige for spilleren.
- Hver kattetype har unike egenskaper eller angrepsmetoder.
- Spilleren kan enkelt velge hvilken kattetype de ønsker å plassere.

<br>

### Brukerhistorie 4: *Se rotter bevege seg langs stien*
**Beskrivelse:** Som en spiller ønsker jeg å se rotter bevege seg langs stien mot målet, slik at jeg kan planlegge hvor jeg skal plassere mine forsvarsbrikker.

**Akseptansekriterier:**
- Rotter vises tydelig mens de beveger seg langs stien.
- Bevegelsesmønsteret til rottene er forutsigbart nok til at spilleren kan planlegge forsvar.
- Rotter når et definert mål hvis de ikke stoppes.

<br>

### Brukerhistorie 5: *Kontrollere spillopplevelsen*
**Beskrivelse:** Som en spiller ønsker jeg å kunne starte, akselerere, pause, og restarte spillet, slik at jeg har full kontroll over spillopplevelsen.

**Akseptansekriterier:**
- Det er tydelige kontroller for å starte, akselerere spillets hastighet, pause, og restarte spillet.
- Disse kontrollene er enkelt tilgjengelige og intuitive å bruke.

<br>

### Brukerhistorie 6: *Tjene og bruke penger*
**Beskrivelse:** Som en spiller ønsker jeg å tjene penger for hver rotte jeg dreper, slik at jeg kan kjøpe flere forsvarsbrikker/katter for å forbedre mitt forsvar.

**Akseptansekriterier:**
- Spilleren tjener penger for hver drepte rotte.
- Opptjente penger kan brukes i spillet til å kjøpe nye katter eller oppgradere eksisterende.

<br>

#### Brukerhistorier i prioritert rekkefølge

1. Som en spiller ønsker jeg å se en sti hvor rotter kan bevege seg fra start til slutt, for å vite hvor jeg skal plassere mine forsvarere.

2. Som en spiller ønsker jeg å kunne plassere forsvarere langs stien for å stoppe rotter fra å nå målet.

3. Som en spiller ønsker jeg å velge mellom ulike forsvarertyper for å anvende diverse strategier.

4. Som en spiller ønsker jeg å se rotter bevege seg mot målet for å planlegge forsvarsstrategier.


5. Som en spiller, ønsker jeg å kunne starte, akselerere, pause, og restarte spillet, slik at jeg har full kontroll over spillopplevelsen.

6. Som en spiller, ønsker jeg å tjene penger for hver rotte jeg dreper, slik at jeg kan kjøpe flere forsvarsbrikker/katter for å forbedre mitt forsvar.





