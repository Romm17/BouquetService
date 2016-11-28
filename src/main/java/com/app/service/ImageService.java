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
 * This class is a HttpServlet that returns bouquet image by bouquet id
 * Created by romm on 14.11.16.
 */
@ManagedBean
public class ImageService extends HttpServlet {

    /**
     * Application logger
     */
    private static final Logger logger = Logger.getLogger(ImageService.class);

    /**
     * DAO to access entities
     */
    @EJB
    private BouquetDAO bouquetDAO;

    /**
     * This method returns bouquet image by bouquet id in response
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String bouquetId = req.getParameter("id");

        if (bouquetId == null || bouquetId.equals("") || bouquetId.equals("null")) {
            resp.setStatus(404);
        }
        else {
            Bouquet bouquet = bouquetDAO.get(Integer.parseInt(bouquetId));
            String mimeType = req.getServletContext().getMimeType(bouquet.getFilename());
            resp.setContentType(mimeType);
            byte[] imgData = bouquet.getImage();

            OutputStream o = resp.getOutputStream();
            o.write(imgData);
            o.flush();
            o.close();
        }
    }
}
