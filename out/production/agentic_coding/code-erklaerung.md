# Den Wordle-Code verstehen

Autor: Yassir (GitHub: yxssir92)

Lies die Methoden am besten in dieser Reihenfolge: `main`, `playRound`, `isValidGuess`, `showFeedback`.

## 1. Die Klasse und die Imports

`public class Wordle` ist die einzige selbst geschriebene Klasse. Eine Klasse ist hier einfach der Rahmen für unser Programm. Die Datei heißt deshalb `Wordle.java`.

Die drei Imports stellen Hilfsmittel aus der Java-Standardbibliothek bereit:

- `Scanner` liest Eingaben aus der Konsole.
- `Random` wählt eine zufällige Zahl aus.
- `Locale.ROOT` sorgt dafür, dass die Umwandlung in Groß- oder Kleinbuchstaben unabhängig von der eingestellten Systemsprache gleich funktioniert.

Wir verwenden keine Collections und keine Vererbung. Die Hilfsmittel sind normale Bibliotheksaufrufe.

## 2. `main`: Hier startet das Programm

Java startet bei `public static void main(String[] args)`.

- `public` bedeutet: Die Methode ist von außen aufrufbar.
- `static` bedeutet: Wir müssen kein eigenes Wordle-Objekt erstellen, um die Methode aufzurufen.
- `void` bedeutet: Die Methode liefert keinen Ergebniswert zurück.
- `String[] args` enthält mögliche Startargumente. In diesem Spiel verwenden wir sie nicht.

`String[] words` ist ein Array, also eine feste Folge von Texten. Ein einzelner Text hat den Typ `String`. Unsere Wortliste enthält zehn Wörter mit je fünf Buchstaben.

Array-Positionen beginnen bei **0**. Bei zehn Wörtern sind die Positionen daher 0 bis 9. `random.nextInt(words.length)` liefert eine zufällige Zahl in genau diesem Bereich. `words[wordIndex]` liest das Wort an dieser Position.

`new Scanner(System.in)` erstellt den Eingabeleser. `System.in` steht für die Konsoleneingabe. `System.out.println(...)` gibt Text aus und beginnt danach eine neue Zeile. `System.out.print(...)` bleibt in derselben Zeile.

Die `while`-Schleife wiederholt das Spiel, solange `playAgain.equals("j")` wahr ist. `equals` vergleicht den Inhalt von Texten. Für Textvergleiche verwenden wir nicht `==`.

Nach der Runde lesen wir die Antwort ein. Nur `j` startet eine weitere Runde; jede andere Antwort beendet das Spiel. `trim()` entfernt Leerzeichen am Anfang und Ende. `toLowerCase(Locale.ROOT)` macht aus `J` ein `j`.

`hasNextLine()` prüft, ob noch eine Eingabe gelesen werden kann. Damit beendet sich das Programm auch sauber, wenn die Eingabe geschlossen wird. Am Ende schließt `scanner.close()` den Eingabeleser.

## 3. `playRound`: Eine Runde mit sechs Versuchen

Die Methode erhält zwei Parameter: den Eingabeleser `scanner` und das gesuchte Wort `secretWord`. Parameter sind Werte, die eine Methode beim Aufruf bekommt.

`int attempt = 1` legt eine ganze Zahl an. Die Schleife läuft, solange diese Zahl höchstens 6 ist.

`scanner.nextLine()` liest eine ganze Eingabezeile. Wir entfernen äußere Leerzeichen und wandeln den Text in Großbuchstaben um. So wird beispielsweise `apfel` zu `APFEL`.

Danach ruft das Programm `isValidGuess(guess)` auf. Das Ausrufezeichen `!` bedeutet „nicht“. `if (!isValidGuess(guess))` bedeutet daher: „Wenn die Eingabe nicht gültig ist“.

Eine ungültige Eingabe führt zu `continue`. Das springt direkt zum nächsten Schleifendurchlauf. Weil `attempt++` erst weiter unten steht, wird dabei kein Versuch verbraucht.

Bei einer gültigen Eingabe zeigt `showFeedback` die Hinweise an. Stimmt der Text mit dem gesuchten Wort überein, gibt das Programm den Sieg aus. `return` beendet dann die Methode sofort.

Wenn das Wort falsch war, erhöht `attempt++` die Versuchszahl um eins. Nach dem sechsten falschen Versuch endet die Schleife und das Programm verrät das gesuchte Wort.

## 4. `isValidGuess`: Die Eingabe prüfen

