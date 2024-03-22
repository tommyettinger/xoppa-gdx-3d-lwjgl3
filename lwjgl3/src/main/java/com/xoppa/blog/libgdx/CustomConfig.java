package com.xoppa.blog.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.util.function.Supplier;

/**
 * Stores information needed to describe and launch a stage of the tutorial, as a new window.
 */
public class CustomConfig extends Lwjgl3ApplicationConfiguration {
    public String name;
    public String data;
    public int width, height;
    public Supplier<? extends ApplicationListener> ctor;

    /**
     *
     * @param name used for the title of the tutorial window
     * @param width the width of the window to open
     * @param height the height of the window to open
     * @param ctor a constructor, method reference, lambda, or other function that returns an ApplicationListener
     */
    public CustomConfig(String name, int width, int height, Supplier<? extends ApplicationListener> ctor) {
        this(name, width, height, ctor, "");
    }

    /**
     *
     * @param name used for the title of the tutorial window
     * @param width the width of the window to open
     * @param height the height of the window to open
     * @param ctor a constructor, method reference, lambda, or other function that returns an ApplicationListener
     * @param data allows associating extra information with this window creator, as a String
     */
    public CustomConfig(String name, int width, int height, Supplier<? extends ApplicationListener> ctor, String data) {
        super();
        setTitle(this.name = name);
        setWindowIcon(Files.FileType.Internal
                , "libgdx128.png"
                , "libgdx64.png"
                , "libgdx32.png"
                , "libgdx16.png");
        setWindowedMode(this.width = width, this.height = height);
        this.ctor = ctor;
        this.data = data;
    }

    @Override
    public String toString() {
        return name;
    }
}
