# Rapport – innlevering 2
**Team:** *Teamnavn* – *medlemmer*...

# Oppdatering på Prosjektstatus: Prioriterte Krav og Fremdrift

## Status på MVP
I lys av vårt pågående arbeid mot å utvikle og forbedre vårt spill, ønsker vi å gi en oppdatering på status for prioriterte krav, hvor langt vi har kommet i utviklingsprosessen, og de stegene vi har tatt siden vår siste oppdatering. Vår innsats har vært sentrert rundt utviklingen av brukerhistorier og realiseringen av Minimum Viable Product (MVP). Vi er nå nær ved å fullføre MVP, og her presenterer vi en detaljert gjennomgang av vår progresjon.

### Utførte Arbeid på MVPen siden siste Oppdatering

**Basis Spillområde**
- Implementert en definert sti for rottebevegelse som strekker seg fra startpunkt til mål.

**Katt- og Rotte-Enheter**
- Rotte-Enheter: Introdusert minst én rotte som navigerer langs stien mot et bestemt mål.

**Enkel Spillopplevelse**
- Utviklet funksjonalitet for å starte, akselerere, pause, og restarte spillet.
- Innført en mekanisme for opptjening av penger for hver drepte rotte, som videre kan brukes for kjøp av flere katter.
- Implementert en livstelling for å spore spillerens gjenværende liv.

**Grunnleggende Brukergrensesnitt (UI)**
- Utviklet en enkel meny for spillstart.
- Skjermdisplay implementert for visning av poeng og spillerliv.

### MVP-Elementer med Gjenstående Arbeid

**Basis Spillområde**
- Utvikling av enkel bakgrunnsgrafikk for plassering av katter står igjen, sammen med en tilhørende meny for dette formålet.

**Katt- og Rotte-Enheter**
- Katt-Enheter: Vi har introdusert flere kattetyper, men mangler fortsatt funksjonalitet for oppgradering av disse.

**Grunnleggende Brukergrensesnitt (UI)**
- En kjøpsmeny for anskaffelse av flere katter er fortsatt under utvikling.

### Fremtidige Prioriteter og Fokusområder
Med et klart blikk på å fullføre MVP-en, vil vårt neste skritt være å ferdigstille kjøpsmenyen for katter og implementere oppgraderingsmuligheter for kattene. Videre ønsker vi å gjøre spillet mer tilgjengelig og brukervennlig ved å innføre interaktive knapper som opereres med musen. Dette inkluderer endringer i funksjoner for å starte, akselerere, pause, og navigere spillmenyer.

For å styrke vår testing og kvalitetssikring, er det også planlagt utvikling av ytterligere tester. I tillegg vil introduksjonen av en "Rat Factory"-klasse, designet for å generere rotter i bølger basert på spillerens nivå, være et sentralt fokus.

## Brukerhistorier for krav vi jobber med/skal begynne på snarest

### CatMenu
**Brukerhistorie for CatMenu:** 
Som en spiller ønsker jeg å velge mellom minst to typer katter fra en brukervennlig meny som jeg kan plassere på strategiske steder på kartet. 

**Akseptansekriterier:**
- **Tilgjengelighet av Kattetyper:** Spillet må tilby minst to forskjellige kattetyper som spilleren kan velge mellom. Hver kattetype skal ha distinkte egenskaper eller angrepsmetoder som påvirker spillets strategi.

**Arbeidsoppgaver for tilgjengelighet av kattetyper:**
- Utvikle en abstrakte klasser som arver en katteklasse. Hver abstrakte klasse skal inneholde styrken til katten, fireraten, rekkevidden og bilde som representerer katten.

- **Brukervennlig Meny:** Spillet skal inneholde en intuitiv og lett
navigerbar meny der spilleren kan velge blant de tilgjengelige kattetypene. Menyen bør være tilgjengelig i spillets hovedgrensesnitt uten å forstyrre den pågående spillopplevelsen.

**Arbeidsoppgaver for brukervennlig meny:**
- Lage en grunnleggende spillmekanikk for å plassere forsvarsenheter (katter) på kartet.
- Utvikle et grensesnitt for valg og plassering av kattetyper.

- **Visuell Feedback:** Når en katt er valgt og skal bli plassert på kartet, skal spillet gi visuell feedback for å bekrefte valget og plasseringen.

**Arbeidsoppgaver for visuell feedback:**
- Integrere sjekk om en katt er valgt eller ikke, vise på kartet hvor den kan stå når spilleren drar den over spillerbrettet. Katten skal ikke bli plassert før katten er sluppet.

- **Kostnadsbehandling:** Spillet skal automatisk trekke den relevante kostnaden for valgt kattetype fra spillerens tilgjengelige lommebok. Dersom spilleren ikke har råd, skal valget av kattetype være deaktivert eller mota en melding om at spilleren ikke har råd.

**Arbeidsoppgaver for kostnadsbehandling:**
- Knytte mynter opptjent for å sjekke om man har råd til hver katt, om man ikke har råd skal man få en melding om at man ikke har råd.

### Katter som kan oppgraderes
**Brukerhistorie for Katter som kan oppgraderes:** 
Som en spiller ønsker jeg å kunne oppgradere mine katter slik at deres rekkevidde, styrke, og skuddrate kan forbedres, for å effektivt håndtere stadig vanskeligere bølger av angripende rotter.

**Akseptansekriterier:**
- **Tilgjengelighet for Oppgradering:** Spilleren skal kunne velge katter som allerede er plassert på kartet for oppgradering.

**Arbeidsoppgaver for Tilgjengelighet for Oppgradering:**
- Fastsett en kostnadsstruktur for hver type oppgradering (rekkevidde, styrke, skuddrate) for hver kattetype. Dette inkluderer å bestemme startkostnader og hvordan kostnadene øker med hver oppgradering.
- Utvikle en funksjon som sjekker om spilleren har nok ressurser til å utføre ønsket oppgradering før transaksjonen godkjennes.
- Oppdater spillerens ressurser (trekk fra) umiddelbart etter at en oppgradering er gjennomført.
- Når katten er oppgradert endres bildet av katten til noe annet for å reflektere oppgraderingen.

- **Oppgraderingsalternativer:** Når en katt er valgt for oppgradering, skal spilleren presenteres med oppgraderingsalternativer som inkluderer forbedringer av rekkevidde, styrke (skade per angrep), og skuddrate (angrep per sekund).

**Arbeidsoppgaver for Oppgraderingsalternativer:**
- Utvikle en oppgraderingsmeny som viser kostnadene for hver oppgradering.
- Legge til en grense på oppgraderinger så man ikke kan gjøre kattene for sterke.

- **Kostnadshåndtering:** Hver oppgradering skal ha en kostnad som trekkes fra spillerens lommebok. Spilleren skal ikke kunne gjennomføre en oppgradering uten tilstrekkelige ressurser.

**Arbeidsoppgaver for Kostnadshåndtering:**
- Knytte mynter opptjent for å sjekke om man har råd til hver oppgradering, om man ikke har råd skal man få en melding om at man ikke har råd.

## Vi skal starte på:
- Knapper som kan trykkes på med mus.
- Rottebølger

### Brukerhistorie for Rotte
