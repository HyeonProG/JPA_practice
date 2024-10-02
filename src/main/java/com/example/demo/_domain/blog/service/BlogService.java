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

    // 수정 비즈니스 로직에 대한 생각!
    // 영속성 컨텍스트에서 또는 DB에 존재하는 Article 엔티티(row)를 가지고 와서
    // 상태 값을 수정하고 그 결과를 호출한 곳을 반환 한다.
    @Transactional
    public Article update(Integer id, ArticleDTO dto) {
        // 수정 로직
        Article articleEntity = postRepository
                                .findById(id)
                                .orElseThrow(() -> new Exception400("NOT FOUND : " + id));
        // 객체 상태 값 변경
        articleEntity.update(dto.getTitle(), dto.getContent());

        // 영속성 컨텍스트 - 더티 체킹 알아보기
        // 레포지토리의 save() 메서드는 수정할 때도 사용 가능하다.
        // 단, 호출하지 않는 이유는 더티 체킹(Dirty Checking)이 동작하기 때문
        // 즉, 트랜잭션 커밋 시 자동으로 영속성 컨텍스트와 데이터페이스(DB)에 변경 사항이 반영된다.

        // DB에 save 처리
        // postRepository.save(articleEntity);

        return articleEntity;
    }

}
