# Rapport – innlevering 2

#### Team: Skadedyrkontrollørene_inc (Gruppe 1)

- Jonathan Christensen 
- Axel Lundeby 
- Theodor Nissen-Meyer 
- Jacob Foss 

# Prosjektrapport

## Referater fra Standup-møter


### Referat 19.02.24
Første standup siden første innlevering, samt start på sprint - alle var tilstede.

- **Hva sitter vi igjen med av inntrykk?**
    - Fortsatt usikkerhet rundt libGDX, men fornøyd med at vi har kommet i gang. Vi føler at det viktigste til nå var å prøve seg litt frem, og sette seg inn i det ukjente så godt som mulig. Vi vet at jobben som skal gjøres er stor, men vi må prøve å bygge sten for sten.

- **Hva er veien videre?**
    - Vi har satt noen krav til MVP, så dette blir prioritet frem mot innlevering 8. mars. Vi fordeler oppgaver, og man jobber i egne branches i git. Samt har vi lyst til å prøve par-programmering på noen punkter for å sjekke ut hvordan dette fungerer. Vi vet ikke hva som blir vanskeligst å jobbe med fremover, men håper på å ha en viss forståelse av hva som er mest utfordrende i løpet av første halvdel av sprinten. Fordeling av oppgaver frem mot MVP er som følger: 

        - **Basis Spillområde:**
            - En sti for rottebevegelse fra start til slutt - Theodor/Jonathan
            - Enkel bakgrunnsgrafikk for kattutplassering - Theodor

        - **Katt- og Rotte-Enheter:**
            - Katt-Enheter: Minst to oppgraderbare kattetyper (f.eks., korthårs- og langhårskatter) - Axel/Theodor
            - Rotte-Enheter: Minst én rotte som følger stien mot et mål - Jonathan

        - **Enkel Spillopplevelse:**
            - Funksjoner for å starte, akselerere, pause, og restarte spillet. - Jonathan/Jacob
            - Penger opptjent per drept rotte for kjøp av flere katter - Axel
            - Livstelling for spillerens gjenværende liv. -Jacob/Axel

        - **Grunnleggende Brukergrensesnitt (UI):**
            - Enkel meny for spillstart - Jacob
            - Skjermdisplay for poeng og spillerliv - Axel/Jacob
            - Kjøpsmeny for flere katter Theodor


### Referat 26.02.24
Andre standup siden første innlevering - Alle var tilstede.

- **Hva har blitt gjort siden forrige møte?**
    - Vi har mer forståelse for hva som må til for å oppnå det produktet vi vil. Startet på MVP-en, et par punkter har kommet på plass, men mange oppgaver er ikke ferdigstilte. Omtrent 3/4 av punktene er igangsatt. Innsett at batch, texture og render er utfordringer, og at dette kan by på problemer.

- **Hva skal vi gjøre til neste møte?**
    - Fortsette å jobbe mot MVP. Prøve å finne ut hvordan vi skal løse det med animasjoner (kart, rotter, katter etc). Kan hende vi må outsource dette. Vi får høre med folk vi kjenner om de er villige til å hjelpe oss. Skal prøve å få skrevet tester, men er ikke første prioritet.

#### Dagens status på MVP:

- **Basis Spillområde:**
    - En sti for rottebevegelse fra start til slutt - Theodor/Jonathan - Ferdig
    - Enkel bakgrunnsgrafikk for kattutplassering - Theodor - Ferdig

- **Katt- og Rotte-Enheter:**
    - Katt-Enheter: Minst to oppgraderbare kattetyper (f.eks., korthårs- og langhårskatter) - Axel/Theodor
    - Rotte-Enheter: Minst én rotte som følger stien mot et mål - Jonathan

- **Enkel Spillopplevelse:**
    - Funksjoner for å starte, akselerere, pause, og restarte spillet. - Jonathan/Jacob
    - Penger opptjent per drept rotte for kjøp av flere katter - Axel
    - Livstelling for spillerens gjenværende liv. -Jacob/Axel

- **Grunnleggende Brukergrensesnitt (UI):**
    - Enkel meny for spillstart - Jacob
    - Skjermdisplay for poeng og spillerliv - Axel/Jacob
    - Kjøpsmeny for flere katter Theodor

### Referat 04.03.24
**Tredje standup siden første innlevering, og avslutning av første sprint - Alle var tilstede**

