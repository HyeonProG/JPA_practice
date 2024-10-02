package com.example.demo._domain.blog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo._domain.blog.dto.ArticleDTO;
import com.example.demo._domain.blog.entity.Article;
import com.example.demo._domain.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController // @Controller + @ResponseBody
public class BlogApiController {

    private final BlogService blogService;

    // URL 즉, 주소 설계 - http://localhost:8080/api/article

    @PostMapping("/api/article")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleDTO dto) {
        // 1. 인증 검사
        // 2. 유효성 검사

        Article savedArticle = blogService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }
    

}
