package database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class used to describe content of Words primary key
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class WordsEntityPK implements Serializable {
    /**
     * private field holding column label in the table
     */
    private int id;
    /**
     * private field holding column label in the table
     */
    private String letter;

    /**
     * Public getter
     * @return id
     */
    @Column(name = "ID", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    /**
     * Public setter
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Public getter
     * @return letter
     */
    @Column(name = "LETTER", nullable = false, length = 128)
    @Id
    public String getLetter() {
        return letter;
    }

    /**
     * Public setter
     * @param letter String
     */
    public void setLetter(String letter) {
        this.letter = letter;
    }

    /**
     * Public overridden method
     * @param o Object object
     * @return boolean if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordsEntityPK that = (WordsEntityPK) o;
        return id == that.id && Objects.equals(letter, that.letter);
    }

    /**
     * Public overridden method
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, letter);
    }
}
