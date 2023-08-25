package pl.weronikak98.ideas.question.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.weronikak98.ideas.question.domain.model.Answer;
import pl.weronikak98.ideas.question.domain.model.Question;
import pl.weronikak98.ideas.question.service.AnswerService;
import pl.weronikak98.ideas.question.service.QuestionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/questions/{question-id}/answers")
public class AnswerController {

    /**
     * C - create
     *   POST /questions
     *
     * R - read
     *   GET /questions
     *   GET /questions/[id]
     *
     * U - update
     *   PUT /questions/[id]
     *
     * D - delete
     *   DELETE /questions/[id]
     */

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    List<Answer> getAnswers(@PathVariable("question-id") UUID questionId) {
        return answerService.getAnswers(questionId);
    }

    @GetMapping("{answer-id}")
    Answer getAnswer(@PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerId) {
        return answerService.getAnswer(answerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Answer createAnswer (@PathVariable("question-id") UUID questionId, @RequestBody Answer answer){
        return answerService.createAnswer(questionId, answer);
    }

    @PutMapping("{answer-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Answer updateAnswer (@PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerId,
                         @RequestBody Answer answer){
        return answerService.updateAnswer(answerId, answer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{answer-id}")
    void deleteAnswer (@PathVariable("answer-id") UUID answerId){
        answerService.deleteAnswer(answerId);
    }
}


