package br.msmed.voll.qrcodegenetation.controller;

import br.msmed.voll.qrcodegenetation.QrCodeGeneratorService;
import br.msmed.voll.qrcodegenetation.dto.QrCodeGenerationRequest;
import br.msmed.voll.qrcodegenetation.dto.QrCodeGenerationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerationResponse> generate(@RequestBody QrCodeGenerationRequest request){
       try {
            QrCodeGenerationResponse response = this.qrCodeGeneratorService.generateQrCode(request.text());
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
