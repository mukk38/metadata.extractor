package tr.com.muskar.metadata.extractor.service.ocr;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.ocr.TesseractOCRParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OcrService {

    public Map<String, Object> extractTextFromImage(MultipartFile file){
        Map<String, Object> response = new HashMap<>();
        try {
            TesseractOCRParser parser = new TesseractOCRParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();
            TesseractOCRConfig config = new TesseractOCRConfig();
            config.setLanguage("eng");
//            config.setLanguage("tur");
            context.set(TesseractOCRConfig.class, config);
            parser.parse(file.getInputStream(), handler, metadata, context);
            response.put("extractedText", handler.toString());

            Map<String, String> metadataMap = new HashMap<>();
            for (String name : metadata.names()) {
                metadataMap.put(name, metadata.get(name));
            }
            response.put("metadata", metadataMap);

        } catch (Exception e) {
            response.put("error", "Error extracting OCR text: " + e.getMessage());
        }
        return response;
    }
}
