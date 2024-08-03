package org.utilities;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorFinder {

    private static final Map<String, Color> COLOR_MAP = new HashMap<>();

    static {
        COLOR_MAP.put("Red", new Color(255, 0, 0));
        COLOR_MAP.put("Green", new Color(0, 255, 0));
        COLOR_MAP.put("Blue", new Color(0, 0, 255));
        COLOR_MAP.put("White", new Color(255, 255, 255));
        COLOR_MAP.put("Black", new Color(0, 0, 0));
        COLOR_MAP.put("Yellow", new Color(255, 255, 0));
        COLOR_MAP.put("Cyan", new Color(0, 255, 255));
        COLOR_MAP.put("Magenta", new Color(255, 0, 255));
        COLOR_MAP.put("Pink", new Color(255, 192, 203));
        COLOR_MAP.put("Orange", new Color(255, 165, 0));
        COLOR_MAP.put("Gray", new Color(128, 128, 128));
        COLOR_MAP.put("Maroon", new Color(128, 0, 0));
        COLOR_MAP.put("Olive", new Color(128, 128, 0));
        COLOR_MAP.put("Purple", new Color(128, 0, 128));
        COLOR_MAP.put("Teal", new Color(0, 128, 128));
        COLOR_MAP.put("Navy", new Color(0, 0, 128));
        // Add more colors as needed
    }

    public static String getColorNameFromRGBA(int r, int g, int b, int a) {
        Color inputColor = new Color(r, g, b, a);
        String closestColorName = null;
        double closestDistance = Double.MAX_VALUE;

        for (Map.Entry<String, Color> entry : COLOR_MAP.entrySet()) {
            double distance = colorDistance(inputColor, entry.getValue());
            if (distance < closestDistance) {
                closestDistance = distance;
                closestColorName = entry.getKey();
            }
        }

        return closestColorName;
    }

    public static double colorDistance(Color c1, Color c2) {
        int rDiff = c1.getRed() - c2.getRed();
        int gDiff = c1.getGreen() - c2.getGreen();
        int bDiff = c1.getBlue() - c2.getBlue();
        return Math.sqrt(rDiff * rDiff + gDiff * gDiff + bDiff * bDiff);
    }

    public static Color parseRgbaString(String rgbaString) {
        if (rgbaString.startsWith("rgba")) {
            // Example: rgba(255, 48, 76, 1)
            String[] values = rgbaString.replace("rgba(", "").replace(")", "").split(",");
            int r = Integer.parseInt(values[0].trim());
            int g = Integer.parseInt(values[1].trim());
            int b = Integer.parseInt(values[2].trim());
            float a = Float.parseFloat(values[3].trim());
            int alpha = Math.round(a * 255);
            return new Color(r, g, b, alpha);
        } else if (rgbaString.startsWith("rgb")) {
            // Example: rgb(255, 48, 76)
            String[] values = rgbaString.replace("rgb(", "").replace(")", "").split(",");
            int r = Integer.parseInt(values[0].trim());
            int g = Integer.parseInt(values[1].trim());
            int b = Integer.parseInt(values[2].trim());
            return new Color(r, g, b);
        } else {
            throw new IllegalArgumentException("Unsupported color format: " + rgbaString);
        }
    }
}
