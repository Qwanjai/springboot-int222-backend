package int222.backend.models.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;


@Component("imgService")
@Scope("singleton")
public class ImageService {


    private static final String IMAGE_PATH = "./src/images/";
    public String saveImg(MultipartFile imageFile) throws IOException {
        String imageName = imageFile.getOriginalFilename();
        File myFile = new File(IMAGE_PATH + imageName);
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(imageFile.getBytes());
        fos.close();
        return "Upload Successfully ";
    }

    public String deleteImg(String imgName) throws IOException{
        File myFile = new File(IMAGE_PATH +imgName);
        if (myFile.delete()) {
            return ("Deleted the file: " + myFile.getName());
        } else {
           return ("Failed to delete the file.");
        }
    }



}
