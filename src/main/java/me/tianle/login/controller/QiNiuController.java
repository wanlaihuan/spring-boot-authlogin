package me.tianle.login.controller;

import com.qiniu.storage.model.DefaultPutRet;
import me.tianle.login.resp.RespCode;
import me.tianle.login.resp.RespEntity;
import me.tianle.login.resp.EntityJsonValue;
import me.tianle.login.server.QiNiuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image/qiniutest")
public class QiNiuController {

    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "/uploadImage", method = {RequestMethod.GET, RequestMethod.POST})
    public RespEntity uploads() {
        String localFilePath = "D:\\qiniu\\test.png";

        DefaultPutRet putRet = QiNiuService.getInstance().uploadFile(localFilePath, null);

        EntityJsonValue.with()
                .put("image_url", putRet == null ? "" : "/" + putRet.key);

        return new RespEntity(RespCode.SUCCESS, EntityJsonValue.with().toJsonValue());
    }

    /**
     * 文件删除
     * @return
     */
    @RequestMapping(value = "/deleteImage", method = {RequestMethod.GET, RequestMethod.POST})
    public RespEntity delete() {

        boolean isDeleted = QiNiuService.getInstance().deleteFile(null);

        EntityJsonValue.with().put("isDeleted", isDeleted);

        return new RespEntity(RespCode.SUCCESS, EntityJsonValue.with().toJsonValue());
    }

}
