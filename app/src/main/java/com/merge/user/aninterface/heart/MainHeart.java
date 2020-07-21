package com.merge.user.aninterface.heart;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;
import com.merge.user.aninterface.FaceArFragment;
import com.merge.user.aninterface.R;
import com.merge.user.aninterface.mask.MainMask;

public class MainHeart extends AppCompatActivity {

    private static final String TAG = MainHeart.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private FaceArFragment arFragment;

    //declare button
    private Button alchemistbtn;
    private Button semirimlessbtn;
    private Button victorrectangularbtn;
    private Button wayfarerbtn;
    private Button cateyesunglassesbtn;
    private Button back;
    //---------------------

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.heart_face_ar);
        alchemistbtn = (Button)findViewById(R.id.alchemist_ovalbutton);
        semirimlessbtn = (Button)findViewById(R.id.semi_rimlessbutton);
        victorrectangularbtn = (Button)findViewById(R.id.victor_rectangularbutton);
        wayfarerbtn = (Button)findViewById(R.id.wayfarerbutton);
        cateyesunglassesbtn = (Button)findViewById(R.id.cateye_sunglassesbutton);
        back = (Button)findViewById(R.id.back);
        arFragment = (FaceArFragment)getSupportFragmentManager().findFragmentById(R.id.face_fragment);

        //assign listener to button
        alchemistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAlchemist();
            }
        });

        semirimlessbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSemirimless();
            }
        });

        victorrectangularbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToVictorrectangular();
            }
        });

        wayfarerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWayfarer();
            }
        });

        cateyesunglassesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCateyesunglasses();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBack();
            }
        });

    }

    public void goToAlchemist() {
        Intent intent = new Intent(this, AlchemistHeart.class);
        String message = "alchemist glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToSemirimless() {
        Intent intent = new Intent(this, SemiRimless.class);
        String message = "semirimless glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToVictorrectangular() {
        Intent intent = new Intent(this, VictorRectangular.class);
        String message = "victor rectangular glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToWayfarer() {
        Intent intent = new Intent(this, WayfarerHeart.class);
        String message = "wayfarer glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToCateyesunglasses() {
        Intent intent = new Intent(this, CatEyeSunglasses.class);
        String message = "cateye sunglasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToBack() {
        Intent intent = new Intent(this, MainMask.class);
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
