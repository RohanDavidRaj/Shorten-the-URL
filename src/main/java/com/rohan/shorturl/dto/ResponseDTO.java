package com.rohan.shorturl.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
		private boolean error;
		private String msg;
		private Object data;

}
