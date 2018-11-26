package pis.hue1;
import java.util.Arrays;

public class Wuerfel implements Codec {
    private String losung;
    private int[] permutation;

    @Override
    public String kodiere(String klartext) {
        StringBuilder geheimtextBuilder = new StringBuilder(klartext.length());

        int nummer = 1;
        while (nummer <= permutation.length) {
            // Get position
            int position = -1;
            for (position = 0; position < permutation.length; position++) {
                if (permutation[position] == nummer) break;
            }

            // Insert characters of klartext position and multiples of position to geheimtext
            while (position < klartext.length()) {
                geheimtextBuilder.append(klartext.charAt(position));
                position += permutation.length;
            }

            nummer++;
        }

        return geheimtextBuilder.toString();
    }

    @Override
    public String dekodiere(String geheimtext) {
        StringBuilder klarttextBuilder = new StringBuilder(geheimtext);

        int nummer = 1;
        int geheimtextIndex = 0;
        while (nummer <= permutation.length) {
            // Get position
            int position = -1;
            for (position = 0; position < permutation.length; position++) {
                if (permutation[position] == nummer) break;
            }

            // Insert characters of geheimtext position and multiples of position to klartext
            while (position < geheimtext.length()) {
                klarttextBuilder.setCharAt(position, geheimtext.charAt(geheimtextIndex));
                geheimtextIndex++;
                position += permutation.length;
            }

            nummer++;
        }
        return klarttextBuilder.toString();
    }

    @Override
    public String gibLosung() {
        return this.losung;
    }

    @Override
    public void setzeLosung(String schluessel) throws IllegalArgumentException {
        if (schluessel.length() == 0) {
            throw new IllegalArgumentException("Schlüssel kann nicht leer sein");
        }

        this.losung = schluessel;
        String grossschreibung = this.losung.toUpperCase();

        // convert input string to char array
        char charArray[] = grossschreibung.toCharArray();

        // sort tempArray
        Arrays.sort(charArray);

        // Get permutation by considering last index and last char
        int[] permutation = new int[this.losung.length()];
        int position = 1;
        int letzterIndex = 0;
        char letztesZeichen = 0;
        boolean istErstesZeichen = true;
        for (char c: charArray) {
            int index;
            if (!istErstesZeichen && letztesZeichen == c) {
                index = grossschreibung.toUpperCase().indexOf(c, letzterIndex + 1);
            } else {
                index = grossschreibung.toUpperCase().indexOf(c);
                letztesZeichen = c;
                istErstesZeichen = false;
            }
            permutation[index] = position;
            letzterIndex = index;
            position++;
        }
        this.permutation = permutation;
    }

    public Wuerfel(String losung) {
        this.setzeLosung(losung);
    }
}