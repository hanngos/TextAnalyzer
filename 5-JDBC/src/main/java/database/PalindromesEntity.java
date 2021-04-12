package database;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class used to describe content of Palindromes table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
@Entity
@Table(name = "Palindromes", schema = "APP")
@IdClass(PalindromesEntityPK.class)
public class PalindromesEntity {
    /**
     * private field holding column label in the table
     */
    private int id;
    /**
     * private field holding column label in the table
     */
    private String palindrome;
    /**
     * private field holding column label in the table
     */
    private int occurrence;
    /**
     * private field holding object of TextEntity type
     */
    private TextsEntity P_FK;

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
     * @return palindrome
     */
    @Id
    @Column(name = "PALINDROME", nullable = false, length = 128)
    public String getPalindrome() {
        return palindrome;
    }

    /**
     * Public setter
     * @param palindrome String
     */
    public void setPalindrome(String palindrome) {
        this.palindrome = palindrome;
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
        PalindromesEntity that = (PalindromesEntity) o;
        return id == that.id && occurrence == that.occurrence && Objects.equals(palindrome, that.palindrome);
    }

    /**
     * Public overridden method
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, palindrome, occurrence);
    }

    /**
     * Public getter
     * @return P_FK
     */
    @OneToOne(mappedBy = "P_FK")
    public TextsEntity getP_FK() {
        return P_FK;
    }

    /**
     * Public setter
     * @param p_FK TextsEntity object
     */
    public void setP_FK(TextsEntity p_FK) {
        P_FK = p_FK;
    }
}
