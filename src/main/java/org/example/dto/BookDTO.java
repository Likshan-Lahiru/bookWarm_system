package org.example.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDTO {
    String id;
    String title;
    String author;
    String genre;
    String status;
    BranchDTO branch;
}
