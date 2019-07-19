package com.example.geennews;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jy.base.BaseAcctivity;
import com.jy.beans.MyLikeBeans;
import com.jy.beans.ZhiHuDetailBean;
import com.jy.dao.DBUtils;
import com.jy.utils.HtmlUtil;
import com.tencent.smtt.sdk.WebView;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhiHuDetailsActivity extends BaseAcctivity {


    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.view_main)
    WebView viewMain;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView tvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;
    @BindView(R.id.fab_like)
    FloatingActionButton fabLike;
    private int newsId;
    ZhiHuDetailBean value1;
    @Override
    protected void initView() {
        newsId = getIntent().getIntExtra("id", -1);

        MyLikeBeans myLikeBeans = new MyLikeBeans();
        myLikeBeans.setId(newsId);
        MyLikeBeans myLikeBeans1 = DBUtils.queryOne(myLikeBeans);
        if (myLikeBeans1!=null){
            fabLike.setSelected(true);
        }else{
            fabLike.setSelected(false);
        }
        initData();
    }

    private static final String TAG = "ZhiHuDetailsActivity";

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApis.zhihuUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ZhihuApis zhihuApis = retrofit.create(ZhihuApis.class);
        zhihuApis.getZhihuDetails(newsId + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(ZhiHuDetailBean value) {
                        value1=value;
                        Glide.with(ZhiHuDetailsActivity.this)
                                .load(value.getImage())
                                .into(detailBarImage);
                        clpToolbar.setTitle(value.getTitle());
                        detailBarCopyright.setText(value.getImage_source());
                        String htmlData = HtmlUtil.createHtmlData(value.getBody(), value.getCss(), value.getJs());
                        viewMain.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void initMvp() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zhi_hu_details;
    }



    @OnClick(R.id.fab_like)
    public void onClick() {
        MyLikeBeans myLikeBeans = new MyLikeBeans();
        myLikeBeans.setType(1);
        myLikeBeans.setId(newsId);
        myLikeBeans.setImage(value1.getImage());
        myLikeBeans.setTitle(value1.getTitle());
        DBUtils.insert(myLikeBeans);

    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ButterKnife.bind(this);
//    }

}
