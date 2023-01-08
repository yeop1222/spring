package spring.ch04.springdatajdbcsample.repository;

import org.springframework.data.repository.CrudRepository;
import spring.ch04.springdatajdbcsample.entity.Member;

/**
 * Member 테이블: 리포지토리
 */
public interface MemberCrudRepository extends CrudRepository<Member, Integer> {
}
