package il.co.moveo.repostiroeyandnetworkbound.view.interfaceView;

import com.moveosoftware.infrastructure.mvp.view.BaseView;

public interface PostView extends BaseView{
    void finishActivity();

    String getPostId();

    void notifyTheUser();

    void updatePostDataIfNeeded(String title, String description);
}
