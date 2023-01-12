package spring.ch09.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ch09.quiz.entity.Quiz;
import spring.ch09.quiz.repository.QuizRepository;

import java.util.Optional;

@Service
@Transactional
public class QuizServiceImpl implements QuizService{

    /** Repository injection */
    @Autowired
    QuizRepository repository;

    @Override
    public Iterable<Quiz> selectAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Quiz> selectOneById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Quiz> selectOneRandomQuiz() {

        //랜덤으로 id값 가져옴
        Integer randId = repository.getRandomId();

        //퀴즈가 없으면
        if(randId == null) {
            //빈 Optional 인스턴스 반환
            return Optional.empty();
        }
        return repository.findById(randId);
    }

    @Override
    public Boolean checkQuiz(Integer id, Boolean myAnswer) {
        //퀴즈 정오답 판단용 변수
        Boolean check = false;

        //대상 퀴즈 가져오기
        Optional<Quiz> optQuiz = repository.findById(id);

        //퀴즈 가져왔는지 확인
        if(optQuiz.isPresent()) {
            Quiz quiz = optQuiz.get();
            //정답 확인
            if(quiz.getAnswer().equals(myAnswer)) {
                check = true;
            }
        }
        return check;
    }

    @Override
    public void insertQuiz(Quiz quiz) {
        repository.save(quiz);
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        repository.save(quiz);
    }

    @Override
    public void deleteQuizById(Integer id) {
        repository.deleteById(id);
    }
}
