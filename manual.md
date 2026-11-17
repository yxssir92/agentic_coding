# Wordle – Bedienungsanleitung

Errate ein deutsches Wort mit fünf Buchstaben. Du hast dafür sechs Versuche.

## Spiel starten

Du brauchst ein installiertes Java Development Kit (JDK). Entpacke die ZIP-Datei
und öffne ein Terminal in dem Ordner, in dem `Wordle.java` liegt. Führe diese
beiden Befehle nacheinander aus:

```sh
javac -encoding UTF-8 Wordle.java
java Wordle
```

In IntelliJ IDEA kannst du alternativ den Projektordner öffnen, `Wordle.java`
aufrufen und auf das grüne Dreieck neben `main` klicken. Wähle **Run** und gib
deine Tipps unten in der **Run**-Konsole ein.

## Ein Wort raten

1. Warte auf `Versuch 1/6 - Dein Wort:`.
2. Tippe fünf Buchstaben von A bis Z ein, zum Beispiel `lampe`, und drücke Enter.
   Kleinbuchstaben und Leerzeichen am Anfang oder Ende sind erlaubt. Eine
   ungültige Eingabe wie `AB12C` verbraucht keinen Versuch.
3. Lies die Hinweise und gib deinen nächsten Tipp ein.

| Hinweis | Bedeutung |
| --- | --- |
| `G` | Der Buchstabe steht an der richtigen Stelle. |
| `Y` | Der Buchstabe kommt an einer anderen Stelle im Lösungswort vor. |
| `X` | Für diesen Buchstaben gibt es keinen freien Treffer im Lösungswort. |

Bei mehrfach vorkommenden Buchstaben werden zuerst die richtigen Stellen
bewertet. Ein Buchstabe in der Lösung kann nur einmal zugeordnet werden.

**Beispiel:** Wenn `LAMPE` die Lösung ist und du `ALARM` eingibst, siehst du:

```text
A[Y] L[Y] A[X] R[X] M[Y]
```

Das erste A erhält `Y`; das zweite A erhält `X`, weil in `LAMPE` nur ein A
vorkommt. Der nächste Tipp sollte das A an der zweiten Stelle ausprobieren.

## Runde beenden oder weiterspielen

Wenn du das Wort errätst, zeigt das Spiel, wie viele Versuche du gebraucht hast.
Nach sechs falschen Tipps wird das Lösungswort verraten. Bei der Frage
`Noch einmal spielen? (j/n)` startest du mit `j` eine neue Runde oder beendest
das Spiel mit `n`.
