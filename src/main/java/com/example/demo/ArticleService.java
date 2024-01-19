package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> list(){
        List<Article> articleList = this.articleRepository.findAll();
        return articleList;
    }

    public void create(String subject, String content){
        Article a = new Article();
        a.setSubject(subject);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(a);
    }
    public Article detail(Long id){

        Optional<Article> optionalArticle = this.articleRepository.findById(id);

        if (optionalArticle.isEmpty()) {
            throw  new RuntimeException("x");
        }

        return optionalArticle.get();
    }
    public void modify(String subject, String content,Article article){
        article.setContent(content);
        article.setSubject(subject);

        this.articleRepository.save(article);
    }
    public void delete(Article article){

        this.articleRepository.delete(article);
    }

}
