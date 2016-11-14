package com.app;

import com.app.entity.Bouquet;
import com.app.entity.Section;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Arrays;

/**
 * Created by romm on 12.11.16.
 */
public class App {

    private static byte[] in;

    public static byte[] readImage (String filename) throws IOException {
        // open image
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        BufferedImage img= ImageIO.read(new File(filename));
        ImageIO.write(img, "jpg", baos);
        baos.flush();

        return baos.toByteArray();
    }

    private static void addRose() throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Section roses = new Section("Roses");
        entityManager.persist(roses);
        Section peonies = new Section("Peonies");
        entityManager.persist(peonies);
        Section tulips = new Section("Tulips");
        entityManager.persist(tulips);
        byte[] buff = readImage("resources/1.jpg");
        Bouquet rosesBouquet = new Bouquet("Bouquet of roses", roses, 133.33, buff, "resources/1.jpg");
        entityManager.persist(rosesBouquet);
        buff = readImage("resources/2.jpg");
        Bouquet peoniesBouquet = new Bouquet("Bouquet of peonies", peonies, 15.00, buff, "resources/2.jpg");
        entityManager.persist(peoniesBouquet);
        buff = readImage("resources/3.png");
        Bouquet tulipsBouquet = new Bouquet("Bouquet of tulips", tulips, 100.01, buff, "resources/3.png");
        entityManager.persist(tulipsBouquet);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void getRose() throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Bouquet roses = entityManager.find(Bouquet.class, 1);
        byte[] rosesImage = roses.getImage();
        System.out.println(Arrays.equals(in, rosesImage));
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void main(String[] args) throws IOException {
        addRose();
//        getRose();
    }
}
