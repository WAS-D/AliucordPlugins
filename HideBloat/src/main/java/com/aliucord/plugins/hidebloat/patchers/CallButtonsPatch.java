package com.aliucord.plugins.hidebloat.patchers;

import com.aliucord.plugins.hidebloat.util.Const;
import com.aliucord.plugins.hidebloat.patchers.base.BasePatcher;
import com.aliucord.plugins.hidebloat.util.Util;
import com.discord.databinding.WidgetUserSheetBinding;
import com.discord.widgets.user.usersheet.WidgetUserSheet;
import com.discord.widgets.user.usersheet.WidgetUserSheetViewModel;

import java.lang.reflect.Method;

import top.canyie.pine.Pine;

public class CallButtonsPatch extends BasePatcher {

    public CallButtonsPatch() throws Exception{
        super(Const.Key.CALL_BUTTONS_KEY, Const.ViewName.CALL_BUTTONS_NAME, WidgetUserSheet.class.getDeclaredMethod("configureProfileActionButtons", WidgetUserSheetViewModel.ViewState.Loaded.class));
    }

    @Override
    public void patchBody(Pine.CallFrame callFrame) {
        WidgetUserSheet _this = (WidgetUserSheet) callFrame.thisObject;

        try {
            Method _binding = _this.getClass().getDeclaredMethod("getBinding");
            _binding.setAccessible(true);
            WidgetUserSheetBinding binding = (WidgetUserSheetBinding) _binding.invoke(_this);

            if (binding == null) return;

            Util.hideViewCompletely(binding.i);
            Util.hideViewCompletely(binding.J);
        } catch (Throwable ignored) {}

        callFrame.setResult(callFrame.getResult());
    }
}