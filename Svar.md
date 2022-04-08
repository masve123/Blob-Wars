# Svar på spørsmål

*I denne filen legger du inn svar på spørsmålene som stilles i oppgaveteksten, eventuelt kommentarer og informasjon om kreative løsninger*

   
## Oppgave 1

* Nevn noen viktige klasser og hvorfor vi trenger disse klassene?

Det finnes flere viktige klasser i dette programmet. Pakkene GUI og terminal holder på klasser som i hovedsak har med selve visningen å gjøre, mens game, player og grid holder på modellen og selve back-end delen av programmet. Jeg har valgt å fokusere på de tre siste når det kommer til viktige klasser ettersom det står i oppgaven at det er disse som er viktigst å forstå.

- Klassen "Game" er et eksempel på en viktig klasse. Det er her blant annet her selve game-loopen kjøres. Det er også en abstrakt klasse, som har ansvar for en del felles kode som igjen brukes av subklasser.

- Noen andre viktige klasser er naturligvis "Grid" og "AbstractPlayer". "Grid" er en klasse som holder på et rutenett, og er derfor helt essensielt for at programmet skal fungere. "AbstractPlayer" er på lik linje med game en abstrakt klasse som er viktig for å unngå duplikasjon av kode, og gjør det lettere å holde koden organisert. "AbstractPlayer" holder på et navn og et symbol som er unike for hver spiller.

- "GameBoard" er også en viktig klasse med en del dependencies. Den har ansvar for selve brettet, og for å sjekke at ulike posisjoner er gyldige. Den har både setters og getters, copy og andre metoder som er viktige blant annet for innkapsling. Den har også implementert andre viktige metoder, som hashcode og equals.



* Hvor brukes abstraction, encapsulation, inheritance, composition og polymorphism?

- Abstraction: Et av de viktigste eksemplene på abstraksjon er faktisk interface. Så sånn sett kan vi si at det brukes abstraksjon allerede i "Player" interfacet. Deretter knyttes "Player interfacet til den abstrakte klassen "AbstractPlayer". Her samles de metodene som interfacet kreves implementert, minus de som er forskjellige for de ulike spillerne. Eksempelvis arver "HumanPlayer", "AlphaBetaPlayer", "DumbPlayer" og "RandomPlayer" alle fra "AbstractPlayer". Deretter arver både "ConsolePlayer" og "GuiPlayer" fra "HumanPlayer". Så her er det flere lag med abstraksjon som deler koden inn i ulike deler (som igjen skaper modularitet).

- Encapsulation: Tradisjonelt sett innebærer innkapsling at man setter klasser, metoder og feltvariabeler til "private" og deretter bruker getters og setters for å hente ut og sette ulike verdier. Man kan også se på innkapsling i ett litt bredere perspektiv; ved bruk av restriktive grensesnitt oppnår man også en form for innkapsling. Et spesielt viktig grensesnitt er "Iterable", som eksempelvis "IGrid" arver fra. 

Et annet eksempel på en klasse som i høy grad innkapslet er "PlayerList". Her kombineres komposisjon med bruk av private feltvariabler. I tillegg arver klassen fra "Iterable<Player>".
Samme gjelder også "GameBoard" klassen der man også bruker komposisjon med private feltvariabler. "GameBoard" har også setter og getter metoder, tillegg til en swap-metode som likner litt på set-metoden, men med den funksjonen at den kan putte inn en piece selv om det allerede finnes en på den aktuelle "location"

- Inheritance: Er nevnt tidligere, men det er flere superklasser i dette programmet. Blant annet  arver "HumanPlayer", "AlphaBetaPlayer", "DumbPlayer" og "RandomPlayer" alle fra "AbstractPlayer" i player-pakken. I grid-pakken er det to tilfeller av inheritance, der interfacet "IGrid" arver fra Iterable<T>, som er en generisk type. Dette er nødvendig for å kunne bruke "for-each" løkke. "GridLocationIterator" klassen arver fra både Iterator<Location> og Iterable<Location>. I game-pakken brukes det komposisjon som et alternativ til arv i klassen "GameBoard".

- Composition: brukes blant annet i  "Gameboard" klassen. Som et alternativ til inheritance, som er en klasse som arver en del av en annen klasse. Det som skjer her er at det brukes en feltvariabel "grid" som holder på en type "player". Denne brukes deretter til å kalle på de forskjellige metodene.

