package com.app.service;

import com.app.entity.Bouquet;
import com.app.repository.BouquetDAO;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by romm on 14.11.16.
 */
@ManagedBean
public class ImageService extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ImageService.class);

    @EJB
    private BouquetDAO bouquetDAO;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String bouquetId = req.getParameter("id");

        Bouquet bouquet = bouquetDAO.get(Integer.parseInt(bouquetId));
        String mimeType = req.getServletContext().getMimeType(bouquet.getFilename());
        logger.info("Mimetype of image " + bouquetId + " = '" + mimeType + "'");
        resp.setContentType(mimeType);
        byte[] imgData = bouquet.getImage();

        OutputStream o = resp.getOutputStream();
        o.write(imgData);
        o.flush();
        o.close();

    }
}
