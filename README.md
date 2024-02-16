# INF112 Project - *Skadedyrkontrollørene INC, Return of the Rats*

* Team: *Skadedyrkontrollørene_inc* (Gruppe 1): 
    * *Jonathan Christensen - Developer/Git*
    * *Axel Lundeby - Developer/Grafikk*
    * *Theodor Nissen-Meyer - Lead Developer*
    * *Jacob Foss - Developer/Testansvarlig* 

### Om spillet
I dette strategispillet er målet å forsvare byen mot en invasjon av rotter ved hjelp av ulike typer katter plassert langs rottens invasjonsrute. Spillet foregår i en todimensjonal byverden, hvor spillere kan utplassere kattene rundt en fastsatt rute som rottene følger.

### Kjente feil
Vi tror det kan bli vanskelig å sørge for at kattene faktisk kaster objecter på rotta, og ikke gjennom/bak. 

### Kjøring
* Kompileres med `mvn package`.
* Kjøres med `java -jar target/gdx-.jar`
* Krever Java 21 eller senere

### Credits
Bilde av rotte: https://pngtree.com/freepng/cute-rat-isolated_13147967.html

Bilde av map: https://bloons.fandom.com/wiki/Bloons_Tower_Defense_1_Map?file=BTD1_Map.png




# INF112 libGDX + Maven template 
Simple skeleton with [libGDX](https://libgdx.com/). 

**Important:** Replace this README with info about *your* project!


# Maven Setup
This project comes with a working Maven `pom.xml` file. You should be able to import it into Eclipse using *File → Import → Maven → Existing Maven Projects* (or *Check out Maven Projects from SCM* to do Git cloning as well). You can also build the project from the command line with `mvn clean compile` and test it with `mvn clean test`.

Pay attention to these folders:
* `src/main/java` – Java source files go here (as usual for Maven) – **IMPORTANT!!** only `.java` files, no data files / assets
* `src/main/resources` – data files go here, for example in an `assets` sub-folder – **IMPORTANT!** put data files here, or they won't get included in the jar file
* `src/test/java` – JUnit tests
* `target/classes` – compiled Java class files

**TODO:** You should probably edit the `pom.xml` and fill in details such as the project `name` and `artifactId`:


```xml

	< !-- FIXME - set group id -->
	<groupId>inf112.skeleton.app</groupId>
	< !-- FIXME - set artifact name -->
	<artifactId>gdx-app</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>jar</packaging>

	< !-- FIXME - set app name -->
	<name>mvn-app</name>
	< !-- FIXME change it to the project's website -->
	<url>http://www.example.com</url>
```




