package com.b_cube.website.domain.designton.api;

import com.b_cube.website.domain.designton.dto.DesigntonDTO;
import com.b_cube.website.domain.designton.service.DesigntonService;
import com.b_cube.website.global.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "DesigntonController", description = "디자인톤 컨트롤러")
@RestController
@RequestMapping("/api/designton")
@RequiredArgsConstructor
public class DesigntonController {

    private final DesigntonService designtonService;

    @Operation(summary = "디자인톤 목록 조회")
    @GetMapping
    public ResponseEntity<List<DesigntonDTO>> getDesignton() {
        List<DesigntonDTO> designtons = designtonService.getDesignton();
        return ResponseEntity.ok(designtons);
    }

    @Operation(summary = "디자인톤 목록 추가", description = "form-data 형식으로 진행해야 함")
    @PostMapping
    public ResponseEntity<BaseResponse> addDesignton(
            @Parameter(description = "프로젝트 이름(문자열)")
            @RequestParam("title") String title,
            @Parameter(description = "프로젝트 진행 년도(문자열)")
            @RequestParam("year") String year,
            @Parameter(description = "프로젝트 참여자 이름(문자열)")
            @RequestParam("participant") String participant,
            @Parameter(description = "MultipartFile 이미지 삽입")
            @RequestParam("imagePath") MultipartFile imagePath,
            @Parameter(description = "MultipartFile pdf 삽입")
            @RequestParam("pdfPath")  MultipartFile pdfPath

    ) throws IOException {
        BaseResponse baseResponse = designtonService.addDesignton(title, year, participant, imagePath, pdfPath);
        return ResponseEntity.ok(baseResponse);
    }

    @Operation(summary = "디자인톤 목록 수정", description = "form-data 형식으로 진행해야 함")
    @PatchMapping("/{id}")
    public ResponseEntity<DesigntonDTO> updateDesignton(
            @PathVariable Long id,
            @Parameter(description = "프로젝트 이름(문자열)")
            @RequestParam("title") String title,
            @Parameter(description = "프로젝트 진행 년도(문자열)")
            @RequestParam("year") String year,
            @Parameter(description = "프로젝트 참여자 이름(문자열)")
            @RequestParam("participant") String participant,
            @Parameter(description = "MultipartFile 이미지 삽입")
            @RequestParam("imagePath") MultipartFile imagePath,
            @Parameter(description = "MultipartFile pdf 삽입")
            @RequestParam("pdfPath")  MultipartFile pdfPath

    ) throws IOException {
        DesigntonDTO designton = designtonService.updateDesignton(id, title, year, participant, imagePath, pdfPath);
        return ResponseEntity.ok(designton);
    }

    @Operation(summary = "디자인톤 목록 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteDesignton(
            @Parameter(description = "디자인톤 id 값")
            @PathVariable Long id
    ) {
        BaseResponse baseResponse = designtonService.deleteDesignton(id);
        return ResponseEntity.ok(baseResponse);
    }
}
