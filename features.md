# Funktionen
# Features – Wordle in der Konsole

**Autor:** Yassir (GitHub: yxssir92)

Der Spieler soll ein verborgenes Wort mit fünf Buchstaben in höchstens sechs
Versuchen erraten. Hinweise nach jeder Eingabe helfen beim nächsten Versuch.
Die folgenden sieben Features beschreiben die bereits umgesetzten Funktionen.
Die Prüfkriterien geben an, woran man erkennt, dass eine Funktion richtig arbeitet.

## 1. Zufälliges Lösungswort auswählen

Zu Beginn jeder Runde wählt das Programm zufällig ein Wort aus einer festen
Wortliste aus. Das Lösungswort bleibt während der gesamten Runde gleich und
wird vor dem Raten nicht angezeigt.

Die Wortliste enthält: `APFEL`, `BLUME`, `LAMPE`, `TISCH`, `WOLKE`, `KATZE`,
`HUNDE`, `SONNE`, `REGEN` und `RADIO`.

**Prüfkriterien:**

- Das ausgewählte Wort stammt aus der Liste und hat genau fünf Buchstaben.
- Vor dem ersten Versuch ist das Lösungswort verborgen.
- Bei einer neuen Runde wird erneut ein Wort ausgewählt. Dasselbe Wort darf erneut vorkommen.

## 2. Ratewort einlesen und prüfen

Der Spieler gibt sein Ratewort über die Konsole ein und bestätigt mit Enter.
Das Programm entfernt Leerzeichen am Anfang und Ende und wandelt die Eingabe
in Großbuchstaben um. Anschließend muss sie genau fünf Buchstaben von A bis Z
enthalten. Eine ungültige Eingabe führt zu einer Fehlermeldung und kann
wiederholt werden, ohne einen Versuch zu verbrauchen.

**Prüfkriterien:**

- `apfel` und `  Apfel  ` werden als `APFEL` verarbeitet.
- `ABC`, `ABCDEF`, `AB12C`, `AB CD` und eine leere Eingabe werden abgewiesen.
- Nach einer ungültigen Eingabe bleibt die angezeigte Versuchszahl gleich.
- Auch `ZZZZZ` wird akzeptiert: Es gibt keine Wörterbuchprüfung für Ratewörter.

## 3. Hinweise für jeden Buchstaben anzeigen

Nach jedem gültigen Versuch zeigt das Programm das Ratewort zusammen mit
einem Hinweis pro Buchstabe an. Die Hinweise werden als Text ausgegeben:

| Hinweis | Bedeutung |
| --- | --- |
| `G` | Der Buchstabe steht an der richtigen Position. |
| `Y` | Der Buchstabe passt zu einer noch freien anderen Position im Lösungswort. |
| `X` | Für diesen Buchstaben ist kein passender Treffer mehr verfügbar. |

**Beispiel:** Bei Lösungswort `LAMPE` und Ratewort `ALARM` erscheint:

```text
A[Y] L[Y] A[X] R[X] M[Y]
```

1. **Zufälliges Wort:** Für jede Runde wird eines von zehn Wörtern ausgewählt.
2. **Eingabe prüfen:** Ratewörter müssen genau fünf Buchstaben von A bis Z enthalten. Ungültige Eingaben verbrauchen keinen Versuch. Kleinbuchstaben werden akzeptiert.
3. **Hinweise anzeigen:** Nach jedem gültigen Versuch zeigt das Programm für jeden Buchstaben G (richtige Stelle), Y (andere Stelle) oder X (nicht vorhanden).
4. **Doppelte Buchstaben berücksichtigen:** Ein Buchstabe des gesuchten Wortes wird höchstens einmal zugeordnet. Richtige Positionen haben Vorrang.
5. **Versuche begrenzen:** Pro Runde gibt es sechs gültige Versuche. Die aktuelle Versuchszahl wird angezeigt.
6. **Spielergebnis anzeigen:** Bei einem Sieg wird die Anzahl der Versuche angezeigt. Bei einer Niederlage wird das gesuchte Wort verraten.
7. **Erneut spielen:** Nach einer Runde kann der Spieler mit `j` eine neue Runde starten.
   **Prüfkriterien:**

- Jeder der fünf eingegebenen Buchstaben erhält genau einen Hinweis.
- Bei einem vollständig richtigen Ratewort erhalten alle Buchstaben `G`.
- Ein Buchstabe, der nicht im Lösungswort vorkommt, erhält `X`.

