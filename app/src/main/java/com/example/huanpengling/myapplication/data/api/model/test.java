package com.example.huanpengling.myapplication.data.api.model;

import java.util.List;

/**
 * Created by huanpeng.ling on 2015/12/14.
 */
public class test {
  /**
   * channelId : 5572a109b3cdc86cf39001db
   * channelName : 国内最新
   * chinajoy : 0
   * desc : 在2015年11月之前有过一次转型的经历，并最终服务定位于移动互联网人才的招聘平台社区。
   * imageurls : [{"height":119,"url":"http://www.donews.com/idonews/Upload/image/20151214/20151214170109_33874.jpg","width":169}]
   * link : http://www.donews.com/idonews/article/7567.shtm
   * long_desc : 中国有句古话，穷则变，变则通，通则久——《周易》。说明了当事物发展到极点，就要发生变化，发生变化才会使事物的发展不受阻塞，事物才能不断发展。且在面临不能发展的困局时，就必须改变现状，进行转型。接下来让我们来盘点那些在市场上转型失败后裁员的一些公司案例。不仅是在互联网行业，转型裁员的还有很多实业企业。
   * nid : 4179414251478660025
   * pubDate : 2015-12-14 17:38:00
   * source : DoNews
   * title : 不转型等死，转型可能是找死?
   */

  private String channelId;
  private String channelName;
  private int chinajoy;
  private String desc;
  private String link;
  private String long_desc;
  private String nid;
  private String pubDate;
  private String source;
  private String title;
  /**
   * height : 119
   * url : http://www.donews.com/idonews/Upload/image/20151214/20151214170109_33874.jpg
   * width : 169
   */

  private List<ImageurlsEntity> imageurls;

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public void setChinajoy(int chinajoy) {
    this.chinajoy = chinajoy;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public void setLong_desc(String long_desc) {
    this.long_desc = long_desc;
  }

  public void setNid(String nid) {
    this.nid = nid;
  }

  public void setPubDate(String pubDate) {
    this.pubDate = pubDate;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setImageurls(List<ImageurlsEntity> imageurls) {
    this.imageurls = imageurls;
  }

  public String getChannelId() {
    return channelId;
  }

  public String getChannelName() {
    return channelName;
  }

  public int getChinajoy() {
    return chinajoy;
  }

  public String getDesc() {
    return desc;
  }

  public String getLink() {
    return link;
  }

  public String getLong_desc() {
    return long_desc;
  }

  public String getNid() {
    return nid;
  }

  public String getPubDate() {
    return pubDate;
  }

  public String getSource() {
    return source;
  }

  public String getTitle() {
    return title;
  }

  public List<ImageurlsEntity> getImageurls() {
    return imageurls;
  }

  public static class ImageurlsEntity {
    private int height;
    private String url;
    private int width;

    public void setHeight(int height) {
      this.height = height;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public void setWidth(int width) {
      this.width = width;
    }

    public int getHeight() {
      return height;
    }

    public String getUrl() {
      return url;
    }

    public int getWidth() {
      return width;
    }
  }
}
