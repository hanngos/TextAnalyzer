package database;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class used to describe content of Words table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
@Entity
@Table(name = "Words", schema = "APP")
@IdClass(WordsEntityPK.class)
public class WordsEntity {
    /**
     * private field holding column label in the table
     */
    private int id;
    /**
     * private field holding column label in the table
     */
    private String letter;
    /**
     * private field holding column label in the table
     */
    private int occurrence;
    /**
     * private field holding object of TextEntity type
     */
    private TextsEntity W_FK;

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
     * @return letter
     */
    @Id
    @Column(name = "LETTER", nullable = false, length = 128)
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
     * Public getter
     * @return occurrence
     */
    @Basic
    @Column(name = "OCCURRENCE", nullable = false)
    public int getOccurrence() {
        return occurrence;
    }

    /**
     * Public setter
     * @param occurrence int
     */
    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
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
        WordsEntity that = (WordsEntity) o;
        return id == that.id && occurrence == that.occurrence && Objects.equals(letter, that.letter);
    }

    /**
     * Public overridden method
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, letter, occurrence);
    }

    /**
     * Public getter
     * @return W_FK
     */
    @OneToOne(mappedBy = "W_FK")
    public TextsEntity getW_FK() {
        return W_FK;
    }

    /**
     * Public setter
     * @param w_FK TextsEntity object
     */
    public void setW_FK(TextsEntity w_FK) {
        W_FK = w_FK;
    }
}
