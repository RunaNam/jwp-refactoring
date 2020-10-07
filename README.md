# 키친포스

## 기존 코드 요구 사항 분석

### 테이블
 - 매장에 존재하는 테이블을 등록할 수 있다. (POST /api/tables)
    - 이때, 등록과 동시에 손님을 받을 수 있도록  
      손님 수와 빈 테이블인지를 입력할 수 있다.  
    - 테이블 그룹 ID와 테이블 ID는 이 때 설정할 수 없다.  
      테이블 ID 는 auto increment 니까 그렇고,  
      테이블 그룹 ID는... 모르겠다.  
      솔직히 등록과 동시에 손님을 받을 수 있게 되어있는 것 자체가 이상하다.  
      테이블 그룹 ID 설정은 못하면서...  
 - 매장에 존재하는 모든 테이블들에 대한 정보를 얻을 수 있다. (GET /api/tables)
     - 각 테이블의 ID,  
       테이블그룹,  
       해당 테이블을 사용중인 손님 수,  
       테이블이 비어있는지 아닌지
 - 어떤 테이블을 주문 가능한 상태로 바꿀 수 있다. (PUT /api/tables/1/empty)
     - 어떤 테이블에 손님이 와서 앉았다면,  
       그 테이블은 주문을 할 수 있어야하므로  
       주문 가능한 상태, 즉 테이블이 비어있지 않은 상태로 바꾸면 된다.
     - 손님이 왔다 갔다면,  
       그 테이블은 주문을 할 수 없어야하므로
       주문 불가능한 상태, 즉 테이블이 비어있는 상태로 바꾸면 된다.
 - 어떤 테이블에 착석해있는 손님 수를 변경할 수 있다. (PUT /api/tables/1/number-of-guests)

### 메뉴 그룹
 - 메뉴 그룹을 등록할 수 있다. (POST /api/menu-groups)
     - ex) 식사류/안주류/사이드/음료
 - 메뉴 그룹 리스트를 조회할 수 있다.

### 메뉴
 - 새로운 메뉴를 등록할 수 있다. (POST /api/menus)
 - 전체 메뉴 리스트를 조회할 수 있다.
 
### 주문
 - 주문을 할 수 있다.
     - 테이블 id, 주문 메뉴와 메뉴 당 수량을 입력하여 주문한다.  
 - 주문 목록 전체를 조회할 수 있다.  
 - 각 주문마다 준비 상황 (order status)를 업데이트할 수 있다.
     - 주문 상태에는 `???(조리)`, `MEAL(식사)`, `COMPLETION(계산 완료)`가 있다.

### 상품(product)
 - 모든 메뉴는 하나 이상의 상품(product)으로 구성된다.
     - ex) `후라이드 세트` 메뉴 : `후라이드 치킨`(상품) + `감자튀김`(상품)
 - 새로운 상품을 등록할 수 있다.
     - 왜 메뉴에도 가격이 따로 있는데 상품에도 가격이 따로 있는걸까.... 맘에안든다.
 - 모든 상품을 조회할 수 있다.

### 테이블 그룹
 - 테이블 여러개를 그룹지을 수 있다.
     - 여러개의 테이블을 한 팀의 손님들이 사용할 때,  
       계산을 통합해서 할 수 있게 하기 위함이다.  
 - 테이블 그룹을 해제할 수 있다.

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
