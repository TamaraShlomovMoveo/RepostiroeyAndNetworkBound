package il.co.moveo.repostiroeyandnetworkbound.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moveosoftware.infrastructure.view.BaseRecyclerAdapter;
import com.moveosoftware.infrastructure.view.BaseViewHolder;

import il.co.moveo.repostiroeyandnetworkbound.R;
import il.co.moveo.repostiroeyandnetworkbound.model.model.Post;

public class PostAdapter extends BaseRecyclerAdapter<PostAdapter.PostViewHolder, Post> {
    private ClickListener mClickListener;

    public void setClickListener(PostAdapter.ClickListener clickListener) {
        this.mClickListener = clickListener;
        Log.e("click", "c");
    }

    @Override
    protected Class<Post> getDataType() {
        return Post.class;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(itemView);
    }


    class PostViewHolder extends BaseViewHolder<Post> implements View.OnClickListener {
        TextView mTitle, mDescription;

        PostViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title);
            mDescription = itemView.findViewById(R.id.tv_description);
            itemView.setOnClickListener(this);
        }
        @Override
        protected void bind(Post post, int position) {
            mTitle.setText(post.getTitle());
            mDescription.setText(post.getDescription());
        }

        @Override
        public void onClick(View v) {
            mClickListener.itemClicked(v, getAdapterPosition());
        }
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }
}