#### Hva har vi gjort siden forrige møte?
- Fortsatt arbeidet på MVP, som nå er omtrent 90% fullført. Par-programmering har vist seg å være effektivt.
- Utfordringer med batch, render, og texture har blitt adressert og vi har nå en god forståelse for disse.
- Spesielt fokus på menyutvikling for å forhindre at spillet kjører i bakgrunnen mens menyen er aktiv.
- Har funnet hjelp til animasjoner, men dette blir ikke implementert i MVP.
- Tester er under utvikling, målet er å inkludere dem innen 08.03.24, selv om de kanskje blir lagt i en egen branch.

#### Hva skal vi gjøre til neste møte?
- Målet er å fullføre MVP før 07.03.24 for å være klar for innlevering den 08.03.24.
- Rydde opp i Git, inkludert planlegging for mulige merge-conflicts.
- Utvikle en enkel meny og gjøre liv/score synlig på skjermen.
- Jobbe med implementering av oppgraderbare katter.
- Oppdatere README med den nye funksjonaliteten.

#### Dagens status på MVP:
- **Basis Spillområde:**
    - En sti for rottebevegelse og enkel bakgrunnsgrafikk for utplassering av katter - Ferdig

- **Katt- og Rotte-Enheter:**
    - Katt-Enheter: Delvis ferdig. Utfordringer med valg mellom forskjellige katter pga. menyintegrering.
    - Rotte-Enheter: Ferdig.

- **Enkel Spillopplevelse:**
    - Funksjoner for å starte, akselerere, pause, og restarte spillet, samt penger opptjent per drept rotte og livstelling - Ferdig.

- **Grunnleggende Brukergrensesnitt (UI):**
    - Enkel meny for spillstart, skjermdisplay for poeng og spillerliv - Ferdig.
    - Kjøpsmeny for flere katter: Delvis ferdig, utfordringer med GUI/Texture/Render.


### Referat 07.03.24
**Team-møte om hvordan vi ligger an før innlevering**

Vi gikk gjennom hva som må til for å få levert prosjektet i morgen. Dette inkluderer:

- **Prosjektrapport:** Den er ferdig og dekker alle nødvendige aspekter av prosjektet.
- **Krav og spesifikasjon:** Disse dokumentene er også ferdige og klare til innlevering.
- **Produkt-og-kode punktet til oblig 2:** Arbeidet er fullført, og det er opprettet en egen branch for dette. Planen er å pushe dette i morgen.

Under møtet ble vi enige om at:
- **Test-branchen ikke merges med main:** Testene er ikke ferdige, men påbegynt til en viss grad, og vi har besluttet å ikke merge test-branchen med main for denne innleveringen. Vi blir enige om å fikse tester til neste gang og inkludere dette i prosjektrapporten, noe som understreker viktigheten av testing og videre planer for prosjektet.
- **Spørsmål til Tobias:** Vi har et par spørsmål som Tobias trenger å adressere. Dette omhandler spesielt tester. 

Dette møtet var avgjørende for å sikre at alt er på plass før den kommende fristen, og for å klarlegge planer for hva som gjenstår å gjøre etter innleveringen.

## Prosjektrapport


### Teamroller og Dynamikk

Vårt team har etablert klare roller som fungerer bra, og det har ikke vært nødvendig å foreta endringer i disse rollene gjennom prosjektet. En oversikt over rollene og deres ansvar er som følger:

- **Lead Developer (Theodor):** Har det overordnede ansvaret for kodebasen, innsikt i prosjektets tekniske aspekter, og bistår andre utviklere ved behov. Denne rollen krever en person med solid forståelse for koding og evnen til å holde oversikt over prosjektets fremdrift teknisk sett.

- **Developer:** Alle teammedlemmer har denne rollen og bidrar med utviklingen av programvaren. Samarbeid og støtte mellom medlemmene er nøkkelen til suksess.

- **Git Ansvarlig (Jonathan):** Spesialisert i bruk av Git, denne rollen innebærer å opprettholde repoet, sikre at det er oppdatert, og sette regler for bruk (som "ikke lov å pushe til main"). Jonathan er kontaktpunktet for spørsmål eller problemer relatert til Git.

- **Grafikk (Axel):** Ansvarlig for å sikre at spillets grafikk stemmer overens med de ønskede visuelle målene. Axel har oppgaven med å integrere og justere grafikk slik at den passer med spillets design og funksjonalitet.