- Polymorphism: Polymorphisme er et prinsipp i objektorientert programmering som omhandler at flere subklasser arver fra en superklasse. I dette programmet fungerer blant annet den abstrakte klassen "AbstractPlayer" som en superklasse, ettersom det er flere subklasser som arver fra denne klassen. 

* Hvilke andre spill (eller utvidelser) vil det være enkelt å legge til i dette prosjektet, og hvilke spill vil by på utfordringer?

- Ved gjenbruk av kode er det flere muligheter for ulike spill i dette prosjektet. Mye av grunnverket er lagt allerede, blant annet i grid-pakken som allerede har laget et velfungerende grid. Det samme gjelder også i player-pakken, som har laget alle de ulike spillerne.


-----

* Hvor er SOLID prinsippene brukt/ikke brukt.
Merk at SOLID prinsippene ikke er pensum før i INF112 så vi forventer ikke så mye av dere her,
men de av dere som ønsker å få 15/15 må vise at dere har prøvd litt på dette.

SOLID-prinsippene er objektorienterte designkonsepter som brukes i ulike former for utvikling. Det er en forkortelse for "Single Responsibility Principle", "Open-Closed Principle", "Liskov Substitution Principle", "Interface Segregation Principle" og "Dependency Inversion Principle".

- Single Responsibility Principle: En klasse skal ha kun være ansvarlig for en enkelt del eller funksjon i systemet.

Det finnes flere eksempler på dette i koden som er implementert. Et eksempel er klassen "GridLocationIterator" som arver fra "Iterator<Location>". Denne klassen har ansvar for å iterere over alle "locations" i et grid. Et annet eksempel er LocationComparator, som arver fra Comparator<Location>. Denne klassen har ansvar for å sammenligne to "locations" og returnere en verdi som indikerer hvilken "location" som har størst avstand fra et gitt objekt.



- Open-Closed Principle: En klasse skal være åpen for utvidelser, men ikke modifikasjoner.

Det finnes en operator i Java, "Instanceof" som kan brukes for å teste om et objekt er en instans av en spesiell type. Den er kjent som "the type comparison-operator" fordi den har ansvar for å sammenligne to objekter og returnere true hvis de er av samme type. Men tradisjonelt sett ønsker man å unngå dette, og heller bruke arv slik at man unngår å endre på selve superklassen. Istedenfor kan man legge til endringer i en subklasse ved bruk av @override metoder. 

Jeg har notert meg at det brukes "instanceof" i "Input" - klassen. 


- Liskov Substitution Principle: En subklasse skal kunne erstatte en superklasse uten at systemet mister funksjonalitet.





- Interface Segregation Principle: Ingen utviklere skal bli tvunget til å ta i bruk metoder som ikke trengs.

Tradisjonelt sett er dette årsaken bak at grensesnitt generelt skal være restriktive og kun ha metoder som er aktuelle for det spesifikke man er ute etter. Et eksempel på dette er bruken av "Iterator<Location>" og "Iterable<Location>" i klassen "GridLocationIterator". Her trenger man kun å implementere én metode iterator(), og to metoder hasNext() og next() for at bruken av grensesnittet skal være gyldig. 
Et eksempel på dette som derimot vil bryte med ISP-prinsippet er bruken av listener- og event- interface der man ikke implementerer alle metodene. I semesteroppgave 1 der man skulle lage tetris var dette noe som ikke ble fulgt, blant annet i "TetrisController" der flere KeyListener-metoder ikke ble tatt i bruk.



- Dependency Inversion Principle: Klasser av høy modularitet skal ikke være avhengig av klasser av lavere modularitet. Begge bør være avhengig av abstraksjon.

Her er bruken av abstraksjon viktig. Ved å dele opp klasser i grensesnitt og abstrakte klasser er det lettere å endre koden og endre den slik det er nødvendig. Dette er en av de mest viktige prinsippene i objektorientert programmering (modularitet). I dette programmet er det gjort flere ganger, blant annet ved bruk av viktige interface som player og abstrakte klasser. Særlig i player-pakken finner vi eksempler på dette.


## Oppgave 2

- Plan for å implementere Blob Wars:
Blob Wars likner en del på Othello, både når det gjelder regler men også UI. Ideen er at jeg kan bruke en abstrakt klasse for å abstrahere vekk de metodene som er like i Othello og Blob Wars. Jeg tenker også å lage en ny klasse som arver fra Location, men som istedenfor å holde på en location tar inn to. --> ----Se "Location" - klassen----

- Tegn klassediagram --- gjøres i det allerede eksisterende klassediagrammet

- 

## Oppgave 3
(skriv svar her)