Diese Methode liefert einen `boolean`, also `true` (wahr) oder `false` (falsch).

Zuerst wird die Länge geprüft. Ist sie nicht 5, liefert die Methode sofort `false` zurück. `!=` bedeutet „ungleich“.

Danach prüft eine `for`-Schleife jeden Buchstaben:

```java
for (int i = 0; i < guess.length(); i++)
```

Die Schleife startet bei Position 0. Sie läuft, solange die Position kleiner als die Textlänge ist. Nach jedem Durchlauf erhöht `i++` die Position um eins.

`guess.charAt(i)` liest das Zeichen an Position `i`. Ein einzelnes Zeichen hat den Typ `char`. Zeichen stehen in einfachen Anführungszeichen, zum Beispiel `'A'`; Texte stehen in doppelten Anführungszeichen, zum Beispiel `"APFEL"`.

Die Bedingung `letter < 'A' || letter > 'Z'` erkennt Zeichen außerhalb von A bis Z. `||` bedeutet „oder“. Zahlen, Umlaute, Satzzeichen und Leerzeichen innerhalb des Wortes werden abgewiesen.

Wenn alle Prüfungen bestanden sind, liefert die Methode `true`. Sie prüft nicht, ob das eingegebene Wort tatsächlich im Wörterbuch existiert. Das hält das Projekt klein.

## 5. `showFeedback`: Die Buchstaben vergleichen

Das ist die anspruchsvollste Stelle im Programm. Wir brauchen zwei Arrays mit je fünf Plätzen:

- `hints` speichert den Hinweis für jede Position des Ratewortes. Am Anfang steht überall `X`.
- `used` merkt für jede Position des gesuchten Wortes, ob deren Buchstabe bereits zugeordnet wurde. Ein neues `boolean`-Array enthält zunächst nur `false`.

**Erster Durchgang:** Wir vergleichen die Buchstaben an derselben Position. Sind sie gleich, setzen wir den Hinweis auf `G` und markieren die Position im gesuchten Wort als verwendet.

**Zweiter Durchgang:** Für jeden noch nicht grünen Buchstaben suchen wir eine freie passende Position im gesuchten Wort. Die äußere Schleife mit `i` läuft durch das Ratewort. Die innere Schleife mit `j` sucht im gesuchten Wort.

`!used[j] && guess.charAt(i) == secretWord.charAt(j)` bedeutet: „Die Position wurde noch nicht verwendet **und** die beiden Buchstaben sind gleich.“ `&&` bedeutet „und“. Bei einzelnen Zeichen dürfen wir `==` zum Vergleich verwenden.

Bei einem Treffer setzen wir den Hinweis auf `Y` und die gefundene Position auf verwendet. `break` beendet die innere Suchschleife, weil dieser Ratebuchstabe nun zugeordnet ist.

Findet die Suche keinen freien passenden Buchstaben, bleibt der Hinweis `X`.

### Warum zwei Durchgänge?

Gesuchtes Wort: `LAMPE`. Ratewort: `ALARM`.

Die Ausgabe ist:

```text
A[Y] L[Y] A[X] R[X] M[Y]
```

Im gesuchten Wort gibt es nur ein A. Deshalb darf nur eines der beiden geratenen A einen gelben Hinweis bekommen.

Ein weiteres Beispiel: Gesuchtes Wort `SONNE`, Ratewort `NNNNN`. Hier erhalten nur die N an Position 3 und 4 ein G. Die anderen N erhalten X. Richtige Positionen werden zuerst vergeben, damit ihnen kein gelber Treffer den Buchstaben wegnimmt.

Die letzte Schleife gibt jeden Ratebuchstaben zusammen mit seinem Hinweis aus.

## 6. Selbst ausprobieren

1. Starte das Spiel und gib `abc` ein. Die Versuchszahl muss gleich bleiben.
2. Gib `ab12c` ein. Diese Eingabe muss ebenfalls abgewiesen werden.
3. Gib ein Wort aus der Wortliste ein und lies die Hinweise.
4. Zum Üben kannst du die Wortliste vorübergehend auf `{"APFEL"}` reduzieren. Dann kennst du das gesuchte Wort und kannst Sieg und Niederlage gezielt ausprobieren.
5. Stelle danach die ursprüngliche Wortliste wieder her.

Für den Einstieg kannst du den Tagesmodus, Statistiken und echte Farben weglassen. Die vorhandenen sieben Funktionen erfüllen bereits die Mindestanzahl der Aufgabe.
