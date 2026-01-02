# API 명세서

# ERD
<img width="1223" height="494" alt="image" src="https://github.com/user-attachments/assets/c4029b81-c263-47a0-8ffd-1d36dd4ea7d2" />

# 스프링 과제

<details>
<summary><strong> 이론 소개 </strong></summary>

<img width="2000" height="500" alt="image" src="https://github.com/user-attachments/assets/56f1227b-0310-4716-a25d-beb8e44c71b2" />

### 클라이언트에서 요청(request)을 하면 그에 대한 응답(response)이 옵니다.
- 각각의 레이어를 연결해주는 것을 **dto**라고 합니다.
- DB와 레포지터리를 연결해주는 것을 **Entity**라 합니다.

### Controller 기능 : HTTP 요청을 서비스로 전달
- URL 매핑 : /schedules
- HTTP 방식 구분 : @PostMapping, @GetMapping, @PutMapping, @DeleteMapping
- 요청 데이터 받기 : @RequestBody, @PathVariable
- 응답 형태 결정 : ResponseEntity, 상태 코드

### Service 기능 : 비즈니스 로직 + 트랜잭션(DB 작업)

### Repository 기능
- DB와 소통


</details>

# 공통 조건
- `3 Layer Architecture`에 따라 각 Layer의 목적에 맞게 개발해야 합니다.
- CRUD 필수 기능은 모두 데이터베이스 연결 및 `JPA`를 사용해서 개발해야 합니다.
- 일정 작성, 수정, 조회 시 반환 받은 일정 정보에 `비밀번호`는 제외해야 합니다.
- 일정 수정, 삭제 시 선택한 일정의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 가능합니다.

# 필수 과제
<details>
<summary><strong> 1. 일정 생성 (일정 작성하기) </strong></summary>

## @PostMapping
- 일정 제목 : `title`
- 일정 내용 : `content`
- 작성자 : `author`
- 비밀번호 : `password`
- 작성/수정일 : `createdAt / modifiedAt`

- API 응답에 비밀번호는 제외
</details>

<details>
<summary><strong> 2. 전체 일정 조회 </strong></summary>
  
## @GetMapping
### 전체 조회
- 작성자명(`author`) 기준으로 등록된 일정 목록 조회(조회에 포함시킴)
  - 하나의 API로 작성
  - 수정일 기준 내림차순
  
> API 응답에 비밀번호는 제외
### 선택 조회
- 일정의 고유 ID로 선택한 일정 조회

> API 응답에 비밀번호는 제외

</details>

<details>
<summary><strong> 3. 선택한 일정 수정하기 </strong></summary>
  
## @PutMapping
- 수정 가능 항목 : 일정 제목 (`title`), 작성자명 (`author`)
    - 서비스에 일정 수정을 요청할 때 `비밀번호`를 함께 전달
    - `작성일`은 변경 불가. `수정일`은 수정 완료 시점으로 변경
      
- API 응답에 `비밀번호`는 제외

</details>

<details>
<summary><strong> 4. 선택한 일정 삭제하기 </strong></summary>
  
## @DeleteMapping
- 삭제 시 비밀번호 함께 전달
- 존재 여부 체크
  - 없으면 에러
  - 있으면 삭제


</details>
 
# 도전 과제

<details>
<summary><strong> 5. 댓글 생성(댓글 작성하기) </strong></summary>
  
### 일정에 댓글을 작성할 수 있습니다.
  - 댓글 생성 시, 포함되어야할 데이터
    - `댓글 내용`, `작성자명`, `비밀번호`, `작성/수정일`, `일정 고유식별자(ID)`를 저장
      - `작성/수정일`은 날짜와 시간을 모두 포함한 형태
    - 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
    - 최초 생성 시, `수정일`은 `작성일`과 동일
    - `작성일`, `수정일` 필드는 `JPA Auditing`을 활용하여 적용합니다.
    - 하나의 일정에는 댓글을 10개까지만 작성할 수 있습니다.
</details>

<details>
<summary><strong> 6. 일정 단건 조회 업그레이드 </strong></summary>
  
###  일정 단건 조회 업그레이드
- 일정 단건 조회 시, 해당 일정에 등록된 댓글들을 포함하여 함께 응답합니다.
<img width="732" height="1089" alt="image" src="https://github.com/user-attachments/assets/a27a1bed-2d29-4e36-95f3-9498bbf5a213" />

</details>

# TIL
<details>
<summary><strong> 새로 안 것들 </strong></summary>

### findById와 existsById 기능
#### findById : Optional<엔티티> 반환
- 수정은 존재 + 데이터 + 변경 기능이 필요하므로 findById를 쓰고 
#### existsById : boolean 값 반환 
- 삭제는 존재 여부만 필요하므로 해당 id가 있는지 존재 여부만 파악하면 deleteById로 지우면 되니 더 간단한 exsitsById를 쓴다.
#### (그러나 해당 프로젝트에서는 비밀번호 인증 때문에 findById를 쓴다...)
## JPA의 마법 : 정해진 단어만 수정하지 않으면 알아서 맞춰줌 ㅇㅇ : countBy, findBy, Desc 등등 고유명사에 붙여도 멀쩡히 기능함!!!
예) **findAllByOrderBy**ModifiedAt**Desc**()

</details>

<details>
<summary><strong> 참고 주소 </strong></summary>
- 정렬 방법 : https://velog.io/@kimdy0915/Spring-Data-JPA%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%A0%95%EB%A0%AC-%EA%B5%AC%ED%98%84JPA-method-Pageable-Page
- 수정/삭제 : https://sjparkk-dev1og.tistory.com/147#google_vignette
- 댓글수 제한 : https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
</details>
