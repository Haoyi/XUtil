/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuexiang.xutil.common;

import android.support.annotation.NonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 字符串相关工具类
 * @author xuexiang
 * @date 2018/2/4 下午6:37
 */
public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断字符串是否为 null 或长度为 0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(final CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为 null 或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null 或全空格<br> {@code false}: 不为 null 且不全空格
     */
    public static boolean isEmptyTrim(final String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 获取String内容
     * @param s
     * @return
     */
    public static String getString(String s) {
        return isEmptyTrim(s) ? "" : s;
    }

    /**
     * 判断字符串是否为 null 或全为空白字符
     *
     * @param s 待校验字符串
     * @return {@code true}: null 或全空白字符<br> {@code false}: 不为 null 且不全空白字符
     */
    public static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串 a
     * @param b 待校验字符串 b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(final CharSequence a, final CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串 a
     * @param b 待校验字符串 b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(final String a, final String b) {
        return a == null ? b == null : a.equalsIgnoreCase(b);
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null 返回 0，其他返回自身长度
     */
    public static int length(final CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(final String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(final String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(final String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 获取异常栈信息，不同于Log.getStackTraceString()，该方法不会过滤掉UnknownHostException.
     *
     * @param t {@link Throwable}
     * @return 异常栈里的信息
     */
    public static String getStackTraceString(@NonNull Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    public static String toString(Object object) {
        if (object == null) {
            return "null";
        }
        if (!object.getClass().isArray()) {
            return object.toString();
        }
        if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        }
        if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        }
        if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        }
        if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        }
        if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        }
        if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        }
        if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        }
        if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        }
        if (object instanceof Object[]) {
            return Arrays.deepToString((Object[]) object);
        }
        return "Couldn't find a correct type for the object";
    }


    /**
     * 获取文件扩展名
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if (!isEmpty(filename)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 过滤字符串中所有的特殊字符
     *
     * @param str
     * @return
     */
    public static String replaceSpecialCharacter(String str) {
        String dest = "";
        if (str != null) {
            String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            dest = m.replaceAll("").trim();
        }
        return dest;
    }

    /**
     * 过滤字符串中的[和]
     *
     * @param str
     * @return
     */
    public static String replaceBracket(String str) {
        String dest = "";
        if (str != null) {
            String regEx = "[\\[\\]]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            dest = m.replaceAll("").trim();
        }
        return dest;
    }

    /**
     * 过滤字符串中的空格
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 根据分隔符将String转换为List
     *
     * @param str
     * @param separator
     *            分隔符
     * @return
     */
    public List<String> stringToList(String str, String separator) {
        List<String> list = Arrays.asList(str.split(separator));
        return list;
    }

    /**
     * 根据分隔符将List转换为String
     *
     * @param list
     * @param separator
     * @return
     */
    public String listToString(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 获取对象的类名
     * @param object
     * @return
     */
    public static String getSimpleName(Object object) {
        return object.getClass().getSimpleName();
    }

    /**
     * 获取对象的类名
     * @param object
     * @return
     */
    public static String getName(Object object) {
        return object.getClass().getName();
    }

}