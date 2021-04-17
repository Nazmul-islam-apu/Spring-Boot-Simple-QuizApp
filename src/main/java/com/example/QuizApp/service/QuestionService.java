package com.example.QuizApp.service;

import com.example.QuizApp.model.Question;
import com.example.QuizApp.model.QuestionForm;
import com.example.QuizApp.model.Result;
import com.example.QuizApp.repository.QuestionRepository;
import com.example.QuizApp.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService {
    @Autowired
    Question question;
    @Autowired
    Result result;
    @Autowired
    QuestionForm questionForm;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ResultRepository resultRepository;

    public QuestionForm getQuestions(){
        List<Question> allQuestion = questionRepository.findAll();
        List<Question> random_question = new ArrayList<Question>();
        Random random = new Random();

        for (int i =0;i<10;i++){
            int rand = random.nextInt(allQuestion.size());
            random_question.add(allQuestion.get(rand));
            allQuestion.remove(rand);
        }

        questionForm.setQuestions(random_question);

        return questionForm;

    }

    public int getResult(QuestionForm questionForm){
        int correct = 0;
        for(Question q: questionForm.getQuestions()){
            if(q.getAns()==q.getChosen()){
                correct++;
            }
        }
        return correct;
    }

    public void saveScore(Result result){
        Result newResult = new Result();
        newResult.setUsername(result.getUsername());
        newResult.setScore(result.getScore());
        resultRepository.save(newResult);
    }

    public List<Result> allResult(){
        List<Result> all_result = resultRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
        return all_result;
    }


}
