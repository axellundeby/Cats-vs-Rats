# Rapport – innlevering 4


## Referat 15. april 2024
**Tilstede:** Alle 

#### Hva har skjedd siden forrige møte?
- Vi er fornøyde med resultatet vi leverte inn som Oblig3. Ikke skjedd noe siden det.

#### Hva skal vi gjøre til neste møte?
- Fortsette med det vi vet at vi ikke har implementert. Det vil si at vi skal implementere flere tester, lyd, fikse kjøpsmenyen, fikse at man kan oppdatere katter hver for seg. Applikasjonen opptar mye minne, og krasjer. Dette må vi løse på et vis. Resterende grafikk må inn.

## Referat 22. april 2024
**Tilstede:** Alle 

#### Hva har skjedd siden forrige møte?
- Løsning for lyd er sett på, ikke ferdig implementert. Kjøpsmeny er oppdatert, delvis implementert. Tester er startet på, vi må finne en måte å få test-prosentcoverage for applikasjonen vår.

#### Hva skal vi gjøre til neste møte?
- Endre layout, få inn grafikk, og finne ut av hvorfor applikasjonen opptar så mye minne. Vi tror det handler om at vi henter «nye» bilder hele tiden. Vi må gjøre en vurdering på om vi trenger prosjektiler? Vi har katte-animasjoner som viser at de angriper, og da dropper vi sannsynligvis prosjektil.

## Referat 29. april 2024
**Tilstede:** Alle 

#### Hva har skjedd siden forrige møte?
- Vi har endret layout til «landskapsmodus», og fått inn all grafikk unntatt hjelpemenyen - Den blir ferdig til fredag 26.04. Det samme blir alt av lyd. Tester er startet på og vi har kommet et godt stykke på vei. Vi har også funnet ut hvordan vi kan sjekke prosentcoverage for tester. Samt er kjøpsmenyen implementert slik den skal være, og det er nå mulig å velge enkelte katter som skal oppgraderes. Vi har endret bildebruken slik at appen ikke opptar mye minne.

#### Hva skal vi gjøre til neste møte?
- Vi har fått tilbakemelding på forrige innlevering, og må jobbe mot å få full pott før siste innlevering. Basert på tilbakemeldingen har vi følgende TO DO-liste. Vi føler vi ligger godt an til å klare dette.
  - **TO DO:**
    - Få inn forside/hjelpeside - Denne er allerede implementert
    - Lyd koblet til hendelser - Også implementert
    - Objectfabrikkene våre er ikke optimale - Dette er under arbeid
    - Tester er automatiske, og trenger litt høyere prosent for å nå kravet til tester - Dette er under arbeid, snart ferdigstilt
    - Gå over dokumentasjon - Dette er delvis gjort
    - Forbedre Rat.java - Dette er gjort

## Referat 1. mai 2024
**Tilstede:** Alle 

#### Hva har skjedd siden forrige møte?
- Vi har fortsatt på TO DO-listen, og jobbet med det som kreves for å få full score. Tatt bort det som er implementert fra TO DO. Det nærmer seg sluttresultat

#### Hva skal vi gjøre til neste møte?
- Fullføre applikasjonen.
  - **TO DO:**
    - Objectfabrikkene våre er ikke optimale - Veldig snart i boks 
    - Tester er automatiske, og trenger litt høyere prosent for å nå kravet til tester - Nærmest Ferdig
    - Gå over dokumentasjon - 95% gjennomgått

## Team Roles and Dynamics

**Gruppedynamikk:**

- **Lead Developer (Theodor):** Har det overordnede ansvaret for kodebasen, innsikt i prosjektets tekniske aspekter, og bistår andre utviklere ved behov.
- **Developer:** Alle teammedlemmer har denne rollen og bidrar med utviklingen av applikasjonen.
- **Git Ansvarlig (Jonathan):** Spesialisert i bruk av Git, denne rollen innebærer å opprettholde repoet, lage backups og sikre at det er oppdatert.
- **Grafikk (Axel):** Ansvarlig for å sikre at spillets grafikk stemmer overens med de ønskede visuelle målene.
- **Testansvarlig (Jacob):** Fokuserer på utvikling og implementering av tester for å sikre at koden fungerer som forventet.

