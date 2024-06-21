
package model;

public class Translation {
    private int id;
    private int wordId;
    private int languageId;
    private String translation;

    public Translation(int id, int wordId, int languageId, String translation) {
        this.id = id;
        this.wordId = wordId;
        this.languageId = languageId;
        this.translation = translation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
