package com.wangdi.springboot13fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author dwang
 * @create 2020-02-27 17:47
 */
@Controller
@RequestMapping("upload/")
public class UploadController {

    @GetMapping({"/index",""})
    public String index(){
        return "upload";
    }

    @PostMapping("/uploadFile")
    public String upload(@RequestParam("files") MultipartFile multipartFile, Model model){


        String imgUrl =  "http://47.98.152.1";
        // 上传图片到服务器
        // 配置fdfs的全局链接地址
        String tracker = UploadController.class.getResource("/tracker.conf").getPath();// 获得配置文件的路径
        try {
            ClientGlobal.init(tracker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TrackerClient trackerClient = new TrackerClient();
        // 获得一个trackerServer的实例
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String originalFilename="";
        // 通过tracker获得一个Storage链接客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);
        try {
            byte[] bytes = multipartFile.getBytes();// 获得上传的二进制对象
            // 获得文件后缀名
            originalFilename = multipartFile.getOriginalFilename();// a.jpg
            System.out.println(originalFilename);
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i+1);
            String[] uploadInfos = storageClient.upload_file(bytes, extName, null);
            for (String uploadInfo : uploadInfos) {
                imgUrl += "/"+uploadInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("msg", "上传成功");
        model.addAttribute("imageName", originalFilename);
        model.addAttribute("imageUrl", imgUrl);

        return "success";
    }

}
