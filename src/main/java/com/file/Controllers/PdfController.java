package com.file.Controllers;



import com.file.Service.*;
import com.lowagie.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;
    @GetMapping("/createPdf")
    public ResponseEntity<InputStreamResource> createPdf() throws DocumentException, IOException{

        ByteArrayInputStream pdf = pdfService.generateResume();

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Content-Disposition","inline;file=Hitman.pdf");
        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));


    }

}

