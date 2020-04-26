package com.car4s.rest.jedis;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by 张少强
 */
public class JacaTest {

    @Test
    public void StringSubstring(){
        String a = "asdf fdas";
        String s = a.substring(0, 6);
        System.out.println(s);
    }

}
