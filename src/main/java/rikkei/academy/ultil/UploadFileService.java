package rikkei.academy.ultil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import rikkei.academy.modules.products.ProductImages;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static rikkei.academy.modules.category.service.CategoryServicelmpl.uploadFolder;


@Service
public class UploadFileService {
    @Autowired
    private ServletContext context;

    public List<ProductImages> uploadFile(List<MultipartFile> files) {
        if(files != null && files.size() != 0) {
            String uploadPath = context.getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            List<ProductImages> images = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                ProductImages productImages = new ProductImages();
                String fileName = files.get(i).getOriginalFilename();
                try {
                    FileCopyUtils.copy(files.get(i).getBytes(), new File(uploadPath + File.separator + fileName));
                    FileCopyUtils.copy(files.get(i).getBytes(), new File(uploadFolder + fileName));
                    productImages.setUrl("/uploads/" + fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                images.add(productImages);
            }
            return images;
        }
        return null;
    }

}
