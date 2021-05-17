package sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

public class Main implements ActionListener {

    private TransformGroup objectTransformGroup;
    private Transform3D object = new Transform3D();
    private Timer timer = new Timer(50, this);
    private float angle = 0;


    public Main() {
        BranchGroup scene = createSceneGraph();
        SimpleUniverse u = new SimpleUniverse();
        u.getViewingPlatform().setNominalViewingTransform();
        Transform3D lookAt = new Transform3D();
        lookAt.lookAt( new  Point3d( 0.0, 1.0, -2.3 )
                , new  Point3d( 0.0, 0.0, 0.0 )
                , new Vector3d( 0.0, 1.0, 0.0) );
        lookAt.invert();
        u.getViewingPlatform().getViewPlatformTransform().setTransform(lookAt);
        u.addBranchGraph(scene);
        timer.start();
    }

    private BranchGroup createSceneGraph() {

        Background background = new Background(new Color3f(Color.WHITE)) ;
        BranchGroup objRoot = new BranchGroup();

        objectTransformGroup = new TransformGroup();
        objectTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//        buildTree();
        objRoot.addChild(objectTransformGroup);
        buildPC();

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        Color3f light1Color = new Color3f(1.0f, 1f, 1f);
        Vector3f light1Direction = new Vector3f(7.0f, 7.0f, 11.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);
        objRoot.addChild(background);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);

        return objRoot;
    }

    private void setObject() {

    }

    private void buildPC() {
        TransformGroup display = new TransformGroup();
        Transform3D transform = new Transform3D();
        Box box1 = PCConstructor.getBox(0.27f, 0.4f, 0.01f, "", new Color3f(0f, 0f, 0f));
        Vector3f vector = new Vector3f(.0f, .1f, .0f);
        transform.setTranslation(vector);
        display.setTransform(transform);
        display.addChild(box1);
        objectTransformGroup.addChild(display);

        TransformGroup stand1 = new TransformGroup();
        Transform3D transform1 = new Transform3D();
        Cylinder cylinder1 = PCConstructor.getCylinder(0.02f, 0.1f);
        Vector3f vector1 = new Vector3f(0f, -.2f, .0f);
        transform1.setTranslation(vector1);
        stand1.setTransform(transform1);
        stand1.addChild(cylinder1);
        objectTransformGroup.addChild(stand1);

        TransformGroup stand2 = new TransformGroup();
        Transform3D transform2 = new Transform3D();
        Cylinder cylinder2 = PCConstructor.getCylinder(0.2f, 0.01f);
        Vector3f vector2 = new Vector3f(0f, -.25f, .0f);
        transform2.setTranslation(vector2);
        stand2.setTransform(transform2);
        stand2.addChild(cylinder2);
        objectTransformGroup.addChild(stand2);

        TransformGroup tower = new TransformGroup();
        Transform3D transform3 = new Transform3D();
        Box box3 = PCConstructor.getBox(0.3f, 0.15f, (float) 0.3, "src/sample/metalic.jpg", new Color3f(0.341f, 0.325f, 0.321f));
        Vector3f vector3 = new Vector3f(.7f, .0f, .0f);
        transform3.setTranslation(vector3);
        tower.setTransform(transform3);
        tower.addChild(box3);
        objectTransformGroup.addChild(tower);

        TransformGroup keyboard = new TransformGroup();
        Transform3D transform5 = new Transform3D();
        Box box5 = PCConstructor.getBox(0.01f, 0.33f, 0.1f, "src/sample/keyboard.jpg", new Color3f(0.341f, 0.325f, 0.321f));
        Vector3f vector5 = new Vector3f(.0f, -.27f, -.35f);
        transform5.setTranslation(vector5);
        keyboard.setTransform(transform5);
        keyboard.addChild(box5);
        objectTransformGroup.addChild(keyboard);

//        float keycapwidth = .02f;
//
//        for (float i =0; i< box5.getXdimension()/keycapwidth; i+=0.015f)
//        {
//            createKeyCap(.01f, .02f, .02f,  );
//        }
    }

    private void createKeyCap(float height, float width, float length, float x, float y, float z, Color3f emissive) {
        TransformGroup kc = new TransformGroup();
        Transform3D transform = new Transform3D();
        Box cap = PCConstructor.getBox(height, width, length, "", emissive);
        Vector3f vector = new Vector3f(x, y, z);
        transform.setTranslation(vector);
        kc.setTransform(transform);
        kc.addChild(cap);
        objectTransformGroup.addChild(kc);
    }


    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        object.rotY(angle);
        angle += 0.05;

        objectTransformGroup.setTransform(object);
    }
}
