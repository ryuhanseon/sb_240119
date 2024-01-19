package com.example.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("/list")
    public String articleList(Model model){
           List<Article> articles = this.articleService.list();
            model.addAttribute("articles", articles);
        return "article_list";
    }
    @GetMapping("/create")
    public String articleCreate(){
        return "create_form";
    }
    @PostMapping("/create")
    public String articleCreate(@RequestParam(value = "subject")String subject,
                                @RequestParam(value = "content")String content){
        this.articleService.create(subject, content);
        return "redirect:/article/list";
    }
    @GetMapping("/detail/{id}")
    public String articleDetail(@PathVariable("id")Long id,Model model){
        Article article = this.articleService.detail(id);
        model.addAttribute("article", article);
        return "article_detail";
    }
    @GetMapping("/modify/{id}")
    public String articleModify(@PathVariable("id")Long id,Model model){
        Article article = this.articleService.detail(id);
        model.addAttribute("article", article);
        return "modify_form";

    }
    @PostMapping("/modify/{id}")
    public String articleModify(@RequestParam(value = "subject")String subject,
                                @RequestParam(value = "content")String content,
                                @PathVariable("id") Long id
                                ){
        Article article = this.articleService.detail(id);

        this.articleService.modify(subject,content,article);

        return "redirect:/article/detail/"+id;
    }
    @GetMapping("/delete/{id}")
    public String articleDelete(@PathVariable("id")Long id
                                ){
        Article article = this.articleService.detail(id);
        this.articleService.delete(article);

        return "redirect:/article/list";
    }

}
