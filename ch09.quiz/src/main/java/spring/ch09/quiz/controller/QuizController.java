package spring.ch09.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.ch09.quiz.entity.Quiz;
import spring.ch09.quiz.form.QuizForm;
import spring.ch09.quiz.service.QuizService;

import java.util.Optional;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    /** DI 대상 */
    @Autowired
    QuizService service;

    /** form-backing bean 초기화 */
    @ModelAttribute
    public QuizForm setUpForm() {
        QuizForm form = new QuizForm();
        //라디오 버튼의 초기값 결정
        form.setAnswer(true);
        return form;
    }

    /** 퀴즈 목록 표시 */
    @GetMapping
    public String showList(QuizForm quizForm, Model model) {
        //신규 등록 설정
        quizForm.setNewQuiz(true);

        //퀴즈 목록 취득
        Iterable<Quiz> list = service.selectAll();

        //표시용 모델에 저장
        model.addAttribute("list",list);
        model.addAttribute("title", "등록 폼");

        return "crud";
    }

    /** Quiz 데이터 1건 등록 */
    @PostMapping("/insert")
    public String insert(@Validated QuizForm quizForm, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes) {

        //Form 에서 Entity 로 넣기
        Quiz quiz = new Quiz();
        quiz.setQuestion(quizForm.getQuestion());
        quiz.setAnswer(quizForm.getAnswer());
        quiz.setAuthor(quizForm.getAuthor());

        //입력 체크
        if (!bindingResult.hasErrors()) {
            service.insertQuiz(quiz);
            redirectAttributes.addFlashAttribute("complete", "등록이 완료되었습니다.");
            return "redirect:/quiz";
        } else {
            //에러 발생시 목록 표시로 변경
            return showList(quizForm, model);
        }
    }

    /** Quiz 데이터를 1건 취득해서 폼 안에 표시 */
    @GetMapping("/{id}")
    public String showUpdate(QuizForm quizForm, @PathVariable Integer id, Model model) {
        //Quiz 취득 (Optional)
        Optional<Quiz> quizOpt = service.selectOneById(id);

        //QuizForm 에 채우기
        Optional<QuizForm> quizFormOpt = quizOpt.map(t -> makeQuizForm(t));

        //QuizForm 이 null 아니면 값 획득
        if(quizFormOpt.isPresent()) {
            quizForm = quizFormOpt.get();
        }

        //변경용 모델 생성
        makeUpdateModel(quizForm, model);
        return "crud";
    }

    /** 변경용 모델 생성 */
    private void makeUpdateModel(QuizForm quizForm, Model model) {
        model.addAttribute("id", quizForm.getId());
        quizForm.setNewQuiz(false);
        model.addAttribute("quizForm", quizForm);
        model.addAttribute("title", "변경 폼");
    }

    /** id 를 키로 사용해 데이터 변경 */
    @PostMapping("/update")
    public String update(@Validated QuizForm quizForm, BindingResult result,
                         Model model, RedirectAttributes redirectAttributes) {
        //QuizForm 에서 Quiz로 채우기
        Quiz quiz = makeQuiz(quizForm);
        //입력 체크
        if(!result.hasErrors()) {
            //변경 처리, Flash scope 사용해서 리다이렉트 설정
            service.updateQuiz(quiz);
            redirectAttributes.addFlashAttribute("complete", "변경이 완료되었습니다.");
            //변경 화면 표시
            return "redirect:/quiz/" + quiz.getId();
        } else {
            //변경용 모델 생성
            makeUpdateModel(quizForm, model);
            return "crud";
        }
    }

    /** id 를 키로 사용해 데이터 삭제 */
    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
        //퀴즈 정보 1건 삭제하고 리다이렉트
        service.deleteQuizById(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("delcomplete", "삭제 완료.");
        return "redirect:/quiz";
    }

    /** Quiz 데이터를 랜덤으로 한 건 가져와 화면에 표시 */
    @GetMapping("/play")
    public String showQuiz(QuizForm quizForm, Model model) {
        //Quiz 정보 획득 (Optional)
        Optional<Quiz> quizOpt = service.selectOneRandomQuiz();

        //값 있는지 확인
        if(quizOpt.isPresent()) {
            //QuizForm으로 채우기
            Optional<QuizForm> quizFormOpt = quizOpt.map(t->makeQuizForm(t));
            quizForm = quizFormOpt.get();
        } else {
            model.addAttribute("msg", "등록된 문제가 없습니다.");
            return "play";
        }

        // 표시용 모델에 저장
        model.addAttribute("quizForm", quizForm);
        return "play";
    }

    /** 퀴즈의 정오답 판단 */
    @PostMapping("/check")
    public String checkQuiz(QuizForm quizForm, @RequestParam Boolean answer, Model model) {
        if(service.checkQuiz(quizForm.getId(), answer)) {
            model.addAttribute("msg", "정답입니다.");
        } else {
            model.addAttribute("msg", "오답입니다.");
        }

        return "answer";
    }

    // ------------ 여기부터 Form 과 도메인 객체 다시 채우기 ----------------
    /** QuizForm 에서 Quiz 로 채우기, 반환값으로 리턴 */
    private Quiz makeQuiz(QuizForm quizForm) {
        Quiz quiz = new Quiz();
        quiz.setId(quizForm.getId());
        quiz.setQuestion(quizForm.getQuestion());
        quiz.setAnswer(quizForm.getAnswer());
        quiz.setAuthor(quizForm.getAuthor());
        return quiz;
    }

    /** Quiz 에서 QuizForm 으로 채우기, 반환값으로 리턴 */
    private QuizForm makeQuizForm(Quiz quiz) {
        QuizForm form = new QuizForm();
        form.setId(quiz.getId());
        form.setQuestion(quiz.getQuestion());
        form.setAnswer(quiz.getAnswer());
        form.setAuthor(quiz.getAuthor());
        form.setNewQuiz(false);
        return form;
    }
}
