package com.xbl.skinloader;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.xbl.skinlib.base.BaseActivity;
import com.xbl.skinlib.entity.AttrFactory;
import com.xbl.skinlib.entity.DynamicAttr;
import com.xbl.skinlib.listener.ILoaderListener;
import com.xbl.skinlib.loader.SkinManager;
import com.xbl.skinlib.util.L;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends BaseActivity {

    /**
     * Put this skin file on the root of sdcard
     * eg:
     * /mnt/sdcard/BlackFantacy.skin
     */
    private String SKIN_NAME = "BlackFantacy.skin";
    private String SKIN_DIR;


    private TextView titleText;
    private Button setOfficalSkinBtn;
    private Button setNightSkinBtn;
    private RelativeLayout mBottom;

    private boolean isOfficalSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        SKIN_DIR = mContext.getExternalFilesDir(null) + File.separator + SKIN_NAME;
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        initView();
    }

    private void initView() {
        titleText = (TextView) findViewById(R.id.title_text);
        titleText.setText("设置皮肤");
        setOfficalSkinBtn = (Button) findViewById(R.id.set_default_skin);
        setNightSkinBtn = (Button) findViewById(R.id.set_night_skin);
        mBottom = (RelativeLayout) findViewById(R.id.bottom_layout);

        isOfficalSelected = !SkinManager.getInstance().isExternalSkin();

        if(isOfficalSelected){
            setOfficalSkinBtn.setText("官方默认(当前)");
            setNightSkinBtn.setText("黑色幻想");
        }else{
            setNightSkinBtn.setText("黑色幻想(当前)");
            setOfficalSkinBtn.setText("官方默认");
        }

        setNightSkinBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onSkinSetClick();
            }
        });

        setOfficalSkinBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onSkinResetClick();
            }
        });
        addView();
    }

    private void addView() {
        TextView textView = new TextView(mContext);
        textView.setText("Small Article (动态new)");
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setLayoutParams(param);
        textView.setTextColor(mContext.getResources().getColor(R.color.color_sel_skin_btn_text));
        textView.setTextSize(20);
        mBottom.addView(textView);

        List<DynamicAttr> mDynamicAttr = new ArrayList<DynamicAttr>();
        mDynamicAttr.add(new DynamicAttr(AttrFactory.TEXT_COLOR, R.color.color_sel_skin_btn_text));
        dynamicAddView(textView, mDynamicAttr);
    }

    protected void onSkinResetClick() {
        if(!isOfficalSelected){
            SkinManager.getInstance().restoreDefaultTheme();
            Toast.makeText(getApplicationContext(), "切换成功", Toast.LENGTH_SHORT).show();
            setOfficalSkinBtn.setText("官方默认(当前)");
            setNightSkinBtn.setText("黑色幻想");
            isOfficalSelected = true;
        }
    }

    private void onSkinSetClick() {
        if(!isOfficalSelected) return;

        File skin = new File(SKIN_DIR);

        if(skin == null || !skin.exists()){
            Toast.makeText(getApplicationContext(), "请检查" + SKIN_DIR + "是否存在", Toast.LENGTH_SHORT).show();
            return;
        }

        SkinManager.getInstance().load(skin.getAbsolutePath(),
                new ILoaderListener() {
                    @Override
                    public void onStart() {
                        L.e("startloadSkin");
                    }

                    @Override
                    public void onSuccess() {
                        L.e("loadSkinSuccess");
                        Toast.makeText(getApplicationContext(), "切换成功", Toast.LENGTH_SHORT).show();
                        setNightSkinBtn.setText("黑色幻想(当前)");
                        setOfficalSkinBtn.setText("官方默认");
                        isOfficalSelected = false;
                    }

                    @Override
                    public void onFailed() {
                        L.e("loadSkinFail");
                        Toast.makeText(getApplicationContext(), "切换失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}