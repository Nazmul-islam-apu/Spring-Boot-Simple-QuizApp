package com.example.QuizApp.controller;

import com.example.QuizApp.model.QuestionForm;
import com.example.QuizApp.model.Result;
import com.example.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuizController {

    @Autowired
    Result result;
    @Autowired
    QuestionService questionService;

    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult(){
        return result;
    }


    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @PostMapping("/quiz")
    public String quizPage(@RequestParam String username, Model model, RedirectAttributes redirectAttributes){
        if(username.equals("")){
            redirectAttributes.addFlashAttribute("warning","You must enter your name");
            return "redirect:/";
        }
        submitted = false;
        result.setUsername(username);

        QuestionForm questionForm = questionService.getQuestions();
        model.addAttribute("qForm",questionForm);
        return "quizPage";
    }

    @PostMapping("/quizSubmit")
    public String submit(@ModelAttribute QuestionForm questionForm, Model model){
        if(!submitted){
            result.setScore(questionService.getResult(questionForm));
            questionService.saveScore(result);
            submitted = true;
        }
        model.addAttribute("result",result);
        return "result";
    }

    @GetMapping("score")
    public String showScore(Model model){
        List<Result> total_result = questionService.allResult();
        model.addAttribute("total_result",total_result);
        return "total_result";
    }

}
