package com.app.service;

import com.app.entity.Bouquet;
import com.app.repository.BouquetDAO;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class represents all methods application need to manage Bouquets
 * Created by romm on 13.11.16.
 */
@ManagedBean
@SessionScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BouquetService {

    /**
     * Application logger
     */
    private static final Logger logger = Logger.getLogger(BouquetService.class);

    /**
     * DAO to access entities
     */
    @EJB
    private BouquetDAO bouquetDAO;

    /**
     * Service to manage sections
     */
    @ManagedProperty("#{sectionService}")
    private SectionService sectionService;

    /**
     * Field to temporary store object
     */
    private Bouquet bouquet;

    /**
     * Field to temporary store object
     */
    private Integer bouquetId;

    /**
     * Field to temporary store object
     */
    private Integer bouquetSectionId;

    /**
     * Field to temporary store object
     */
    private UploadedFile image;

    /**
     * Field to temporary store object
     */
    private Double lowerPrice;

    /**
     * Field to temporary store object
     */
    private Double upperPrice;

    public BouquetService() {
        logger.info("Bouquet Service created");
        bouquet = new Bouquet();
    }

    /**
     * Set filters on page to default
     */
    @PostConstruct
    public void resetFilter() {
        logger.warn("Data reseted");
        lowerPrice = 0.;
        upperPrice = 1000000.;
        bouquetSectionId = null;
    }

    public void doNothing() {
        logger.info(lowerPrice);
        logger.info(upperPrice);
        logger.info(bouquetSectionId);
    }

    /**
     *
     * @return list of all bouquets
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Bouquet> getAllBouquets() {
        logger.info("Get all bouquets called");
        List<Bouquet> bouquets = bouquetDAO.getAll();
        if (bouquetId == null && bouquets != null && bouquets.size() > 0) {
            bouquetId = bouquets.get(0).getId();
        }
        return bouquets;
    }

    /**
     * Fileter by price and section
     * @return filtered list of bouquets
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Bouquet> getAllBouquetsWithFilter() {
        List<Bouquet> bouquets = bouquetDAO.getAll();
        bouquets = bouquets.stream()
                .filter(x -> x.getPrice() >= lowerPrice)
                .filter(x -> x.getPrice() <= upperPrice)
                .filter(x -> bouquetSectionId == null || Objects.equals(x.getSection().getId(), bouquetSectionId))
                .collect(Collectors.toList());
        logger.warn("Lower " + lowerPrice + ", Upper " + upperPrice
                + ", section " + (bouquetSectionId == null ? "null" : bouquet));
        return bouquets;
    }

    /**
     * Add bouquet to repository
     * @throws IOException
     */
    public void addBouquet() throws IOException {
        bouquet.setSection(sectionService.getSectionById(bouquetSectionId));
        bouquet.setFilename(image.getFileName());
        bouquet.setImage(IOUtils.toByteArray(image.getInputstream()));
        bouquetDAO.create(bouquet);
    }

    /**
     * Update bouquet to repository
     * @throws IOException
     */
    public void updateBouquet() throws IOException {
        String bouquetTitle = bouquet.getTitle();
        Double bouquetPrice = bouquet.getPrice();
        bouquet = bouquetDAO.get(bouquetId);
        bouquet.setTitle(bouquetTitle);
        bouquet.setPrice(bouquetPrice);
        bouquet.setSection(sectionService.getSectionById(bouquetSectionId));
        bouquet.setFilename(image.getFileName());
        bouquet.setImage(IOUtils.toByteArray(image.getInputstream()));
        bouquetDAO.update(bouquet);
    }

    /**
     * Update bouquets on page
     */
    public void updateView() {
        bouquet = bouquetDAO.get(bouquetId);
        bouquetSectionId = bouquet.getSection().getId();
    }

    /**
     * Remove bouquet from repository
     */
    public void removeBouquetById() {
        bouquetDAO.delete(bouquetDAO.get(bouquetId));
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public Integer getBouquetSectionId() {
        return bouquetSectionId;
    }

    public void setBouquetSectionId(Integer bouquetSectionId) {
        logger.info("Section set to : " + bouquetSectionId);
        this.bouquetSectionId = bouquetSectionId;
    }

    public SectionService getSectionService() {
        return sectionService;
    }

    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public Integer getBouquetId() {
        return bouquetId;
    }

    public void setBouquetId(Integer bouquetId) {
        this.bouquetId = bouquetId;
    }

    public Double getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(Double lowerPrice) {
        logger.info("Lower price set to: " + lowerPrice);
        this.lowerPrice = lowerPrice;
    }

    public Double getUpperPrice() {
        return upperPrice;
    }

    public void setUpperPrice(Double upperPrice) {
        logger.info("Upper price set to: " + upperPrice);
        this.upperPrice = upperPrice;
    }
}
