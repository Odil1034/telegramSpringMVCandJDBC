package uz.pdp.maven.service;

import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.maven.dto.SignupDto;
import uz.pdp.maven.model.upload.Upload;
import uz.pdp.maven.repository.UploadRepository;
import uz.pdp.maven.utils.FileWriter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class UploadService implements BaseService<Upload> {

    @Autowired
    private UploadRepository uploadRepository;

    public UploadService(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    @Override
    public Upload get(String id) {
        return uploadRepository.get(id);
    }

    @Override
    public boolean save(Upload upload) {
        return uploadRepository.save(upload);
    }

    @Override
    public boolean delete(String id) {
        return uploadRepository.delete(id);
    }

    @Override
    public boolean update(Upload upload) {
        return uploadRepository.update(upload);
    }

    @Override
    public List<Upload> getAll() {
        return uploadRepository.getAll();
    }

    public Upload getUserImageByUserId(String userId) {
        return uploadRepository.getUserImageByUserId(userId);
    }

    public Upload createUpload(Part filePart, String ownerId) {
        try {
            InputStream inputStream = filePart.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            String originalName = filePart.getSubmittedFileName();
            String mimeType = filePart.getContentType();
            String extension = filePart.getContentType().substring(filePart.getContentType().lastIndexOf(".") + 1);
            String generatedName = FileWriter.write(bytes, extension);

            System.out.println("originalName = " + originalName);
            System.out.println("mimeType = " + mimeType);
            System.out.println("extension = " + extension);
            System.out.println("generatedName = " + generatedName);

            Upload build = Upload.builder()
                    .originalName(originalName)
                    .mimeType(mimeType)
                    .extension(extension)
                    .generatedName(generatedName)
                    .ownerId(ownerId)
                    .build();

            save(build);

            return build;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
