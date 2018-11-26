package pis.hue1;

interface Codec {
    /**
     * @param klartext Klartext der verschlüsselt werden soll
     * @return Verschlüsselter Geheimtext
     */
    public String kodiere(String klartext);


    /**
     * @param geheimtext Geheimtext der entschlüsselt werden soll
     * @return Entschlüsselter Klartext
     */
    public String dekodiere(String geheimtext);


    /**
     * @return Losung des Codecs
     */
    public String gibLosung();


    /**
     * @param schluessel Zu setzende Losung des Codecs
     * @throws IllegalArgumentException
     */
    public void setzeLosung(String schluessel) throws IllegalArgumentException; // bei ungeeignetem Schlüssel!
}