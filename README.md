# 📖 SangSangjakka


<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/7cd401e1-a565-4899-9ff3-4d8f5ab294d4" width="750" height="400"/>

<br/>
<br/>
<br/>


## 프로젝트 소개

**상상자까는 이미지 생성 AI와 문장 생성 AI를 활용한 동화책 제작 및 공유 홈페이지입니다.  
성장 시기에 맞는 책 구입의 재정적 어려움과 제한된 동화책 선택의 한계를 극복하며, 어린 나이부터 접하는 디지털 미디어 중독 문제를 해결하고자합니다.**
- 사용자별 취향, 선호도 파악을 통해 동화책 맞춤 추천과 AI 이미지 생성시 취향별 그림체 적용이 가능합니다.
- 다른 사용자의 동화책을 읽고 리뷰를 남길 수 있습니다.
- 자유게시판을 통해 사용자간 소통할 수 있습니다.
- 마이페이지에서 자신이 작성한 게시물과 댓글을 확인하고 개인정보를 수정할 수 있습니다.
- 관리자 페이지에서 사용자와 관리자의 행동로그를 실시간으로 확인할 수 있습니다.
- 관리자 페이지에서 사용자 통계를 확인할 수 있습니다.

<br/>


## 팀원 구성
곽지현, 김은솔. 박동수, 박미영, 송성혜, 정원혁, 황시원

<br/>

## 1. 개발 기간
- 전체 개발 기간: 24.04.19 - 24.05.13
- DB 구현: 24.04.24 - 24.04.28
- UI 구현: 24.04.25 - 24.05.07
- 기능 구현:  24.04.30 - 24.05.12

<br/>


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

<br/>

## 3. 프로젝트 구조
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/c9f309c1-439a-4229-93ac-cd1e985e4675" width="750" height="400"/>

<br/>
<br/>

## 4. 역할 분담
### 🐨 곽지현
- #### DB
  * DAO작업, DTO작업, 가중치 정보 생성
- #### 기능
  * 생성형AI API 연동, 문장 번역 API 연동, 사용자 백데이터 수집, 관리자 로그, 사용자 로그, 공지사항 게시판

<br/>

### 🐹 김은솔 
- #### UI
  * 마이페이지(개인정보, 내가 쓴 글), 명예의 전당, 게시판(자유게시판, 공지사항, 건의사항)
- #### 기능
  * 마이페이지(개인정보, 내가 쓴 글), 명예의 전당

<br/>

### 🐱 박동수
- #### UI
  * 동화책 제작
- #### 기능
  * 마이페이지(탭기능, 차트, 보관함), 문장 생성 AI 연결, 동화책 제작

<br/>

### 🦝 박미영
- #### UI
  * 관리자 페이지
- #### 기능
  * 관리자 동화책 관리(동화책 수상 관리, 신고동화책 관리, 개인 저장소 기본 용량 관리),
  * 관리자 게시판 관리(공지사항, 건의사항, 자유게시판, 동화 공유게시판, 리뷰 관리, 댓글 관리)

<br/>

### 🐰 송성혜
- #### UI
  * 웹 사이트 디자인 및 전반적인CSS, 메인 페이지, 회원가입, 명예의 전당, 동화제작
- #### 기능
  * 메인 페이지, 자유게시판, 동화 공유 게시판, 동화 공유 게시판 리뷰, 회원가입

<br/>

### 🦊 정원혁
- #### UI
  * 로그인, 로그아웃, 동화 공유 게시판
- #### 기능
  * 로그인, 로그아웃, 건의사항, 자유게시판 댓글

<br/>

### 🐶 황시원
- #### DB
  * DB 더미 작업
- #### UI
  * 관리자페이지 기본, 대시보드 회원, 동화책, 게시판, 관리자별 각각 통계 및 차트
- #### 기능
  * 대시보드 회원, 동화책, 게시판, 관리자별 각각 통계 및 차트, 관리자 관리, 회원 관리

<br/>

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


<br/>

## 6. 동화책 제작 프로세스

