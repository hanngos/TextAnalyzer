package database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class used to describe content of Palindromes primary key
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class PalindromesEntityPK implements Serializable {
    /**
     * private field holding column label in the table
     */
    private int id;
    /**
     * private field holding column label in the table
     */
    private String palindrome;

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
     * @return palindrome
     */
    @Column(name = "PALINDROME", nullable = false, length = 128)
    @Id
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
     * Public overridden method
     * @param o Object object
     * @return boolean if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PalindromesEntityPK that = (PalindromesEntityPK) o;
        return id == that.id && Objects.equals(palindrome, that.palindrome);
    }

    /**
     * Public overridden method
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, palindrome);
    }
}
