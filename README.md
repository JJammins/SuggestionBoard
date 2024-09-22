# 학원 건의 게시판 프로젝트 (개인프로젝트)
이 웹페이지는 수강했던 학원에서 건의사항이 있을 시 수기로 작성하여 사무실로 제출해야하는 불편함을 해소해보고자 하는 생각으로 기획하였다.


학원에 건의사항이 있을 시 웹페이지에서 글을 작성할 수 있으며, 관리자 또한 웹페이지를 통해 건의사항을 접수할 수 있다.


## 프로젝트 기간
* 2024.08.23 ~ 2024.09.02


## 주요 기능
* Spring Security로 로그인 기능 구현
* 관리자와 일반유저의 권한 존재
* 게시글 및 댓글 CRUD 구현
* 댓글은 관리자만 작성 가능
* 댓글의 여부에 따라 답변완료, 답변대기 구분
* 건의 분류 별 조회 가능

## 기술 스택
#### 프론트엔드</h4>
* JavaScript
* ThymeLeaf
* BootStrap
* HTML & CSS

#### 백엔드</h4>
* SpringBoot
* MariaDB

#### 배포
* AWS LigntSail
* AWS RDS

## 프론트엔드 화면
#### 로그인 & 회원가입 페이지
![image](https://github.com/user-attachments/assets/b21227e7-4f31-4ede-ae42-28df3113f6e0)


![image](https://github.com/user-attachments/assets/12269f76-f33a-47da-96d8-a1549bc539fb)

#### 메인
![image](https://github.com/user-attachments/assets/f1564bca-f10a-4195-a57f-45b43d632282)

#### 건의목록 - 최신순 건의보기
![image](https://github.com/user-attachments/assets/20c86dc8-d838-4f0f-89ad-f7ad77f37acf)

#### 건의목록 - 답변대기
![image](https://github.com/user-attachments/assets/52099359-66b9-4ab7-a7ee-19af64f4dd4e)

#### 건의목록 - 답변완료
![image](https://github.com/user-attachments/assets/7ea0893f-6de9-4263-b6b2-543fbb64dfcc)

#### 건의별 분류보기 - 시설/장비
![image](https://github.com/user-attachments/assets/de4171cf-cc6d-47ea-a2e4-59637c358eb6)

#### 건의별 분류보기 - 구매신청
![image](https://github.com/user-attachments/assets/0078f4b0-f3f5-4e8a-b604-45521e3c279c)

#### 건의별 분류보기 - 개방신청
![image](https://github.com/user-attachments/assets/a017a51d-ee2b-4f0c-bef7-4a56187c5d8c)

#### 건의별 분류보기 - 취업상담
![image](https://github.com/user-attachments/assets/30c6e1bf-62be-4dad-bf23-91faa1814f28)

#### 건의하기
![image](https://github.com/user-attachments/assets/f0ea79cd-9c1e-45e3-8a31-b237fe5345a3)


## 모바일 버전
#### 로그인 & 회원가입 페이지
![image](https://github.com/user-attachments/assets/357a57c2-5d2f-475d-8315-010cff4d2391)


![image](https://github.com/user-attachments/assets/5456576d-bed4-4ded-a6cd-082f4b61170c)

#### 메인
![image](https://github.com/user-attachments/assets/8cadd9f2-5ec7-4eb0-b576-46f8a2f3a93a)

#### 건의목록
![image](https://github.com/user-attachments/assets/13bfe93f-3d27-474a-acb0-f8ff46f274f9)

#### 분류별 건의보기
![image](https://github.com/user-attachments/assets/e2587f81-e220-4b08-bb57-4b964496df2f)

#### 건의하기
![image](https://github.com/user-attachments/assets/73723ae3-8f25-49fc-9756-fbbefc90c43e)


## 프로젝트 후기
팀프로젝트가 끝난 후 남은 기간동안 짧게 개인프로젝트를 진행하게 되었다.<br>
어떤 프로젝트를 할지 고민하다가 눈에 띈 건의사항을 적는 종이를 보며 건의 게시판을 한 번 만들어보고자 하며 시작하게된 프로젝트이다.<br>
팀프로젝트에서 기획의 중요성을 알게 되었기에 이번 프로젝트를 기획할 땐 좀 더 꼼꼼히 기획할 수 있었고, 실제로 DB를 구축하는데 있어서 큰 변동사항은 없었다.<br>
프론트 파트에서 타임리프의 변수를 자바스크립트에서 사용하는데에 있어 오랜 시간을 잡아먹었지만 해결해 냈고, 덕분에 원하는 항목별로 조회할 수 있게끔 구현할 수 있었다.<br>
부트스트랩의 페이징네이션 기능을 그대로 적용할 수 없어 자바스크립트로 직접 구현하였다.<br>
개인적으로 지금까지 진행한 프로젝트중에 가장 마음에 드는 결과를 내었으며, 이런식으로도 구현할 수 있겠다 싶은 것들이 많았고, 실제로 구현하면서 많이 발전할 수 있는 프로젝트였다.