### [동화책 제작 전체적인 흐름]
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/1f2027cd-ca40-42b1-bfb5-4403c47fe755" width="400" height="500"/>
<br/>

### [사용자 취향 분석]
- 10개의 동화책 장르를 5개의 성향으로 변환합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/86ac0cdb-0749-45f6-9ba7-8633482bd3d2" width="400" height="500"/>


<br/>

### [선호도 점수 추출]

- 사용자 행동 별 가중치를 부여합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/99d5f799-75de-49a7-b4f5-7351e945330a" width="600" height="400"/>


<br/>
<br/>
<br/>


- 추출한 점수를 바탕으로 사용의 취향을 분석합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/650da876-b315-4749-9585-1a3daad77354" width="400" height="250"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/c68c1e00-2447-473b-b95b-efeb19f09025" width="400" height="150"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/8df4c5ac-1028-4d9a-bc01-7223f9bd4b2d" width="450" height="370"/>

<br/>
<br/>

### [취향에 기반한 동화책 제작]

- 선호도 점수를 바탕으로 개인별 상위 4개의 카테고리를 추출합니다.(동점인 경우 랜덤 추출)
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/64e63c41-00e3-44c1-ad89-26409ead292a" width="650" height="250"/>

<br/>
<br/>
<br/>

- 그림 생성시 프롬프트에 사용자의 취향을 추가합니다.
- 취향 분석에 따른 사용자 맞춤 이미지가 생성됩니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/055220d5-9ba1-477e-b880-a8d93ec51209" width="650" height="300"/>

 
## 7. 페이지별 기능
### 👤사용자
### [메인화면]
- 메인화면, 동화제작, 동화나라, 명예의전당, 공지사항, 자유게시판, 공지사항이 네비바로 있습니다
- 스크롤을 내리면 어제의 인기도서와 사용자의 선호도에 맞춘 책을 추천해줍니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/c2a518fd-a51f-4a3c-8d00-d10715bb5475" width="600" height="1100"/>

<br/>
<br/>
<br/>

### [회원가입]


- 회원가입시 [이름, 이메일, 아이디, 비밀번호, 비밀번호 확인, 주소, 자녀연령대, 유입경로] 정보를 입력합니다.
- 아이디, 닉네임 중복확인을 하지 않으면 회원가입이 불가능합니다.
- 정보를 입력하지 않고 회원가입 버튼을 누르면 정보를 입력하지 않은 곳으로 커서가 이동합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/60601e41-7ba6-40c7-94bc-b6bcce14e633" width="600" height="1400"/>

<br/>

### [로그인]
- 정보를 입력하지 않고 로그인 버튼을 누르면 정보를 입력하지 않은 곳으로 커서가 이동합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/17fa0ecd-cbeb-4734-99da-e2fe0dccec90" width="400" height="300"/>

<br/>

<br/>

### [아이디, 비밀번호 찾기]

- 아이디는 이름과 이메일 주소를 입력하면 입력한 정보와 일치하는 아이디를 알려줍니다.
- 비밀번호는 아이디와 주민등록번호 앞자리를 입력하면 입력한 정보와 일치하는 아이디를 알려줍니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/f62c0b0f-9ec7-4003-b4fb-a7bbc1efcfb1" width="400" height="300"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/2f5afcd0-e5ee-4a19-9cda-bde6584ee09d" width="400" height="300"/>

<br/>
<br/>


### [마이페이지]

- 자신의 개인정보를 확인할 수 있습니다
- 닉네임, 이메일, 전화번호, 주소를 수정할 수 있습니다.
- 닉네임, 이메일 수정시 유효성 + 중복 검사를 합니다
- 주소 수정시 API를 사용해 우편번호 검색이 가능합니다
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/2786d396-46c9-4d99-afb5-5f15807d442a" width="600" height="800"/>

<br/>
<br/>
<br/>
<br/>
<br/>
  
- 자신이 만든 동화책을 확인할 수 있습니다.
- 자신의 동화책 취향을 확인할 수 있습니다.
- 스크랩한 동화책을 확인할 수 있습니다
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/6745eb78-28da-4940-b1f9-6893ab4b86cd" width="600" height="800"/> 

