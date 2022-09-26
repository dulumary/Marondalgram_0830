package com.marondal.marondalgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public static final String FILE_UPLOAD_PATH = "D:\\김인규쌤\\web_0823\\spring\\marondalgram\\upload";
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
	public static String saveFile(int userId, MultipartFile file) {
		
		// 서버에 저장할 위치를 잡아준다. 
		// 디렉토리 (폴더) 생성
		// 사용자 id + 현재 시간
		// UNIX TIME : 1970년 1월1일을 기준으로 현재까지 흐른 시간 (millisecond 1/1000)
		// 1_45651445145
		// D:\\김인규쌤\\web_0823\\spring\\marondalgram\\upload\\1_45651445145\\asdf.jpg
		
		// /1_2131211232/
		String directoryName = "/" + userId + "_" + System.currentTimeMillis() + "/";
		
		// 디렉토리 생성
		String filePath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			// 디렉토리 생성 실패!
			// 디렉토리 생성 실패 로그 추가 
			logger.error("FileManagerService - saveFile : file 디렉토리 생성 에러 " + filePath);
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			
			String fileFullPath = filePath + file.getOriginalFilename();
			Path path = Paths.get(fileFullPath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			
			// 파일 저장 실패
			logger.error("FileManagerService - saveFile : 파일 저장 실패");
			return null;
		}
		
		// 클라이언트에서 접근 가능한 경로 
		// D:\\김인규쌤\\web_0823\\spring\\marondalgram\\upload 해당 디렉토리 아래 경로
		// /images/~
		
		return "/images" + directoryName + file.getOriginalFilename();
		
	}
	
	
	
	// 파일 삭제 기능
	public static boolean removeFile(String filePath) { // /images/2_90812098510/test.png
		
		// 삭제 경로는 /images 를 제거 하고,
		// 실제 파일 저장 경로룰 이어 붙여주면 된다. 
		// D:\\김인규쌤\\web_0823\\spring\\memo\\upload/2_90812098510/test.png
		
		if(filePath == null) {
			return true;
		}
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(realFilePath);
		// 파일이 있는지 확인
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				
				e.printStackTrace();
				return false;
			}
		}
		
//		D:\\김인규쌤\\web_0823\\spring\\memo\\upload/2_90812098510
		
		path = path.getParent();
		
		// 디렉토리 존재하는
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				
				e.printStackTrace();
				
				return false;
			}
		}
		
		return true;
		
	}

}