Vi har en god dynamikk innad i gruppen, og vi har ikke møtt på utfordringer eller uenigheter som har krever endring av vår struktur. Vi holder hverandre løpende informert og oppdatert om prosjektets status, mye takket være tiden vi tilbringer sammen på lesesalen. Det er lett å informere og spørre hverandre hvis det er noe man lurer på. De ukentlige standup-møtene er fine å ha for å opprettholde fremgangen i prosjektet, og det spiller fortsatt en viktig rolle for å oppsummere og forberede oss på den kommende uken og hvilke oppgaver man skal gjennomføre. Sammen med Kanban-boardet får vi god oversikt, og hjelper oss å jobbe strukturert med relevante oppgaver frem mot siste innlevering. Her er boardet: [Trello Board](https://trello.com/b/YKJUgETU)

**Forbedringspunkter:**
- Vi er fornøyde med resultatet, men vi har fortsatt et par forbedringspunkter som vi skulle ønske spillet hadde:
  - **Prosjektiler:** Få prosjektil til å fungere slik vi ønsker.
  - **Drag-and-drop:** Få prosjektil til å fungere slik vi ønsker.



## Krav og Spesifikasjoner

Her er en oppdatering på vår prosjektstatus, inkludert prioriterte krav, fremdrift og hva vi har gjort siden forrige rapport.

### MVP-status og gjennomførte oppgaver

Siden vår forrige rapport har vi gjennomført flere viktige forbedringer. Her er en oppsummering:

- **Lydeffekter:** Implementert unike lydeffekter for hver kattetype når de angriper, kjøpes eller oppgraderes eller når spilleren mister liv av rotter.
- **Abstraksjon:** 
  - De interatktive knappene for menyen og oppgraderingene er nå abstrakte ved bruk av en button, noe som gjør det enklere å legge til nye knapper og funksjonaliteter. Absraksjonen har også hjulpet på den totale minnebruken til spillet.
  - Kattene har nå blitt mer abstrakte ved bruk av en riktig implementasjon av en abstrakt 
- **AssetManager:** Opprettet en AssetManager for bedre håndtering av spillets ressurser, som bidrar til effektivitet. Dermed har spillet nå en mer strukturert og organisert ressursbehandling. Blandingen av abstraksjonsknappene og AssetManager har forbedret spillets ytelse og minnebruk betraktlig. 
- **Hjelpemeny:** Laget en hjelpemeny for å gi spillere veiledning og instruksjoner om spillets maksiner og knapper.
- **Kattegrafikk:** Forbedret grafikken for kattene for å gjøre dem mer visuelt tiltalende og tydelige.
- **Kjøpsmenyforbedringer:** Oppdatert kjøpsmenyen for å lettere plassere kattene på kartet og forbedre brukervennligheten.
- **Frysing av katter:** Implementert funksjonalitet for at kattene kan fryses midlertidig under spillet.
- **Hovedsidegrafikk:** Lagt til grafiske ny bakgrunnsbilde og forbedret brukergrensesnittet på hovedsiden med start og hjelp-knapper.
- **Spline-bevegelse for katter:** Endret kattenes bevegelsesmekanikk for å bruke spline, noe som gir jevnere og mer naturlig bevegelse på kartet.
- **Oppgraderingsmuligheter for alle katter:** Fullført implementering av oppgraderingsmuligheter for hver enkelt katt, slik at spillerne kan tilpasse og styrke kattene sine etter behov.
- **Tester:** Utviklet flere tester for å forbedre kvalitetssikringen og sikre at spillet fungerer som forventet under ulike forhold.


### MVP-funksjonaliteter med gjenværende arbeid

Selv om vi har gjort betydelige fremskritt, er det fortsatt noen funksjonaliteter som mangler fullførelse:

- **Prosjektilmekanikk:** Vi jobber fortsatt med å fikse feilene i prosjektilmekanikken for å sikre at prosjektilene fungerer som forventet og gir en jevn spillopplevelse.
- **Drag-and-Drop:** Vi har ennå ikke implementert drag-and-drop-funksjonalitet for å plassere kattene på kartet. Dette vil være en viktig funksjon for å forbedre brukervennligheten.
## Fremtidige prioriteter og fokusområder

Videre vil vårt fokus være på å fullføre disse gjenværende oppgavene og forbedre spillopplevelsen ytterligere:

- **Fullføre prosjektilmekanikken:** Fikse feilene i prosjektilmekanikken for å sikre jevn prosjektilavfyring.
  - **Brukerhistorie:** Som spiller ønsker jeg at prosjektilene skal avfyres jevn for å sikre effektiv bekjempelse av rotter.
  - **Akseptansekriterier:**
    - Prosesselen skal avfyres når spilleren klikker på en katt.
    - Prosesselen skal treffe rotter innenfor rekkevidde.
    - Prosesselen skal avfyres med riktig retning og hastighet.
  - **Arbeidsoppgaver:**
    1. Identifisere og fikse feilene i eksisterende prosjektilmekanikk.
    2. Implementere en jevn prosjektilavfyring.
    3. Utføre testing for å verifisere funksjonaliteten.

- **Drag-and-Drop:** Gjøre små justeringer og forbedringer i grafikk, brukergrensesnitt og generell spillopplevelse for et mer naturlig og intuitivt spill.
  - **Brukerhistorie:** Som spiller ønsker jeg en intuitiv og brukervennlig drag-and-drop-funksjonalitet for å plassere katter.
  - **Akseptansekriterier:**
    - Spilleren skal kunne dra og slippe katter fra kjøpsmenyen til ønskede plasseringer på kartet.
    - Kattene skal plasseres på kartet i henhold til spillerens handlinger.
    - Spilleren skal kunne justere plasseringen av kattene etter behov.
  - **Arbeidsoppgaver:**
    1. Implementere drag-and-drop-funksjonalitet i brukergrensesnittet.
    2. Koble drag-and-drop-handlinger til riktig spilloppførsel.
    3. Utføre brukertesting for å validere funksjonaliteten.

- **Testing:** Utvikle og implementere flere tester for å sikre stabilitet og kvalitet gjennom hele spillet.
  - **Brukerhistorie:** Som utvikler ønsker jeg et  sett med tester for å sikre stabilitet og kvalitet i spillet.
  - **Akseptansekriterier:**
    - Testdekning bør omfatte alle viktige funksjoner og interaksjoner i spillet.
    - Testene skal kunne kjøres automatisk som en del av kontinuerlig integrasjon.
    - Testene skal identifisere og rapportere eventuelle feil eller mangler i spillet.
  - **Arbeidsoppgaver:**
    1. Identifisere kritiske områder som krever testing.
    2. Utvikle testtilfeller og scenarier for å dekke disse områdene.
    3. Implementere testene og integrere dem i kontinuerlig integrasjonsprosessen.

- **Lyd:** Fortsette å legge til lydeffekter for å forbedre spillopplevelsen og gi spillere et mer engasjerende spill.
  - **Brukerhistorie:** Som spiller ønsker jeg varierte og passende lyder for å forbedre den generelle opplevelsen av spillet.
  - **Akseptansekriterier:**
    - Lydeffekter skal være av høy kvalitet og morsomme å høre på.
    - Lydenivået skal være balansert og ikke forstyrre spilleren.
    - Lydeffekter skal legge til atmosfære og forbedre følelsen av spillverdenen.
  - **Arbeidsoppgaver:**
    1. Identifisere områder i spillet der lydeffekter kan forbedre opplevelsen.
    2. Velge passende lydeffekter for de forskjellige hendelsene i spillet.
    3. Implementere lydeffekter og justere lydnivået etter behov.

### Prioriterte oppgaver i synkende rekkefølge

Basert på våre nåværende behov og fremdrift, har vi prioritert oppgavene slik:

1. **Fullføre prosjektilmekanikken**
2. **Drag-and-Drop**
3. **Testing**
4. **Lyd**

### Oversikt over kjente bugs

Vi har for øyeblikket ingen identifiserte bugs i spillet. 

# Produkt og Kode

## Utbedring av Feil siden Sist

Vi har rettet og forbedret følgende punkter siden forrige rapport:

- **Lydeffekter:** Implementert unike lydeffekter for hver kattetype når de angriper, kjøpes eller oppgraderes, samt når spilleren mister liv av rotter.
- **Abstraksjon:**
  - Introduserte en abstraksjon for interaktive knapper og kattene for å forbedre minnebruken og legge til nye funksjonaliteter enklere.
- **AssetManager:** Opprettet en AssetManager for bedre håndtering av spillets ressurser, forbedret effektiviteten og organiseringen av ressursbehandling.
- **Hjelpemeny:** Laget en hjelpemeny for å gi spillere veiledning og instruksjoner om spillets mekanikker og knapper.
- **Kattegrafikk:** Forbedret grafikken for kattene for å gjøre dem mer visuelt tiltalende og tydelige.
- **Kjøpsmenyforbedringer:** Oppdatert kjøpsmenyen for enklere plassering av kattene på kartet og forbedret brukervennlighet.
- **Frysing av Katter:** Implementert funksjonalitet for midlertidig frysing av kattene under spillet.
- **Hovedsidegrafikk:** Lagt til nytt bakgrunnsbilde og forbedret brukergrensesnittet på hovedsiden med start- og hjelp-knapper.
- **Spline-bevegelse for Katter:** Endret kattenes bevegelsesmekanikk for å bruke spline, noe som gir jevnere og mer naturlig bevegelse på kartet.
- **Oppgraderingsmuligheter for alle Katter:** Fullført implementering av oppgraderingsmuligheter for hver enkelt katt.
- **Tester:** Utviklet flere tester for å forbedre kvalitetssikringen og sikre at spillet fungerer som forventet under ulike forhold.

### README.md Dokumentasjon

For å lette bygging, testing og kjøring av koden vår, har vi oppdatert README.md med detaljert informasjon om prosjektet. Under vurdering vil koden også bli brukertestet.

### Plattformkompatibilitet

Prosjektet vårt kan nå bygges, testes og kjøres på Linux, Windows og OS X. Vi har sikret at stier til grafikk og lyd matcher eksakt på alle plattformer.


### Kodekvalitet og Testdekning

Vi har lagt stor vekt på kodekvalitet og testdekning. Automatiske tester dekker forretningslogikken i systemet, og vi har også utført manuelle tester basert på akseptansekriteriene.

### Statiske Analyseverktøy

Vi har benyttet SpotBugs og SonarQube for å identifisere feil vi kanskje ikke har tenkt på tidligere. Dette har bidratt til å forbedre kvaliteten på koden vår.

### Prioriterte Oppgaver

Basert på våre nåværende behov og fremdrift, har vi prioritert oppgavene slik:

1. **Fullføre prosjektilmekanikken**
2. **Drag-and-Drop**
3. **Testing**
4. **Lyd**

Utførte oppgaver er ferdige, og vi har fjernet filer og kode som ikke er relevant for prosjektet.

### Klassediagram

Vi har utarbeidet et klassediagram som viser strukturen til de viktigste klassene i prosjektet.
![Klassediagram](/src/main/resources/buttons_game/angryCat.png)