- **Testansvarlig:** Fokuserer på utvikling og implementering av tester for å sikre at koden fungerer som forventet. Denne rollen innebærer også å sikre at all dokumentasjon for testene er skrevet på en forståelig måte.

### Gruppedynamikk

Gruppedynamikken er god, og det har ikke vært noen problemer som har krevd løsning. Vi kommuniserer kontinuerlig og holder hverandre oppdatert om prosjektets status, hovedsakelig fordi vi tilbringer mye tid sammen på lesesalen. Dette har ført til at de ukentlige standup-møtene kanskje ikke føles like nødvendige, men de tjener fortsatt en viktig funksjon for å oppsummere og planlegge for kommende uke. Bruken av et kanban-board har også bidratt positivt til å holde oversikt over arbeidsflyt og fremgang.

### Forbedringspunkter

Selv om prosjektet generelt har gått bra, ser vi noen områder for forbedring, spesielt teknisk:

- **Automatiske GUI-tester og Unit-tester:** Vi anerkjenner behovet for å styrke vår testing, spesielt for GUI og gjennom unit-tester. Planen er å diskutere dette nærmere med gruppelederen for å finne en vei fremover.

- **Tester for Spillmetoder og GUI:** Vi ser verdien i å implementere tester som kan kjøre underveis i utviklingsprosessen for å sikre at alt fungerer som det skal.

I sum bidrar alle teammedlemmene jevnt til kodebasen, og vi føler ikke at det er noen skjevfordeling av arbeidet. Dette, sammen med våre forbedringsplaner, gir et godt grunnlag for videre suksess i prosjektet.




# Oppdatering på Prosjektstatus: Prioriterte Krav og Fremdrift

## Status på MVP
Her er en redgjørelse av statusen for prioriterte krav, hvor langt vi har kommet i utviklingsprosessen, og de stegene vi har tatt siden vår siste oppdatering. Vår innsats har vært sentrert rundt utviklingen av brukerhistorier og realiseringen av Minimum Viable Product (MVP). MVPen har ikke blitt endret siden vi skrev den. Vi er nå nær ved å fullføre MVP, og her presenterer vi en detaljert gjennomgang av vår progresjon.

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
Hovedfokuset våret ligger nå på å MVP-en, derfor vil vårt neste skritt være å ferdigstille kjøpsmenyen for katter og implementere oppgraderingsmuligheter for kattene. Videre ønsker vi å gjøre spillet mer tilgjengelig og brukervennlig ved å innføre interaktive knapper som opereres med musen. Dette inkluderer endringer i funksjoner for å starte, akselerere, pause, og navigere spillmenyer.

For å forbedre testingen og kvalitetssikring, er det også planlagt utvikling av ytterligere tester. I tillegg vil introduksjonen av en "Rat Factory"-klasse, designet for å generere rotter i bølger basert på spillerens nivå, være et sentralt fokus.

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

## Prioritering av oppgavene i synkende rekkefølge
 - CatMenu 
 - Katter som kan oppgraderes.
 - Rottebølger
 - Knapper som kan trykkes på med mus


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



# Produkt og kode

## Oppdateringer siden sist

Vi har gjort følgende forbedringer og tillegg til spillet:

- **Sti for Rotter:**
    - Rotter har nå en definert sti de følger gjennom hele brettet.

- **Kattetyper:**
    - Tre forskjellige kattetyper har blitt implementert, hver med unik funksjonalitet. For øyeblikket krever valg av kattetype endringer i koden, men dette planlegges forenklet gjennom et brukergrensesnitt.
        - En enkel katt som angriper én og én rotte innenfor sin radius.
        - En katt som kan angripe opptil tre rotter samtidig, men med litt mindre radius og tregere avfyringshastighet.
        - En katt som midlertidig fryser rotter på stien, med planer om å implementere en effekt som reduserer rottens hastighet.

- **Rottegenerering:**
    - Rotter opprettes og sendes ut på sin ferd med jevne mellomrom.

- **Tastaturkontroller:**
    - Spillet kan nå kontrolleres med tastaturet for å starte (Mellomrom), akselerere (S), pause (P) og restarte (R) spillet.

- **Poeng og Penger:**
    - Ved å utslette rotter opptjenes det poeng og penger, som vises til høyre på skjermen i spillets meny.

- **Startmeny:**
    - Spillet introduserer nå en enkel, retro-stil startmeny for en bedre brukeropplevelse.

![image](https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Kant_foto.jpg/800px-Kant_foto.jpg)