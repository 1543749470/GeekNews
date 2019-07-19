package com.jy.beans;

import java.util.List;

public class DailyBeforeBean {


    /**
     * date : 20190709
     * stories : [{"images":["https://pic2.zhimg.com/v2-3b63aa5b5f7b97ade5b37c9793aa2ad5.jpg"],"type":0,"id":9713215,"ga_prefix":"070920","title":"如何评价《怪奇物语》第三季?"},{"images":["https://pic1.zhimg.com/v2-ad9412c8b062188e2663a44f831b45d0.jpg"],"type":0,"id":9713194,"ga_prefix":"070916","title":"开普勒是如何得出开普勒三大定律的？"},{"images":["https://pic1.zhimg.com/v2-062961c4471ebb9ba6df45296638d098.jpg"],"type":0,"id":9713184,"ga_prefix":"070909","title":"中国有什么 ACG 爱好者圣地巡礼的地方？"},{"images":["https://pic1.zhimg.com/v2-e6ee18abc80ebf07490f0a2787c3993c.jpg"],"type":0,"id":9713253,"ga_prefix":"070907","title":"为什么把鱼放进可乐和雪碧中浸泡 30 天，鱼没了？"},{"images":["https://pic4.zhimg.com/v2-c07f3b9ffa5298549d967d95dba38ccf.jpg"],"type":0,"id":9713107,"ga_prefix":"070906","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic2.zhimg.com/v2-3b63aa5b5f7b97ade5b37c9793aa2ad5.jpg"]
         * type : 0
         * id : 9713215
         * ga_prefix : 070920
         * title : 如何评价《怪奇物语》第三季?
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