<br/>
<br/>
<br/>

- 자신이 쓴 글과 댓글을 확인할 수 있습니다.
- 게시글의 제목을 누르면 해당 글로 이동합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/139f2015-57d2-4ffe-a254-8ba610ec250c" width="600" height="800"/>

<br/>
<br/>


### [동화책 제작]




- 기존에 주로 선택하던 방식과 새로운 형식으로 책을 만들 수 있는 선택지를 제공합니다.
- 책의 내용과 이미지는 각각 직접 입력과 업로드가 가능하며 원하면 AI의 도움을 받을 수 있습니다.
- 각 페이지마다 이미지와 내용을 삽입할 수 있으며 페이지 추가를 통해 여러 페이지를 만들고 수정, 삭제할 수 있습니다.
- 페이지가 완성되면 표지 페이지를 만들고 책의 정보(제목, 소개, 장르)를 선택하면 동화책이 완성되며 동화공유게시판과 마이페이지 보관함으로 자동으로 동화책이 업로드 됩니다.

<img width="70%" src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/9471c1f0-11db-41fe-bcd4-0d1a8ffb940f"/>
<img width="70%" src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/f1167282-2529-42b3-a34f-1bd19c15ee66"/>


<br/>

### [동화 공유 게시판]
- 다양한 동화책을 볼 수 잇는 게시판이며 각 동화책 마다 스크랩수, 좋아요 수, 리뷰 수를 확인할 수 있습니다.
- 동화책을 선택 후 들어가면 각 페이지의 그림과 내용을 확인할 수 있으며 좋아요, 스크랩, 신고할 수 있습니다.
- 리뷰 작성이 가능하며 본인이 단 리뷰만 수정, 삭제할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/ad4b6749-e490-4976-ba6b-9c87582045c7" width="700" height="600"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/f2a0f038-1d69-4410-be22-a2e08d34ae07" width="700" height="1000"/>

<br/>
<br/>
<br/>

### [명예의 전당]
- 각 달의 좋아요, 리뷰, 스크랩 수를 가장 많이 받은 동화책 중 관리자의 검토를 받은 동화책 5권이 명예의 전당 페이지로 이동합니다.
- 책 표지를 클릭하면 이미지가 돌아가면서 뒷 페이지의 책 정보가 표시됩니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/022f8846-35c7-4048-9a2f-876486149176" width="700" height="900"/>


<br/>

### [자유게시판]
- 회원은 모두 자유 게시판의 게시글을 등록, 수정, 삭제가 가능하며 다른 회원의 게시글에 댓글과 신고를 할 수 있습니다.
- 게시글은 최신순, 조회순, 댓글순으로 정렬이 가능하며 각 게시글마다 댓글 개수, 작성자, 등록일, 조회수, 제목을 확인할 수 있습니다.
- 제목, 내용, 제목+내용으로 검색할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/db26bcf8-c4a2-48b2-b32b-fa14c3b35f89" width="700" height="700"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/31179faa-d6cc-4110-8180-1a7678428f98" width="700" height="500"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/40f3e23a-0282-4bb6-9a76-5a832b11e1b6" width="700" height="600"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/4edd0f58-3e9c-4339-820a-497a6d3c158b" width="700" height="600"/>


<br/>
<br/>
<br/>

### [공지사항 게시판]
- 관리자만 공지사항을 등록할 수 있으며 등록할 시 고정글을 선택할 수 있습니다.
- 제목, 내용, 제목+내용으로 검색할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/8871e572-41dd-4e8e-b91f-6bf5112cabcd" width="700" height="900"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/ec82f460-0b41-4c02-9cfe-be50f4d723f4" width="700" height="450"/>

<br/>
<br/>
<br/>

