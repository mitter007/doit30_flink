package com.spdbccc.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String[] args) {
        String input = "Hello, my age is 25 and my friend's age is 30.";

        // 定义正则表达式
        String regex = "\\d+"; // 匹配一个或多个数字

        // 编译正则表达式为 Pattern 对象
        Pattern pattern = Pattern.compile(regex);

        // 创建 Matcher 对象，并使用正则表达式匹配输入字符串
        Matcher matcher = pattern.matcher(input);

        // 查找匹配的数字
        while (matcher.find()) {
            System.out.println("Found: " + matcher.group());
        }
    }	
}
