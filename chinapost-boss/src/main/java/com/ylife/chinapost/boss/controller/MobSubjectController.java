/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ylife.chinapost.boss.controller;


import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.main.model.MobSubject;
import com.ylife.main.service.MobSiteBasicService;
import com.ylife.main.service.MobSubjectService;
import com.ylife.utils.xml.XmlElementUtil;
import com.ylife.utils.xml.XmlUtil;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

/**
 * 控制器-移动版首页设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午2:15:43
 */
@Controller
public class MobSubjectController {

    private static final String APPCONT = "app_cont";
    private static final String APPCONT3 = "app_cont_3";
    private static final String ROLLADV = "roll_adv";
    private static final String FULLROLL = "fullRoll";
    private static final String GOODSMOD = "goodsmod";
    private static final String BLANKBOX = "blankbox";
    private static final String DIVIDING = "dividing";
    private static final String STYLE = "style";
    private static final String WIDTHB = "widthB";
    private static final String HEIGHTB = "heightB";
    private static final String MUSICNAME = "musicname";
    private static final String BLANK_BOX = "blank-box";
    private static final String QUERYMOBHOMEPAGE_HTM = "queryMobHomePage.htm";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";

    XmlUtil xmlUtil = new XmlUtil();
    XmlElementUtil xmlElementUtil = new XmlElementUtil();
    /** 商家ID */
    static final Long STOREID = -1L;
    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";
    static final String xslFilePath = "mobile_home_page/xsl/9gdemo_save.xsl";
    static final String htmlFilePath = "mobile_home_page/html/9gdemo.html";

    /**
     * 查看移动版可视化配置专题列表
     * 
     * @Title: queryMobSubject
     * @Description: 根据当前商家的商家ID，查看该商家的移动版可视化配置专题列表，包括当前启用的和备选的
     * @param merchantId
     *            商家ID
     * @return
     */
    @RequestMapping("/queryMobSubject")
    public ModelAndView queryMobSubject(Long merchantId,String flag) {
        ModelAndView mav = new ModelAndView();
        if (null == merchantId) {
            merchantId = STOREID;
        }
        mav.addObject("merchantId", merchantId);
        // 获取该商家未启用的首页列表
        List<MobSubject> list = this.mobSubjectService.selectAllSubjectUnstatusByMerchantId(merchantId);
        mav.addObject("homePages", list);
        mav.setViewName("mob_add_subject");
        mav.addObject("VERSION", Constants.JS_VERSION);
        return mav;
    }
    
    /**
     * ajax拿取所有的专区做内联
     * @Title ajaxQueryMobSubject
     * */
    @RequestMapping("/ajaxQueryMobSubject")
    @ResponseBody
    public List<MobSubject> ajaxQueryMobSubject(){
    	List<MobSubject> list = this.mobSubjectService.selectAllSubjectUnstatusByMerchantId(-1L);
    	return list;
    }
    /**
     * 根据首页ID查看移动版首页，并进行编辑
     * 
     * @Title: setMobMain
     * @Description: 根据首页ID查看移动版首页，并进行编辑
     *               <p>
     *               获取首页对象后，通过XmlUtil工具类把该对象中保存的xml字符串转换成XML Document。
     *               </p>
     *               <p>
     *               再根据商家ID和首页ID生成xml文件，供后台调用xml生成html
     *               </p>
     * @param merchantId
     *            商家ID
     *            <p>
     *            如果商家ID为null，则默认为-1，代表大boss的商家ID
     *            </p>
     * @param
     *
     *            <p>
     *            如果首页ID为null，则通过商家ID，查看该商家当前启用的首页
     *            </p>
     * @param request
     * @return
     */
    @RequestMapping("/setMobSubject")
    public ModelAndView setMobMain(Long merchantId, Long subjectId, HttpServletRequest request) {
        // log.debug("============进入移动版新首页配置控制器："+new Date().toLocaleString()+"=================");
        request.getSession().setAttribute("rsv", mkRSV());
        ModelAndView mav = new ModelAndView();
        if (null == merchantId) {
            merchantId = STOREID;
        }
        MobSubject homePage = null;
        if (null == subjectId) {
        	homePage = new MobSubject();
            homePage.setTitle(request.getParameter("title"));
            homePage.setMerchantId(merchantId);
            homePage = this.mobSubjectService.initHomePage(homePage);
            return new ModelAndView(new RedirectView("queryMobSubject.htm"));
        } else {
            homePage = this.mobSubjectService.getSubjectById(subjectId);
        }

        Document doc = xmlUtil.str2Document(homePage.getDoc());
        String filePath = request.getSession().getServletContext().getRealPath("/");
        String xmlFileName = xmlFilePath.replace(".xml", "_" + merchantId + "_" + subjectId + ".xml");
        String fileName = filePath + xmlFileName;
        // 把doc写入用户的文件
        xmlUtil.createXml(fileName, doc);
        mav.addObject("xmlFilePath", xmlFileName);
        mav.addObject("merchantId", merchantId);
        // mav.addObject("homePageId", homePageId);
        mav.addObject("homePage", homePage);
        mav.addObject("mobSiteBasic", this.mobSiteBasicService.selectCurrMobSiteBasic(request));
        mav.setViewName("set_mob_subject");
        mav.addObject("VERSION",Constants.JS_VERSION);
        // log.debug("============退出移动版新首页配置控制器："+new Date().toLocaleString()+"=================");
        return mav;
    }


