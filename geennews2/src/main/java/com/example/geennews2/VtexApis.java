package com.example.geennews2;


import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by codeest on 16/12/22.
 */

public interface VtexApis {

    String HOST = "https://www.v2ex.com/";

    String TAB_HOST = "https://www.v2ex.com/?tab=";



//    /**
//     * 获取节点信息
//     * @return
//     */
//    @GET("/api/nodes/show.json")
//    Flowable<NodeBean> getNodeInfo(@Query("name") String name);




//    /**
//     * 获取主题列表
//     * https://www.v2ex.com/api/topics/show.json?node_name=qna
//     * @return
//     */
//    @GET("/api/topics/show.json")
//    Flowable<List<NodeListBean>> getTopicList(@Query("node_name") String name);

//    /**
//     * 获取主题信息
//     * @return
//     */
//    @GET("/api/topics/show.json")
//    Flowable<List<NodeListBean>> getTopicInfo(@Query("id") String id);

//    /**
//     * 获取主题回复
//     * @return
//     */
//    @GET("/api/replies/show.json")
//    Flowable<List<RepliesListBean>> getRepliesList(@Query("topic_id") String id);
}
