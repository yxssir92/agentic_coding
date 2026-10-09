# Wordle – Java-Konsolenspiel

## Autor

Yassir (GitHub: [yxssir92](https://github.com/yxssir92))

## Projektbeschreibung

Ein einfaches Wordle-Spiel für die Konsole als persönliches Semesterprojekt.
Der Spieler errät ein deutsches Wort mit fünf Buchstaben und hat dafür sechs Versuche.
Nach jeder gültigen Eingabe gibt es Hinweise zu den einzelnen Buchstaben.

Das Projekt verwendet strukturierte Programmierung in Java: eine einfache Klasse,
Arrays, Methoden, Bedingungen und Schleifen. Es verwendet keine Vererbung,
Collections, Interfaces, Streams, Lambdas oder Frameworks.

## Voraussetzungen und Start

Ein installiertes Java Development Kit (JDK), Version 8 oder neuer, genügt.
Im Projektordner im Terminal ausführen:

```sh
javac -encoding UTF-8 Wordle.java
java Wordle
```

Alternativ den Projektordner in IntelliJ IDEA öffnen, ein JDK auswählen und
die `main`-Methode in `Wordle.java` starten.

## Spielregeln

- Gib ein Wort mit genau fünf Buchstaben von A bis Z ein.
- Kleinbuchstaben werden automatisch in Großbuchstaben umgewandelt.
- Ungültige Eingaben verbrauchen keinen Versuch.
- `G`: Der Buchstabe steht an der richtigen Position.
- `Y`: Der Buchstabe kommt im Wort an einer anderen Position vor.
- `X`: Für diesen Buchstaben gibt es keinen passenden, noch freien Treffer.
- Mehrfach vorkommende Buchstaben werden nur so oft berücksichtigt, wie sie im gesuchten Wort vorhanden sind.
- Nach sechs falschen Versuchen wird das Wort angezeigt.
- Mit `j` startet anschließend eine weitere Runde. Jede andere Antwort beendet das Spiel.

Beispiel: Für das gesuchte Wort `LAMPE` und die Eingabe `ALARM` erscheint:

```text
A[Y] L[Y] A[X] R[X] M[Y]
```

Zur Vereinfachung akzeptiert das Spiel jede Eingabe aus fünf Buchstaben ohne
Wörterbuchprüfung. Die zehn möglichen Lösungswörter stehen direkt im Code.
Es gibt keinen Tagesmodus und keine farbige Terminalausgabe.

## Projektdateien

- `Wordle.java`: der vollständige Programmcode mit Kommentaren.
- `readme.md`: Beschreibung, Autor und Startanleitung.
- `features.md`: sieben nummerierte Funktionen mit Beispielen und Prüfkriterien.
- `agents.md`: grundlegende Anweisungen für die Projektarbeit.
- `code-erklaerung.md`: Erklärung des Codes für Einsteiger.
- `yassir_git_project.txt`: Repository-Link für die alternative Abgabe.

## Abgabe

Das Projekt kann als `wordle-yassir.zip` oder über die Textdatei
`yassir_git_project.txt` abgegeben werden. Vor einer Abgabe über den Repository-Link
müssen die Projektdateien in das verbundene Git-Repository hochgeladen sein.
