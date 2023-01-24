package com.ecommerce.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    @Size(min = 3,max = 10,message = "invalid firstname name(3-10 character)")
    private String firstName;

    @Size(min = 3,max = 10,message = "invalid lastname name(3-10 character)")
    private String lastName;
    private String username;

    @Size(min = 5,max = 15,message = "invalid password name(5-15 character)")
    private String password;

    private String repeatPassword;
}

