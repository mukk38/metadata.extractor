package tr.com.muskar.metadata.extractor.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tr.com.muskar.metadata.extractor.service.TikaService;
import tr.com.muskar.metadata.extractor.service.metadata.TikaMetadataService;
import tr.com.muskar.metadata.extractor.service.ocr.OcrService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/metadata")
@RequiredArgsConstructor
public class MetadataController {
    private final TikaService tikaService;
    private final TikaMetadataService tikaMetadataService;
    private final OcrService ocrService;

    @PostMapping("/extract")
    public ResponseEntity<Map<String, String>> extractMetadata(@RequestParam("file") MultipartFile file) {
        Map<String, String> metadataMap = tikaService.extractMetaData(file);
        if (Objects.nonNull(metadataMap)) {
            return ResponseEntity.ok(metadataMap);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Metadata extraction failed"));
        }
    }

    @GetMapping("/formats")
    public ResponseEntity<List<String>> getFormats() {
        return ResponseEntity.ok(Arrays.asList(tikaService.getSupportedFormats()));
    }

    @PostMapping("/extract-text")
    public ResponseEntity<String> extractText(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(tikaService.extractText(file));
    }

    @PostMapping("/extract-pdf")
    public ResponseEntity<Map<String, Object>> extractPdf(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(tikaService.extractPdf(file));
    }

    @PostMapping("/extract-media-metadata")
    public ResponseEntity<Map<String,String>> extractMediaMetadata(@RequestParam("file") MultipartFile file){
       return ResponseEntity.ok(tikaMetadataService.extractMediaMetadata(file));
    }
    @PostMapping("/extract-ocr")
    public ResponseEntity<Map<String,Object>> extractTextFromImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(ocrService.extractTextFromImage(file));
    }


}
