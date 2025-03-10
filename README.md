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

### Windows için

[Tesseract Link](https://github.com/UB-Mannheim/tesseract/wiki) adresinden kurulum yapabilirsiniz.


## 📜 API Dökümantasyonu
Swagger UI üzerinden API'yi test edebilirsiniz:  
🔗 [Swagger UI](http://localhost:8303/swagger-ui/index.html)

## 📌 API Kullanımı

### 1️⃣ **Metin Çıkarma**
```http
POST /tika/extract-text

file (form-data) → .pdf, .docx, .csv gibi dosyalar
{
    "extractedText": "Bu bir örnek metindir...",
    "metadata": {
        "Content-Type": "application/pdf",
        "Author": "John Doe"
    }
}

```

### 2 **Pdf Extract**
```http
POST /tika/extract-pdf

file (form-data) → .pdf
{
    "extractedText": "Bu bir örnek metindir...",
    "metadata": {
        "Content-Type": "application/pdf",
        "Author": "John Doe"
    }
}

```

### 3 ** OCR**
```http
POST /tika/extract-ocr



```

## 📌 İletişim ve Bağlantılar
- 🔗 **GitHub:** [github.com/mukk38](https://github.com/mukk38)
- 💼 **LinkedIn:** [linkedin.com/in/mukk38](https://www.linkedin.com/in/mustafa-karakaya-bab235a6/)
- ✉️ **E-posta:** [mukk38@gmail.com](mailto:mukk38@gmail.com)



