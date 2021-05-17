package sample;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;

import java.awt.*;

public class PCConstructor {
    public static Box getBox(float height, float width, float length, String picture, Color3f emissive) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Box(width, height, length, primflags, getTowerAppearance(picture, emissive));
    }

    public static Cylinder getCylinder(float radius, float height) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Cylinder(radius, height, primflags, getStandAppearance());
    }


    private static Appearance getTowerAppearance(String picture, Color3f emissive) {
        Appearance ap = new Appearance();
        Color3f ambient = new Color3f(0f, 0.0f, 0.0f);
        Color3f diffuse = new Color3f(0.1f, 0.1f, 0.1f);
        Color3f specular = new Color3f(0.5f, 0.5f, 0.5f);
        float shininess = 50f;
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, shininess));
        if (!picture.equals("")) {
            TextureLoader loader = new TextureLoader(picture, "LUMINANCE", new Container());
            Texture texture = loader.getTexture();
            texture.setBoundaryModeS(Texture.WRAP);
            texture.setBoundaryModeT(Texture.WRAP);
            texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));
            TextureAttributes texAttr = new TextureAttributes();
            texAttr.setTextureMode(TextureAttributes.MODULATE);
            ap.setTexture(texture);
            ap.setTextureAttributes(texAttr);
        }
        return ap;
    }

    private static Appearance getStandAppearance() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(0.0f, .0f, 0.0f);
        Color3f ambient = new Color3f(1.0f, 0.0f, 0.0f);
        Color3f diffuse = new Color3f(0.2f, 0.15f, .15f);
        Color3f specular = new Color3f(0.0f, 0.8f, 0.0f);
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

}
