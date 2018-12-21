package com.gyumaru.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

public class UploadFileUtil {
	
	/**
	 * 上传文件
	 * */
    public static String uploadFile1(MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException{
        try {
                //文件的原始名称
                String originalFilename=multipartFile.getOriginalFilename();
                String newFileName=null;
                if (multipartFile!=null&&originalFilename!=null&&originalFilename.length()>0){

                    newFileName= UUID.randomUUID().toString().replace("-", "")+originalFilename.substring(originalFilename.lastIndexOf("."));
                    //存储图片的物理路径
                    System.out.println(originalFilename.substring(originalFilename.lastIndexOf(".")));
//                    String pic_path = request.getContextPath()+"/webapp/uploadfiles";
                    String pic_path = //srequest.getRequestURI()
//                    request.getContextPath()+"/uploadfiles";
                    		request.getSession().getServletContext().getRealPath("uploadfiles");
                    if(!new File(pic_path).exists()){
                    	   new File(pic_path).mkdir();
                    	}
                    System.out.println(pic_path);
                    //新图片路径
                    File targetFile = new File(pic_path, newFileName);
                    //内存数据读入磁盘
                    multipartFile.transferTo(targetFile);
                }
                return "uploadfiles/"+newFileName;
        }
        catch (IOException e){
            e.printStackTrace();
            return "error";
        }
    }
	
	/**
	 * 上传文件
	 * */
    public static String uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException{
        try {
                //文件的原始名称
                String originalFilename=multipartFile.getOriginalFilename();
                String newFileName=null;
                if (multipartFile!=null&&originalFilename!=null&&originalFilename.length()>0){
                    newFileName=originalFilename.substring(0, originalFilename.lastIndexOf("."))+ new Date().getTime()+originalFilename.substring(originalFilename.lastIndexOf("."));
                    //存储图片的物理路径
                    System.out.println(originalFilename.substring(originalFilename.lastIndexOf(".")));
                    String new_path =request.getSession().getServletContext().getRealPath("uploadfiles");
                    if(!new File(new_path).exists()){
                    	   new File(new_path).mkdir();
                    	}
                    System.out.println(new_path);
                    //新文件路径
                    File targetFile = new File(new_path, newFileName);
                    //内存数据读入磁盘
                    multipartFile.transferTo(targetFile);
                }
                return newFileName;
        }
        catch (IOException e){
            e.printStackTrace();
            return "error";
        }
    }
    
    /**
     * 压缩图片 0.4倍等比缩放
     * 压缩完成后保存压缩后的图片，原文件删除
     * */
    public static String compressImage(MultipartFile multipartFile, HttpServletRequest request,String path){
    	if(multipartFile!=null){
    	 String originalFilename=multipartFile.getOriginalFilename();
    	 String newFileName=UUID.randomUUID().toString().replace("-", "")+originalFilename.substring(originalFilename.lastIndexOf("."));
    	 String pic_path=request.getSession().getServletContext().getRealPath(path);
    	 if(!new File(pic_path).exists()){
    		 new File(pic_path).mkdir();
    	 }
    	 File targetfile=new File(pic_path,newFileName);
    	 try {
			multipartFile.transferTo(targetfile);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
    	 //压缩图片
    	 String thumbFilename=UUID.randomUUID().toString().replace("-", "")+suffix;
    	 
    	 if(thumbFilename.contains(".png")){
    		 thumbFilename=thumbFilename.replace(".png", ".jpg");
    	 }
    	 long size = multipartFile.getSize();
    	 
    		 try {
    			 if(size<200*1024){
				Thumbnails.of(pic_path+"/"+newFileName).scale(0.4f).outputQuality(0.8).outputFormat("jpg").toFile(pic_path+"/"+thumbFilename);
    			 }else{
    			Thumbnails.of(pic_path+"/"+newFileName).scale(0.4f).outputQuality(0.8).outputFormat("jpg").toFile(pic_path+"/"+thumbFilename);
    			 }
			} catch (IOException e) {
				e.printStackTrace();
			}
       		 
    		File delfile=new File(pic_path+"/"+newFileName);
    		if(!delfile.exists())
    			System.out.println("文件不存在");
    		if(delfile.delete()){
    			System.out.println("文件删除成功");
    		}else{
    			System.out.println("文件删除失败");
    		}
    	 return "uploadfiles/"+thumbFilename;
    	}
    	return null;
    }  
}
