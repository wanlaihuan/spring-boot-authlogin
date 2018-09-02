package me.tianle.login.server;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 七牛云服务器
 */
public class QiNiuService {
    // 空间的文件服务器地址
    public static String QINIU_SERVICE_ADDRESS = "http://pedn72rwy.bkt.clouddn.com";
    static String accessKey = "XT0h0_TwavG9SAIW8MxYrIeBvFSpzLmUqcjd6a27";
    static String secretKey = "Na8JTM4OAZcTBTEsqJpEmS9rjciWrZubiFINYYJ5";
    static String bucket = "springimage"; // 控件名称
    static Auth auth;
    static String upToken;
    static Configuration cfg;

    static {
        auth = Auth.create(accessKey, secretKey);
        upToken = auth.uploadToken(bucket);
        //构造一个带指定Zone对象的配置类
        cfg = new Configuration(Zone.zone2());
    }

    static class INSTANCE {
        public static QiNiuService INSTANCE = new QiNiuService();
    }

    public static QiNiuService getInstance() {
        return INSTANCE.INSTANCE;
    }

    private UploadManager uploadManager;

    /**
     * 上传文件，比如图片
     *
     * @param filePath
     * @param fileName null 会设置一个默认的文件名称
     * @return DefaultPutRet key 就是文件名称 hash 就是文件内容的hash值
     */
    public DefaultPutRet uploadFile(String filePath, String fileName) {
        uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名,key 就是最终生成的文件名
        String key = fileName;
        try {
            Response response = uploadManager.put(filePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    /**
     * 创建 BucketManager 用来删除空间中的文件
     * @return
     */
    public BucketManager creatBucketManager() {
        BucketManager bucketManager = new BucketManager(auth, cfg);

        return bucketManager;
    }

    /**
     * 删除指定的文件
     * @param fileName
     * @return
     */
    public boolean deleteFile(String fileName) {
        String key = fileName;
        try {
            BucketManager bucketManager = new BucketManager(auth, cfg);
            bucketManager.delete(bucket, key);

            return true;
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }

        return false;
    }
}
