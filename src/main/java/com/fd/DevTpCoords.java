package com.fd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DevTpCoords {
    public static Map<String, Map<ArrayList<Integer>, String>> getCoords() {
        Map<String, Map<ArrayList<Integer>, String>> coords = new HashMap<>();

        coords.put("stronghold", new HashMap<>() {{
            put(new ArrayList<>(3) {{
                // x, y, z
                add(151);
                add(157);
                add(162);
            }}, "nether");
        }});
        coords.put("burningVillage", new HashMap<>() {{
            put(new ArrayList<>(3) {{
                // x, y, z
                add(13);
                add(149);
                add(312);
            }}, "nether");
        }});
        coords.put("desert", new HashMap<>() {{
            put(new ArrayList<>(3) {{
                // x, y, z
                add(1458);
                add(129);
                add(945);
            }}, "nether");
        }});
        coords.put("jungle", new HashMap<>() {{
            put(new ArrayList<>(3) {{
                // x, y, z
                add(-55);
                add(175);
                add(-614);
            }}, "overworld");
        }});

        return coords;
    }
    //    public static Map<String, Map<String, ArrayList<Integer>>> getCoords() {
//        Map<String, ArrayList<Integer>> overworldCoords = new HashMap<>();
//
//
//        overworldCoords.put("jungle", new ArrayList<>(3) {{
//            // x, y, z
//            add(-55);
//            add(175);
//            add(-614);
//        }});
//
//
//        Map<String, ArrayList<Integer>> netherCoords = new HashMap<>();
//        netherCoords.put("stronghold", new ArrayList<>(3) {{
//            // x, y, z
//            add(151);
//            add(157);
//            add(162);
//        }});
//        netherCoords.put("burningVillage", new ArrayList<>(3) {{
//            // x, y, z
//            add(13);
//            add(149);
//            add(312);
//        }});
//        netherCoords.put("desert", new ArrayList<>(3) {{
//            // x, y, z
//            add(1458);
//            add(129);
//            add(945);
//        }});
//
//        Map<String, Map<String, ArrayList<Integer>>> coords = new HashMap<>();
//        coords.put("overworld", overworldCoords);
//        coords.put("nether", netherCoords);
//
//        return coords;
//
//    }

}