## 4. Mehrfach vorkommende Buchstaben korrekt bewerten

Diese einfache Version verwendet keine Wörterbuchprüfung für Ratewörter, keine echten Farben und keinen Tagesmodus.
Ein Buchstabe darf insgesamt nur so oft einen passenden Hinweis erhalten,
wie er im Lösungswort vorkommt. Richtige Positionen (`G`) haben Vorrang vor
Treffern an anderen Positionen (`Y`).

**Prüfkriterien:**

- Bei Lösungswort `LAMPE` und Ratewort `ALARM` erhält das erste A ein `Y` und das zweite A ein `X`, weil die Lösung nur ein A enthält.
- Bei Lösungswort `SONNE` und Ratewort `NNNNN` erhalten nur das dritte und vierte N ein `G`. Die übrigen N erhalten `X`.
- Ein bereits zugeordneter Buchstabe des Lösungswortes wird nicht für einen weiteren Treffer verwendet.

## 5. Versuche anzeigen und auf sechs begrenzen

Vor jeder Eingabe zeigt das Programm den aktuellen Versuch an, zum Beispiel
`Versuch 2/6 - Dein Wort:`. Jeder gültige falsche Tipp verbraucht einen Versuch.
Nach sechs gültigen falschen Tipps endet die Runde.

**Prüfkriterien:**

- Eine neue Runde beginnt mit `Versuch 1/6`.
- Nach einem gültigen falschen Tipp erhöht sich die Versuchszahl um eins.
- Es gibt keinen siebten Versuch.
- Das Wort kann auch im sechsten Versuch noch erfolgreich erraten werden.

## 6. Sieg oder Niederlage erkennen und ausgeben

Wenn das Ratewort mit dem Lösungswort übereinstimmt, endet die Runde sofort
mit einer Gewinnmeldung und der Anzahl der benötigten Versuche. Nach dem
sechsten falschen Tipp zeigt das Programm eine Verlustmeldung und verrät
das Lösungswort.

**Prüfkriterien:**

- Bei Lösungswort `APFEL` und erstem Tipp `APFEL` erscheint `Gewonnen! Du hast 1 Versuch(e) gebraucht.`
- Nach einem Sieg wird kein weiterer Rateversuch angefordert.
- Bei Lösungswort `APFEL` und sechs Tipps `ZZZZZ` erscheint `Leider verloren. Das Wort war: APFEL`.
- Für eine abgeschlossene Runde wird entweder Sieg oder Niederlage gemeldet.

## 7. Weitere Runde starten oder Spiel beenden

Nach einer abgeschlossenen Runde fragt das Programm:
`Noch einmal spielen? (j/n)`. Mit `j` startet eine neue Runde mit erneut
ausgewähltem Lösungswort und sechs verfügbaren Versuchen. Jede andere Antwort
beendet das Spiel mit `Danke fürs Spielen!`.

**Prüfkriterien:**

- `j`, `J` und `  j  ` starten eine neue Runde.
- Die neue Runde beginnt wieder mit `Versuch 1/6`.
- `n` beendet das Spiel ohne eine weitere Runde.
- Wird die Konsoleneingabe geschlossen, beendet sich das Programm ohne eine weitere Eingabe anzufordern und ohne eine Fehlermeldung durch den Eingabeleser.

## Umfang dieser Version

Das Projekt ist ein einfaches Java-Konsolenspiel. Die Hinweise erscheinen als
Textzeichen, nicht als echte Farben. Ein Tagesmodus, eine Wörterbuchprüfung
für Ratewörter und das Speichern von Ergebnissen sind nicht enthalten.

## Hinweise zur manuellen Überprüfung

Zum Starten im Projektordner `javac -encoding UTF-8 Wordle.java` und anschließend
`java Wordle` ausführen. Eingabeprüfung, Versuchszahl und Neustart lassen sich
direkt im laufenden Spiel überprüfen.

Für die Beispiele mit bekanntem Lösungswort kann die Wortliste in `main`
vorübergehend auf ein Wort reduziert werden, zum Beispiel:

```java
String[] words = {"LAMPE"};
```

Danach neu kompilieren und starten. Nach der Überprüfung die ursprüngliche
Liste mit zehn Wörtern wiederherstellen und erneut kompilieren.