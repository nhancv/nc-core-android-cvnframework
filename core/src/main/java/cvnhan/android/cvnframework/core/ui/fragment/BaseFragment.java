package cvnhan.android.cvnframework.core.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectDependencies();
        injectViews(view);
        onInjected();
    }

    protected void onInjected() {

    }

    protected void injectDependencies() {
    }

    protected void injectViews(View view) {
        ButterKnife.inject(this, view);
    }

}