    @RequestMapping("/saveAllModSubject")
    public ModelAndView saveAllModSubject(Long subjectId, HttpServletRequest request) {
        // request.getSession().setAttribute("rsv", mkRSV());
        MobSubject homePage = this.mobSubjectService.getSubjectById(subjectId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        Element root = document.getDocumentElement();
        // 清空
        xmlElementUtil.removeElement(root, APPCONT);
        xmlElementUtil.removeElement(root, APPCONT3);
        xmlElementUtil.removeElement(root, "img_adv");
        xmlElementUtil.removeElement(root, ROLLADV);
        xmlElementUtil.removeElement(root, FULLROLL);
        xmlElementUtil.removeElement(root, "text");
        xmlElementUtil.removeElement(root, GOODSMOD);
        xmlElementUtil.removeElement(root, BLANKBOX);
        xmlElementUtil.removeElement(root, "dividing");

        // 获取html内容
        String ipCont = request.getParameter("ipCont");
        org.jsoup.nodes.Document doc = Jsoup.parse(ipCont);
        Elements items = doc.getElementsByClass("item");

        for (int i = 0; i < items.size(); i++) {
            org.jsoup.nodes.Element item = items.get(i);
            String itemId = item.attr("id");
            String id = itemId.substring(8);
            if (itemId.indexOf(APPCONT) != -1 && itemId.indexOf("app_cont_3") == -1) {// 魔方
                // 创建新魔方模块
                Element newAppCont = root.getOwnerDocument().createElement(APPCONT);
                xmlElementUtil.setElementAttr(newAppCont, "id", id);
                root.appendChild(newAppCont);

                Elements imgs = item.getElementsByClass("imgapp");
                for (int j = 0; j < imgs.size(); j++) {
                    org.jsoup.nodes.Element img = imgs.get(j);
                    String imgId = img.attr("id").substring(2);
                    String src = img.attr("src");
                    String href = img.parent().attr("href");
                    String sRow = imgId.substring(3, 4);
                    String sCol = imgId.substring(5, 6);
                    String rows = imgId.substring(7, 8);
                    String cols = imgId.substring(9);

                    int width = Integer.valueOf(cols) * 160;
                    int height = Integer.valueOf(rows) * 160;

                    // 获取图片信息，并处理成后台预览用属性
                    Double widthvalue = (new Double(width)) / 2;
                    Double heightvalue = (new Double(height)) / 2;
                    String left = "left:" + ((Integer.valueOf(sCol) - 1) * 160 / 2 - 1) + "px;";
                    String top = "top:" + ((Integer.valueOf(sRow) - 1) * 160 / 2) + "px;";
                    String style = "position:absolute;" + left + top;
                    // 前台展示用的属性
                    String widthB = (widthvalue / 320) * 100 + "%";
                    String heightB = (heightvalue / 320) * 100 + "%";
                    String leftB = (((new Double(sCol) - 1) * 160 / 2) / 320) * 100 + "%";
                    String topB = (((new Double(sRow) - 1) * 160 / 2) / 320) * 100 + "%";
                    String styleB = "position:absolute;left:" + leftB + ";top:" + topB + ";";
                    // 创建a标签元素
                    Element newA = newAppCont.getOwnerDocument().createElement("a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    // 创建img元素
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "id", imgId);
                    xmlElementUtil.setElementAttr(newImg, "src", src);
                    xmlElementUtil.setElementAttr(newImg, "class", "imgapp");
                    xmlElementUtil.setElementAttr(newImg, WIDTH, widthvalue.toString());
                    xmlElementUtil.setElementAttr(newImg, HEIGHT, heightvalue.toString());
                    xmlElementUtil.setElementAttr(newImg, STYLE, style);
                    xmlElementUtil.setElementAttr(newImg, WIDTHB, widthB);
                    xmlElementUtil.setElementAttr(newImg, HEIGHTB, heightB);
                    xmlElementUtil.setElementAttr(newImg, "styleB", styleB);

                    newA.appendChild(newImg);
                    newAppCont.appendChild(newA);

                }
            }
            if (itemId.indexOf("app_cont_3") != -1) {// 魔方
                // 创建新魔方模块
                Element newAppCont = root.getOwnerDocument().createElement("app_cont_3");
                id = itemId.substring(10);
                xmlElementUtil.setElementAttr(newAppCont, "id", id);
                root.appendChild(newAppCont);

                Elements imgs = item.getElementsByClass("imgapp");
                for (int j = 0; j < imgs.size(); j++) {
                    org.jsoup.nodes.Element img = imgs.get(j);
                    String imgId = img.attr("id").substring(2);

                    String src = img.attr("src");
                    String href = img.parent().attr("href");
                    String sRow = imgId.substring(4, 5);
                    String sCol = imgId.substring(6, 7);
                    String rows = imgId.substring(8, 9);
                    String cols = imgId.substring(10);

                    int width = Integer.valueOf(cols) * 220;
                    int height = Integer.valueOf(rows) * 220;

                    // 获取图片信息，并处理成后台预览用属性
                    Double widthvalue = (new Double(width)) / 2;
                    Double heightvalue = (new Double(height)) / 2;
                    String left = "left:" + ((Integer.valueOf(sCol) - 1) * 220 / 2 - 1) + "px;";
                    String top = "top:" + ((Integer.valueOf(sRow) - 1) * 220 / 2) + "px;";
                    String style = "position:absolute;" + left + top;
                    // 前台展示用的属性
                    String widthB = (widthvalue / 330) * 100 + "%";
                    String heightB = (heightvalue / 330) * 100 + "%";
                    String leftB = (((new Double(sCol) - 1) * 220 / 2) / 330) * 100 + "%";
                    String topB = (((new Double(sRow) - 1) * 220 / 2) / 330) * 100 + "%";
                    String styleB = "position:absolute;left:" + leftB + ";top:" + topB + ";";
                    // 创建a标签元素
                    Element newA = newAppCont.getOwnerDocument().createElement("a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    // 创建img元素
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "id", imgId);
                    xmlElementUtil.setElementAttr(newImg, "src", src);
                    xmlElementUtil.setElementAttr(newImg, "class", "imgapp");
                    xmlElementUtil.setElementAttr(newImg, WIDTH, widthvalue.toString());
                    xmlElementUtil.setElementAttr(newImg, HEIGHT, heightvalue.toString());
                    xmlElementUtil.setElementAttr(newImg, STYLE, style);
                    xmlElementUtil.setElementAttr(newImg, WIDTHB, widthB);
                    xmlElementUtil.setElementAttr(newImg, HEIGHTB, heightB);
                    xmlElementUtil.setElementAttr(newImg, "styleB", styleB);

                    newA.appendChild(newImg);
                    newAppCont.appendChild(newA);
                }
            }
            else if (itemId.indexOf("img_adv_") != -1) {// 广告图片
                org.jsoup.nodes.Element a = item.getElementsByTag("a").get(0);
                org.jsoup.nodes.Element img = a.getElementsByTag("img").get(0);
                String href = a.attr("href");
                String src = img.attr("src");

                // 创建广告图片节点
                Element newImgAdv = root.getOwnerDocument().createElement("img_adv");
                xmlElementUtil.setElementAttr(newImgAdv, "id", id);
                root.appendChild(newImgAdv);

                // 创建a标签元素
                Element newA = newImgAdv.getOwnerDocument().createElement("a");
                xmlElementUtil.setElementAttr(newA, "href", href);
                // 创建img元素
                Element newImg = newA.getOwnerDocument().createElement("img");
                xmlElementUtil.setElementAttr(newImg, "src", src);
                xmlElementUtil.setElementAttr(newImg, WIDTH, "100%");
                xmlElementUtil.setElementAttr(newImg, WIDTHB, "100%");

                newA.appendChild(newImg);
                newImgAdv.appendChild(newA);
            } else if (itemId.indexOf(ROLLADV) != -1) {// 轮播广告
                Elements slides = item.getElementsByClass("swiper-slide");
                // 创建轮播广告节点
                Element newImgAdv = root.getOwnerDocument().createElement(ROLLADV);
                xmlElementUtil.setElementAttr(newImgAdv, "id", id);
                root.appendChild(newImgAdv);
                for (int j = 1; j < (slides.size() - 1); j++) {
                    org.jsoup.nodes.Element slide = slides.get(j);
                    org.jsoup.nodes.Element a = slide.getElementsByTag("a").get(0);
                    org.jsoup.nodes.Element img = a.getElementsByTag("img").get(0);
                    String href = a.attr("href");
                    String src = img.attr("src");
                    // 创建a标签元素
                    Element newA = newImgAdv.getOwnerDocument().createElement("roll_a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    // 创建img元素
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "src", src);
                    xmlElementUtil.setElementAttr(newImg, WIDTH, "100%");
                    xmlElementUtil.setElementAttr(newImg, WIDTHB, "100%");
                    xmlElementUtil.setElementAttr(newImg, HEIGHT, "155px");
                    xmlElementUtil.setElementAttr(newImg, HEIGHTB, "50%");

                    newA.appendChild(newImg);
                    newImgAdv.appendChild(newA);
                }

            } else if (itemId.indexOf(FULLROLL) != -1) {// 全屏轮播
                String sd = item.getElementsByClass("fullRollSD").get(0).val();
                String music = "";
                if (!item.getElementsByTag("audio").isEmpty()) {
                    music = item.getElementsByTag("audio").get(0).attr("src");
                }
                String musicname = "";
                if (!item.getElementsByClass(MUSICNAME).isEmpty()) {
                    musicname = item.getElementsByClass(MUSICNAME).get(0).val();
                }

                // 创建全屏轮播节点
                Element newFullRoll = root.getOwnerDocument().createElement(FULLROLL);
                xmlElementUtil.setElementAttr(newFullRoll, "id", id);
                xmlElementUtil.setElementAttr(newFullRoll, "sd", sd);
                xmlElementUtil.setElementAttr(newFullRoll, "music", music);
                xmlElementUtil.setElementAttr(newFullRoll, MUSICNAME, musicname);
                root.appendChild(newFullRoll);
                Elements slides = item.getElementsByClass("swiper-slide");
                for (int j = 1; j < (slides.size() - 1); j++) {
                    org.jsoup.nodes.Element slide = slides.get(j);
                    org.jsoup.nodes.Element a = slide.getElementsByTag("a").get(0);
                    org.jsoup.nodes.Element img = a.getElementsByTag("img").get(0);
                    String href = a.attr("href");
                    String src = img.attr("src");
                    // 创建a标签元素
                    Element newA = newFullRoll.getOwnerDocument().createElement("roll_a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    // 创建img元素
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "src", src);
                    xmlElementUtil.setElementAttr(newImg, WIDTHB, "100%");
                    xmlElementUtil.setElementAttr(newImg, HEIGHTB, "100%");

                    newA.appendChild(newImg);
                    newFullRoll.appendChild(newA);
                }
            } else if (itemId.indexOf("text_app") != -1) {// 文字
                String content = item.html();
                // 创建文字节点
                Element newText = root.getOwnerDocument().createElement("text");
                newText.setNodeValue(content);
                xmlElementUtil.setElementValue(newText, content);
                xmlElementUtil.setElementAttr(newText, "id", id);
                root.appendChild(newText);
            } else if (itemId.indexOf(GOODSMOD) != -1) {// 商品
                String styleClass = item.attr("styleClass");
                // 创建商品节点
                Element newGoodsmod = root.getOwnerDocument().createElement(GOODSMOD);
                xmlElementUtil.setElementAttr(newGoodsmod, "id", id);
                xmlElementUtil.setElementAttr(newGoodsmod, "class", styleClass);
                root.appendChild(newGoodsmod);
                Elements as = item.getElementsByTag("a");
                for (int j = 0; j < as.size(); j++) {
                    org.jsoup.nodes.Element a = as.get(j);
                    String href = a.attr("href");
                    String src = a.getElementsByTag("img").get(0).attr("src");
                    String name = a.getElementsByTag("h3").get(0).html();
                    String price = a.getElementsByTag("span").get(0).html();
                    // 创建li标签元素
                    Element newLi = newGoodsmod.getOwnerDocument().createElement("li");
                    // 创建a标签元素
                    Element newA = newLi.getOwnerDocument().createElement("goods_a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    xmlElementUtil.setElementAttr(newA, "name", name);
                    xmlElementUtil.setElementAttr(newA, "price", price);
                    // 创建img元素
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "src", src);

                    newA.appendChild(newImg);
                    newLi.appendChild(newA);
                    newGoodsmod.appendChild(newLi);
                }
            } else if (itemId.indexOf(BLANKBOX) != -1) {// 空白占位
                String height = "30px;";
                if (item.getElementsByClass(BLANK_BOX) != null && item.getElementsByClass(BLANK_BOX).get(0) != null
                        && item.getElementsByClass(BLANK_BOX).get(0).attr(STYLE) != null) {
                    if (item.getElementsByClass(BLANK_BOX).get(0).attr(STYLE).split(":").length > 1) {
                        height = item.getElementsByClass(BLANK_BOX).get(0).attr(STYLE).split(":")[1];
                    }
                }

                // 创建空白占位节点
                Element newBlankbox = root.getOwnerDocument().createElement(BLANKBOX);
                xmlElementUtil.setElementAttr(newBlankbox, "id", id);
                xmlElementUtil.setElementAttr(newBlankbox, HEIGHT, height);
                root.appendChild(newBlankbox);
            } else if (itemId.indexOf(DIVIDING) != -1) {// 分隔线
                // 创建分隔线节点
                Element newBlankbox = root.getOwnerDocument().createElement(DIVIDING);
                xmlElementUtil.setElementAttr(newBlankbox, "id", id);
                root.appendChild(newBlankbox);
            }

        }
        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobSubjectService.updateSubject(homePage);
        String customerName = (String) request.getSession().getAttribute("name");
        LOGGER.info(customerName+"修改手机专题模板"+request.getSession().getAttribute("operaPath") + ",用户名:" + customerName);
        return new ModelAndView(new RedirectView("queryMobSubject.htm"));
    }


    /**
     * 删除专题
     * 
     * @Title: deleteSubject
     * @Description: 根据首页ID，删除专题
     * @param subjectId
     *            首页ID
     * @return
     */
    @RequestMapping("/deleteSubject")
    public ModelAndView deleteSubject(Long subjectId) {
        this.mobSubjectService.deleteHomePageById(subjectId);
        return new ModelAndView(new RedirectView("queryMobSubject.htm"));
    }

    /*
     * 生成移动版新首页token
     */
    private String mkToken() {
        String tokenValue = null;
        // 生成tokin
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(new Date().toString().getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            tokenValue = new BigInteger(1, md.digest()).toString(16);
            LOGGER.debug("==========token码为" + tokenValue);

        } catch (Exception e) {
            LOGGER.error("生成token码错误：=>", e);
        }
        return tokenValue;
    }

    /*
     * 生成重复提交token
     */
    private String mkRSV() {
        String tokenValue = null;
        // 生成tokin
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(new Date().toString().getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            tokenValue = new BigInteger(1, md.digest()).toString(16);
            LOGGER.debug("==========token码为" + tokenValue);

        } catch (Exception e) {
            LOGGER.error("生成token码错误：=>", e);
        }
        return tokenValue;
    }
   
    @RequestMapping("/updateSubjectById")
    @ResponseBody
    public void updateSubjectById(Long subjectId , String title){
    	MobSubject subject = new MobSubject();
    	subject.setSubjectId(subjectId);
    	subject.setTitle(title);
    	this.mobSubjectService.updateSubject(subject);
    }

    
    @Resource
    private MobSubjectService mobSubjectService;
    @Resource
    private MobSiteBasicService mobSiteBasicService;

    private static final Logger LOGGER = Logger.getLogger(MobSubjectController.class);
}
