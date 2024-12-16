package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.BOOK)
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
}

