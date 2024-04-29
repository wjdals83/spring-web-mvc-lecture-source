package com.ohgiraffers.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,       // requestParam 요청 시에 들어온 파일과 설명.
                                   @RequestParam String singleFileDescription,
                                   Model model) throws IOException {                                // 최종적으로 모델에 넣어 뷰에 사용하게끔 model 을 만들어줌

        System.out.println("singleFile = " + singleFile);
        System.out.println("singleFileDescription = " + singleFileDescription);

        /* 필기. 파일을 저장할 경로 설정 */
        Resource resource = resourceLoader.getResource("classpath:static/img/single");

        String filePath = null;

        if(!resource.exists()) {    // 저장할 경로가 존재 않는 경우
            /* 만약 static 폴더에 파일이 없는 경우 만들어준다. */
            String root = "src/main/resources/static/img/single";
            File file = new File(root);     //자동으로 static 하위 경로에 넣어주도록 만드는 중

            file.mkdirs();                   // mkdir 폴더를 만들어 줌

            filePath = file.getAbsolutePath();                   // 절대경로
        } else {            // 경로가 존재한다면
            filePath = resourceLoader.getResource("classpath:static/img/single").getFile().getAbsolutePath();       // 파일에 대한 얘외처리 반드시
        }

        /* 필기. 파일명 변경 처리 */
        String originFileName = singleFile.getOriginalFilename();
        System.out.println("originFileName = " + originFileName);

        /* 확장자 제거 */
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);

        String savedName = UUID.randomUUID().toString().replace("-", "") + ext;        // 스트링 타입이 아니므로 스트링 타입으로 바꿔주기. 식별 가능하게끔 문자열을 자동으로 만드렁줌.
        System.out.println("savedName = " + savedName);

        /* 파일을 저장 */
        singleFile.transferTo(new File(filePath + "/" + savedName));
        model.addAttribute("message", "파일 업로드 성공!!!!");
        model.addAttribute("img", "static/img/single/" + savedName);

        return "result";             // 오류 안나게끔 일단 / 로 잡아놓기

    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles,
                                   @RequestParam String multiFileDescription,
                                   Model model) throws IOException {

        System.out.println("multipartFiles = " + multiFiles);
        System.out.println("multiFileDescription = " + multiFileDescription);

        Resource resource = resourceLoader.getResource("classpath:static/img/multi");
        String filePath = null;;

        if(!resource.exists()) {

            String root = "src/main/resources/static/img/multi";
            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            filePath = resourceLoader.getResource("classpath:static/img/multi").getFile().getAbsolutePath();
        }

        System.out.println("filePath = " + filePath);

        List<FileDTO> files = new ArrayList<>();
        List<String> saveFiles = new ArrayList<>();

        for(MultipartFile file : multiFiles) {
            /* 파일명 변경 처리 */
            String originFileName = file.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            files.add(new FileDTO(originFileName, savedName, filePath, multiFileDescription));

            file.transferTo(new File(filePath + "/" + savedName));
            saveFiles.add("static/img/multi/" + savedName);
        }

        model.addAttribute("message", "파일 업로드 성공!!!!!!");
        model.addAttribute("imgs", saveFiles);

        return "result";

    }


}
