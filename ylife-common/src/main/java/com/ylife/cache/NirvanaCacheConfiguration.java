package com.ylife.cache;

import com.ylife.cache.annotation.MethodAnnotationSource;
import com.ylife.cache.annotation.SimpleSpecificMethodAnnotationSource;
import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.utils.Assert;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by InThEnd on 2017/1/17.
 * Cache方案的配置类。
 */
public class NirvanaCacheConfiguration implements Configuration, InitializingBean, ApplicationContextAware {

    private CacheManager cacheManager;

    private MethodAnnotationSource annotationSource = new SimpleSpecificMethodAnnotationSource();

    /**
     * 可信数据存活时间。默认为无限长。
     */
    private int trustedCacheLivesSecond = 0;

    /**
     * 空缓存存活时间。一个Cache如果未命中，那在这段时间内访问此key的get操作将直接返回空。
     * 直到这这段时间结束。这样可以有效防止缓存穿透。但是由于add操作结果的不可预知，这个
     * 时间不宜设置过大，以免出现实际上某个key值的Object已经加入数据库，但是由于add操作写缓存失败，
     * 导致业务长时间无法获取此Object的情况。
     * 默认为5分钟。
     */
    private int nullCacheLivesSecond = 5 * 60;

    /**
     * 不可信缓存存活时间。处在{@link ObjectStatus#CONTROLLABLE}或者{@link ObjectStatus#UNCERTAIN}
     * 状态的cache存活时间。
     * 默认为30分钟。
     */
    private int untrustedCacheLivesSecond = 30 * 60;

    /**
     * 一个当前操作的存活时间。
     * 如果一个当前操作超过此时间未结束，则认为操作无法完成。将可能从当前操作列表中移除。
     *
     * @see CacheObject#removeDeathOperation(int)
     */
    private int operationLivesSecond = 5 * 60;

    /**
     * 重试次数。
     */
    private int retryTimes = 3;

    /**
     * 当前操作线程的唯一ID生成器。
     */
    public Generator generator = IdGeneratorFactory.create("CACHE_EDIT_THREAD", IdGeneratorFactory.SPartSize.TWO, IdGeneratorFactory.NPartSize.FIVE);


    /**
     * 获得cache存活时间。多个条件满足取最短时间。
     *
     * @see CacheObject
     */
    public int obtainLiveTimes(CacheObject cacheObject) {
        int time = getTrustedCacheLivesSecond();
        switch (cacheObject.getStatus()) {
            case COINCIDENT:
                break;
            case CONTROLLABLE:
            case UNCERTAIN:
                if (time == 0 || time > getUntrustedCacheLivesSecond()) {
                    time = getUntrustedCacheLivesSecond();
                }
                break;
        }

        if (cacheObject.getValueWrapper() == null) {
            if (time == 0 || time > getUntrustedCacheLivesSecond()) {
                time = getUntrustedCacheLivesSecond();
            }
        } else if (cacheObject.getValueWrapper().getValue() == null) {
            if (time == 0 || time > getNullCacheLivesSecond()) {
                time = getNullCacheLivesSecond();
            }
        }
        return time;
    }

    /**
     * 产生一个操作。
     */
    public CurrentOperation obtainOperation(CurrentOperation.Operation operation) {
        long id = getGenerator().generate();
        return new CurrentOperation(id, operation);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheManager, "cacheManager must not be null.");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }


    //===================================GET/SET========================================

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public int getTrustedCacheLivesSecond() {
        return trustedCacheLivesSecond;
    }

    public void setTrustedCacheLivesSecond(int trustedCacheLivesSecond) {
        this.trustedCacheLivesSecond = trustedCacheLivesSecond;
    }

    public int getNullCacheLivesSecond() {
        return nullCacheLivesSecond;
    }

    public void setNullCacheLivesSecond(int nullCacheLivesSecond) {
        this.nullCacheLivesSecond = nullCacheLivesSecond;
    }

    public int getUntrustedCacheLivesSecond() {
        return untrustedCacheLivesSecond;
    }

    public void setUntrustedCacheLivesSecond(int untrustedCacheLivesSecond) {
        this.untrustedCacheLivesSecond = untrustedCacheLivesSecond;
    }

    public int getOperationLivesSecond() {
        return operationLivesSecond;
    }

    public void setOperationLivesSecond(int operationLivesSecond) {
        this.operationLivesSecond = operationLivesSecond;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public MethodAnnotationSource getAnnotationSource() {
        return annotationSource;
    }

    public void setAnnotationSource(MethodAnnotationSource annotationSource) {
        this.annotationSource = annotationSource;
    }
}
