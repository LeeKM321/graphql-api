package com.codeit.graphql.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Record로 선언하셔도 됩니다!
@Getter @Setter @ToString
public class UpdateBookInput {
    private String title;
    private String isbn;
    private Integer publishedYear;
    private Double price;
    private Long authorId;
}
