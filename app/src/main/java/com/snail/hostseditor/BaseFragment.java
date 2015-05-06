package com.snail.hostseditor;

import android.view.View;

import com.hannesdorfmann.mosby.dagger1.viewstate.lce.Dagger1MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * All fragments should extend this for dependency injection.
 */
public abstract class BaseFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends Dagger1MvpLceViewStateFragment<CV, M, V, P> {

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    protected void injectDependencies() {
        getObjectGraph().inject(this);
    }
}
