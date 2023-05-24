package WebCapstone.WebCapstone.controller;


import WebCapstone.WebCapstone.DTO.ForumDTO;
import WebCapstone.WebCapstone.DTO.NicknameDTO;
import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.SignupDTO;
import WebCapstone.WebCapstone.entity.ForumEntity;
import WebCapstone.WebCapstone.service.ForumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/forum")
public class ReviewController {
    @Autowired
    ForumService forumService;

    @PostMapping("/store")
    public ResponseDTO<?> Reviewcreate(@Valid @RequestBody ForumDTO requestBody, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {//데이터검증
            System.out.println("Form data has some errors");
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error:errors) {
                System.out.println(error.getDefaultMessage());
            }
            return ResponseDTO.setFailed("올바르게 정보를 입력해주세요");
        }
        ResponseDTO<?> result = forumService.Store(requestBody);
        return result;

    }

    @PostMapping("/getreview")
    public List<ForumEntity> getReview(@RequestBody NicknameDTO nicknameDTO){
        return forumService.getAllReview(nicknameDTO);
    }

}