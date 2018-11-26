package pis.hue1;

public class Caesar implements Codec {
    private String losung;

    @Override
    public String kodiere(String klartext) {
        StringBuilder geheimtextBuilder = new StringBuilder(klartext.length());
        for (char c: klartext.toCharArray()) {
            int ascii = (int) c;
            if (ascii >= 65 && ascii <= 90) {  // for upper case
                ascii += (losung.length() % 26);
                if (ascii > 90) {
                    ascii = 64 + ascii - 90;
                }
                geheimtextBuilder.append((char) ascii);
            } else if (ascii >= 97 && ascii <= 122) { // for lower case
                ascii += (losung.length() % 26);
                if (ascii > 122) {
                    ascii = 96 + ascii - 122;
                }
                geheimtextBuilder.append((char) ascii);
            } else {
                geheimtextBuilder.append(c);
            }
        }
        return geheimtextBuilder.toString();
    }

    @Override
    public String dekodiere(String geheimtext) {
        StringBuilder klartextBuilder = new StringBuilder(geheimtext.length());
        for (char c: geheimtext.toCharArray()) {
            int ascii = (int) c;
            if (ascii >= 65 && ascii <= 90) {  // for upper case
                ascii -= (losung.length() % 26);
                if (ascii < 65) {
                    ascii = 91 - (65 - ascii);
                }
                klartextBuilder.append((char) ascii);
            } else if (ascii >= 97 && ascii <= 122) { // for lower case
                ascii -= (losung.length() % 26);
                if (ascii < 97) {
                    ascii = 123 - (97 - ascii);
                }
                klartextBuilder.append((char) ascii);
            } else {
                klartextBuilder.append(c);
            }
        }
        return klartextBuilder.toString();
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
    }
}