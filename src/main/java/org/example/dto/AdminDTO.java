package org.example.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminDTO {

    String name;
    String email;
    int telephone;
    String password;
}