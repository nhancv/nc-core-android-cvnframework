package cvnhan.android.cvnframework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cvnhan.android.cvnframework.R;
import cvnhan.android.cvnframework.activity.UsersActivity;
import cvnhan.android.cvnframework.adapter.UsersAdapter;
import cvnhan.android.cvnframework.core.ui.fragment.NetworkFragment;
import cvnhan.android.cvnframework.core.ui.widget.NoNetworkView;
import cvnhan.android.mvp.model.ModelManager;
import cvnhan.android.mvp.model.User;
import cvnhan.android.mvp.presenter.UsersPresenter;
import cvnhan.android.mvp.view.UsersView;

public class UsersFragment extends NetworkFragment implements UsersView {

    @InjectView(R.id.vNoNetwork)
    NoNetworkView vNoNetwork;
    @InjectView(R.id.vProgressBar)
    ProgressBar vProgressBar;
    @InjectView(R.id.rvUsers)
    RecyclerView rvUsers;

    @Inject
    UsersAdapter adapter;

    @Inject
    UsersPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        ((UsersActivity) getActivity()).component().inject(this);
    }

    @Override
    protected void onInjected() {
        super.onInjected();
        setupLoading(rvUsers, vProgressBar);
        rvUsers.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvUsers.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onStart(this, ModelManager.getGitUserModel());
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onStop();
    }

    @Override
    protected View getMainView() {
        return rvUsers;
    }

    @Override
    protected NoNetworkView getNoNetworkView() {
        return vNoNetwork;
    }

    @Override
    protected boolean isNetworkRequired() {
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showLoading(boolean show) {
        setShowLoading(show);
    }

    @Override
    public void render(List<User> users) {
        adapter.changeDataSet(users);
    }
}
