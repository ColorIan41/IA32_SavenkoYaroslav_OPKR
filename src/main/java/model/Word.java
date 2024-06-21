
package model;

public class Word {
    private int id;
    private String word;
    private int languageId;

    public Word(int id, String word, int languageId) {
        this.id = id;
        this.word = word;
        this.languageId = languageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

}
