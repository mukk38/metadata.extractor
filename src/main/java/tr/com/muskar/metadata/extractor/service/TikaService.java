package tr.com.muskar.metadata.extractor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tr.com.muskar.metadata.extractor.exception.CouldNotExtractTextFromFileException;
import tr.com.muskar.metadata.extractor.exception.FileCouldNotBeFoundException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class TikaService {

    private final Tika tika = new Tika();

    public String detectMimeType(byte[] fileData) {
        return tika.detect(fileData);
    }

    public Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metadataMap = new HashMap<>();
        try {
            Metadata metadata = new Metadata();
            tika.parse(file.getInputStream(), metadata);

            for (String name : metadata.names()) {
                metadataMap.put(name, metadata.get(name));
            }

            metadataMap.put("fileName", file.getOriginalFilename());
            metadataMap.put("contentType", tika.detect(file.getInputStream()));
            return metadataMap;
        } catch (Exception e) {
            log.error(e.getMessage() + " error when ");
            return null;
        }
    }

    public String[] getSupportedFormats() {
        return new String[]{
                "application/pdf",
                "image/jpeg",
                "image/png",
                "application/msword",
                "text/plain",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        };
    }

    public String extractText(MultipartFile file) {
        try {
            return tika.parseToString(file.getInputStream());
        } catch (IOException e) {
            throw new FileCouldNotBeFoundException(e.getMessage());
        } catch (TikaException e) {
            throw new CouldNotExtractTextFromFileException(e.getMessage());
        }
    }

    public Map<String, Object> extractPdf( MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            PDFParser parser = new PDFParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();

            parser.parse(file.getInputStream(), handler, metadata, context);

            // Metni ekleyelim
            response.put("extractedText", handler.toString());

            // Metadata ekleyelim
            Map<String, String> metadataMap = new HashMap<>();
            for (String name : metadata.names()) {
                metadataMap.put(name, metadata.get(name));
            }
            response.put("metadata", metadataMap);

        } catch (Exception e) {
            response.put("error", "Error extracting PDF: " + e.getMessage());
        }
        return response;
    }
}
