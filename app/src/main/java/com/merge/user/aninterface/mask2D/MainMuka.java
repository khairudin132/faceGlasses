package com.merge.user.aninterface.mask2D;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;
import com.merge.user.aninterface.ActivityAR;
import com.merge.user.aninterface.FaceArFragment;
import com.merge.user.aninterface.R;

public class MainMuka extends AppCompatActivity {

    private static final String TAG = MainMuka.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private FaceArFragment arFragment;

    //declare button
    private Button roundMask;
    private Button heartMask;
    private Button squareMask;
    private Button ovalMask;
    private Button back;
    //---------------------

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.activity_main_muka);
        roundMask = (Button)findViewById(R.id.roundMask);
        heartMask = (Button)findViewById(R.id.heartMask);
        squareMask = (Button)findViewById(R.id.squareMask);
        ovalMask = (Button)findViewById(R.id.ovalMask);
        back = (Button)findViewById(R.id.back);
        arFragment = (FaceArFragment)getSupportFragmentManager().findFragmentById(R.id.face_fragment);

        //assign listener to button
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

        ovalMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOvalMask();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBack();
            }
        });

    }

    public void goToRoundMask() {
        Intent intent = new Intent(this, BulatMuka.class);
        String message = "alchemist glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToHeartMask() {
        Intent intent = new Intent(this, HatiMuka.class);
        String message = "browline glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToSquareMask() {
        Intent intent = new Intent(this, PetakMuka.class);
        String message = "czteye glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToOvalMask() {
        Intent intent = new Intent(this, BujurMuka.class);
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
