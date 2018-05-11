package com.yc.stickyindexview.utils;

import com.github.promeg.pinyinhelper.Pinyin;

import java.util.regex.Pattern;

/**
 * @author by KABUQINUOFU on 2018/5/11.
 */
public class PinYinUtil {

    private static final String PATTERN_LETTER = "^[a-zA-Z]+$";

    /**
     * 将中文转换为pinyin
     */
    public static String getPingYin(String inputString) {
        char[] input = inputString.trim().toCharArray();
        StringBuilder output = new StringBuilder();
        for (char anInput : input) {
            output.append(Pinyin.toPinyin(anInput));
        }
        return output.toString().toLowerCase();
    }

    /**
     * 是否为字母
     */
    public static boolean isLetter(String inputString) {
        return Pattern.matches(PATTERN_LETTER, inputString);
    }

}
