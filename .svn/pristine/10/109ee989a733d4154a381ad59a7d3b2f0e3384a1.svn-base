package com.ylife.utils;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;

public class BASE64DecodedMultipartFile implements MultipartFile {
	private final byte[] imgContent;
	//后缀名
	private String fileNameSuffix="";

	public BASE64DecodedMultipartFile(String imgContent) throws IOException {
		// 获取后缀名   base64的图片信息
		String [] fileName = imgContent.split(",");
		fileNameSuffix = fileName[0].split("/")[1].split(";")[0];
		
		@SuppressWarnings("restriction")
		BASE64Decoder decoder = new BASE64Decoder();
		//Base64解码  
        @SuppressWarnings("restriction")
		byte[] b = decoder.decodeBuffer(fileName[1]);
        for(int i=0;i<b.length;++i)
        {  
            if(b[i]<0)  
            {//调整异常数据  
                b[i]+=256;
            }
        }
		this.imgContent = b;
	}

	@Override
	public String getName() {
		// TODO - implementation depends on your requirements
		return "1429770517905."+fileNameSuffix;
	}

	@Override
	public String getOriginalFilename() {
		// TODO - implementation depends on your requirements
		return "1429770517905."+fileNameSuffix;
	}

	@Override
	public String getContentType() {
		// TODO - implementation depends on your requirements
		return null;
	}

	@Override
	public boolean isEmpty() {
		return imgContent != null && imgContent.length > 0;
	}

	@Override
	public long getSize() {
		return imgContent.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return imgContent;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(imgContent);
	}

	@SuppressWarnings("resource")
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		new FileOutputStream(dest).write(imgContent);
	}
}