### [건의사항 게시판]
- 회원은 모두 자유 게시판의 게시글을 등록, 수정, 삭제할 수 있습니다.
- 게시글은 최신순, 조회순, 댓글순으로 정렬이 가능하며 각 게시글마다 댓글 개수, 작성자, 등록일, 조회수, 제목을 확인할 수 있습니다.
- 건의사항의 답변은 관리자만 작성할 수 있습니다.
- 제목, 내용, 제목+내용으로 검색이 가능하지만 공개글과 본인작성 게시글만 출력됩니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/4c57a363-a549-4b5d-bed8-24cd7d09833f" width="700" height="700"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/25173c0f-2c63-4450-94fb-f69b5f2efe14" width="700" height="400"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/70b50b47-dcb8-4fce-8271-1bf02c2ca166" width="700" height="600"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/2bc45b01-31d1-421a-ab30-16fffe857131" width="700" height="550"/>


<br/>
<br/>
<br/>

----

### 👤관리자

<br/>
<br/>

### [관리자 로그인]
- 상상자까 메인 페이지 푸터에 있는 이미지를 Ctrl를 누르고 클릭하면 관리자 페이지로 이동할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/813eab12-2075-4c7c-a635-fb0d92af353e" width="700" height="450"/>

<br/>
<br/>

### [관리자 메인화면]
- 대시보드에서는 새로운 동화책, 게시글, 유저, 건의사항 수를 한눈에 확인할 수 있으며 사이드 바에서 원하는 메뉴로 페이지를 이동할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/05d979e6-1ecb-4671-9567-c3aecc935fbb" width="700" height="450"/>

<br/>
<br/>

### [회원관리]
- 회원 관리에서는 전체 회원 정보 조회 및 수정이 가능하며 회원을 차단할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/1695da2d-59f1-4895-a34d-dafccb7a4bf3" width="700" height="500"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/f861ee15-106b-4456-8c40-ed371a615773" width="700" height="400"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/4cbb6b48-e699-41da-9951-e6ffe4c9165a" width="700" height="350"/>

<br/>
<br/>

### [회원관리 > 개인용량관리]
- 개인 저장소 기본 용량 메뉴에서는 회원 개별 용량 조회가 가능하며 용량을 변경할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/8dada383-dbaf-484b-8321-b2eab12a1689" width="700" height="500"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/e4b69748-3d14-418d-8a77-94fb62501b11" width="700" height="450"/>

<br/>
<br/>
 
### [회원관리 > 통계]
- 회원 통계에서는, 탈퇴자, 신규 가입자 수를 확인할 수 있으며 회원의 성별, 사용자 연령대, 자녀 연령대를 그래프를 통해 비교해서 확인할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/36e5fb9d-8b0e-43ab-af7f-f35c5f03fcdb" width="700" height="800"/>

  <br/>
  <br/>

### [게시판 관리 > 통계]
- 신규 작성글, 신고된 게시글, 신고 댓글 수를 확인할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/db527452-3f8a-4292-b167-c0251a324f3c" width="700" height="800"/>

<br/>
<br/>
  
### [게시판 관리 > 각 게시판 별 관리]

<br/>

#### 자유게시판
- 자유게시판 메뉴에서는 게시글 조회가 가능하며 게시글과 댓글은 공개, 비공개 처리가 가능합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/cce0fb52-0aa9-4bba-8c9c-8d4a5e731c77" width="700" height="500"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/721e1004-ffcc-4f0c-a07f-fdaf0b6e0d66" width="700" height="800"/>

<br/>
<br/>

#### 공지사항

- 동화책 공유 게시판 메뉴에서는 동화책 조회가 가능하며 동화책과 리뷰는 공개, 비공개 처리가 가능합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/9a747173-3f45-4fb3-9dd3-2258153855a1" width="700" height="600"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/2a7be0d0-b091-40ce-adba-13b16d82d8d0" width="700" height="350"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/5e10b4ec-5c32-44d3-9b0a-dd508fa30f97" width="700" height="350"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/154e9866-2471-4ccd-8977-eba46f306217" width="700" height="300"/>

<br/>
<br/>

#### 건의사항
- 건의사항 메뉴에서는 회원이 작성한 건의사항을 바로 확인할 수 있으며 건의사항에 대한 답변을 작성, 수정, 삭제가 가능합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/7cff329d-34b0-4efa-8b0c-670bda3be099" width="700" height="500"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/eb20fdd3-e633-457e-b186-cd67fd720392" width="700" height="400"/>

