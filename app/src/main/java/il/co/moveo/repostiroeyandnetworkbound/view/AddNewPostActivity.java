package il.co.moveo.repostiroeyandnetworkbound.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moveosoftware.infrastructure.mvp.view.BaseActivity;

import il.co.moveo.repostiroeyandnetworkbound.R;
import il.co.moveo.repostiroeyandnetworkbound.model.model.Post;
import il.co.moveo.repostiroeyandnetworkbound.presenter.PostPresenter;
import il.co.moveo.repostiroeyandnetworkbound.view.interfaceView.PostView;

public class AddNewPostActivity extends BaseActivity implements View.OnClickListener,PostView {
    private EditText mTitle;
    private EditText mDescription;
    private Button mButtonPublish;
    PostPresenter presenter;
    String mPostId="";
   // static final String POST_ID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);
        initViews();
    }

    public void initViews() {
        mTitle=findViewById(R.id.et_title);
        mDescription=findViewById(R.id.et_description);
        mButtonPublish=findViewById(R.id.btn_publish);
        mButtonPublish.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_publish){
            presenter.publishPost(mTitle.getText().toString(),
                    mDescription.getText().toString(),mPostId);
        }
    }

    @Override
    protected PostPresenter getPresenter() {
        presenter= new PostPresenter(this);
        return presenter;
    }

    @Override
    protected void bind() {
        presenter.bind();
    }

    @Override
    protected void unbind() {
        presenter.unbind();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPostId=presenter.getPostId();
        if(!mPostId.equals("")) {
            presenter.updatePostDataIfNeeded(mPostId);
            mButtonPublish.setText(R.string.update);
        }
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bind();
    }

    @Override
    public String getPostId() {
        if(getIntent().getStringExtra(Post.ID)==null)
            return "";
        else
         return getIntent().getStringExtra(Post.ID);
    }

    @Override
    public void notifyTheUser() {
        Toast.makeText(this,"Required fields",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updatePostDataIfNeeded(String title, String description) {
        mTitle.setText(title);
        mDescription.setText(description);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
