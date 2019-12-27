package com.puhuanyu.erp.myerp.util;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  @Description 图片处理工具
 *  @ClassName PhotoImageUtil
 *  @Author 忠哥
 *  @data 2019-12-27 11:36
 */
@Component
public class PhotoImageUtil
{
    @Value("${saveImgPath}")
    private String saveImgPath;//目录

    /**
     * @Description 将上传图片保存在本地，并返回全路径，如果不是图片，则返回“请上传图片文件”，
     * @Param [filePath] 上传图片的路径
     * @return java.lang.String
     * @Author 忠哥
     * @Date 2019-12-27 14:13
     */
    public String dataBase_imgPath(String filePath) throws IOException {
    String resultPath = "";    //指定图片的保存路径
    try {
        File file = new File(filePath);
        if(!file.isFile()){//判断上传的东西是不是个文件
            throw new Exception(file+"不是个文件");
        }
        if(file != null && file.exists()){
            BufferedImage src = ImageIO.read(file);
            if(src != null){//判断上传的是否为图片文件,该方法适用的图片格式为 bmp/gif/jpg/png
                String fileType = getFileType(filePath);//图片格式
                String imageName = getImageName();//图片名称
                resultPath = saveImgPath + imageName + "." + fileType;//图片全路径
                produceImg(src, fileType, resultPath);//生成图片
            }else{
                return "请上传图片文件";
            }
        }
    } catch (Exception e){
        e.printStackTrace();
    }
    return resultPath;
}

    /**
     * @Description 获取图片格式（也就是后缀名）
     * @Param [filePath] 上传的文件路径
     * @return java.lang.String
     * @Author 忠哥
     * @Date 2019-12-27 11:47
     */
    public String getFileType(String filePath){
        int lastLength = filePath.lastIndexOf(".");
        String fileType = filePath.substring(lastLength + 1);
        return fileType;
    }

    /**
     * @Description 获取随机生成的图片名称（年月日时分秒+（0-99））
     * @Param []
     * @return java.lang.String
     * @Author 忠哥
     * @Date 2019-12-27 13:21
     */
    public String getImageName(){
        Date date = new Date(System.currentTimeMillis());
        String strDate = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
        int random = (int)(Math.random()*99);
        String imageName = strDate + random;
        return imageName;
    }

    /**
     * @Description 将新图片保存在本地
     * @Param [src, fileType, resultPath] src为图片缓存器（读取文件转换成缓存器），fileTyp为图片类型，resultPath为新图片全路径
     * @return void
     * @Author 忠哥
     * @Date 2019-12-27 13:29
     */
    public void produceImg(BufferedImage src, String fileType, String resultPath) throws IOException {
        File file1 = new File(saveImgPath);
        if(!file1.exists()){//创建多级目录
            file1.mkdirs();
        }
        ImageIO.write(src, fileType, new File(resultPath));//将图片以流的方式输出到指定路径
    }
}
