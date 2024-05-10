package libert.saehyeon.libertname.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtil {

    public static String toSliceString(String[] args, int startLength) {
        return toSliceString(toList(args), startLength, args.length);
    }

    public static String toSliceString(List<String> args, int startLength) {
        return toSliceString(args, startLength, args.size());
    }

    public static String toSliceString(List<String> args, int startLength, int endLength) {
        return join(args, "", startLength, endLength);
    }

    /**
     * 문자열 배열에서 특정 인덱스 범위 내의 요소들만 join합니다.
     * @param args 문자열 배열
     * @param join join 문자열
     * @param startLength 시작 요소 번째 (2번째 ~ 5번째일 경우 2)
     * @param endLength 종료 요소 번째 (2번째 ~ 5번째일 경우 5)
     * @return
     */
    public static String join(List<String> args, String join, int startLength, int endLength) {
        return String.join(join, args.subList(startLength, endLength));
    }

    public static String join(String[] args, String join, int startLength, int endLength) {
        return String.join(join, toList(args).subList(startLength, endLength));
    }

    public static String join(String[] args, String join, int startLength) {
        return String.join(join, toList(args).subList(startLength, args.length));
    }

    public static String join(List<String> args, String join, int startLength) {
        return String.join(join, args.subList(startLength, args.size()));
    }

    public static <T> List<T> toList(T[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }

    public static <T> ArrayList<T> toArrayList(T[] array) {
        return new ArrayList<>(toList(array));
    }
}
