package com.ylife.goods.model;

/**
 * @author AthrunNatu
 * @since 2014年3月18日下午4:45:17
 */
public final class Upload {

    private Upload() {
    }

    /**
     * 上传至FastDFS
     *
     * @param path
     *            上传文件路径
     * @return 上传的文件在服务器上的路径
     */
    public static String uploadToFSD(String path) {
        try {
            return UploadClient.getUploads().uploadToDFS(path);
        } catch (Exception e) {
            return "error";
        }
    }

    public static String uploadToFSD(String path, FastDFSInfo fastdfs) {
        try {
            return UploadClient.getUploads().uploadToDFS(path, fastdfs);
        } catch (Exception e) {
            return "error";
        }
    }
}
