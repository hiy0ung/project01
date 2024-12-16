package org.example.springbootpractice.common.constant;

public class ApiMappingPattern {
    // 인증 절차 생성 로직
    // : 회원가입, 로그인
    public static final String AUTH = "/api/v1/auth";

    // 일반 회원 관련 로직: 회원 조회(단건, 전체, 필터링), 수정, 삭제
    public static final String USER = "/api/v1/users";

    public static final String POST = "/api/v1/posts";
    public static final String BOOK = "/api/v1/books";
    public static final String STORE = "/api/v1/stores";

    public static final String MENU = "/api/v1/menus";
    public static final String MENU_OPTION = "/api/v1/options";
    public static final String MENU_OPTION_DETAILS = "/api/v1/optionDetails";
    public static final String REVIEW = "/api/v1/reviews";
    public static final String ORDER = "/api/v1/orders";
    public static final String STATS = "/api/v1/stats";
//    public static final String GROUP = "/api/v1/group";
}