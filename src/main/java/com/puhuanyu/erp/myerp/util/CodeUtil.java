package com.puhuanyu.erp.myerp.util;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 *  @Description 验证码工具类
 *  @ClassName CodeUtil
 *  @Author 忠哥
 *  @data 2020-01-08 13:29
 */
@Component
public class CodeUtil {
    //随机验证码的字符串集
    private static final String RANDOM_STRS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String FONT_NAME = "Fixedsys";//字体名称
    private static final int FONT_SIZE = 18;//字体大小

    private Random random = new Random();

    private int width = 100;// 图片宽
    private int height = 40;// 图片高
    private int lineNum = 50;// 干扰线数量
    private int strNum = 4;// 随机产生字符数量

    /**
     * @Description 用画笔画一张图片（1.画随机颜色的背景色，2.画随机颜色的50条干扰线，3.画4个随机颜色的验证码）
     * @Param [randomCode] 空的字符串，用来接收验证码
     * @return java.awt.image.BufferedImage 返回一张照片
     * @Author 忠哥
     * @Date 2020-1-8 15:12
     */
    public BufferedImage getRandomCodeImage(StringBuffer randomCode){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);//生成图片
        Graphics g = image.getGraphics();//在那张图片上把画笔拿起来
        g.setColor(getRandColor(200, 250));//指定某种颜色让画笔去蘸
        g.fillRect(0, 0, width, height);//画笔使用某种颜色填充了一个矩形（给背景上色），初始坐标+宽度+高度
        g.setColor(getRandColor(110, 120));//画笔蘸另一种颜色
        for (int i = 0; i <= lineNum; i++) {//画50条干扰直线
            drawLine(g);
        }
        g.setFont(new Font(FONT_NAME, Font.ROMAN_BASELINE, FONT_SIZE));//给接下来的绘制的字体设置字体格式（字体名称，风格，大小）
        for (int i = 1; i <= strNum; i++) {
            randomCode.append(drawString(g, i));//把4个字符拼接起来
        }
        g.dispose();//放下画笔
        return image;
    }

    /**
     * @Description 获得随机颜色
     * @Param [fc, bc] fc,bc是随便传回来的两个int参数
     * @return java.awt.Color 返回颜色
     * @Author 忠哥
     * @Date 2020-1-8 13:53
     */
    private Color getRandColor(int fc, int bc){
        if(fc > 255){
            fc = 255;
        }
        if(bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * @Description 绘制干扰线
     * @Param [g] g表示画笔
     * @return void
     * @Author 忠哥
     * @Date 2020-1-8 13:43
     */
    private void drawLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int x0 = random.nextInt(16);
        int y0 = random.nextInt(16);
        g.drawLine(x, y,x + x0, y + y0);//随机两点坐标绘制一条直线
    }

    /**
     * @Description 绘制字符串
     * @Param [g, i] g是画笔，i是循环的第几个字符
     * @return java.lang.String 返回字符
     * @Author 忠哥
     * @Date 2020-1-8 13:50
     */
    private String drawString(Graphics g, int i){
        //画笔上色
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = getRandomString(random.nextInt(RANDOM_STRS.length()));//获取随机字符串
        g.translate(random.nextInt(3), random.nextInt(3));//画笔画的当前位置x+,y+设为坐标原点
        g.drawString(rand, 15 * i, 20);//画第i个字符，x，y表示起笔的坐标，rand表示字符
        return rand;
    }

    /**
     * @Description 根据字符串下标，拿到字符
     * @Param [num] 字符串的下标
     * @return java.lang.String 返回字符
     * @Author 忠哥
     * @Date 2020-1-8 13:39
     */
    private String getRandomString(int num){
        return String.valueOf(RANDOM_STRS.charAt(num));
    }

    public static void main(String[] args) {//此main方法测试生成照片
        CodeUtil tool = new CodeUtil();
        StringBuffer code = new StringBuffer();
        BufferedImage image = tool.getRandomCodeImage(code);
        System.out.println("random code = " + code);
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", new FileOutputStream(new File(
                    "E:/puhuanyu/pugongzi/random-code.jpg")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
