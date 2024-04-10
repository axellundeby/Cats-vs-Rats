# INF112 Project - *Skadedyrkontrollørene INC, Return of the Rats*

* Team: *Skadedyrkontrollørene_inc* (Gruppe 1): 
    * *Jonathan Christensen - Developer/Git*
    * *Axel Lundeby - Developer/Grafikk*
    * *Theodor Nissen-Meyer - Lead Developer*
    * *Jacob Foss - Developer/Testansvarlig* 

### Om spillet
I dette strategispillet er målet å forsvare byen mot en invasjon av rotter ved hjelp av ulike typer katter plassert langs rottens invasjonsrute. Spillet foregår i en todimensjonal verden, hvor spillere kan utplassere kattene rundt en fastsatt rute som rottene følger. 

### Hvordan spille spillet
Når spillet starter får man frem en enkel meny. Trykk "space" for å starte. Deretter "p" for å sette spillet i gang. Da vil rottene spawne i intervaller. Det går også an å spawne rotter slik man selv ønsker ved å trykke på "u". Trykker man på "s" går spillet fortere, og trykker du igjen går spillet tilbake til normal fart. Man plasserer ut katter ved å trykke på skjermen. For å stoppe spillet trykker man på "p" igjen. Og for å restarte trykker man på "r" eller esc-knappene. Hvis man står i menyen, og trykker på esc-knappen vil man gå ut av spillet.

### Kjente feil
Spillet krasjer når rotter beveger seg ut av det definerte spillområdet.
Spillet lagger når nye rotter spawner ved hjelp av "u", og når man trykker for å plassere katter.
Kattene kan plasseres på stien
En katt kan spawne i venstrehjørnet når man plasserer en katt
Som påpekt under *Produkt og kode* i oblig2.md har vi flere katter som vi har implementert (som for øyeblikket krever endring i koden for å få bruke), men når vi prøver disse skjer det flere feil. Blant annet at man får ny katt når man restarter spillet. Noen kxatter fordeler ikke skaden de kan påføre rottene slik vi vil. 

### Kjøring
* Kompileres med `mvn package`.
* Kjøres med `java -jar target/returnOfTheRats-1.0-SNAPSHOT-fat.jar`
* Krever Java 21 eller senere

### Credits