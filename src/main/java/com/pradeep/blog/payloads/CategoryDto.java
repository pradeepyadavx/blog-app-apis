package com.pradeep.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty(message = "title can not be empty ")
    @Size(message = "max 100 caharter is allowed", max = 100)
    private String categoryTitle;

    @NotEmpty(message = "Category Description can not be empty")
    private String categoryDescription;

}
