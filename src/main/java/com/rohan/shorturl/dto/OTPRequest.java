package com.rohan.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OTPRequest {
    private String phoneNumber;
    private String OTP;

    // Getters and setters
}
