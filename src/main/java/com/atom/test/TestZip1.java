package com.atom.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class TestZip1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();

	}

	public static void test () {

        File tempInput = null;
        File tempOutput = null;
System.out.println("########################## entreee id");
        try {
            // 1. Télécharger le PDF
            URL pdfUrl = new URL("http://127.0.0.1:8000/rokhaya_diop_cni.pdf");
            tempInput = File.createTempFile("downloaded-", ".pdf");
            try (InputStream in = pdfUrl.openStream();
                 FileOutputStream out = new FileOutputStream(tempInput)) {
                in.transferTo(out);
            }

            // 2. Créer un fichier temporaire de sortie
            String tmpDir = System.getProperty("java.io.tmpdir");
            tempOutput = new File("/Users/prose/compresse", "compressed-" + UUID.randomUUID() + ".pdf");

            // 3. Compression du PDF
            compressPdfByRasterizing(tempInput, tempOutput);

            // 4. Retourner le fichier compressé
            InputStreamResource resource = new InputStreamResource(new FileInputStream(tempOutput));
            
         // Emplacement où tu veux le sauvegarder
            String savePath = "/Users/prose/compresse/rokhaya_diop_cni.pdf";
            saveInputStreamResource(resource, savePath);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tempInput != null) tempInput.delete();
            if (tempOutput != null) tempOutput.deleteOnExit(); // Optionnel : nettoyage plus tard
        }
    
	}
	
    public static void compressPdfByRasterizing(File inputFile, File outputFile) throws IOException {
        try (PDDocument original = PDDocument.load(inputFile);
             PDDocument result = new PDDocument()) {

            PDFRenderer renderer = new PDFRenderer(original);

            for (int i = 0; i < original.getNumberOfPages(); i++) {
                // Convertir chaque page en image (100 DPI)
                BufferedImage image = renderer.renderImageWithDPI(i, 100); // ou 72 DPI pour compression maximale

                // Compresser l’image en JPEG
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
                ImageWriteParam param = writer.getDefaultWriteParam();
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(0.8f); // compression forte

                try (ImageOutputStream ios = ImageIO.createImageOutputStream(baos)) {
                    writer.setOutput(ios);
                    writer.write(null, new IIOImage(image, null, null), param);
                }
                writer.dispose();

                // Lire l’image compressée
                PDImageXObject img = PDImageXObject.createFromByteArray(result, baos.toByteArray(), "page.jpg");
                PDPage page = new PDPage();
                result.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(result, page)) {
                    contentStream.drawImage(img, 0, 0, page.getMediaBox().getWidth(), page.getMediaBox().getHeight());
                }
            }
            //outputFile.
            result.save(outputFile);
        }
    }
    
    public static void saveInputStreamResource(InputStreamResource resource, String outputPath) throws IOException {
        try (InputStream inputStream = resource.getInputStream();
             OutputStream
             outputStream = new FileOutputStream(outputPath)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
