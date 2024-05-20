# 📖 SangSangjakka


<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/7cd401e1-a565-4899-9ff3-4d8f5ab294d4" width="750" height="400"/>




## 프로젝트 소개

- 상상자까는 이미지 생성 AI와 문장 생성 AI를 활용한 동화책 제작 및 공유 홈페이지입니다. 성장 시기에 맞는 책 구입의 재정적 어려움과 제한된 동화책 선택의 한계를 극복하며, 어린 나이부터 접하는 디지털 미디어 중독 문제를 해결하고자합니다.
- 사용자별 취향, 선호도 파악을 통해 동화책 맞춤 추천과 AI 이미지 생성시 취향별 그림체 적용이 가능합니다.
- 다른 사용자의 동화책을 읽고 리뷰를 남길 수 있습니다.

## 팀원 구성
곽지현, 김은솔. 박동수, 박미영, 송성혜, 정원혁, 황시원

## 1. 개발 기간
- 전체 개발 기간: 24.04.19 - 24.05.13
- DB 구현: 24.04.24 - 24.04.28
- UI 구현: 24.04.25 - 24.05.07
- 기능 구현:  24.04.30 - 24.05.12

## 2. 개발 환경
### 프론트 엔드
HTML5,
CSS3,
JavaScript (ES6),
jQuery,
AJAX
### 백엔드
Tomcat 9.0,
Java (JDK 11),
JSP,
Servlet
### 데이터베이스
Oracle DB 11C
### 개발 도구
Eclipse 2022-06 R,
Visual Studio Code,
SQL Developer 23.1.1
### 개발 환경
Window 10,
Window 11
### 버전 관리
Git
### 클라우드 서비스
AWS EC2
### 협업 도구
GitHub,
Notion,
Discord
### AI 기술
Stability AI,
DeepL
### 라이브러리 및 JAR파일
commons-io-2.16.1.jar,
commons-lang3-3.14.0.jar,
cos.jar,
deepl-java.jar,
gson-2.10.1.jar,
json-simple-1.1.1.jar,
jstl-1.2.jar,
lombok-1.18.30.jar,
ojdbc8.jar,
slf4j-api-2.0.13.jar,
slf4j-simple-2.0.13.jar,
taglibs-standard-impl-1.2.5.jar,
taglibs-standard-jstlel-1.2.5.jar,
taglibs-standard-spec-1.2.5.jar
## 3. 프로젝트 구조
## 4. 역할 분담
- 곽지현:
DAO, DTO작업
생성형AI API연동
문장 번역 API연동
사용자 백데이터 수집
가중치 정보 생성
관리자 로그
사용자 로그
공지사항 게시판
- 김은솔: 
마이페이지 CSS +
개인정보, 내가 쓴 글 관리 
명예의 전당 CSS + Data Reading
PPT
- 박동수: 
마이페이지 탭기능, 차트, 보관함
문장 생성 ai 연결
동화책 제작 프런트+백+로직
- 박미영: 
관리자 페이지 프론트
관리자 로그인
관리자 동화책 관리(동화책 수상 관리, 신고동화책 관리, 개인 저장소 기본 용량 관리), 
관리자 게시판 관리(공지사항, 건의사항, 자유게시판, 동화 공유게시판, 리뷰 관리, 댓글 관리)
- 송성혜: 
웹 사이트 디자인 및 전반적인CSS
메인 페이지
자유게시판 CRUD
동화 공유 게시판 CRUD
동화 공유 게시판 리뷰 CRUD
회원가입 
명예의 전당 UX + UI
동화제작 UX + UI
- 정원혁: 
로그인
로그아웃
건의사항 CRUD
자유게시판 댓글 CRUD
동화 공유 게시판 프론트
- 황시원: 
대시보드 회원, 동화책, 게시판, 관리자별 각각 통계 및 차트
관리자 관리 CRUD
회원 관리 CRUD
DB작업
관리자페이지 기본 CSS

## 5. 신경 쓴 부분
### 1. SOLID 원칙 준수

- 자유게시판(게시글, 댓글, 신고, 검색)
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/c0cbc150-f310-4280-a6ff-d0ebea88171e" width="600" height="300"/>

- 사용자, 관리자
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/71918ecc-306f-49c0-ac74-04e897fffe57" width="600" height="300"/>

- 건의 게시판(게시글, 댓글, 검색)
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/41a0a128-342a-43e3-8655-8054168dae71" width="600" height="300"/>

  
### 2. 의존성 주입
다른 클래스에서 DAO 객체를 직접 생성하지 않고 DAOManager 통해 객체를 받아 사용하여  
**유지보수성**과 **확장성**을 향상하고 **테스트**에 용이하도록 했습니다.

