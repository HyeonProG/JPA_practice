package com.example.demo._domain.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo._domain.blog.dto.ArticleDTO;
import com.example.demo._domain.blog.entity.Article;
import com.example.demo._domain.blog.repository.PostRepository;
import com.example.demo.common.ApiUtil;
import com.example.demo.common.errors.Exception400;

import java.util.List;

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

    // 전체 게시글 조회 기능
    public List<Article> findAll() {
        List<Article> articles = postRepository.findAll();
        return articles;
    }

    // 게시글 상세 보기 조회
    public Article findById(Integer id) {
        // Optional<T>는 Java 8 버전에서 도입된 클래스
        // 값이 존재할 수도 있고 업을 수도 있는 상황을 명확하게 처리하기 위해 사용
        return postRepository.findById(id).orElseThrow(() -> new Exception400("해당 게시글이 없습니다."));
    }

}
