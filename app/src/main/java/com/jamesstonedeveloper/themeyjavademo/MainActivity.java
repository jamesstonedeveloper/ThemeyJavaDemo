package com.jamesstonedeveloper.themeyjavademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.jamesstonedeveloper.themeyjava.Themey;

import static com.jamesstonedeveloper.themeyjava.Themey.CircleAnimation.INWARD;
import static com.jamesstonedeveloper.themeyjava.Themey.CircleAnimation.OUTWARD;

public class MainActivity extends AppCompatActivity {

    private Button btnDay;
    private Button btnNight;
    private Button btnToggle;
    private ImageView iGreen, iRed, iBlue;
    private ConstraintLayout mainLayout;
    private int circleAnimation = INWARD;
    private Switch switchInwards, switchOutwards, switchNone;
    private SeekBar seekBar;
    private TextView tvAnimationSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Themey.getInstance().delayedInit(this, true);

        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        Themey.getInstance().setRootLayout(mainLayout);
        Themey.getInstance().setDefaultTheme(R.style.AppTheme);

        btnDay = findViewById(R.id.btnDay);
        btnNight = findViewById(R.id.btnNight);
        btnToggle = findViewById(R.id.btnToggle);
        iGreen = findViewById(R.id.iGreen);
        iRed = findViewById(R.id.iRed);
        iBlue = findViewById(R.id.iBlue);
        switchInwards = findViewById(R.id.switchInwards);
        switchOutwards = findViewById(R.id.switchOutwards);
        switchNone = findViewById(R.id.switchNone);
        seekBar = findViewById(R.id.seekBar);
        tvAnimationSpeed = findViewById(R.id.tvAnimationSpeed);

        setOnClickListeners();
    }

    private void setOnClickListeners() {

        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themey.getInstance().changeTheme(AppCompatDelegate.MODE_NIGHT_NO, circleAnimation);
            }
        });

        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themey.getInstance().changeTheme(AppCompatDelegate.MODE_NIGHT_YES, circleAnimation);
            }
        });

        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themey.getInstance().toggleDayNight(circleAnimation);
            }
        });

        iGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themey.getInstance().changeTheme(R.style.GreenTheme, circleAnimation, (int)(iGreen.getX() + (iGreen.getWidth() / 2)), mainLayout.getHeight() - (iGreen.getHeight() / 2));
            }
        });

        iRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themey.getInstance().changeTheme(R.style.RedTheme, circleAnimation, (int)(iRed.getX() + (iRed.getWidth() / 2)), mainLayout.getHeight() - (iRed.getHeight() / 2));
            }
        });

        iBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themey.getInstance().changeTheme(R.style.BlueTheme, circleAnimation, (int)(iBlue.getX() + (iBlue.getWidth() / 2)), mainLayout.getHeight() - (iBlue.getHeight() / 2));
            }
        });

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setEnabled(!isChecked);
                if (isChecked) {
                    switch (buttonView.getId()) {
                        case R.id.switchInwards:
                            circleAnimation = INWARD;
                            switchNone.setChecked(false);
                            switchOutwards.setChecked(false);
                            break;
                        case R.id.switchOutwards:
                            circleAnimation = OUTWARD;
                            switchInwards.setChecked(false);
                            switchNone.setChecked(false);
                            break;
                        case R.id.switchNone:
                            circleAnimation = Themey.CircleAnimation.NONE;
                            switchInwards.setChecked(false);
                            switchOutwards.setChecked(false);
                    }
                }
            }
        };
        switchNone.setOnCheckedChangeListener(onCheckedChangeListener);
        switchOutwards.setOnCheckedChangeListener(onCheckedChangeListener);
        switchInwards.setOnCheckedChangeListener(onCheckedChangeListener);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Themey.getInstance().setAnimationDuration(progress);
                tvAnimationSpeed.setText(String.format("Animation Speed: %d", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
