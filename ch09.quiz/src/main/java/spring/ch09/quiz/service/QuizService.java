package spring.ch09.quiz.service;

import spring.ch09.quiz.entity.Quiz;

import java.util.Optional;

/** Quiz service */
public interface QuizService {

    /** 등록된 모든 퀴즈 정보 가져옴 */
    Iterable<Quiz> selectAll();

    /** id를 키로 사용해 퀴즈 정보 한건 가져 옴 */
    Optional<Quiz> selectOneById(Integer id);

    /** 퀴즈 정보를 랜덤으로 한 건 가져옴 */
    Optional<Quiz> selectOneRandomQuiz();

    /** 퀴즈의 정오답 여부 판단 */
    Boolean checkQuiz(Integer id, Boolean myAnswer);

    /** 퀴즈 등록 */
    void insertQuiz(Quiz quiz);

    /** 퀴즈 변경 */
    void updateQuiz(Quiz quiz);

    /** 퀴즈 삭제 */
    void deleteQuizById(Integer id);
}
