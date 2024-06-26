package com.primer_parcial.shop.service;

import com.primer_parcial.shop.model.Article;

import java.util.List;

public interface ArticleService {
    Article createArticle(Article article);
    Article getArticleById(Long id);
    List<Article> getAllArticle();
    Article updateArticle(Long id, Article newArticle);

    Article deleteArticle(Long id);
}
