package com.xbl.skinlib.entity;

import android.view.View;

import com.xbl.skinlib.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class SkinItem {

    public View view;

    public List<SkinAttr> attrs;

    public SkinItem(){
        attrs = new ArrayList<SkinAttr>();
    }

    public void apply(){
        if(ListUtils.isEmpty(attrs)){
            return;
        }
        for(SkinAttr at : attrs){
            at.apply(view);
        }
    }

    public void clean(){
        if(ListUtils.isEmpty(attrs)){
            return;
        }
        for(SkinAttr at : attrs){
            at = null;
        }
    }

    @Override
    public String toString() {
        return "SkinItem [view=" + view.getClass().getSimpleName() + ", attrs=" + attrs + "]";
    }
}