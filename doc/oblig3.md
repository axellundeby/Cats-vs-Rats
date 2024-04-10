# Rapport – innlevering 3
**Team:** *Teamnavn* – *medlemmer*...


# Oppdatering på prosjektstatus: Prioriterte krav og fremdrift

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
- Tester: Utvikling av ytterligere tester for bedre kvalitetssikring.
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
