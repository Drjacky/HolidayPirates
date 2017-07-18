package ir.hosseinabbasi.holidaypirates.ui.main;

import java.util.List;

import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.CommentsUserCombined;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.ui.base.MvpView;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

public interface MainMvpView extends MvpView {
    void refreshPostsList(List<Posts> postList);
    void openDetailActivityWithData(List<Object> combinedList);
}
