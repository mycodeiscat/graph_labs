import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.imageio.ImageIO;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


class Sword extends JFrame {

    private Canvas3D canvas;
    private SimpleUniverse universe;
    private BranchGroup root;

    private TransformGroup sword;

    private Map<String, Shape3D> shapeMap;

    Sword() throws IOException {

        configureWindow();
        configureCanvas();
        configureUniverse();

        root = new BranchGroup();
        root.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);

        addImageBackground("resources/ubw.png");
        addLightToUniverse();

        changeViewAngle();

        sword = getSwordGroup();

        TransformGroup room = new TransformGroup();
        room.addChild(sword);

        root.addChild(room);

        addAppearance();

        SwordAnimation sword = new SwordAnimation(this);
        canvas.addKeyListener(sword);

        root.compile();
        universe.addBranchGraph(root);
    }

    private void configureWindow() {
        setTitle("UBW Animation");
        setSize(1000, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureCanvas() {
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        canvas.setFocusable(true);
        add(canvas, BorderLayout.CENTER);
    }

    private void configureUniverse() {
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }

    private void addImageBackground(String imagePath) {
        TextureLoader t = new TextureLoader(imagePath, canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }

    private void addLightToUniverse() {
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(1000);

        DirectionalLight directionalLight = new DirectionalLight(
                new Color3f(new Color(255, 255, 255)),
                new Vector3f(0, -0.5f, -0.5f));
        directionalLight.setInfluencingBounds(bounds);

        AmbientLight ambientLight = new AmbientLight(
                new Color3f(new Color(255, 255, 245)));
        ambientLight.setInfluencingBounds(bounds);

        root.addChild(directionalLight);
        root.addChild(ambientLight);
    }

    private TransformGroup getSwordGroup() throws IOException {
        Transform3D scale = new Transform3D();
        scale.setScale(new Vector3d(0.8, 0.8, 0.8));

        Transform3D yRotation = new Transform3D();
        yRotation.rotY(Math.PI);

        Transform3D zRotation = new Transform3D();
        zRotation.rotZ(.06);

        Transform3D xRotation = new Transform3D();
        xRotation.rotX(-.5);

        zRotation.mul(xRotation);
        yRotation.mul(zRotation);
        scale.mul(yRotation);

        TransformGroup group = getModelGroup("resources/swords.obj");
        group.setTransform(scale);
        return group;
    }

    private TransformGroup getModelGroup(String path) throws IOException {
        Scene scene = getSceneFromFile(path);
        shapeMap = scene.getNamedObjects();

        printModelElementsList(shapeMap);

        TransformGroup group = new TransformGroup();

        for (String shapeName : shapeMap.keySet()) {
            Shape3D shape = shapeMap.get(shapeName);

            scene.getSceneGroup().removeChild(shape);
            group.addChild(shape);
        }

        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        return group;
    }

    private void printModelElementsList(Map<String, Shape3D> shapeMap) {
        for (String name : shapeMap.keySet()) {
            System.out.printf("Name: %s\n", name);
        }
    }

    Texture2D getTexture(String path, String mode) {
        TextureLoader textureLoader = new TextureLoader(path, mode, canvas);
        ImageComponent2D image = textureLoader.getImage();
        Texture2D texture =
                new Texture2D(Texture2D.BASE_LEVEL, Texture2D.RGBA,
                        image.getWidth(), image.getHeight());
        texture.setImage(0, image);
        texture.setEnable(true);
        texture.setBoundaryModeS(Texture.WRAP);

        texture.setBoundaryModeT(Texture.WRAP);
        return texture;
    }

    private void addAppearance() {
        Appearance appearance1 = new Appearance();
        Texture2D texture = getTexture("resources//base.png", "LUMINANCE");
//        TextureAttributes texAttr = new TextureAttributes();
//        texAttr.setTextureMode(TextureAttributes.MODULATE);
        PolygonAttributes polygonAttributes = new PolygonAttributes();
        polygonAttributes.setCullFace(PolygonAttributes.CULL_NONE);
        //appear.setTexCoordGeneration(new TexCoordGeneration());
        appearance1.setTexture(texture);
        appearance1.setPolygonAttributes(polygonAttributes);

//        appearance1.setTextureAttributes(texAttr);
        shapeMap.get("pplane4").setAppearance(appearance1);

        Appearance appearance2 = new Appearance();
        Texture2D texture2 = getTexture("resources/roug.png", "LUMINANCE");
//        TextureAttributes texAttr = new TextureAttributes();
//        texAttr.setTextureMode(TextureAttributes.MODULATE);
        PolygonAttributes polygonAttributes2 = new PolygonAttributes();
        polygonAttributes2.setCullFace(PolygonAttributes.CULL_NONE);
        //appear.setTexCoordGeneration(new TexCoordGeneration());
        appearance2.setTexture(texture2);
        appearance2.setPolygonAttributes(polygonAttributes);

//        appearance1.setTextureAttributes(texAttr);
        shapeMap.get("pplane5").setAppearance(appearance2);

    }

    private Appearance getAppearance(Color materialColor) {
        Appearance appearance = new Appearance();
        appearance.setMaterial(getMaterial(materialColor));
        return appearance;
    }

    private Material getMaterial(Color defaultColor) {
        Material material = new Material();
        material.setEmissiveColor(new Color3f(Color.BLACK));
        material.setAmbientColor(new Color3f(defaultColor));
        material.setDiffuseColor(new Color3f(defaultColor));
        material.setSpecularColor(new Color3f(defaultColor));
        material.setShininess(80);
        material.setLightingEnable(true);
        return material;
    }

    private void changeViewAngle() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();
        vpTranslation.setTranslation(new Vector3f(0, 0, 6));
        vpGroup.setTransform(vpTranslation);
    }

    private static Scene getSceneFromFile(String location) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(location));
    }

    TransformGroup getSwordTransformGroup() {
        return sword;
    }

}
