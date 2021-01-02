package com.min.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class LoginVo {
	private String id;
	private String password;
	private String enabled;
	
	
}
