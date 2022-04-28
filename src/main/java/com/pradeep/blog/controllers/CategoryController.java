package com.pradeep.blog.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.blog.payloads.ApiResponse;
import com.pradeep.blog.payloads.CategoryDto;
import com.pradeep.blog.services.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    //POST ceate

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto cat = this.categoryService.createCategory(categoryDto);

        return new ResponseEntity<CategoryDto>(cat, HttpStatus.CREATED);

    }


    //PUT update

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {

        CategoryDto cat = this.categoryService.updateCategory(categoryDto, categoryId);

        return new ResponseEntity<CategoryDto>(cat, HttpStatus.CREATED);

    }

    //DELETE delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {

        this.categoryService.deleteCategory(categoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Succesffuly !", true, new Date()), HttpStatus.OK);
    }


    //Get get Cat
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getcategory(@PathVariable Integer categoryId) {
        CategoryDto cat = this.categoryService.getCategory(categoryId);
        return ResponseEntity.ok(cat);
    }

    // GET ALLCATE
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {

        List<CategoryDto> catList = this.categoryService.getCategories();

        return new ResponseEntity<List<CategoryDto>>(catList, HttpStatus.OK);
    }

}
