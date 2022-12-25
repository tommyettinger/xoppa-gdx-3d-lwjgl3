package com.xoppa.blog.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * Stores information needed to describe and launch a stage of the tutorial, as a new window.
 */
public class CustomConfig extends Lwjgl3ApplicationConfiguration {
    public String name;
    public String data;
    public int width, height;
    public Class<? extends ApplicationListener> clazz;
    public CustomConfig(String name, int width, int height, Class<? extends ApplicationListener> clazz) {
        this(name, width, height, clazz, "");
    }
    public CustomConfig(String name, int width, int height, Class<? extends ApplicationListener> clazz, String data) {
        super();
        setTitle(this.name = name);
        setWindowIcon(Files.FileType.Internal
                , "libgdx128.png"
                , "libgdx64.png"
                , "libgdx32.png"
                , "libgdx16.png");
        setWindowedMode(this.width = width, this.height = height);
        this.clazz = clazz;
        this.data = data;
    }

    @Override
    public String toString() {
        return name;
    }
}
