package com.mypractice.rabbitmq.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("email_id")
	private String emailId;
	@JsonProperty("phone_no")
	private String phoneNo;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("dob")
	private LocalDate dob;
	
}
