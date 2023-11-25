package com.fd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DevTpCoords {
    public static Map<String, Map<String, ArrayList<Integer>>> getCoords() {
        Map<String, Map<String, ArrayList<Integer>>> coords = new HashMap<>();

        coords.put("stronghold", new HashMap<>() {{
            put("nether",new ArrayList<>(3) {{
                // x, y, z
                add(151);
                add(157);
                add(162);
            }});
        }});
        coords.put("burningVillage", new HashMap<>() {{
            put("nether",new ArrayList<>(3) {{
                // x, y, z
                add(13);
                add(149);
                add(312);
            }});
        }});
        coords.put("desert", new HashMap<>() {{
            put("nether",new ArrayList<>(3) {{
                // x, y, z
                add(1458);
                add(129);
                add(945);
            }});
        }});
        coords.put("jungle", new HashMap<>() {{
            put("overworld",new ArrayList<>(3) {{
                // x, y, z
                add(-55);
                add(175);
                add(-614);
            }});
        }});
        return coords;
    }

}
