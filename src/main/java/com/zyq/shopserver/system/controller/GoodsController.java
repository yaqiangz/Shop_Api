package com.zyq.shopserver.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyq.shopserver.security.constants.SecurityConstants;
import com.zyq.shopserver.system.entity.GoodAttr;
import com.zyq.shopserver.system.entity.GoodPic;
import com.zyq.shopserver.system.entity.Goods;
import com.zyq.shopserver.system.service.GoodsService;
import com.zyq.shopserver.system.service.GoodsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping(SecurityConstants.BASE_URL)
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @GetMapping(value = "/goods", produces = "application/json;charset=utf-8")
    public String getGoodsList(String query, Integer pagenum, Integer pagesize) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        if (pagesize == null) {
            dataMap = null;
            metaMap.put("msg", "pagesize不能为空");
            dataMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else if (pagenum == null) {
            dataMap = null;
            metaMap.put("msg", "pagenum不能为空");
            dataMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            if (query == null)
                query = "";
            System.out.println(query);
            List<Goods> goodsList = goodsService.getGoodsList(query);
            dataMap.put("total", goodsList.size());
            dataMap.put("pagenum", pagenum);
            metaMap.put("msg", "获取成功");
            metaMap.put("status", HttpServletResponse.SC_OK);
            int start = (pagenum - 1) * pagesize;
            List<Goods> resultList = new ArrayList<>();
            for (int i = start; i < goodsList.size() && i < start + pagesize; i++) {
                resultList.add(goodsList.get(i));
            }
            dataMap.put("data", resultList);
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }

    @PostMapping(value = "/goods", produces = "application/json;charset=utf-8")
    public Map<String, Object> addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Goods goods = objectMapper.readValue(request.getInputStream(), Goods.class);
        if (goods.getGoods_name() == null) {
            metaMap.put("msg", "商品名称不能为空");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
            resultMap.put("data", null);
        } else {
            Goods goodsByName = goodsService.getGoodsByName(goods.getGoods_name());
            if (goodsByName != null) {
                metaMap.put("msg", "商品已存在");
                metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
                resultMap.put("data", goodsByName);
            } else {
                int time = (int) (new Date().getTime());
                goods.setAdd_time(time);
                goods.setUpd_time(time);
                String goods_cat = goods.getGoods_cat();
                String[] split = goods_cat.split(",");
                goods.setCat_one_id(Integer.parseInt(split[0]));
                goods.setCat_two_id(Integer.parseInt(split[1]));
                goods.setCat_three_id(Integer.parseInt(split[2]));
                goods.setIs_del("0");
                Integer integer = goodsService.insertGoods(goods);
                if (integer == 0) {
                    resultMap.put("data", null);
                    metaMap.put("msg", "添加失败");
                    metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    for (GoodPic pic : goods.getPics()) {
                        String pic1 = pic.getPic();
                        int i = pic1.lastIndexOf("/");
                        pic.setPics_big(new StringBuffer(pic1).insert(i + 1, "big_").toString());
                        pic.setPics_mid(new StringBuffer(pic1).insert(i + 1, "mid_").toString());
                        pic.setPics_sma(new StringBuffer(pic1).insert(i + 1, "sma_").toString());
                        goodsService.insertPics(pic, goods.getGoods_id());
                    }
                    for (GoodAttr attr : goods.getAttrs()) {
                        attr.setGoods_id(goods.getGoods_id());
                        attr.setCat_id(goods.getCat_three_id());
                        goodsService.insertAttr(attr);
                        goodsService.insertGoodAttr(attr, goods.getGoods_id());
                    }
                    goods = goodsService.getGoodsByName(goods.getGoods_name());
                    resultMap.put("data", goods);
                }
            }
        }
        resultMap.put("meta", metaMap);
        return resultMap;
    }

    @RequestMapping(value = "/upload", produces = "application/json;charset=utf-8")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if (file.isEmpty()) {
            dataMap = null;
            metaMap.put("msg", "文件为空");
            metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                //构建上传目标路径，找到了项目的target的classes目录
                File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
                if (!destFile.exists()) {
                    destFile = new File("");
                }
                //输出目标文件的绝对路径
                System.out.println("file path:"+destFile.getAbsolutePath());
                //拼接子路径
                File upload = new File(destFile.getAbsolutePath(), "/target/classes/static/tmp_uploads/");
                //若目标文件夹不存在，则创建
                if(!upload.exists()) {
                    upload.mkdirs();
                }
                System.out.println("完整的上传路径："+upload.getAbsolutePath()+"/"+file);
                //根据srcFile大小，准备一个字节数组
                byte[] bytes = file.getBytes();
                //拼接上传路径
                //Path path = Paths.get(UPLOAD_FOLDER + srcFile.getOriginalFilename());
                //通过项目路径，拼接上传路径
                // 获得文件原始名称
                String fileName = file.getOriginalFilename();
                // 获得文件后缀名称
                String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                // 生成最新的uuid文件名称
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String newFileName = uuid + "."+ suffixName;
                Path path = Paths.get(upload.getAbsolutePath()+"/"+newFileName);
                //** 开始将源文件写入目标地址
                Files.write(path, bytes);
                upload = new File(destFile.getAbsolutePath(), "/src/main/resources/static/tmp_uploads/");
                //若目标文件夹不存在，则创建
                if(!upload.exists()) {
                    upload.mkdirs();
                }
                path = Paths.get(upload.getAbsolutePath()+"/"+newFileName);
                //** 开始将源文件写入目标地址
                Files.write(path, bytes);
                dataMap.put("tmp_path", "tmp_uploads/" + newFileName);
                dataMap.put("url", SecurityConstants.SERVER_AND_PORT + "tmp_uploads/" + newFileName);
                metaMap.put("msg", "上传成功");
                metaMap.put("status", HttpServletResponse.SC_OK);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        resultMap.put("data", dataMap);
        resultMap.put("meta", metaMap);
        return JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
    }
}
