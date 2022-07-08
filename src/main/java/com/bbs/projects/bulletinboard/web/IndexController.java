package com.bbs.projects.bulletinboard.web;

import com.bbs.projects.bulletinboard.service.posts.PostsService;
import com.bbs.projects.bulletinboard.web.dto.PostsResponseDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    //첫 페이지 호출 시, index 뷰를 호출
    @GetMapping("/")
    public String index(Model model) {
        //Model 객체에 findAllDesc 결과를 담아 index 뷰에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    //글 등록 화면으로 이동하기 위해 posts-save 뷰를 호출
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    //글 수정 화면으로 이동
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        //Model 객체에 해당 DTO 속성 값을 담아 뷰에 전달
        model.addAttribute("post", dto);

        return "posts-update";
    }
}