<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/f3ae1081-39ab-4ca2-9dce-bb60eab2dd7a" width="600" height="300"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/f3d2e5a1-7f36-4267-b5a1-3a9fa5ee1f01" width="600" height="200"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/2e73ae06-a86a-4df3-aaaf-9e066695c8fc" width="600" height="150"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/319136ed-ca05-471b-ae25-602587cddb4f" width="600" height="70"/>



 
### 6. 페이지별 기능

### 7. 회고

##### 곽지현
이번 프로젝트에서 팀장으로서 팀원들과 협력하여 주요 기능들을 구현하고 프로젝트를 성공적으로 완수했습니다. 인터페이스를 역할에 따라 분리하고 추상화하여 설계함으로써 구현체에 의존하지 않는 이점을 얻었지만, 프로젝트 진행 중 인터페이스 변경이 잦아지는 문제점이 발견되었습니다.
 향후에는 인터페이스 설계 시 단일 책임 원칙을 엄격히 적용하고 추상화 수준을 적절히 조정하여 안정성과 유연성을 높이는 것이 중요하다는 교훈을 얻었습니다. 또한, 사용자 로그인 체크와 관리자 로그 기능 구현 시 하드코딩으로 인한 코드 중복과 유지보수성 저하 문제를 경험했습니다. 이러한 문제를 해결하기 위해 필터을 접목했다면 코드의 모듈화와 재사용성을 높일 수 있었을 것입니다.
 이번 프로젝트의 경험을 바탕으로 인터페이스 설계의 중요성과 필터의 큰 개념인 AOP의 장점을 깨닫고, 더 나은 설계와 구현을 위해 노력할 것입니다. 다음 프로젝트에서는 AOP를 적극 활용하여 공통기능을 분리하고, 코드의 품질과 유지보수성을 향상시키고자 합니다.
##### 김은솔
이번 프로젝트를 통해 객체와 자바스크립트에 대한 이해도가 크게 향상되었습니다. 처음에는 Ajax를 사용하는 것이 익숙하지 않아 원하는 데이터를 가져오는 데 어려움을 겪었지만, 코드를 한 줄씩 분석하면서 이해도를 높여나갔습니다. 결과적으로 전당페이지와 마이페이지의 기능을 구현할 수 있었고 정보를 담고 출력하고 다듬는 과정에서 객체에 대한 이해도가 증가했습니다. 
CSS에서 float와 flex 등을 사용하여 화면 정렬에 대한 이해도를 높이고, 사용자의 편의성을 고려한 인터페이스를 고민해보는 시간이 되었습니다.
 프로젝트를 통해 모호하게만 느꼈던 DB, 프론트엔드, 백엔드의 역할을 구분할 수 있게되었습니다. 각 역할은 서로 다르지만 유기적으로 연결되어 있고 체계적인 구조를 갖추면 코드의 가독성과 유지보수성이 향상될 수 있음을 깨달았습니다. 다음 프로젝트나 업무를 진행할 때는 체계적인 코딩을 해야된다는 생각이 들었습니다.
 시간이 촉박해서 코드를 통일성 있게 짜지 못하고 추가하고 싶었던 명예의전당 월 별 조회 기능을 완성하지 못해 아쉬움이 남았습니다. 따라서 리팩토링을 통해 코드를 간결하게 만들고 기능을 완성해보고싶습니다. 
##### 박동수
이번 프로젝트를 통해 자바스크립트와 서블릿에 대한 이해도가 크게 향상되었습니다. 페이지 전환, 생성형 AI API 연결, 자바스크립트 동적 생성 등의 다양한 기술적 도전 과제를 해결하면서 많은 성장을 이뤘습니다. 자바스크립트와 서블릿을 사용해 웹 페이지를 동적으로 전환하는 과정에서 여러 문제를 해결하며 해당 기술에 대한 깊은 이해를 얻었고, API 호출과 데이터 처리에 대한 경험을 쌓았습니다. 동적 자바스크립트 생성에서는 DOM 조작, 이벤트 처리, 비동기 프로그래밍 등 자바스크립트의 핵심 개념들을 실무적으로 활용했습니다. 또한, 서블릿을 통해 서버와 클라이언트 간의 통신을 직접 구현해 보면서 이해도를 높였습니다.
 동화책 제작 로직 개발 과정에서는 사용자 데이터를 기반으로 맞춤형 콘텐츠를 생성하는 알고리즘을 구현하면서 데이터 분석과 처리에 대한 실질적인 경험을 쌓았습니다. 이 경험들은 앞으로의 개발 업무에 큰 자산이 될 것이며, 기술적 도전에 대한 자신감을 높여 주었습니다. 자바스크립트와 서블릿을 활용한 웹 애플리케이션 개발과 생성형 AI API 연동 경험은 매우 의미 있었습니다. 앞으로도 이러한 경험을 바탕으로 더욱 발전하는 개발자가 되기를 기대합니다.
