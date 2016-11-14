package com.app.entity;

import javax.persistence.*;

/**
 * Created by romm on 12.11.16.
 */
@Entity
@Table(name = "bouquet")
public class Bouquet {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Column
    private Double price;

    @Lob
    @Column(length=100000)
    private byte[] image;

    @Column
    private String filename;

    public Bouquet() {
    }

    public Bouquet(String title, Section section, Double price, byte[] image, String filename) {
        this.title = title;
        this.section = section;
        this.price = price;
        this.image = image;
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