<br/>
<br/>

#### 동화 공유 게시판
- 동화책 공유 게시판 메뉴에서는 동화책 조회가 가능하며 동화책과 리뷰는 공개, 비공개 처리가 가능합니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/01c9d74a-1bbd-4624-a4f5-43dcffd0ca5a" width="700" height="500"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/722b4b9a-ff22-4ddf-8331-5156aebb3001" width="600" height="1100"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/5ad34af9-e0b1-4010-acaf-e6ac8c2916b7" width="700" height="500"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/585f170d-2f82-4279-b2c6-ffb3cf714184" width="700" height="500"/>

<br/>
<br/>
<br/>


### [관리자 관리]
- 관리자 계정을 조회 및 추가가 가능하며 개인 정보를 수정할 수 있습니다.
- 관리자 로그 메뉴에서는 관리자 계정별 로그를 조회할 수 있습니다.
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/2651a46d-b3f0-42ab-81cd-afc35ab77304" width="700" height="400"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/e63f55ca-cd49-4af7-987e-3ada84d2dbed" width="700" height="350"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/211a0f8a-5198-4cc5-908a-3086461bd3c6" width="700" height="500"/>

<br/>
<br/>

### [동화책 관리]

- 동화책 수상 관리 메뉴에서는 명예의 전당에 동화책을 등록, 삭제할 수 있으며 이전의 수상작들을 조회할 수 있습니다.
- 신고동화책 관리 메뉴에서는 신고 누적횟수가 1건 이상인 동화책을 조회할 수 있으며 동화책을 공개, 비공개 처리할 수 있습니다.

<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/97738595-1fcc-4523-b070-445366424aa4" width="700" height="600"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/f36bbeff-3fa4-45ea-a173-e0a9e0699d86" width="700" height="450"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/a69f26a8-48a8-4177-8d7b-fad91ef9809d" width="700" height="350"/>
<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/e3ac4c5e-07cb-4a64-883f-1317c6fa5f56" width="700" height="350"/>

<br/>
<br/>

### [동화책 관리 > 통계]

- 동화책 통계 메뉴에서는 새로운 동화책, 평균 페이지 수, 총 동화책 수, 월별 최대 동화책을 확인할 수 있습니다.

<img src="https://github.com/SangSang-Jakka/SangSangjakka/assets/155609506/82dd07ab-3126-471d-9b36-410a670d0431" width="700" height="800"/>

<br/>
<br/>
<br/>
<br/>

## 8. 회고

<br/>

#### 🐨 곽지현
이번 프로젝트에서 팀장으로서 팀원들과 협력하여 주요 기능들을 구현하고 프로젝트를 성공적으로 완수했습니다. 인터페이스를 역할에 따라 분리하고 추상화하여 설계함으로써 구현체에 의존하지 않는 이점을 얻었지만, 프로젝트 진행 중 인터페이스 변경이 잦아지는 문제점이 발견되었습니다.
 향후에는 인터페이스 설계 시 단일 책임 원칙을 엄격히 적용하고 추상화 수준을 적절히 조정하여 안정성과 유연성을 높이는 것이 중요하다는 교훈을 얻었습니다. 또한, 사용자 로그인 체크와 관리자 로그 기능 구현 시 하드코딩으로 인한 코드 중복과 유지보수성 저하 문제를 경험했습니다. 이러한 문제를 해결하기 위해 필터을 접목했다면 코드의 모듈화와 재사용성을 높일 수 있었을 것입니다.
 이번 프로젝트의 경험을 바탕으로 인터페이스 설계의 중요성과 필터의 큰 개념인 AOP의 장점을 깨닫고, 더 나은 설계와 구현을 위해 노력할 것입니다. 다음 프로젝트에서는 AOP를 적극 활용하여 공통기능을 분리하고, 코드의 품질과 유지보수성을 향상시키고자 합니다.

<br/>

