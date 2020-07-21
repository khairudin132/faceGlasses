package com.merge.user.aninterface.square;

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

public class MainSquare extends AppCompatActivity {

    private static final String TAG = MainSquare.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private FaceArFragment arFragment;

    //declare button
    private Button alchemistbtn;
    private Button browlinebtn;
    private Button cateyebtn;
    private Button roundframebtn;
    private Button roundsunglassesbtn;
    private Button back;
    //---------------------

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.square_face_ar);
        alchemistbtn = (Button)findViewById(R.id.alchemist_ovalbutton);
        browlinebtn = (Button)findViewById(R.id.browlinebutton);
        cateyebtn = (Button)findViewById(R.id.cateyebutton);
        roundframebtn = (Button)findViewById(R.id.roundframebutton);
        roundsunglassesbtn = (Button)findViewById(R.id.round_sunglassesbutton);
        back = (Button)findViewById(R.id.back);
        arFragment = (FaceArFragment)getSupportFragmentManager().findFragmentById(R.id.face_fragment);

        //assign listener to button
        alchemistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAlchemist();
            }
        });

        browlinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBrowline();
            }
        });

        cateyebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCateye();
            }
        });

        roundframebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRoundframe();
            }
        });

        roundsunglassesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRoundsunglasses();
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
        Intent intent = new Intent(this, Alchemist.class);
        String message = "alchemist glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToBrowline() {
        Intent intent = new Intent(this, Browline.class);
        String message = "browline glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToCateye() {
        Intent intent = new Intent(this, CatEye.class);
        String message = "czteye glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToRoundframe() {
        Intent intent = new Intent(this, RoundFrame.class);
        String message = "roundframe glasses";
        intent.putExtra(intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void goToRoundsunglasses() {
        Intent intent = new Intent(this, RoundSunglasses.class);
        String message = "round sunglasses";
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
