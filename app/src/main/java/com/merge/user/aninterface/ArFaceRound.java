package com.merge.user.aninterface;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.AugmentedFace;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.AugmentedFaceNode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This is an example activity that uses the Sceneform UX package to make common Augmented Faces
 * tasks easier.
 */
public class ArFaceRound extends AppCompatActivity {
    private static final String TAG = ArFaceRound.class.getSimpleName();

    private static final double MIN_OPENGL_VERSION = 3.0;

    private FaceArFragment arFragment;

    private ModelRenderable faceRegionsRenderable;
    private Texture faceMeshTexture;

    private Button gramercybtn;
    private Button wayfarerbtn;
    private Button marcybtn;
    private Button billiebtn;
    private Button carlsunglassesbtn;


    private final HashMap<AugmentedFace, AugmentedFaceNode> faceNodeMap = new HashMap<>();

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    // CompletableFuture requires api level 24
    // FutureReturnValueIgnored is not valid
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.round_face_ar);
        gramercybtn = (Button) findViewById(R.id.gramercy2button);
        wayfarerbtn = (Button) findViewById(R.id.wayfarerbutton);
        marcybtn = (Button) findViewById(R.id.marcybutton);
        billiebtn = (Button) findViewById(R.id.billiebutton);
        carlsunglassesbtn = (Button) findViewById(R.id.carlsunglassesbutton);
        arFragment = (FaceArFragment) getSupportFragmentManager().findFragmentById(R.id.face_fragment);

        ArSceneView sceneView = arFragment.getArSceneView();

        // This is important to make sure that the camera stream renders first so that
        // the face mesh occlusion works correctly.
        sceneView.setCameraStreamRenderPriority(Renderable.RENDER_PRIORITY_FIRST);


        Scene scene = sceneView.getScene();

        /*-------------------------------------------------
            This section is for button to overlay effects
          --------------------------------------------------*/

        //gramercy
        gramercybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gramercy();
            }
        });

        //wayfarer
        wayfarerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wayfarer();
            }
        });

        //marcy
        marcybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marc();
            }
        });

        //bille
        billiebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billie();
            }
        });

        //carl
        carlsunglassesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carlsunglasses();
            }
        });

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
                            faceNode.setFaceMeshTexture(faceMeshTexture);
                            faceNodeMap.put(face, faceNode);
                        }
                    }

                });

        // Remove any AugmentedFaceNodes associated with an AugmentedFace that stopped tracking.
        Iterator<Map.Entry<AugmentedFace, AugmentedFaceNode>> iter =
                faceNodeMap.entrySet().iterator();
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

    /*-------------------------------------------------
     This section is method for overlay 3d
     --------------------------------------------------*/
    public void gramercy() {
        // Load the face regions renderable.
        // This is a skinned model that renders 3D objects mapped to the regions of the augmented face.

        ModelRenderable.builder()
                .setSource(this, R.raw.gramercy2)
                .build()
                .thenAccept(
                        modelRenderable -> {
                            faceRegionsRenderable = modelRenderable;
                            modelRenderable.setShadowCaster(false);
                            modelRenderable.setShadowReceiver(false);
                        });

    }

    public void wayfarer() {
        // Load the face regions renderable.
        // This is a skinned model that renders 3D objects mapped to the regions of the augmented face.

        ModelRenderable.builder()
                .setSource(this, R.raw.new_wayfarer4)
                .build()
                .thenAccept(
                        modelRenderable -> {
                            faceRegionsRenderable = modelRenderable;
                            modelRenderable.setShadowCaster(false);
                            modelRenderable.setShadowReceiver(false);
                        });

    }

    public void marc() {
        ModelRenderable.builder()
                .setSource(this, R.raw.marc)
                .build()
                .thenAccept(
                        modelRenderable -> {
                            faceRegionsRenderable = modelRenderable;
                            modelRenderable.setShadowCaster(false);
                            modelRenderable.setShadowReceiver(false);
                        });

    }

    public void billie() {
        // Load the face regions renderable.
        // This is a skinned model that renders 3D objects mapped to the regions of the augmented face.

        ModelRenderable.builder()
                .setSource(this, R.raw.billie)
                .build()
                .thenAccept(
                        modelRenderable -> {
                            faceRegionsRenderable = modelRenderable;
                            modelRenderable.setShadowCaster(false);
                            modelRenderable.setShadowReceiver(false);
                        });

    }

    public void carlsunglasses() {
        // Load the face regions renderable.
        // This is a skinned model that renders 3D objects mapped to the regions of the augmented face.

        ModelRenderable.builder()
                .setSource(this, R.raw.carl_sunglasses)
                .build()
                .thenAccept(
                        modelRenderable -> {
                            faceRegionsRenderable = modelRenderable;
                            modelRenderable.setShadowCaster(false);
                            modelRenderable.setShadowReceiver(false);
                        });

    }


    /**
     * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
     * on this device.
     *
     * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
     *
     * <p>Finishes the activity if Sceneform can not run
     */
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