#### 🐹 김은솔
이번 프로젝트를 통해 객체와 자바스크립트에 대한 이해도가 크게 향상되었습니다. 처음에는 Ajax를 사용하는 것이 익숙하지 않아 원하는 데이터를 가져오는 데 어려움을 겪었지만, 코드를 한 줄씩 분석하면서 이해도를 높여나갔습니다. 결과적으로 전당페이지와 마이페이지의 기능을 구현할 수 있었고 정보를 담고 출력하고 다듬는 과정에서 객체에 대한 이해도가 증가했습니다. 
CSS에서 float와 flex 등을 사용하여 화면 정렬에 대한 이해도를 높이고, 사용자의 편의성을 고려한 인터페이스를 고민해보는 시간이 되었습니다.
 프로젝트를 통해 모호하게만 느꼈던 DB, 프론트엔드, 백엔드의 역할을 구분할 수 있게되었습니다. 각 역할은 서로 다르지만 유기적으로 연결되어 있고 체계적인 구조를 갖추면 코드의 가독성과 유지보수성이 향상될 수 있음을 깨달았습니다. 다음 프로젝트나 업무를 진행할 때는 체계적인 코딩을 해야된다는 생각이 들었습니다.
 시간이 촉박해서 코드를 통일성 있게 짜지 못하고 추가하고 싶었던 명예의전당 월 별 조회 기능을 완성하지 못해 아쉬움이 남았습니다. 따라서 리팩토링을 통해 코드를 간결하게 만들고 기능을 완성해보고싶습니다. 

<br/>

#### 🐱 박동수
이번 프로젝트를 통해 자바스크립트와 서블릿에 대한 이해도가 크게 향상되었습니다. 페이지 전환, 생성형 AI API 연결, 자바스크립트 동적 생성 등의 다양한 기술적 도전 과제를 해결하면서 많은 성장을 이뤘습니다. 자바스크립트와 서블릿을 사용해 웹 페이지를 동적으로 전환하는 과정에서 여러 문제를 해결하며 해당 기술에 대한 깊은 이해를 얻었고, API 호출과 데이터 처리에 대한 경험을 쌓았습니다. 동적 자바스크립트 생성에서는 DOM 조작, 이벤트 처리, 비동기 프로그래밍 등 자바스크립트의 핵심 개념들을 실무적으로 활용했습니다. 또한, 서블릿을 통해 서버와 클라이언트 간의 통신을 직접 구현해 보면서 이해도를 높였습니다.
 동화책 제작 로직 개발 과정에서는 사용자 데이터를 기반으로 맞춤형 콘텐츠를 생성하는 알고리즘을 구현하면서 데이터 분석과 처리에 대한 실질적인 경험을 쌓았습니다. 이 경험들은 앞으로의 개발 업무에 큰 자산이 될 것이며, 기술적 도전에 대한 자신감을 높여 주었습니다. 자바스크립트와 서블릿을 활용한 웹 애플리케이션 개발과 생성형 AI API 연동 경험은 매우 의미 있었습니다. 앞으로도 이러한 경험을 바탕으로 더욱 발전하는 개발자가 되기를 기대합니다.

<br/>

