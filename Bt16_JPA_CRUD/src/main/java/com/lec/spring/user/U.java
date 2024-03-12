package com.lec.spring.user;


import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class U {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attrs.getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static User getLoggedUser() {

        PrincipalDetails userDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return user;
    }

    public static void printFileInfo(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();   //파일 원본 변수

        if(originalFileName == null || originalFileName.length() == 0) {
            System.out.println("\t파일이 없습니다.");
            return;
        }

        System.out.println("\tOriginal File Name : " + originalFileName);
        System.out.println("\tCleanPath : " + StringUtils.cleanPath(originalFileName));
        System.out.println("\tFile Size : " + file.getSize() + " bytes");  // 용량 (byte)
        System.out.println("\tMIME: " + file.getContentType());  // content type (mime type)


        // 이미지 파일 여부
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(file.getInputStream());

            if(bufferedImage != null){
                System.out.printf("\t이미지 파일입니다: %d x %d\n", bufferedImage.getWidth(), bufferedImage.getHeight());
            } else {
                System.out.println("\t이미지 파일이 아닙니다");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
