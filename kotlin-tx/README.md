# Spring Transactional

## Multi Datasource

RoutingDataSource와 Aspect, 그리고 커스텀 Transactional 어노테이션을 통해서 여러 DB를 사용하는 사용 사례에서도 트랜잭셔널을 적용해볼 수 있다.

그리고 Tx, TxAdvice를 이용해서 Kotlin AOP를 이용해 Transactional을 구현해보는 것도 실습한다.

## Transactional Event

Transactional Event를 이용해서 트랜잭션이 보장된 이벤트 리스너 처리도 실습해본다

**BEFORE_COMMIT**

- 트랜잭션 커밋이 일어나기 이전에 리스너를 실행하여서, 실패하는 경우 본 트랜잭션도 롤백시키는게 가능하다.
- 즉 본 로직의 실행과 리스너 실행이 한 트랜잭션을 보장해야하는 경우에 적용하면 되는 리스너 설정이다

**AFTER_COMMIT**

- 본 로직의 트랜잭션의 커밋이 일어나고 실행되는 리스너에 적용한다.
- 즉, 리스너 로직이 본 로직과 독립적으로 실행되고 결과에 영향을 미칠 이유가 없을 때 사용한다
- AFTER_COMMIT으로 설정된 리스너에서 예외가 발생하더라도 응답에는 영향을 미치지 않는다. 즉, 본 로직에서 예외가 발생하는게 아니라면 에러응답을 내리지 않는다.

## Transactional Event with Kotlin AOP

Kotlin AOP (Tx, TxAdvice) 에서도 Transactional Event가 동작하는지 실습한다

결과 : 동작한다. Kotlin AOP에서도 결국 Transactional을 사용하여 로직을 구현하고 있기 떄문이다.