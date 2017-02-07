package com.mig35.homeservice.ui.main.presenter;

import android.support.annotation.NonNull;

import com.mig35.homeservice.business.control.IControlInteractor;
import com.mig35.homeservice.ui.common.base.RxBasePresenter;
import com.mig35.homeservice.ui.common.model.UserCommand;
import com.mig35.homeservice.ui.main.model.ControlScreenModel;
import com.mig35.homeservice.ui.main.view.IControlView;
import com.mig35.homeservice.utils.general.LiFoFixedSizeQueue;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

import java.util.List;

public final class ControlPresenter extends RxBasePresenter<IControlView, ControlScreenModel> implements IControlPresenter {

    @NonNull
    private final IControlInteractor mControlInteractor;
    @NonNull
    private final LiFoFixedSizeQueue<UndoCommand> mUndoCommands;

    public ControlPresenter(@NonNull final List<IControlElementPresenter> elementPresenters, @NonNull final IControlInteractor controlInteractor, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        super(new ControlScreenModel(elementPresenters), rxSchedulersAbs);

        mControlInteractor = controlInteractor;
        mUndoCommands = new LiFoFixedSizeQueue<>(10);

        executeViewCommandSingle(view -> view.updateScreenModel(mModel));
    }

    @Override
    public void addUserCommand(@NonNull final IControlElementPresenter controlElementPresenter, @NonNull final UserCommand userCommand) {
        final UserCommand undoUserCommand = userCommand.undoCommand();
        if (null != undoUserCommand) {
            mUndoCommands.push(new UndoCommand(controlElementPresenter, undoUserCommand));
        }
    }

    @Override
    public void undoUserCommand() {
        if (!mUndoCommands.isEmpty()) {
            final UndoCommand undoCommand = mUndoCommands.pop();
            undoCommand.execute();
        }
    }

    private static final class UndoCommand {

        @NonNull
        private final IControlElementPresenter mControlElementPresenter;
        @NonNull
        private final UserCommand mUserCommand;

        UndoCommand(@NonNull final IControlElementPresenter controlElementPresenter, @NonNull final UserCommand userCommand) {
            mControlElementPresenter = controlElementPresenter;
            mUserCommand = userCommand;
        }

        public void execute() {
            mControlElementPresenter.sendUserCommand(mUserCommand);
        }
    }
}