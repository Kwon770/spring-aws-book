package com.kwon770.book.springboot.service;

import com.kwon770.book.springboot.domain.posts.Posts;
import com.kwon770.book.springboot.domain.posts.PostsRepository;
import com.kwon770.book.springboot.web.dto.PostsResponseDto;
import com.kwon770.book.springboot.web.dto.PostsSaveRequestDto;
import com.kwon770.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No such post exists. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No such post exists. id=" + id));

        return new PostsResponseDto(entity);
    }
}
