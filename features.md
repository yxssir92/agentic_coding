# Wordle – Funktionsbeschreibung

**Autor:** Yassir (GitHub: yxssir92)

**Stand:** 9. Oktober 2026

**Status:** Alle sieben beschriebenen Features sind umgesetzt.

## Projektziel

Der Spieler errät ein verborgenes deutsches Wort mit fünf Buchstaben in
höchstens sechs Versuchen. Hinweise nach jedem gültigen Tipp helfen beim Raten.
Jedes Feature wird durch Beschreibung, Beispiel und Prüfkriterien konkretisiert.

## 1. Zufälliges Lösungswort auswählen

**Beschreibung:** Zu Beginn jeder Runde wählt das Programm zufällig eines von
zehn Wörtern aus. Die Lösung bleibt während der Runde gleich und wird vor
dem Raten nicht angezeigt.

**Wortliste:** APFEL, BLUME, LAMPE, TISCH, WOLKE, KATZE, HUNDE, SONNE, REGEN, RADIO.

**Beispiel:** In einer Runde wird BLUME ausgewählt. Eine weitere Runde kann
ein anderes Wort oder erneut BLUME auswählen.

**Prüfkriterien:** Die Lösung stammt aus der Liste und hat fünf Buchstaben.
Vor dem ersten Tipp ist sie verborgen. Jede neue Runde wählt erneut eine
Lösung; ein anderes Wort ist dabei nicht garantiert.

## 2. Ratewort einlesen und prüfen

**Beschreibung:** Der Spieler gibt ein Wort ein und bestätigt mit Enter.
Das Programm entfernt äußere Leerzeichen und wandelt die Eingabe in
Großbuchstaben um. Danach muss sie genau fünf Buchstaben von A bis Z enthalten.
Ungültige Eingaben führen zu einer Fehlermeldung und verbrauchen keinen Versuch.

**Beispiel:** `  apfel  ` wird als APFEL verarbeitet. ABC, ABCDEF, AB12C,
AB CD und eine leere Eingabe sind ungültig.

**Prüfkriterien:** Gültige Eingaben werden bewertet. Nach einer ungültigen
Eingabe bleibt die Versuchszahl gleich. Auch ZZZZZ wird akzeptiert:
Ratewörter werden nicht mit einem Wörterbuch abgeglichen.

## 3. Hinweise zu jedem Buchstaben anzeigen

**Beschreibung:** Nach jedem gültigen Tipp zeigt das Programm die fünf
Ratebuchstaben mit je einem Hinweis an. Diese Hinweise helfen beim nächsten Tipp.

| Hinweis | Bedeutung |
| --- | --- |
| G | Richtiger Buchstabe an der richtigen Position. |
| Y | Der Buchstabe passt zu einer noch freien anderen Position im Lösungswort. |
| X | Für diesen Buchstaben ist kein passender Treffer mehr verfügbar. |

**Beispiel:** Lösung LAMPE, Eingabe ALARM:

```text
A[Y] L[Y] A[X] R[X] M[Y]
```

**Prüfkriterien:** Alle fünf Buchstaben erscheinen in der eingegebenen
Reihenfolge und erhalten genau einen Hinweis. Eine vollständig richtige
Eingabe erhält fünf G. Ein Buchstabe, der nicht in der Lösung vorkommt, erhält X.

## 4. Mehrfach vorkommende Buchstaben korrekt bewerten

**Beschreibung:** Ein Buchstabe darf nur so oft einen passenden Hinweis erhalten,
wie er in der Lösung vorkommt. Richtige Positionen (G) haben Vorrang vor
Treffern an anderen Positionen (Y). Jede Position der Lösung wird höchstens
einmal zugeordnet.

**Beispiel:** Lösung SONNE, Eingabe NNNNN:

```text
N[X] N[X] N[G] N[G] N[X]
```

**Prüfkriterien:** Nur das dritte und vierte N erhalten G. Die übrigen N
erhalten X, weil die Lösung nur zwei N enthält. Bei Lösung LAMPE und
Eingabe ALARM erhält nur eines der beiden A ein Y.

## 5. Versuche anzeigen und begrenzen

**Beschreibung:** Vor jeder Eingabe erscheint die aktuelle Versuchszahl.
Ein gültiger falscher Tipp verbraucht einen Versuch. Eine Runde hat
höchstens sechs gültige Versuche.

**Beispiel:** Nach dem ersten gültigen falschen Tipp erscheint
`Versuch 2/6 - Dein Wort:`.

**Prüfkriterien:** Jede Runde beginnt bei Versuch 1/6. Ungültige Eingaben
erhöhen die Zahl nicht. Es gibt keinen siebten Versuch. Auch im sechsten
Versuch kann der Spieler noch gewinnen.

## 6. Sieg oder Niederlage erkennen

**Beschreibung:** Bei einer richtigen Eingabe endet die Runde sofort.
Das Programm meldet den Sieg und die Anzahl der benötigten Versuche.
Nach sechs gültigen falschen Tipps meldet es eine Niederlage und verrät die Lösung.

**Beispiel:** Lösung APFEL, richtiger erster Tipp:

```text
Gewonnen! Du hast 1 Versuch(e) gebraucht.
```

Nach sechs falschen Tipps erscheint bei derselben Lösung:

```text
Leider verloren. Das Wort war: APFEL
```

**Prüfkriterien:** Nach einem Sieg wird kein weiterer Rateversuch angefordert.
Bei einer Niederlage wird die richtige Lösung angezeigt. Eine abgeschlossene
Runde meldet entweder Sieg oder Niederlage, niemals beides.

## 7. Weitere Runde starten oder Spiel beenden

**Beschreibung:** Nach einer Runde fragt das Programm
`Noch einmal spielen? (j/n)`. Mit j startet eine neue Runde mit erneut
ausgewähltem Wort und sechs Versuchen. Jede andere Antwort beendet das Spiel.

**Beispiel:** J startet eine neue Runde. Bei n erscheint
`Danke fürs Spielen!` und das Programm endet.

**Prüfkriterien:** j, J und `  j  ` starten eine Runde bei Versuch 1/6.
n beendet das Programm. Wird die Konsoleneingabe geschlossen, beendet sich
das Programm ohne Fehler durch den Eingabeleser.

## Umfang und Grenzen

Die Umsetzung verwendet eine einfache Java-Klasse, Methoden, Arrays,
Bedingungen und Schleifen. Die Hinweise sind Textzeichen; echte Farben,
ein Tagesmodus, eine Wörterbuchprüfung für Ratewörter und gespeicherte
Ergebnisse sind nicht Bestandteil dieser Version.

## Manuelle Überprüfung

Im Projektordner kompilieren und starten:

```sh
javac -encoding UTF-8 Wordle.java
java Wordle
```

Für Beispiele mit bekanntem Lösungswort kann die Wortliste in main
vorübergehend auf ein Wort reduziert werden, zum Beispiel:

```java
String[] words = {"SONNE"};
```

Danach neu kompilieren und die beschriebenen Eingaben ausprobieren.
Vor der Abgabe die ursprüngliche Liste mit zehn Wörtern wiederherstellen
und erneut kompilieren.
