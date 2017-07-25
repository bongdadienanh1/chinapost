package com.ylife.fdfs;

import com.ylife.utils.ClasspathConfHelper;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by InThEnd on 2016/7/15.
 * fastDFS服务。
 */
public class FastDFS {

    private static final String CONF_FILE_NAME = "fdfs_client.conf";

    private StorageClient storageClient;

    private String url_prefix;

    public FastDFS() {
        try {
            String classPath = new File(FastDFS.class.getResource("/").getFile()).getCanonicalPath();
            String configFilePath = classPath + "/" + CONF_FILE_NAME;
            ClasspathConfHelper helper = new ClasspathConfHelper(CONF_FILE_NAME);
            url_prefix = helper.getValue("url.prefix");

            ClientGlobal.init(configFilePath);

            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient1(trackerServer, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存文件,返回地址。
     */
    public String saveFile(byte[] bytes, String suffix, NameValuePair[] metaList) throws Exception {
        String[] s = storageClient.upload_file(bytes, suffix, metaList);
        System.out.println(s[1]);
        return url_prefix + s[1];
    }

    /**
     * 保存文件,返回地址。
     */
    public String saveFile(byte[] bytes, String suffix) throws Exception {
        return saveFile(bytes, suffix, null);
    }

    /**
     * 保存文件,返回地址。
     */
    public String saveFile(MultipartFile file, String suffix, NameValuePair[] metaList) throws Exception {
        return saveFile(file.getBytes(), suffix, metaList);
    }

    /**
     * 保存文件,返回地址。
     */
    public String saveFile(MultipartFile file, String suffix) throws Exception {
        return saveFile(file, suffix, null);
    }
}
