package com.example.geennews2;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.navigation.NavigationView;
import com.jy.Base.BaseActivity;
import com.jy.Fragments.AboutFragment;
import com.jy.Fragments.CollectionFragment;
import com.jy.Fragments.gank.GankMainFragment;
import com.jy.Fragments.gold.GoldMainFragment;
import com.jy.Fragments.like.MyLike;
import com.jy.Fragments.setting.SettingFragment;
import com.jy.Fragments.vtex.VtexMainFragment;
import com.jy.Fragments.wechat.WeChatFragment;
import com.jy.Fragments.zhihu.ZhiHuMainFragment;
import com.jy.utils.Constants;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


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
    private FragmentTransaction fragmentTransaction;
    private ZhiHuMainFragment zhiHuMainFragment;
    private WeChatFragment weChatFragment;
    private Fragment currFragment;
    private MenuItem lastltem;
    private GankMainFragment gankMainFragment;
    private GoldMainFragment goldMainFragment;
    private AboutFragment aboutFragment;
    private MenuItem mSearchMenuItem;
    private VtexMainFragment vtexMainFragment;
    private MyLike myLike;
    private SettingFragment settingFragment;
    Bundle savedInstanceState;
    private CollectionFragment collectionFragment;
    private AboutFragment mAboutFragment;
    private MenuItem lastItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (savedInstanceState!=null){
//            FragmentManager supportFragmentManager = getSupportFragmentManager();
//            int index = savedInstanceState.getInt("index");
//            Fragment targetFragment = getTargetFragment(index);
//            supportFragmentManager.beginTransaction().show(targetFragment).hide(getTargetFragment(Constants.TYPE_ZHIHU)).commit();
//            navigation.getMenu().findItem(R.id.drawer_zhihu).setChecked(false);
//            navigation.getMenu().findItem(R.id.drawer_setting).setChecked(true);
//        }

        if (savedInstanceState != null) {
            FragmentManager manager = getSupportFragmentManager();
            showType = savedInstanceState.getInt("douban");

            for (int x = 0; x < manager.getFragments().size(); x++) {
                final Fragment fragment = manager.getFragments().get(x);
                // 如果该对象属于settingFragment
                if (fragment instanceof SettingFragment) {
                    settingFragment = (SettingFragment) fragment;
                    manager.beginTransaction().show(fragment);
                    currFragment = fragment;
                }
                if (fragment instanceof ZhiHuMainFragment) {
                    zhiHuMainFragment = (ZhiHuMainFragment) fragment;
                    manager.beginTransaction().hide(fragment);
                }
                if (fragment instanceof WeChatFragment) {
                    weChatFragment = (WeChatFragment) fragment;

                }

                if (fragment instanceof GankMainFragment) {
                    gankMainFragment = (GankMainFragment) fragment;

                }

                if (fragment instanceof GoldMainFragment) {
                    goldMainFragment = (GoldMainFragment) fragment;

                }
                if (fragment instanceof VtexMainFragment) {
                    vtexMainFragment = (VtexMainFragment) fragment;

                }
//                if (fragment instanceof CollectionFragment) {
//                    collectionFragment = (CollectionFragment) fragment;
//
//                }
                if (fragment instanceof AboutFragment) {
                    myLike = (MyLike) fragment;

                }
                if (fragment instanceof AboutFragment) {
                    mAboutFragment = (AboutFragment) fragment;

                }

            }
            manager.beginTransaction().commit();

            lastItem = navigation.getMenu().findItem(R.id.drawer_setting).setChecked(true);
            navigation.getMenu().findItem(R.id.drawer_zhihu).setChecked(false);
        }
    }
//    @Override
//    public void resumeDataState(Bundle savedInstanceState) {
//        super.resumeDataState(savedInstanceState);
//        this.savedInstanceState=savedInstanceState;
//    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("index",showType);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        viewSearch.setMenuItem(item);
        mSearchMenuItem =item;
        return super.onCreateOptionsMenu(menu);
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
                // 根据不同action来区分

                Intent intent = new Intent();

                if (showType == Constants.TYPE_GANK) {

                    intent.setAction("com.gank.search");
                } else if(showType==Constants.TYPE_LIKE) {

                    intent.setAction("com.like.search");

                }
                intent.putExtra("data", query);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
//        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        if (fragmentTransaction!=null){
//            zhiHuMainFragment = new ZhiHuMainFragment();
//            weChatFragment = new WeChatFragment();
//            gankMainFragment = new GankMainFragment();
//            goldMainFragment = new GoldMainFragment();
//            vtexMainFragment = new VtexMainFragment();
//            settingFragment = new SettingFragment();
//            myLike = new MyLike();
//            aboutFragment = new AboutFragment();
//            currFragment=zhiHuMainFragment;
//            fragmentTransaction.add(R.id.fl_main_content,zhiHuMainFragment);
//            fragmentTransaction.show(zhiHuMainFragment);
//            fragmentTransaction.commit();
//        }

        //_____________________搜索__________________________________________


        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (weChatFragment == null) {
            weChatFragment = new WeChatFragment();

        }
        if (gankMainFragment == null) {
            gankMainFragment = new GankMainFragment();

        }

        if (goldMainFragment == null) {
            goldMainFragment = new GoldMainFragment();

        }
        if (vtexMainFragment == null) {
            vtexMainFragment = new VtexMainFragment();

        }
//        if (collectionFragment == null) {
//            collectionFragment = new CollectionFragment();
//
//        }
        if (settingFragment == null) {
            settingFragment = new SettingFragment();

        }
        if (myLike == null) {
            myLike = new MyLike();

        }
        if (mAboutFragment == null) {
            mAboutFragment = new AboutFragment();

        }


        boolean isHasZhihu = false;
        for (int x = 0; x < getSupportFragmentManager().getFragments().size(); x++) {
            final Fragment fragment = getSupportFragmentManager().getFragments().get(x);
            if (fragment instanceof ZhiHuMainFragment) {
                isHasZhihu = true;
            }
        }
        // 假如fragment栈里没有知乎首页，创建并加入
        if (!isHasZhihu && zhiHuMainFragment == null) {

            zhiHuMainFragment = new ZhiHuMainFragment();
            fragmentTransaction.add(R.id.fl_main_content, zhiHuMainFragment);
            fragmentTransaction.commit();
            currFragment = zhiHuMainFragment;

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
                    case R.id.drawer_vtex:
                        toolBar.setTitle("V2EX");

                        showType = Constants.TYPE_VTEX;
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
                    case R.id.drawer_setting:
                        toolBar.setTitle("设置");

                        showType = Constants.TYPE_SETTING;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(true);

                        break;
                    case R.id.drawer_about:
                        toolBar.setTitle("关于");

                        showType = Constants.TYPE_ABOUT;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(true);

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

    private static final String TAG = "MainActivity";
    int showType = Constants.TYPE_ZHIHU;


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
        final int size = getSupportFragmentManager().getFragments().size();
        Log.e(TAG, "switchFragmet: "+size );
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
            case Constants.TYPE_VTEX:
                return vtexMainFragment;
            case Constants.TYPE_LIKE:
                return myLike;
            case Constants.TYPE_SETTING:
                return settingFragment;
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
