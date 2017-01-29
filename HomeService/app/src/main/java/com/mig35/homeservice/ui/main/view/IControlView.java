package com.mig35.homeservice.ui.main.view;

import com.mig35.homeservice.ui.common.view.base.IBaseUserView;
import com.mig35.homeservice.ui.main.model.ControlScreenModel;

public interface IControlView extends IBaseUserView {

    void updateScreenModel(final ControlScreenModel controlScreenModel);
}