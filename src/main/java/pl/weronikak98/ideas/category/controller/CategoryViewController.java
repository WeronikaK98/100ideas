package pl.weronikak98.ideas.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.weronikak98.ideas.category.domain.model.Category;
import pl.weronikak98.ideas.category.service.CategoryService;
import pl.weronikak98.ideas.question.domain.model.Question;
import pl.weronikak98.ideas.question.service.QuestionService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryViewController {

    private final CategoryService categoryService;
    private final QuestionService questionService;

    public CategoryViewController(CategoryService categoryService, QuestionService questionService) {
        this.categoryService = categoryService;
        this.questionService = questionService;
    }

    @GetMapping("{id}")
    String singleView (@PathVariable UUID id, Model model){
        Category category = categoryService.getCategory(id);
        List<Question> questions = questionService.findAllByCategoryId(id);

        model.addAttribute("category", category);
        model.addAttribute("questions", questions);

        return "category/single";
    }
}
