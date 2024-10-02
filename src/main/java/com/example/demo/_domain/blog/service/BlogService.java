package com.example.demo._domain.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo._domain.blog.dto.ArticleDTO;
import com.example.demo._domain.blog.entity.Article;
import com.example.demo._domain.blog.repository.PostRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IoC (빈으로 등록)
public class BlogService {

    // @Autowired --> DI --> 개발자들의 가독성 때문에 작성을 해 준다.
    private final PostRepository postRepository;

    @Transactional // 쓰기 지연 처리
    public Article save(ArticleDTO dto) {
        // 비즈니스 로직이 필요하다면 작성
        return postRepository.save(dto.toEntity());
    }

}
