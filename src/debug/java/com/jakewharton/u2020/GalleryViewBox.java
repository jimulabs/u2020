package com.jakewharton.u2020;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.jakewharton.u2020.data.DataModule;
import com.jakewharton.u2020.data.DebugDataModule;
import com.jakewharton.u2020.data.api.ApiModule;
import com.jakewharton.u2020.data.api.DebugApiModule;
import com.jakewharton.u2020.ui.DebugUiModule;
import com.jakewharton.u2020.ui.UiModule;
import com.jakewharton.u2020.ui.gallery.GalleryView;
import com.jimulabs.mirrorsandbox.MirrorAnimatorSandbox;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

/**
 * Created by lintonye on 15-02-03.
 */
public class GalleryViewBox extends MirrorAnimatorSandbox {
    public GalleryViewBox(View root) {
        super(root);
    }

    @Override
    public void enterSandbox() {

    }

    @Module(
            includes = { DebugDataModule.class, DataModule.class },
            library = true,
            injects = GalleryView.class
    )
    public static class MirrorModule {

        private final Context mContext;

        public MirrorModule(Context context) {
            mContext = context;
        }

        @Provides
        public Application provideApplication() {
            return (Application) mContext.getApplicationContext();
        }
    }

    public static ObjectGraph getObjectGraph(Context context) {
        ObjectGraph graph = ObjectGraph.create(new MirrorModule(context));
        return graph;
    }
}
