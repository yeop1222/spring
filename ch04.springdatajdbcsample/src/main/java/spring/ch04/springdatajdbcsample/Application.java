package spring.ch04.springdatajdbcsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.ch04.springdatajdbcsample.entity.Member;
import spring.ch04.springdatajdbcsample.repository.MemberCrudRepository;

@SpringBootApplication
public class Application {

	/**
	 * 스프링 부트 기동 클래스
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args)
				.getBean(Application.class)
				.execute();
	}

	@Autowired
	MemberCrudRepository repository;

	/**
	 * 등록과 전체 취득 처리
	 */
	private void execute() {
		//등록
		executeInsert();
		//전체 취득
		executeSelect();
	}

	/**
	 * 등록
	 */
	private void executeInsert() {
		//엔티티 생성 (id는 자동생성되므로 null)
		Member member = new Member(null, "이순신");
		//리포지토리 이용해 등록하고 결과 취득
		member = repository.save(member);
		//결과 표시
		System.out.println("등록 데이터:" + member);
	}

	/**
	 * 전체 취득
	 */
	private void executeSelect() {
		System.out.println("--- 전체 데이터 취득합니다 ---");
		//리포지토리 이용해 전체 데이터 취득
		Iterable<Member> members = repository.findAll();
		for (Member member : members) {
			System.out.println(member);
		}
	}
}
