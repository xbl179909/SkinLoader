package com.xbl.skinlib.entity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xbl.skinlib.loader.SkinManager;

public class TextColorAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if(view instanceof TextView){
            TextView tv = (TextView)view;
            if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
                Log.e("attr1", "TextColorAttr");
                tv.setTextColor(SkinManager.getInstance().convertToColorStateList(attrValueRefId));
            }
        }
    }
}