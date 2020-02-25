package com.wjy.space.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

public class FastDfsUtil {
	
	private static TrackerClient trackerClient=null;
	private static TrackerServer trackerServer=null;
	private static StorageServer storageServer=null;
	private static StorageClient1 storageClient=null;
	
	static {
		try {
			ClientGlobal.init("fdfs_client.conf");
			trackerClient=new TrackerClient();
			trackerServer=trackerClient.getTrackerServer();
			storageServer=trackerClient.getStoreStorage(trackerServer);
			storageClient=new StorageClient1(trackerServer,storageServer);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
	}
	
	public static String fdfsUpload(MultipartFile file) {
		try {
			byte[] fileBuff=file.getBytes();
			String fileId="";
			String fileExtName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			fileId=storageClient.upload_file1(fileBuff, fileExtName, null);
			return fileId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public static String fdfsUpload(InputStream inputStream,String fileName) throws IOException, MyException {
		String fileExtName="";
		String savePath="";
		fileExtName=fileName.substring(fileName.lastIndexOf(".")+1);
		ByteArrayOutputStream swapStream=new ByteArrayOutputStream();
		byte[] buff=new byte[1024];
		int len=0;
		while ((len=inputStream.read(buff))!=-1) {
			swapStream.write(buff,0,len);
		}
		byte[] in2b=swapStream.toByteArray();
		String[] strings = storageClient.upload_file(in2b, fileExtName, null);
		for(String str: strings) {
			savePath+="/"+str;
		}
		return savePath;
	}
	
	public static String fdfsUpload(String filePath) throws IOException, MyException {
		String fileExtName="";
		fileExtName=filePath.substring(filePath.lastIndexOf(".")+1);
		String savePath="";
		String[] strings = storageClient.upload_file(filePath, fileExtName, null);
		for(String str:strings) {
			savePath+="/"+str;
		}
		return savePath;
	}
	
	/**
	 * 
	 * 下载文件到目录
	 * @param savePath 文件存储路径
	 * @param localPath 下载路径
	 * @return
	 * @throws MyException 
	 * @throws IOException 
	 */
	public static int fdfsDownload(String savePath,String localPath) throws IOException, MyException {
		String group="";
		String path="";
		int secondindex=savePath.indexOf("/",2);
		group=savePath.substring(1,secondindex);
		path=savePath.substring(secondindex+1);
		int result = storageClient.download_file(group, path,localPath);
		return result;
	}
	
	public static byte[] fdfsDownload(String savePath) throws IOException, MyException {
		byte[] bs=null;
		String group="";
		String path="";
		int secondindex=savePath.indexOf("/",2);
		group=savePath.substring(1,secondindex);
		path=savePath.substring(secondindex+1);
		bs=storageClient.download_file(group, path);
		return bs;
	}
	
	public static byte[] fdfsDownload1(String fileId) throws IOException, MyException {
		return storageClient.download_file1(fileId);
	}
	
	public static int fdfsDeleteFile(String savepath) throws IOException, MyException {
		String group="";
		String path="";
		int secondindex=savepath.indexOf("/",2);
		group=savepath.substring(1,secondindex);
		path=savepath.substring(secondindex+1);
		int result = storageClient.delete_file(group, path);
		return result;
	}
	
	public static int fdfsDeleteFile1(String fileId) throws IOException, MyException {
		return storageClient.delete_file1(fileId);
	}
	
	public static FileInfo fdfsFileInfo(String savePath) throws IOException, MyException {
		String group="";
		String path="";
		int secondindex=savePath.indexOf("/",2);
		group=savePath.substring(1,secondindex);
		path=savePath.substring(secondindex+1);
		FileInfo file_info = storageClient.get_file_info(group, path);
		return file_info;
	}
	

	public static FileInfo fdfsFileInfo1(String fileId) throws IOException, MyException {
		FileInfo file_info1 = storageClient.get_file_info1(fileId);
		return file_info1;
	}
}