##### 박미영
이번 프로젝트에서 관리자 페이지를 맡게 되어 프론트 구현에 대한 부담은 적었으나 사용자와 관리자 양측을 모두 고려하여 기능을 구현해야 했습니다.
 프로젝트 진행 중 Servlet에서 JSP로 데이터를 전달하는 과정에서 값이 제대로 전달되지 않는 문제가 발생할 때마다 이를 해결하기 위해 전체 코드를 되짚어 보며 JSP와 Servlet의 동작 방식을 깊이 이해하는 좋은 경험을 할 수 있었습니다.
 JavaScript로 동적 UI를 구성하는 과정에서도 예상치 못한 동작과 스타일 문제를 겪었지만, 브라우저 개발자 도구로 디버깅하고 CSS 스타일을 수정하며 문제를 해결했습니다. 또한, AJAX 요청 처리 중 서버 응답 오류가 발생했으나, 서버 로그와 클라이언트 콘솔을 통해 디버깅하고 서버 설정을 수정하여 안정적인 비동기 호출을 구현할 수 있었습니다. 이를 통해 웹 기술 전반에 대한 이해를 높이고 문제 해결 능력을 향상시킬 수 있었습니다.
 시간적인 한계로 인해 세밀한 부분을 살펴보지 못한 아쉬움이 남지만, 프로젝트를 성공적으로 완료하게 되어 기쁩니다. 이번 프로젝트에서 디버깅의 중요성을 경험했으며, 다음 프로젝트에서는 JUnit을 공부하여 TDD 기반으로 개발을 진행하고 싶습니다. 테스트 코드를 먼저 작성하여 코드의 품질을 향상시키고 보다 효율적이고 안정적인 기능을 구현하고 싶습니다.
##### 송성혜
이번 프로젝트를 통해 프론트엔드와 백엔드 양쪽에서 많은 성장을 이루었습니다. 프론트엔드에서는 특히 CSS의 position과 grid를 효과적으로 활용하게 되면서, 그동안 어려웠던 이론들이 자연스럽게 익숙해졌습니다.
 백엔드 작업에서는 실제 요청을 보내고 데이터를 받아 수정하여 화면에 출력하는 과정이 매우 흥미로웠습니다. 물론 요청이 제대로 처리되지 않거나 데이터베이스 쿼리에 문제가 생기는 경우도 있었지만, 이러한 에러를 console.log나 System.out.println을 통해 확인하고 해결하는 과정이 큰 재미를 주었습니다. 에러를 검색하고 해결하면서 문제 해결 능력도 많이 향상되었습니다.
 이번 경험을 통해 프론트엔드와 백엔드 모두에서 많은 성장을 이룰 수 있었습니다. 프로젝트를 진행하면서 쌓은 지식과 경험이 앞으로의 개발 작업에 큰 도움이 될 거 같습니다.
##### 정원혁
프로젝트 진행 전 할 수 있을까에 대한 두려움이 많았습니다. 
직접 인터넷을 찾아보며 게시판 만들기도 해보았지만 벽에 부딛힐 때가 많았습니다. 프론트 작업을 하면서는 어떤 화면을 구성해야겠다 라는 기초 틀을 잡고 작업을 하니 훨씬 수월했고 dao안에서 어떤 항목에 대해 물음표 변수로 처리를 해야하는지, 어떤 결과를 들고 dto에 담아 jsp 페이지에서 EL로 처리를 해야되는지 많이 배우고 이해할 수 있는 시간이었습니다. 
 힘들고 누군가의 코드를 보고 그냥 결과물만 뽑아내고 싶다라는 생각도 많았지만 직접 몸소 부딛혀보고 떠오르는 생각을 코드로 작성하고 구현하고자 하는걸 성공했을 때 뿌듯함은 배가 됐습니다. 이번 프로젝트를 통해 앞으로도 많은 어려운 상황이 닥쳐도 해결해나가는 재미도 느낄 수 있었고 앞으로 이런 과정을 더 경험해서 성장할 수 있는 발판을 마련할 수 있었던 것 같습니다.
##### 황시원
이번 프로젝트를 통해 Servlet/JSP가 동작하는 원리에 대해 깊게 이해하는 시간이 되었습니다. 서버와 클라이언트 간의 상호작용을 명확히 이해하게 되었고, 이를 통해 웹 애플리케이션 개발에 대한 자신감을 얻었습니다.
 또한, 사용자 화면을 위해 AJAX를 사용하면서 데이터를 실시간으로 받아오는 과정에서 어려움도 있었지만, 데이터를 실시간으로 확인할 수 있는 편리함을 느끼며 화면 구성에 많은 신경을 썼습니다. AJAX를 통해 비동기식 데이터 처리를 구현하면서 사용자 경험을 향상시키는 방법을 배웠고, 클라이언트와 서버 간의 데이터 통신을 원활하게 하는 방법에 대해 깊이 이해하게 되었습니다. JDBC를 이용해 데이터를 받아오는 과정에서는 코드가 깔끔하지 않아 그 점이 좀 아쉬웠지만 개선하기 더 노력하는 시간을 가져야 겠다고 생각했습니다

