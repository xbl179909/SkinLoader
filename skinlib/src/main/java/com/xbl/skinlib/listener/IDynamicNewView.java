package com.xbl.skinlib.listener;

import android.view.View;

import com.xbl.skinlib.entity.DynamicAttr;

import java.util.List;

public interface IDynamicNewView {
    void dynamicAddView(View view, List<DynamicAttr> pDAttrs);
}