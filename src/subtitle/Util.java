package subtitle;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class Util {

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean is(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean is(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <E> E get(List<E> list, int i, E noValue) {
        return (i < list.size() ? list.get(i) : noValue);
    }

    public static <E> E first(List<E> list, E noValue) {
        return (!list.isEmpty() ? list.get(0) : noValue);
    }

    public static <E> E last(List<E> list, E noValue) {
        return (!list.isEmpty() ? list.get(list.size() - 1) : noValue);
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() < 1);
    }

    public static boolean is(String str) {
        return !isEmpty(str);
    }

    public static String clean(String str) {
        return or(str, "").trim();
    }

    public static <T> T or(T left, T right) {
        return (left != null ? left : right);
    }

    public static <T> T or(T left, T right, T... rights) {
        left = (left != null ? left : right);
        for (int i = 0; i < rights.length && left == null; i++) {
            left = rights[i];
        }
        return left;
    }

    public static Float toFloat(Object obj, Float noValue) {
        if (obj == null) {
            return noValue;
        }
        if (Number.class.isInstance(obj)) {
            return ((Number) obj).floatValue();
        }
        try {
            return Float.valueOf(obj.toString());
        } catch (NumberFormatException e) {
            return noValue;
        }
    }

    public static Double toDouble(Object obj, Double noValue) {
        if (obj == null) {
            return noValue;
        }
        if (Number.class.isInstance(obj)) {
            return ((Number) obj).doubleValue();
        }
        try {
            return Double.valueOf(obj.toString());
        } catch (NumberFormatException e) {
            return noValue;
        }
    }

    public static Integer toInt(Object obj, Integer noValue) {
        if (obj == null) {
            return noValue;
        }
        if (Number.class.isInstance(obj)) {
            return ((Number) obj).intValue();
        }
        try {
            return Integer.valueOf(obj.toString());
        } catch (NumberFormatException e) {
            return noValue;
        }
    }

    public static Long toLong(Object obj, Long noValue) {
        if (obj == null) {
            return noValue;
        }
        if (Number.class.isInstance(obj)) {
            return ((Number) obj).longValue();
        }
        try {
            return Long.valueOf(obj.toString());
        } catch (NumberFormatException e) {
            return noValue;
        }
    }

}