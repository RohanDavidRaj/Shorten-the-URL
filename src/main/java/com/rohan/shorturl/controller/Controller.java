package com.rohan.shorturl.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.rohan.shorturl.dto.ResponseDTO;
import com.rohan.shorturl.dto.URLRegisterDto;
import com.rohan.shorturl.service.ShortURLService;

@RestController
public class Controller {

	@Autowired
	ShortURLService service;

	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> shortTheURL(@RequestBody URLRegisterDto dto) {
		return new ResponseEntity<ResponseDTO>(
				new ResponseDTO(false, "URL Shorten Successfully", service.register(dto)), HttpStatus.OK);

	}
	
	@PostMapping("/getURL")
	private ResponseEntity<ResponseDTO> getOriginalURL(@RequestBody URLRegisterDto dto) {
		return new ResponseEntity<ResponseDTO>(
				new ResponseDTO(false,"Original URL by ShortenURL "+dto.getShortenURL(), service.getOriginalURL(dto))
				, HttpStatus.OK);

	}
	
	    
	    @GetMapping("/generateQRCode")
	    public ResponseEntity<byte[]> generateQRCode(@RequestParam String url, @RequestParam int size) {
	        try {
	            byte[] qrCodeBytes = generateQRCodeImage(url, size);
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_PNG);
	            return new ResponseEntity<>(qrCodeBytes, headers, HttpStatus.OK);
	        } catch (WriterException | IOException e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    private byte[] generateQRCodeImage(String url, int size) throws WriterException, IOException {
	        Map<EncodeHintType, Object> hints = new HashMap<>();
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, size, size, hints);

	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

	        return outputStream.toByteArray();
	    }
	
	////////////////////////////////////////////
//	@PostMapping("/sendOTP")
//    public String sendOTP(@RequestBody OTPRequest request) {
//        // Your Fast2SMS API integration code here
//        String apiKey = "9p8iMSU4gj7rvWcOfYGZA2uBNt6Q0ET3nIaleCz1xKkhDdJXFqP4sad8G9JfZ7X3YTOwQr0qN26ACLyn";
//        String senderID = "FSTSMS";
//        String message = "Your OTP is: " + request.getOTP();
//        String phoneNumber = request.getPhoneNumber();
//        String url = "https://www.fast2sms.com/dev/bulk";
//        System.err.println(request.getOTP()+" "+request.getPhoneNumber());
//
//        RestTemplate restTemplate = new RestTemplate();
//        String requestBody = "sender_id=" + senderID + "&message=" + message + "&language=english&route=p&numbers=" + phoneNumber;
//        System.err.println(requestBody);
//
//        String response = restTemplate.postForObject(url, requestBody, String.class);
//        System.err.println(response);
//        // You may want to parse the response and handle accordingly
//        
//        return response;
//    }
	////////////////////////////////////////////

}
