package collection.ex;

import java.util.*;

// 역할 - 데이터를 보관하고 관리하는 클래스
public class MemberRepository {

  private List<Member> memberList = new ArrayList<>();
  private Map<Integer, Member> memberMap = new HashMap<>();
  private Set<String> emailSet = new HashSet<>(); // 이메일 중복 체크
  // 회원 가입하면 ID 를 자동으로 1씩 증가하는 방법으로 회원에게 할당할 예정
  private int nextId = 1;

  // 회원가입
  public boolean save(String name, String email, int age) {
    // 이메일 중복 확인
    if (emailSet.contains(email)) {
      System.out.println("[오류] 이미 사용 중인 이메일입니다 " + email);
      return false;
    }
    Member member = new Member(nextId, name, email, age);
    memberList.add(member);
    memberMap.put(nextId, member);
    emailSet.add(email);
    nextId++;

    System.out.println("[완료] 회원가입 : " + member.getName());
    return true;
  }

  // ID로 회원 조회  -- 1(입력) --> 반환(Member)
  public Member findById(int id) {
    return memberMap.get(id);
  }

  // 전체 회원 목록 조회
  public List<Member> findAll() {
    return memberList;
  }

  // 이름으로 회원 조회
  public List<Member> findByName(String name) {
    List<Member> result = new ArrayList<>();
    for (Member member : memberList) {
      if (member.getName().equalsIgnoreCase(name)) {
        result.add(member);
      }
    }
    return result;
  }

  // 회원 정보 수
  // 1. id 기준으로 회원정보 조회
  // 2. 새로운 이름 정보 필요
  // 3. 새로운 나이 정보 필요
  // 4. 이메일은 중복 처리 떄문에 수정 불가하게 막을 예정.
  public boolean update(int id, String newName, int newAge) {
    // 수정을 하려면 반드시 먼저 조회를 해야한다.
    Member member = memberMap.get(id);
    if (member == null) {
      System.out.println("존재하지 않는 회원입니다 : " + id);
      return false;
    }
    member.setName(newName);
    member.setAge(newAge);
    System.out.println("[완료] 수정됨 : " + member);
    return true;
  }

  // 전체 회원 수
  public int count() {
    return memberList.size();
  }

  // 회원 삭제하기
  public boolean delete(int id) {
    // 먼저 조회부터 하고 삭제하기
    Member member = memberMap.get(id);
    if (member == null) {
      System.out.println("존재하지 않는 회원입니다 ID : " + id);
      return false;
    }

    // list, map, set - 동기화 처리 중요
    memberList.remove(id - 1);
    memberMap.remove(id);
    emailSet.remove(member.getEmail());
    System.out.println("삭제됨 : " + member.getEmail());
    return true;
  }

  public List<Member> findByAgeRange(int min, int max) {
    List<Member> result = new ArrayList<>();
    for (Member m : memberList) {
      if (m.getAge() >= min && m.getAge() <= max) {
        result.add(m);
      }
    }
    return result;
  }

  public Member findByEmail(String email) {
    if (!emailSet.contains(email)) {
      return null;
    } else {
      for (Member member : memberList) {
        if (member.getEmail().equals(email)) {
          return member;
        }
      }
    }
    return null;


  }


}
