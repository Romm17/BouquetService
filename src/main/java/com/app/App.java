package com.app;

import com.app.entity.Bouquet;
import com.app.entity.Section;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Created by romm on 12.11.16.
 */
public class App {

    public static byte[] readImage (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return data.getData();
    }

    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("app");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Section roses = new Section("Roses");
        entityManager.persist(roses);
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        byte[] rosesImage = readImage("roses.jpg");
        Bouquet rosesBouquet = new Bouquet("Boucuet of roses", roses, 133.33, rosesImage);
        entityManager.persist(rosesBouquet);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
