package vsu.netcracker.project.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.xml.bind.DatatypeConverter;
import java.io.*;

@Service
public class ImageService {

    private ServletContext servletContext;

    @Autowired
    public ImageService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Transactional
    public String saveImage(String base64, String name) {
        if (base64 == null) return null;

        String[] strings = base64.split(",");

        if (strings.length != 2) return base64;

        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images type
                extension = "jpg";
                break;
        }
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = servletContext.getRealPath("/") + "resources/images/" + name + "." + extension;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "http://localhost:8080/resources/images/" + name + "." + extension;
    }
}
