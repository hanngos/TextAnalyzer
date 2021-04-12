package database;

import javax.persistence.*;
import java.util.Objects;
/**
 * Class used to describe content of Info table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
@Entity
@Table(name = "Info", schema = "APP")
public class InfoEntity {
    /**
     * private field holding column label in the table
     */
    private int id;
    /**
     * private field holding column label in the table
     */
    private int chars;
    /**
     * private field holding column label in the table
     */
    private int words;
    /**
     * private field holding object of TextEntity type
     */
    private TextsEntity I_FK;

    /**
     * Public getter
     * @return id
     */
    @Id
    @Column(name = "ID", nullable = false)
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
     * @return chars
     */
    @Basic
    @Column(name = "CHARS", nullable = false)
    public int getChars() {
        return chars;
    }

    /**
     * Public setter
     * @param chars int
     */
    public void setChars(int chars) {
        this.chars = chars;
    }

    /**
     * Public getter
     * @return words
     */
    @Basic
    @Column(name = "WORDS", nullable = false)
    public int getWords() {
        return words;
    }

    /**
     * Public setter
     * @param words int
     */
    public void setWords(int words) {
        this.words = words;
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
        InfoEntity that = (InfoEntity) o;
        return id == that.id && chars == that.chars && words == that.words;
    }

    /**
     * Public overridden method
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, chars, words);
    }

    /**
     * Public getter
     * @return I_FK
     */
    @OneToOne(mappedBy = "I_FK")
    public TextsEntity getI_FK() {
        return I_FK;
    }

    /**
     * Public setter
     * @param i_FK TextsEntity object
     */
    public void setI_FK(TextsEntity i_FK) {
        I_FK = i_FK;
    }
}
