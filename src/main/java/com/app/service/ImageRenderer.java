package com.app.service;

import com.app.entity.Bouquet;
import com.app.repository.BouquetDAO;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by romm on 13.11.16.
 */
@ManagedBean
@ApplicationScoped
public class ImageRenderer {

    @EJB
    private BouquetDAO bouquetDAO;

    private static final Logger logger = Logger.getLogger(ImageRenderer.class);

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String bouquetId = context.getExternalContext().getRequestParameterMap().get("bouquetId");
        logger.warn(bouquetId);
            if (bouquetId == null)
                bouquetId = "1";
            Bouquet bouquet = bouquetDAO.get(Integer.valueOf(bouquetId));
            return new DefaultStreamedContent(new ByteArrayInputStream(bouquet.getImage()));
        }
    }
}
