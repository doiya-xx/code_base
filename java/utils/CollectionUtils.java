package utils;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static void main(String[] args) {
        CollectionUtils collectionUtils = new CollectionUtils();
        collectionUtils.testMap();
        collectionUtils.testListMap();
    }
    
    /**
     * 测试List<Map>的合并
     */
    private void testListMap() {
        List<Map<String, Integer>> listOfMaps = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Map<String, Integer> map = new HashMap<>();
            map.put("table_name1", i + 1);
            listOfMaps.add(map);
        }
        for (int i = 0; i < 4; i++) {
            Map<String, Integer> map = new HashMap<>();
            map.put("table_name2", i + 1);
            listOfMaps.add(map);
        }
        System.out.println(listOfMaps);
        Map<String, Set<Integer>> resultMap = listOfMaps.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
                ));
        System.out.println(resultMap);
    }
    
    /**
     * 测试Map的合并
     */
    private void testMap() {
        Map<String, String> maps = new HashMap<>();
        maps.put("table_name1", "1, 2, 3, 4");
        maps.put("table_name2", "5, 6, 7, 8");
        System.out.println("maps = " + maps);
        Map<String, Set<Integer>> resultMap = maps.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Arrays.stream(e.getValue().split(", "))
                                .map(Integer::parseInt)
                                .collect(Collectors.toSet())
                ));
        System.out.println(resultMap);
    }
    
    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    
    /**
     * 判断多个集合是否为空，只要有一个为空，则返回true
     */
    public static boolean isEmpty(Collection<?>... collections) {
        for (Collection<?> collection : collections) {
            if (isEmpty(collection)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断集合是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
    
    /**
     * 判断多个集合是否不为空，只有都不为空，则返回true
     */
    public static boolean isNotEmpty(Collection<?>... collections) {
        for (Collection<?> collection : collections) {
            if (isEmpty(collection)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 判断Map是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
    
    /**
     * 判断多个Map是否为空，只要有一个为空，则返回true
     */
    public static boolean isEmpty(Map<?, ?>... maps) {
        for (Map<?, ?> map : maps) {
            if (isEmpty(map)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断Map是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
    
    /**
     * 判断多个Map是否不为空，只有都不为空，则返回true
     */
    public static boolean isNotEmpty(Map<?, ?>... maps) {
        for (Map<?, ?> map : maps) {
            if (isEmpty(map)) {
                return false;
            }
        }
        return true;
    }
    
    
}
