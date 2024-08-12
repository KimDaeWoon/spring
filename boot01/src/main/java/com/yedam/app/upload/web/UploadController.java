package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j // spring boot 불러 오는 logo
@Controller
public class UploadController {

	// properties 처리 방식
	@Value("${file.upload.path}")
	private String uploadPath;

	// 파일 찾기
	@GetMapping("formUpload")
	public void formUploadPage() {
	}

	// 파일 처리 input name 이 MultipartFile 매개변수가 되어야 한다
	@PostMapping("uploadForm")
	public String formUploadFile(@RequestPart MultipartFile[] files) { // MultipartFile[] input 에서 multiple 으로 처리 하면 배열로
																		// 넘어 오기에 배열 처리를 해줘야 된다
																		// client 가 보낸 data 처리
																		// multipartResolver 라은 Bean 이 있어야 한다
		log.info(uploadPath);
		for (MultipartFile file : files) {
			log.info(file.getContentType()); // 타입
			log.info(file.getOriginalFilename()); // 이름
			log.info(String.valueOf(file.getSize())); // 사이즈

			String fileName = file.getOriginalFilename();
			String saveName = uploadPath + File.separator + fileName; // 경로 작성

			log.debug("saveName :" + saveName);

			Path savePath = Paths.get(saveName); // Path 경로 처리

			try {
				file.transferTo(savePath); // 지정한 경로 이동 시키는 역할
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return "redirect:formUpload";
	}

	// --------------------------------
	// AJAX 기반 파일 업로드 형식
	// --------------------------------

	@GetMapping("upload")
	public void uploadPage() {
	}

	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {

		List<String> imageList = new ArrayList<>();

		for (MultipartFile uploadFile : uploadFiles) {
			// getContentType 이미지 파일으로 올리는 것으로 제한 한다
			if (uploadFile.getContentType().startsWith("image") == false) {
				System.err.println("this file is not image type");
				return null;
			}

			String fileName = uploadFile.getOriginalFilename();

			System.out.println("fileName : " + fileName);

			// 파일 업로드 시점에서 구분 하기위해 처리
			// 날짜 폴더 생성
			String folderPath = makeFolder();
			// UUID 중복되지 않는 랜덤값 생성 한다
			String uuid = UUID.randomUUID().toString();
			// 저장할 파일 이름 중간에 "_"를 이용하여 구분

			String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;

			String saveName = uploadPath + File.separator + uploadFileName;

			Path savePath = Paths.get(saveName);
			// Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
			System.out.println("path : " + saveName);
			try {
				uploadFile.transferTo(savePath);
				// uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
			} catch (IOException e) {
				e.printStackTrace();
			}
			// DB에 해당 경로 저장
			// 1) 사용자가 업로드할 때 사용한 파일명
			// 2) 실제 서버에 업로드할 때 사용한 경로
			imageList.add(setImagePath(uploadFileName));
		}

		return imageList;
	}

	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}

	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}
