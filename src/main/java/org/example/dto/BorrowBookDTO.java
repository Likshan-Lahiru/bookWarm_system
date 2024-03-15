package org.example.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BorrowBookDTO {
    String id;
    UserDTO user;
    BookDTO book;
    LocalDate borrowDate;
    LocalDate returnDate;
    String status;
}