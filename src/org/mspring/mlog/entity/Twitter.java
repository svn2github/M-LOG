/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.mspring.mlog.entity.security.User;
import org.mspring.platform.cache.service.CacheService;

/**
 * @author Gao Youbo
 * @since 2013-5-23
 * @description
 * @TODO
 */
@Entity
@Table(name = "twitter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Twitter implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6034776484430118789L;

    private Long id;
    private String content;
    private Date createTime;
    private User author;
    private String ip;
    private String tencentWeiboId;
    private Attachment image;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "content", nullable = false, length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "create_time", nullable = false, length = 30)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = CacheService.CacheName.LAZY_CACHE_NAME)
    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = User.class)
    @JoinColumn(name = "author")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column(name = "ip", length = 100)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "tencent_weibo_id", length = 100)
    public String getTencentWeiboId() {
        return tencentWeiboId;
    }

    public void setTencentWeiboId(String tencentWeiboId) {
        this.tencentWeiboId = tencentWeiboId;
    }

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = CacheService.CacheName.LAZY_CACHE_NAME)
    @ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = Attachment.class)
    @JoinColumn(name = "image")
    public Attachment getImage() {
        return image;
    }

    public void setImage(Attachment image) {
        this.image = image;
    }

}
