package com.app.entity;

import javax.persistence.*;

/**
 *
 * Created by romm on 12.11.16.
 */
@Entity
@Table(name = "bouquet")
public class Bouquet {

    /**
     * bouquet id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * bouquet title
     */
    @Column
    private String title;

    /**
     * bouquet category (Roses, Tulips, etc)
     */
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    /**
     * bouquet price
     */
    @Column
    private Double price;

    /**
     * bouquet image
     */
    @Lob
    @Column(length=100000)
    private byte[] image;

    /**
     * bouquet's image path on user machine
     * to identify mime type of image
     */
    @Column
    private String filename;

    /**
     * Default Bouquet constructor
     */
    public Bouquet() {
    }

    /**
     *
     * @param title
     * @param section
     * @param price
     * @param image
     * @param filename
     */
    public Bouquet(String title, Section section, Double price, byte[] image, String filename) {
        this.title = title;
        this.section = section;
        this.price = price;
        this.image = image;
        this.filename = filename;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public Section getSection() {
        return section;
    }

    /**
     *
     * @param section
     */
    public void setSection(Section section) {
        this.section = section;
    }

    /**
     *
     * @return
     */
    public Double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public byte[] getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     *
     * @return
     */
    public String getFilename() {
        return filename;
    }

    /**
     *
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
