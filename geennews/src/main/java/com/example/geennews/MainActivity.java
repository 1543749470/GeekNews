package com.example.geennews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.navigation.NavigationView;
import com.jy.base.BaseAcctivity;
import com.jy.fragments.AboutFragment;
import com.jy.fragments.GankMainFragment;
import com.jy.fragments.GoldMainFragment;
import com.jy.fragments.MyLikeFragment;
import com.jy.fragments.WeChatFragment;
import com.jy.fragments.ZhiHuMainFragment;
import com.jy.utils.Constants;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAcctivity {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_search)
    MaterialSearchView viewSearch;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    @BindView(R.id.fl_main_content)
    FrameLayout flMainContent;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private static final String TAG = "MainActivity";
    private FragmentTransaction fragmentTransaction;
    private ZhiHuMainFragment zhiHuMainFragment;
    private WeChatFragment weChatFragment;
    private GankMainFragment gankMainFragment;
    private GoldMainFragment goldMainFragment;
    private AboutFragment aboutFragment;
    private MenuItem lastltem;
    private MenuItem mSearchMenuItem;
    private MyLikeFragment myLikeFragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        viewSearch.setMenuItem(item);
        mSearchMenuItem =item;
        return true;
    }

    @Override
    protected void initView() {
        toolBar.setTitle(R.string.app_name);
        setSupportActionBar(toolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        viewSearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, "onQueryTextSubmit: "+query );
                //根据不同action来区分
                Intent intent = new Intent();
                intent.putExtra("data",query);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);


                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragmentTransaction!=null){
            zhiHuMainFragment = new ZhiHuMainFragment();
            weChatFragment = new WeChatFragment();
            gankMainFragment = new GankMainFragment();
            goldMainFragment = new GoldMainFragment();
            myLikeFragment = new MyLikeFragment();
            aboutFragment = new AboutFragment();
            currFragment=zhiHuMainFragment;
            fragmentTransaction.add(R.id.fl_main_content,zhiHuMainFragment);
            fragmentTransaction.show(zhiHuMainFragment);
            fragmentTransaction.commit();
        }
        lastltem = navigation.getMenu().findItem(R.id.drawer_zhihu);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.drawer_zhihu:
                        toolBar.setTitle("知乎日报");
                        showType = Constants.TYPE_ZHIHU;
                        // 获取要切换的fragment
                        Fragment targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(false);
                        break;

                    case R.id.drawer_wechat:
                        toolBar.setTitle("微信精选");

                        showType = Constants.TYPE_WECHAT;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(false);

                        break;

                    case R.id.drawer_gank:
                        toolBar.setTitle("干货集中营");

                        showType = Constants.TYPE_GANK;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(true);

                        break;
                    case R.id.drawer_gold:
                        toolBar.setTitle("稀土掘金");

                        showType = Constants.TYPE_GOLD;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(true);

                        break;

                    case R.id.drawer_like:
                        toolBar.setTitle("收藏");

                        showType = Constants.TYPE_LIKE;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(true);

                        break;
                    case R.id.drawer_about:
//                        toolBar.setTitle("干货集中营");

                        showType = Constants.TYPE_ABOUT;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(false);

                        break;
                }
                if (lastltem != null) {
                    lastltem.setChecked(false);
                }

                menuItem.setChecked(true);
                lastltem = menuItem;

                drawer.closeDrawer(Gravity.LEFT);
                return false;


            }


        });
    }

    int showType = Constants.TYPE_ZHIHU;


    Fragment currFragment;
    private void switchFragmet(Fragment targetFragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 目标对象没有加入过
        if (!targetFragment.isAdded()) {
            if (currFragment != null) {
                transaction.hide(currFragment);
                transaction.add(R.id.fl_main_content, targetFragment, targetFragment.getClass().getName());
            }
        } else {
            // 如果目标fragment 已经在事务里，显示即可
            transaction.hide(currFragment).show(targetFragment);
        }
        currFragment = targetFragment;
        transaction.commit();

    }
    private Fragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return zhiHuMainFragment;
            case Constants.TYPE_GANK:
                return gankMainFragment;
            case Constants.TYPE_WECHAT:
                return weChatFragment;
            case Constants.TYPE_GOLD:
                return goldMainFragment;
//            case Constants.TYPE_VTEX:
//                return mVtexFragment;
            case Constants.TYPE_LIKE:
                return myLikeFragment;
//            case Constants.TYPE_SETTING:
//                return mSettingFragment;
            case Constants.TYPE_ABOUT:
                return aboutFragment;
        }
        return zhiHuMainFragment;
    }
    @Override
    protected void initMvp() {

    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


}
