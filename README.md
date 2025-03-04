# Tika Media Extraction API

Bu proje, **Apache Tika** kullanarak farklı formatlardaki dosyalardan **metin, metadata ve OCR verisi** çıkarmayı sağlar.

##  Özellikler
- 📂 **Genel Metin Çıkarma:** `.pdf`, `.docx`, `.pptx`, `.csv` gibi dosyalardan metin çıkarır.
- 🎥 **Medya Metadata Çıkarma:** `.mp4`, `.mp3` ve `.flv` gibi medya dosyalarından metadata bilgilerini çeker.
- 🖼 **OCR ile Metin Çıkarma:** `.png`, `.jpg`, `.tiff` gibi görsellerden ve taranmış PDF'lerden metin çıkarır.

---

## 📌 Kurulum

### 🛠 Gereksinimler
- **Java 17+**
- **Maven 3.6+**
- **Tesseract OCR (Opsiyonel, OCR desteği için)**

```bash
# Ubuntu için Tesseract kurulumu
sudo apt install tesseract-ocr
```

# Windows için

[Tesseract Link](https://github.com/UB-Mannheim/tesseract/wiki)

