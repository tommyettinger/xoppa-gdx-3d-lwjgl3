package com.xoppa.blog.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.lang.reflect.InvocationTargetException;

/**
 * This is the main selection screen that allows picking a tutorial stage to run.
 */
public class Main extends ApplicationAdapter {
    public SelectBox<CustomConfig> choices;
    public TextButton button;
    public Stage stage;
    public Lwjgl3Window activeWindow;
    public static String data;
    @Override
    public void create() {
        super.create();
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("skin/neon-ui.json"), new TextureAtlas("skin/neon-ui.atlas"));
        choices = new SelectBox<CustomConfig>(skin);
        CustomConfig[] items = new CustomConfig[]{
            new CustomConfig("Basic 3D using libGDX, step 1: render a cube", 640, 480, com.xoppa.blog.libgdx.g3d.basic3d.step1.Basic3DTest.class),
            new CustomConfig("Basic 3D using libGDX, step 2: lights", 640, 480, com.xoppa.blog.libgdx.g3d.basic3d.step2.Basic3DTest.class),
            new CustomConfig("Basic 3D using libGDX, step 3: camera controller", 640, 480, com.xoppa.blog.libgdx.g3d.basic3d.step3.Basic3DTest.class),
            new CustomConfig("Load models using libGDX, step 1: load a Wavefront model", 640, 480, com.xoppa.blog.libgdx.g3d.loadmodels.step1.LoadModelsTest.class, "loadmodels/data"),
            new CustomConfig("Load models using libGDX, step 2: use AssetManager", 640, 480, com.xoppa.blog.libgdx.g3d.loadmodels.step2.LoadModelsTest.class, "loadmodels/data"),
            new CustomConfig("Load models using libGDX, step 3: multiple instances", 640, 480, com.xoppa.blog.libgdx.g3d.loadmodels.step3.LoadModelsTest.class, "loadmodels/data"),
            new CustomConfig("Load models using libGDX, step 4: use fbx-conv", 640, 480, com.xoppa.blog.libgdx.g3d.loadmodels.step4.LoadModelsTest.class, "loadmodels/data"),
            new CustomConfig("Loading a scene using libGDX, step 1: coding a scene", 640, 480, com.xoppa.blog.libgdx.g3d.loadscene.step1.LoadSceneTest.class, "loadscene/data"),
            new CustomConfig("Loading a scene using libGDX, step 2: combining models", 640, 480, com.xoppa.blog.libgdx.g3d.loadscene.step2.LoadSceneTest.class, "loadscene/data"),
            new CustomConfig("Loading a scene using libGDX, step 3: loading a modeled scene", 640, 480, com.xoppa.blog.libgdx.g3d.loadscene.step3.LoadSceneTest.class, "loadscene/data"),
            new CustomConfig("Behind the 3D scenes, step 1: base code", 640, 480, com.xoppa.blog.libgdx.g3d.behindscenes.step1.BehindTheScenesTest.class, "behindscenes/data"),
            new CustomConfig("Behind the 3D scenes, step 2: using ModelLoader", 640, 480, com.xoppa.blog.libgdx.g3d.behindscenes.step2.BehindTheScenesTest.class, "behindscenes/data"),
            new CustomConfig("Behind the 3D scenes, step 3: change material by NodePart", 640, 480, com.xoppa.blog.libgdx.g3d.behindscenes.step3.BehindTheScenesTest.class, "behindscenes/data"),
            new CustomConfig("Behind the 3D scenes, step 4: change material by name", 640, 480, com.xoppa.blog.libgdx.g3d.behindscenes.step4.BehindTheScenesTest.class, "behindscenes/data"),
            new CustomConfig("Behind the 3D scenes, step 5: change material per ModelInstance", 640, 480, com.xoppa.blog.libgdx.g3d.behindscenes.step5.BehindTheScenesTest.class, "behindscenes/data"),
            new CustomConfig("Behind the 3D scenes, step 6: using a Renderable", 640, 480, com.xoppa.blog.libgdx.g3d.behindscenes.step6.BehindTheScenesTest.class, "behindscenes/data"),
            new CustomConfig("Behind the 3D scenes, step 7: using a Shader", 640, 480, com.xoppa.blog.libgdx.g3d.behindscenes.step7.BehindTheScenesTest.class, "behindscenes/data"),
            new CustomConfig("Creating a shader with libGDX, step 1: render a sphere", 640, 480, com.xoppa.blog.libgdx.g3d.createshader.step1.ShaderTest.class, "createshader/data"),
            new CustomConfig("Creating a shader with libGDX, step 2: render points", 640, 480, com.xoppa.blog.libgdx.g3d.createshader.step2.ShaderTest.class, "createshader/data"),
            new CustomConfig("Creating a shader with libGDX, step 3: customize default shader", 640, 480, com.xoppa.blog.libgdx.g3d.createshader.step3.ShaderTest.class, "createshader/data"),
            new CustomConfig("Creating a shader with libGDX, step 4: implement shader", 640, 480, com.xoppa.blog.libgdx.g3d.createshader.step4.ShaderTest.class, "createshader/data"),
            new CustomConfig("Creating a shader with libGDX, step 5: enable depth test", 640, 480, com.xoppa.blog.libgdx.g3d.createshader.step5.ShaderTest.class, "createshader/data"),
            new CustomConfig("Creating a shader with libGDX, step 6: cache uniform locations", 640, 480, com.xoppa.blog.libgdx.g3d.createshader.step6.ShaderTest.class, "createshader/data"),
            new CustomConfig("Using materials with libGDX, step 1: using ModelBatch", 640, 480, com.xoppa.blog.libgdx.g3d.usingmaterials.step1.MaterialTest.class, "usingmaterials/data"),
            new CustomConfig("Using materials with libGDX, step 2: add a uniform", 640, 480, com.xoppa.blog.libgdx.g3d.usingmaterials.step2.MaterialTest.class, "usingmaterials/data"),
            new CustomConfig("Using materials with libGDX, step 3: using userData", 640, 480, com.xoppa.blog.libgdx.g3d.usingmaterials.step3.MaterialTest.class, "usingmaterials/data"),
            new CustomConfig("Using materials with libGDX, step 4: using ColorAttribute", 640, 480, com.xoppa.blog.libgdx.g3d.usingmaterials.step4.MaterialTest.class, "usingmaterials/data"),
            new CustomConfig("Using materials with libGDX, step 5: implement canRender", 640, 480, com.xoppa.blog.libgdx.g3d.usingmaterials.step5.MaterialTest.class, "usingmaterials/data"),
            new CustomConfig("Using materials with libGDX, step 6: add another color", 640, 480, com.xoppa.blog.libgdx.g3d.usingmaterials.step6.MaterialTest.class, "usingmaterials/data"),
            new CustomConfig("Using materials with libGDX, step 7: use custom attribute", 640, 480, com.xoppa.blog.libgdx.g3d.usingmaterials.step7.MaterialTest.class, "usingmaterials/data"),
            new CustomConfig("Using materials with libGDX, step 8: update canRender", 640, 480, com.xoppa.blog.libgdx.g3d.usingmaterials.step8.MaterialTest.class, "usingmaterials/data"),
            new CustomConfig("Using materials with libGDX, step 9: create custom attribute", 640, 480, com.xoppa.blog.libgdx.g3d.usingmaterials.step9.MaterialTest.class, "usingmaterials/data"),
            new CustomConfig("3D Frustum culling, step 1: no frustum culling", 640, 480, com.xoppa.blog.libgdx.g3d.frustumculling.step1.FrustumCullingTest.class, "loadscene/data"),
            new CustomConfig("3D Frustum culling, step 2: position culling", 640, 480, com.xoppa.blog.libgdx.g3d.frustumculling.step2.FrustumCullingTest.class, "loadscene/data"),
            new CustomConfig("3D Frustum culling, step 3: bounds culling", 640, 480, com.xoppa.blog.libgdx.g3d.frustumculling.step3.FrustumCullingTest.class, "loadscene/data"),
            new CustomConfig("3D Frustum culling, step 4: sphere culling", 640, 480, com.xoppa.blog.libgdx.g3d.frustumculling.step4.FrustumCullingTest.class, "loadscene/data"),
            new CustomConfig("Ray picking, step 1: selecting objects", 640, 480, com.xoppa.blog.libgdx.g3d.raypicking.step1.RayPickingTest.class, "loadscene/data"),
            new CustomConfig("Ray picking, step 2: moving objects", 640, 480, com.xoppa.blog.libgdx.g3d.raypicking.step2.RayPickingTest.class, "loadscene/data"),
            new CustomConfig("Ray picking, step 3: more precise selecting objects", 640, 480, com.xoppa.blog.libgdx.g3d.raypicking.step3.RayPickingTest.class, "loadscene/data"),
            new CustomConfig("Shapes, step 1: move code to GameObject", 640, 480, com.xoppa.blog.libgdx.g3d.shapes.step1.ShapeTest.class, "loadscene/data"),
            new CustomConfig("Shapes, step 2: Sphere shape", 640, 480, com.xoppa.blog.libgdx.g3d.shapes.step2.ShapeTest.class, "loadscene/data"),
            new CustomConfig("Shapes, step 3: Box shape", 640, 480, com.xoppa.blog.libgdx.g3d.shapes.step3.ShapeTest.class, "loadscene/data"),
            new CustomConfig("Shapes, step 4: Disc shape", 640, 480, com.xoppa.blog.libgdx.g3d.shapes.step4.ShapeTest.class, "loadscene/data"),
            new CustomConfig("Bullet Collision detection, step 1: no collision detection", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.collision.step1.BulletTest.class),
            new CustomConfig("Bullet Collision detection, step 2: using a collision algorithm", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.collision.step2.BulletTest.class),
            new CustomConfig("Bullet Collision detection, step 3: using a collision dispatcher", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.collision.step3.BulletTest.class),
            new CustomConfig("Bullet Collision detection, step 4: add more objects", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.collision.step4.BulletTest.class),
            new CustomConfig("Bullet Collision detection, step 5: using a ContactListener", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.collision.step5.BulletTest.class),
            new CustomConfig("Bullet Collision detection, step 6: optimize the callback method", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.collision.step6.BulletTest.class),
            new CustomConfig("Bullet Collision detection, step 7: using a collision world", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.collision.step7.BulletTest.class),
            new CustomConfig("Bullet Collision detection, step 8: using collision filtering", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.collision.step8.BulletTest.class),
            new CustomConfig("Bullet Dynamics, step 1: add physics properties", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.dynamics.step1.BulletTest.class),
            new CustomConfig("Bullet Dynamics, step 2: add a dynamics world", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.dynamics.step2.BulletTest.class),
            new CustomConfig("Bullet Dynamics, step 3: color objects on the ground", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.dynamics.step3.BulletTest.class),
            new CustomConfig("Bullet Dynamics, step 4: add motion state", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.dynamics.step4.BulletTest.class),
            new CustomConfig("Bullet Dynamics, step 5: contact callback filtering", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.dynamics.step5.BulletTest.class),
            new CustomConfig("Bullet Dynamics, step 6: kinematic body", 640, 480, com.xoppa.blog.libgdx.g3d.bullet.dynamics.step6.BulletTest.class),
            new CustomConfig("A simple card game, step 1: initial setup", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.step1.CardGame.class, "cardgame/data"),
            new CustomConfig("A simple card game, step 2: use meaningful units", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.step2.CardGame.class, "cardgame/data"),
            new CustomConfig("A simple card game, step 3: structure the code", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.step3.CardGame.class, "cardgame/data"),
            new CustomConfig("A simple card game, step 4: controlling the camera", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.step4.CardGame.class, "cardgame/data"),
            new CustomConfig("A simple card game, step 5: add perspective", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.step5.CardGame.class, "cardgame/data"),
            new CustomConfig("A simple card game, step 6: switch to ModelBatch", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.step6.CardGame.class, "cardgame/data"),
            new CustomConfig("A simple card game, step 7: reduce render calls", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.step7.CardGame.class, "cardgame/data"),
            new CustomConfig("A simple card game, step 8: add a table", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.step8.CardGame.class, "cardgame/data"),
            new CustomConfig("A simple card game, step 9: keep the cards on the table", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.step9.CardGame.class, "cardgame/data"),
            new CustomConfig("A simple card game, step A: animating the cards", 640, 480, com.xoppa.blog.libgdx.g3d.cardgame.stepa.CardGame.class, "cardgame/data")
        };
        choices.setItems(items);
        button = new TextButton("Launch!", skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(activeWindow != null)
                    activeWindow.closeWindow();
                final CustomConfig sel = choices.getSelected();
                System.out.println(sel.name);
                try {
                    activeWindow = ((Lwjgl3Application)Gdx.app).newWindow(sel.clazz.getDeclaredConstructor().newInstance(), sel);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    return;
                }
                data = (sel.data == null || sel.data.isEmpty()) ? "data" : sel.data;
            }
        });
        Table table = new Table(skin);
        table.setFillParent(true);
        table.top().row();
        table.row().height(64f);
        table.add(choices).height(48f).spaceRight(32f);
        table.row();
        table.add(button).height(48f);
        table.row();
        table.row().expandY();
        table.bottom();
        table.row().colspan(2);
        table.add("Choose a demo from the dropdown above and click Launch.");
        table.row().colspan(2);
        table.add("Follow along with the tutorials on the blog:");
        table.row().colspan(2);
        table.add("https://xoppa.github.io/blog/basic-3d-using-libgdx/");
        table.row();
        table.add("");
        table.row().colspan(2);
        table.add("Thanks to Raymond Buckley for the Neon skin.");
        table.row().colspan(2);
        table.add("https://ray3k.wordpress.com");
//        table.pack();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        // use this during testing to auto-launch a demo
//        ((Lwjgl3Application)Gdx.app).newWindow(WorldMapTextDemo.config.instantiate(), WorldMapTextDemo.config);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
    
    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0.05f, 0.05f, 0.05f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();
    }
}
