package tracer;

import maths.Vector;

/**
 *
 * @author james
 */
public class Materials {

    /*
        Materials sourced from: https://globe3d.sourceforge.io/g3d_html/gl-materials__ads.htm#26_5
     */
    public static Material MIRROR = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.1, 0.1, 0.1), // ambient
            new Vector(0.1, 0.1, 0.1), // diffuse
            new Vector(0.8, 0.8, 0.8), // specular
            new Vector(0, 0, 0), // emissive
            77 // shininess
    );

    public static Material NEUTRAL = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.2, 0.2, 0.2), // ambient
            new Vector(0.8, 0.8, 0.8), // diffuse
            new Vector(0, 0, 0), // specular
            new Vector(0, 0, 0), // emissive
            0 // shininess
    );

    public static Material GLASS = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0, 0, 0), // ambient
            new Vector(0.588235, 0.670588, 0.729412), // diffuse
            new Vector(0.9, 0.9, 0.9), // specular
            new Vector(0, 0, 0), // emissive
            96 // shininess
    );

    public static Material BRASS = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.329412, 0.223529, 0.027451), // ambient
            new Vector(0.780392, 0.568627, 0.113725), // diffuse
            new Vector(0.992157, 0.941176, 0.807843), // specular
            new Vector(0, 0, 0), // emissive
            28 // shininess
    );

    public static Material BRONZE = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.2125, 0.1275, 0.054), // ambient
            new Vector(0.714, 0.4284, 0.18144), // diffuse
            new Vector(0.393548, 0.271906, 0.166721), // specular
            new Vector(0, 0, 0), // emissive
            26 // shininess
    );

    public static Material POLISHED_BRONZE = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.25, 0.148, 0.06475), // ambient
            new Vector(0.4, 0.2368, 0.1036), // diffuse
            new Vector(0.774597, 0.774597, 0.774597), // specular
            new Vector(0, 0, 0), // emissive
            77 // shininess
    );

    public static Material CHROME = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.25, 0.25, 0.25), // ambient
            new Vector(0.4, 0.4, 0.4), // diffuse
            new Vector(0.774597, 0.774597, 0.774597), // specular
            new Vector(0, 0, 0), // emissive
            77 // shininess
    );

    public static Material COPPER = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.19125, 0.0735, 0.0225), // ambient
            new Vector(0.7038, 0.27048, 0.0828), // diffuse
            new Vector(0.256777, 0.137622, 0.086014), // specular
            new Vector(0, 0, 0), // emissive
            13 // shininess
    );

    public static Material POLISHED_COPPER = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.2295, 0.08825, 0.0275), // ambient
            new Vector(0.5508, 0.2118, 0.066), // diffuse
            new Vector(0.580594, 0.223257, 0.0695701), // specular
            new Vector(0, 0, 0), // emissive
            51 // shininess
    );

    public static Material GOLD = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.24725, 0.1995, 0.0745), // ambient
            new Vector(0.75164, 0.60648, 0.22648), // diffuse
            new Vector(0.628281, 0.555802, 0.366065), // specular
            new Vector(0, 0, 0), // emissive
            51 // shininess
    );

    public static Material POLISHED_GOLD = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.24725, 0.2245, 0.0645), // ambient
            new Vector(0.34615, 0.3143, 0.0903), // diffuse
            new Vector(0.797357, 0.723991, 0.208006), // specular
            new Vector(0, 0, 0), // emissive
            83 // shininess
    );

    public static Material PEWTER = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.105882, 0.058824, 0.113725), // ambient
            new Vector(0.427451, 0.470588, 0.541176), // diffuse
            new Vector(0.333333, 0.333333, 0.521569), // specular
            new Vector(0, 0, 0), // emissive
            10 // shininess
    );

    public static Material SILVER = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.19225, 0.19225, 0.19225), // ambient
            new Vector(0.50754, 0.50754, 0.50754), // diffuse
            new Vector(0.508273, 0.508273, 0.508273), // specular
            new Vector(0, 0, 0), // emissive
            51 // shininess
    );

    public static Material POLISHED_SILVER = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.23125, 0.23125, 0.23125), // ambient
            new Vector(0.2775, 0.2775, 0.2775), // diffuse
            new Vector(0.773911, 0.773911, 0.773911), // specular
            new Vector(0, 0, 0), // emissive
            90 // shininess
    );

    public static Material EMERALD = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.0215, 0.1745, 0.0215), // ambient
            new Vector(0.07568, 0.61424, 0.07568), // diffuse
            new Vector(0.633, 0.727811, 0.633), // specular
            new Vector(0, 0, 0), // emissive
            77 // shininess
    );

    public static Material JADE = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.135, 0.2225, 0.1575), // ambient
            new Vector(0.54, 0.89, 0.63), // diffuse
            new Vector(0.316228, 0.316228, 0.316228), // specular
            new Vector(0, 0, 0), // emissive
            13 // shininess
    );

    public static Material OBSIDIAN = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.05375, 0.05, 0.06625), // ambient
            new Vector(0.18275, 0.17, 0.22525), // diffuse
            new Vector(0.332741, 0.328634, 0.346435), // specular
            new Vector(0, 0, 0), // emissive
            38 // shininess
    );

    public static Material PEARL = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.25, 0.20725, 0.20725), // ambient
            new Vector(1, 0.829, 0.829), // diffuse
            new Vector(0.296648, 0.296648, 0.296648), // specular
            new Vector(0, 0, 0), // emissive
            11 // shininess
    );

    public static Material RUBY = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.1745, 0.01175, 0.01175), // ambient
            new Vector(0.61424, 0.04136, 0.04136), // diffuse
            new Vector(0.727811, 0.626959, 0.626959), // specular
            new Vector(0, 0, 0), // emissive
            77 // shininess
    );

    public static Material TURQUOISE = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.1, 0.18725, 0.1745), // ambient
            new Vector(0.396, 0.74151, 0.69102), // diffuse
            new Vector(0.297254, 0.30829, 0.306678), // specular
            new Vector(0, 0, 0), // emissive
            13 // shininess
    );

    public static Material BLACK_PLASTIC = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0, 0, 0), // ambient
            new Vector(0.01, 0.01, 0.01), // diffuse
            new Vector(0.50, 0.50, 0.50), // specular
            new Vector(0, 0, 0), // emissive
            32 // shininess
    );

    public static Material BLACK_RUBBER = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.02, 0.02, 0.02), // ambient
            new Vector(0.01, 0.01, 0.01), // diffuse
            new Vector(0.4, 0.4, 0.4), // specular
            new Vector(0, 0, 0), // emissive
            10 // shininess
    );

    public static Material materialForColour(RGB colour) {
        Material material = new Material(
                colour, // colour
                new Vector(0, 0, 0), // ambient
                colour.asVector(), // diffuse
                new Vector(0.0225, 0.0225, 0.0225), // specular
                new Vector(0, 0, 0), // emissive
                13 // shininess
        );
        return material;
    }

}
