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

## Funksjoner vi skal starte på:
- Knapper som kan trykkes på med mus.
- Rottebølger

### Brukerhistorie for Rottebølger:
Som spiller ønsker jeg bølger av angripende rotter, slik at spillet blir mer utfordrende for meg gjennom progressiv økning i vanskelighetsgrad.

**Akseptansekriterier:**

- **Definering av Bølger:** Hver bølge skal bestå av et forhåndsdefinert antall rotter, hvor antallet øker eller sammensetningen endres i påfølgende bølger for å gradvis øke vanskelighetsgraden.

**Arbeidsoppgaver for Definering av Bølger:**
- Design av Bølgestruktur: Utarbeid en grunnleggende struktur for hver bølge, inkludert antall rotter og typen rotter (for eksempel, standard, rask, pansret) som skal inngå.
- Opprettelse av Bølgekonfigurasjoner: Definer spesifikke konfigurasjoner for hver bølge, inkludert det eksakte antallet og sammensetningen av rotter. Dette kan variere fra enkle til mer komplekse kombinasjoner etter hvert som spillet skrider frem.

- **Pause mellom Bølger:** Spillet skal settes på pause mellom hver bølge som gir spilleren tid til å forberede forsvar gjennom å plassere eller oppgradere katter.

**Arbeidsoppgaver for Pause mellom Bølger:**
- Koble når alle rotter er døde metoden til at spillet går i pause. Når spiller går i play modus skal ny bølge komme med flere rotter.

### Brukerhistorie for Knapper som kan trykkes på med mus:
Som en spiller ønsker jeg å bruke musen for å interagere med spillets brukergrensesnitt, slik at jeg enkelt kan styre spillet uten å være avhengig av tastaturet.

**Akseptansekriterier:**
- Spillet skal tilby mus-klikkbare knapper for alle viktige funksjoner som start, pause, akselerer, og navigering i menyen.
- Brukergrensesnittet skal være intuitivt og tilrettelegge for enkel navigering og interaksjon med musen.

**Arbeidsoppgaver for Knapper som kan trykkes på med mus:**
- Implementere interaktive knapper i spillets grensesnitt for alle primære funksjoner.
- Sikre at knappene gir tilstrekkelig visuell feedback når de er interagert med, for å indikere aktivitet.
- Tilpasse spillets kontroller til å støtte mus-interaksjon fullt ut, inkludert valg og plassering av katter, samt navigasjon gjennom spillets meny.

Ved å følge disse brukerhistoriene og implementere de tilhørende akseptansekriteriene og arbeidsoppgavene, sikter vi mot å forbedre spillopplevelsen ved å gjøre den mer tilgjengelig, engasjerende og utfordrende for spilleren.


## Oversikt over Kjente Bugs

Vi står overfor to hovedproblemer i vårt spillutviklingsprosjekt som påvirker spillopplevelsen og programmets stabilitet. Disse feilene er detaljert nedenfor, sammen med en innledende vurdering av potensielle løsninger.

### 1. Feil i Angrepslogikken til "Shotgun Cat"

**Problemstilling:**
- "Shotgun Cat", en enhet designet for å angripe flere mål samtidig, fungerer ikke som forventet. Den korrekte logikken skal tillate variabelt antall angrep basert på antallet mål innenfor rekkevidde:
  - **Tre eller flere mål**: Hvert mål angripes én gang.
  - **To mål**: Én mål angripes to ganger, og den andre én gang.
  - **Ett mål**: Det enkelte målet angripes tre ganger.
- Feilen manifesterer seg ved at "Shotgun Cat" angriper alle rotter innenfor rekkevidde tre ganger, uavhengig av antallet tilgjengelige mål.

**Potensiell løsning:**
- Implementering av justerbar skuddrate (fire rate) for "Shotgun Cat" forventes å korrigere denne feilen.

### 2. Krasj ved Rotters Utmarsj fra Spilleområdet

**Problemstilling:**
- Spillet krasjer når rotter beveger seg ut av det definerte spillområdet, noe som indikerer en svakhet i den nåværende implementasjonen av rottens bevegelseslogikk.

**Potensiell løsning:**
- Vurdering av implementering av en mer avansert bevegelsesmekanisme ved hjelp av splines for å tillate en mer kontrollert og visuelt tiltalende bevegelse av rotter gjennom spilleområdet. Dette er forventet å redusere risikoen for krasj relatert til deres bevegelse utenfor kartgrensene.



