package com.lee.demo.constants;

public class ResponseCode {
    public static final String SUCCESS = "1";

    public static class ERROR{
        public static final String REGISTER_USER_EXIST = "2";
        public static final String REGISTER_UN_KNOW = "3";
        public static final String LOGIN_NOT_MATCH = "4";

        public static final String MENU_ADD_FAIL = "100";
        public static final String MENU_QUERY_FAIL = "101";
        public static final String MENU_UPDATE_FAIL = "102";
    }

}
