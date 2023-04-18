package com.losung.dto;

import lombok.Data;

@Data
public class ContactDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