#### 🦝 박미영
이번 프로젝트에서 관리자 페이지를 맡게 되어 프론트 구현에 대한 부담은 적었으나 사용자와 관리자 양측을 모두 고려하여 기능을 구현해야 했습니다.
 프로젝트 진행 중 Servlet에서 JSP로 데이터를 전달하는 과정에서 값이 제대로 전달되지 않는 문제가 발생할 때마다 이를 해결하기 위해 전체 코드를 되짚어 보며 JSP와 Servlet의 동작 방식을 깊이 이해하는 좋은 경험을 할 수 있었습니다.
 JavaScript로 동적 U[I를 구성하는 과정에서도 예상치 못한 동작과 스타일 문제를 겪었지만, 브라우저 개발자 도구로 디버깅하고 CSS 스타일을 수정하며 문제를 해결했습니다. 또한, AJAX 요청 처리 중 서버 응답 오류가 발생했으나, 서버 로그와 클라이언트 콘솔을 통해 디버깅하고 서버 설정을 수정하여 안정적인 비동기 호출을 구현할 수 있었습니다. 이를 통해 웹 기술 전반에 대한 이해를 높이고 문제 해결 능력을 향상시킬 수 있었습니다.
 시간적인 한계로 인해 세밀한 부분을 살펴보지 못한 아쉬움이 남지만, 프로젝트를 성공적으로 완료하게 되어 기쁩니다. 이번 프로젝트에서 디버깅의 중요성을 경험했으며, 다음 프로젝트에서는 JUnit을 공부하여 TDD 기반으로 개발을 진행하고 싶습니다. 테스트 코드를 먼저 작성하여 코드의 품질을 향상시키고 보다 효율적이고 안정적인 기능을 구현하고 싶습니다.

<br/>

#### 🐰 송성혜
이번 프로젝트를 통해 프론트엔드와 백엔드 양쪽에서 많은 성장을 이루었습니다. 프론트엔드에서는 특히 CSS의 position과 grid를 효과적으로 활용하게 되면서, 그동안 어려웠던 이론들이 자연스럽게 익숙해졌습니다.
 백엔드 작업에서는 실제 요청을 보내고 데이터를 받아 수정하여 화면에 출력하는 과정이 매우 흥미로웠습니다. 물론 요청이 제대로 처리되지 않거나 데이터베이스 쿼리에 문제가 생기는 경우도 있었지만, 이러한 에러를 console.log나 System.out.println을 통해 확인하고 해결하는 과정이 큰 재미를 주었습니다. 에러를 검색하고 해결하면서 문제 해결 능력도 많이 향상되었습니다.
 이번 경험을 통해 프론트엔드와 백엔드 모두에서 많은 성장을 이룰 수 있었습니다. 프로젝트를 진행하면서 쌓은 지식과 경험이 앞으로의 개발 작업에 큰 도움이 될 거 같습니다.

<br/>

#### 🦊 정원혁
프로젝트 진행 전 할 수 있을까에 대한 두려움이 많았습니다. 
직접 인터넷을 찾아보며 게시판 만들기도 해보았지만 벽에 부딛힐 때가 많았습니다. 프론트 작업을 하면서는 어떤 화면을 구성해야겠다 라는 기초 틀을 잡고 작업을 하니 훨씬 수월했고 dao안에서 어떤 항목에 대해 물음표 변수로 처리를 해야하는지, 어떤 결과를 들고 dto에 담아 jsp 페이지에서 EL로 처리를 해야되는지 많이 배우고 이해할 수 있는 시간이었습니다. 
 힘들고 누군가의 코드를 보고 그냥 결과물만 뽑아내고 싶다라는 생각도 많았지만 직접 몸소 부딛혀보고 떠오르는 생각을 코드로 작성하고 구현하고자 하는걸 성공했을 때 뿌듯함은 배가 됐습니다. 이번 프로젝트를 통해 앞으로도 많은 어려운 상황이 닥쳐도 해결해나가는 재미도 느낄 수 있었고 앞으로 이런 과정을 더 경험해서 성장할 수 있는 발판을 마련할 수 있었던 것 같습니다.

<br/>

#### 🐶 황시원
이번 프로젝트를 통해 Servlet/JSP가 동작하는 원리에 대해 깊게 이해하는 시간이 되었습니다. 서버와 클라이언트 간의 상호작용을 명확히 이해하게 되었고, 이를 통해 웹 애플리케이션 개발에 대한 자신감을 얻었습니다.
 또한, 사용자 화면을 위해 AJAX를 사용하면서 데이터를 실시간으로 받아오는 과정에서 어려움도 있었지만, 데이터를 실시간으로 확인할 수 있는 편리함을 느끼며 화면 구성에 많은 신경을 썼습니다. AJAX를 통해 비동기식 데이터 처리를 구현하면서 사용자 경험을 향상시키는 방법을 배웠고, 클라이언트와 서버 간의 데이터 통신을 원활하게 하는 방법에 대해 깊이 이해하게 되었습니다. JDBC를 이용해 데이터를 받아오는 과정에서는 코드가 깔끔하지 않아 그 점이 좀 아쉬웠지만 개선하기 더 노력하는 시간을 가져야 겠다고 생각했습니다

