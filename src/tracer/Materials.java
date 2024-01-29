package tracer;

import maths.Vector;

/**
 *
 * @author james
 */
public class Materials {

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

    public static Material POLISHED_SILVER = new Material(
            new RGB(1, 1, 1), // colour
            new Vector(0.23125, 0.23125, 0.23125), // ambient
            new Vector(0.2775, 0.2775, 0.2775), // diffuse
            new Vector(0.773911, 0.773911, 0.773911), // specular
            new Vector(0, 0, 0), // emissive
            90 // shininess
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
