package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Pincode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Integer count;


    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Integer getCount() {
        return count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
