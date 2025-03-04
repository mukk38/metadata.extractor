package tr.com.muskar.metadata.extractor.service.metadata;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.parser.mp4.MP4Parser;
import org.apache.tika.parser.video.FLVParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TikaMetadataService {

    public Map<String,String> extractMediaMetadata(MultipartFile file){
        Map<String, String> metadataMap = new HashMap<>();
        try {
            Metadata metadata = new Metadata();
            BodyContentHandler handler = new BodyContentHandler();
            ParseContext context = new ParseContext();
            String fileName = file.getOriginalFilename();
            if (fileName != null && fileName.endsWith(".mp3")) {
                new Mp3Parser().parse(file.getInputStream(), handler, metadata, context);
            } else if (fileName != null && fileName.endsWith(".mp4")) {
                new MP4Parser().parse(file.getInputStream(), handler, metadata, context);
            } else if (fileName != null && fileName.endsWith(".flv")) {
                new FLVParser().parse(file.getInputStream(), handler, metadata, context);
            } else {
                return Map.of("error", "Unsupported media type");
            }
            for (String name : metadata.names()) {
                metadataMap.put(name, metadata.get(name));
            }
        } catch (Exception e) {
            metadataMap.put("error", "Error extracting metadata: " + e.getMessage());
        }
        return metadataMap;
    }
}
