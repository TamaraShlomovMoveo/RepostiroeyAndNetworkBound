package il.co.moveo.repostiroeyandnetworkbound.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.moveosoftware.infrastructure.mvp.presenter.Presenter;
import com.moveosoftware.infrastructure.mvp.view.BaseActivity;

import il.co.moveo.repostiroeyandnetworkbound.R;
import il.co.moveo.repostiroeyandnetworkbound.model.model.Post;
import il.co.moveo.repostiroeyandnetworkbound.presenter.FeedPresenter;
import il.co.moveo.repostiroeyandnetworkbound.view.interfaceView.FeedView;
import io.realm.RealmResults;

public class FeedActivity extends BaseActivity implements FeedView, PostAdapter.ClickListener {
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private FeedPresenter feedPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        FloatingActionButton fab =findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(getBaseContext(),AddNewPostActivity.class)));
        mRecyclerView=findViewById(R.id.recycler_view);
        mAdapter=new PostAdapter();
        mAdapter.setClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        feedPresenter.allPosts(this);
    }

    @Override
    protected FeedPresenter getPresenter() {
        feedPresenter= new FeedPresenter(this);
        return feedPresenter;
    }

    @Override
    protected void bind() {
        feedPresenter.bind();
    }

    @Override
    protected void unbind() {
        feedPresenter.unbind();
    }

    @Override
    public void OnLoadingFeed() {
        Toast.makeText(this, "Loading feeds", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshData(RealmResults<Post> list) {
       // mAdapter.setData(null);
        mAdapter.update(list);
        mRecyclerView.invalidate();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent=new Intent(this,AddNewPostActivity.class);
        String postId=mAdapter.getData().get(position).getId();
        intent.putExtra(Post.ID,postId);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
