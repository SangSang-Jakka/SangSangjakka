/**
 * 이 패키지는 애플리케이션에서 사용되는 열거형(enum) 클래스를 포함합니다.
 * 열거형은 상수 값의 집합을 정의하는 데 사용되며, 코드의 가독성과 유지보수성을 높입니다.
 * <p>
 * 주요 열거형 클래스:
 * <ul>
 *     <li>{@link com.jakka.model.enums.AdminLog AdminLog}: 관리자 로그 유형을 나타내는 열거형</li>
 *     <li>{@link com.jakka.model.enums.BookAction BookAction}: 동화책에 대한 사용자 액션을 나타내는 열거형</li>
 *     <li>{@link com.jakka.model.enums.Genre Genre}: 동화의 장르를 나타내는 열거형</li>
 *     <li>{@link com.jakka.model.enums.Inflow Inflow}: 유입 경로를 나타내는 열거형</li>
 *     <li>{@link com.jakka.model.enums.RecommendAge RecommendAge}: 동화 추천 연령대를 나타내는 열거형</li>
 *     <li>{@link com.jakka.model.enums.Tendency Tendency}: 동화 취향 성향을 나타내는 열거형</li>
 *     <li>{@link com.jakka.model.enums.UserLog UserLog}: 사용자 로그 유형을 나타내는 열거형</li>
 *     <li>{@link com.jakka.model.enums.UserState UserState}: 사용자 계정 상태를 나타내는 열거형</li>
 * </ul>
 * <p>
 * 열거형 클래스는 각각의 상수 값을 정의하고, 해당 값에 대한 getter 메서드를 제공합니다.
 * 이를 통해 코드에서 열거형 상수를 사용할 때 가독성을 높이고, 오타 등의 실수를 방지할 수 있습니다.
 * <p>
 * 열거형 클래스의 상수 값은 데이터베이스에 저장되는 값과 매핑되어 있습니다.
 * 따라서 열거형 클래스를 사용하여 데이터베이스와의 상호작용 시 일관성을 유지할 수 있습니다.
 */
package com.jakka.model.enums;