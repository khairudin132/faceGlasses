package com.merge.user.aninterface.mask;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.AugmentedFace;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.AugmentedFaceNode;
import com.merge.user.aninterface.ActivityAR;
import com.merge.user.aninterface.FaceArFragment;
import com.merge.user.aninterface.R;
import com.merge.user.aninterface.heart.MainHeart;
import com.merge.user.aninterface.oval.MainOval;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OvalMask extends AppCompatActivity {

    private static final String TAG = OvalMask.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private FaceArFragment arFragment;
    private ModelRenderable faceRegionsRenderable;

    //declare button
    private Button roundMask;
    private Button heartMask;
    private Button squareMask;
    private Button ovalMask;
    private Button select;
    private Button back;
    //---------------------

    private final HashMap<AugmentedFace, AugmentedFaceNode> faceNodeMap = new HashMap<>();

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.mask);
        roundMask = (Button)findViewById(R.id.roundMask);
        heartMask = (Button)findViewById(R.id.heartMask);
        squareMask = (Button)findViewById(R.id.squareMask);
        ovalMask = (Button)findViewById(R.id.ovalMask);
        select = (Button)findViewById(R.id.select);
        back = (Button)findViewById(R.id.back);
        arFragment = (FaceArFragment)getSupportFragmentManager().findFragmentById(R.id.face_fragment);
        ArSceneView sceneView = arFragment.getArSceneView();
        sceneView.setCameraStreamRenderPriority(Renderable.RENDER_PRIORITY_FIRST);
        Scene scene = sceneView.getScene();

        scene.addOnUpdateListener(
                (FrameTime frameTime) -> {
                    if (faceRegionsRenderable == null) {
                        return;
                    }

                    Collection<AugmentedFace> faceList =
                            sceneView.getSession().getAllTrackables(AugmentedFace.class);

                    // Make new AugmentedFaceNodes for any new faces.
                    for (AugmentedFace face : faceList) {
                        if (!faceNodeMap.containsKey(face)) {
                            AugmentedFaceNode faceNode = new AugmentedFaceNode(face);
                            faceNode.setParent(scene);
                            faceNode.setFaceRegionsRenderable(faceRegionsRenderable);
                            faceNodeMap.put(face, faceNode);
                        }
                    }
                });


        ModelRenderable.builder()
                .setSource(this, R.raw.muka_bujur1)
                .build()
                .thenAccept(
                        modelRenderable -> {
                            faceRegionsRenderable = modelRenderable;
                            modelRenderable.setShadowCaster(false);
                            modelRenderable.setShadowReceiver(false);
                        });

        ovalMask.setEnabled(false);

        roundMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRoundMask();
            }
        });

        heartMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHeartMask();
            }
        });

        squareMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSquareMask();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGlasses();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBack();
            }
        });

        Iterator<Map.Entry<AugmentedFace, AugmentedFaceNode>> iter = faceNodeMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<AugmentedFace, AugmentedFaceNode> entry = iter.next();
            AugmentedFace face = entry.getKey();
            if (face.getTrackingState() == TrackingState.STOPPED) {
                AugmentedFaceNode faceNode = entry.getValue();
                faceNode.setParent(null);
                iter.remove();
            }
        }

    }


    public void goToRoundMask() {
        Intent intent = new Intent(this, RoundMask.class);
        String message = "browline glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToHeartMask() {
        Intent intent = new Intent(this, HeartMask.class);
        String message = "czteye glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToSquareMask() {
        Intent intent = new Intent(this, SquareMask.class);
        String message = "roundframe glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToGlasses() {
        Intent intent = new Intent(this, MainOval.class);
        String message = "roundframe glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToBack() {
        Intent intent = new Intent(this, ActivityAR.class);
        String message = "roundframe glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (ArCoreApk.getInstance().checkAvailability(activity)
                == ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE) {
            Log.e(TAG, "Augmented Faces requires ArCore.");
            Toast.makeText(activity, "Augmented Faces requires ArCore", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }

}
