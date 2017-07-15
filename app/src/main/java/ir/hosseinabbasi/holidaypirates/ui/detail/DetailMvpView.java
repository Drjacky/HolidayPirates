package ir.hosseinabbasi.holidaypirates.ui.detail;

import java.util.List;

import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.ui.base.MvpView;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

public interface DetailMvpView extends MvpView {
    void refreshCommentsList();
}
