# 주특기 플러스 개인과제
아웃소싱 프로젝트 활용하여 QueryDSL 적용

## 과제 요구사항

### 사용, 방향성 
- 최대한 QueryDSL 를 사용하여 구현
- Pageable 사용하기
  - 목록 조회는 기본적으로 페이징 조회가 기본이 된다
  - 쿼리를 수행하실때 꼭 Pageable 을 사용하셔서 페이징 조회가 가능하도록 한다
- TestCode 슬라이싱 테스트로 작성하기
  
### 기능 추가
- [x] 좋아요 기능을 추가
  - [x] 음식점 및 리뷰 좋아요 / 좋아요 취소 기능 
      - [x] 사용자가 음식점이나 리뷰에 좋아요를 남기거나 취소할 수 있습니다. 
      - [x] 본인이 작성한 리뷰에는 좋아요를 남길 수 없습니다.
      - [x] 같은 리뷰에는 사용자당 한 번만 좋아요가 가능합니다.
  - [x] 음식점 및 리뷰 단건조회 응답에 좋아요 개수 추가
      - [x] 음식점 단건 정보 조회시 게시글의 좋아요 개수필드를 추가합니다.
      - [x] 리뷰 단건 정보 조회시 리뷰의 좋아요 개수필드를 추가합니다.
- [ ] 내가 좋아하는 음식점 목록, 내가 좋아하는 리뷰 목록 API를 추가
  - [ ] 사용자가 좋아요 했던 음식점 목록을 조회할 수 있습니다.
  - [ ] 사용자가 좋아요 했던 리 목록을 조회할 수 있습니다.
  - [ ] 응답정보는 기존 게시글 목록 조회기능 응답정보와 동일합니다
  - [ ] 기본 정렬은 생성일자 기준으로 최신순으로 정렬합니다.
  - [ ] 페이지네이션하여 각 페이지 당 게시물 데이터가 5개씩 나오게 합니다.
- [ ] 프로필 기능에 “내가 좋아요한 음식점 수/리뷰 수” 응답필드를 추가
  - [ ] 프로필 조회시 응답필드에 내가 좋아요한 음식점 수 필드를 추가합니다.
  - [ ] 프로필 조회시 응답필드에 내가 좋아요한 리뷰 수 필드를 추가합니다.


### 기능 추가 방법
 - 구현되어있는 기능을 요구사항에 맞게 리펙토링
 - 구현되어있지 않는 기능은 요구사항에 맞게 구현