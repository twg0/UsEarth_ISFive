package com.isfive.usearth.web.project;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.project.dto.ProjectCreate;
import com.isfive.usearth.domain.project.dto.ProjectResponse;
import com.isfive.usearth.domain.project.dto.ProjectUpdate;
import com.isfive.usearth.domain.project.dto.ProjectsResponse;
import com.isfive.usearth.domain.project.dto.RewardCreate;
import com.isfive.usearth.domain.project.service.ProjectService;
import com.isfive.usearth.web.project.dto.ProjectModify;
import com.isfive.usearth.web.project.dto.ProjectRegister;
import com.isfive.usearth.web.project.dto.RewardRegister;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@Tag(name = "3. Project", description = "Project API")
public class ProjectController {

	private final ProjectService projectService;
	private final FileImageService fileImageService;

	@Operation(summary = "프로젝트 등록")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProjectResponse> createProject(
			Authentication auth,
			@RequestPart("projectRegister") @Valid ProjectRegister projectRegister,           // 프로젝트 정보
			@RequestPart("repImage") MultipartFile repImage,                    // 대표 이미지
			@RequestPart("projectImageList") List<MultipartFile> projectImageList,      // 프로젝트 첨부 이미지 리스트
			@RequestPart("rewardRegisterList") @Valid List<RewardRegister> rewardRegisterList    // 리워드 정보 리스트
	) {
		FileImage fileImage = fileImageService.createFileImage(repImage);
		List<FileImage> fileImageList = fileImageService.createFileImageList(projectImageList);
		List<RewardCreate> rewardCreateList = rewardRegisterList.stream()
			.map(RewardRegister::toService).collect(Collectors.toList());
		ProjectCreate projectCreate = projectRegister.toService(fileImage);
		ProjectResponse projectResponse = projectService.createProject(auth.getName(), projectCreate, rewardCreateList,
			fileImageList);

		return new ResponseEntity<>(projectResponse, HttpStatus.CREATED);
	}

	@Operation(summary = "프로젝트 목록 조회")
	@GetMapping
	public ResponseEntity<Page<ProjectsResponse>> findProjects(Authentication auth, @RequestParam(defaultValue = "1") Integer page) {
		String username = auth == null ? "" : auth.getName();
		Page<ProjectsResponse> projectsResponses = projectService.readProjects(page, username);
		return new ResponseEntity<>(projectsResponses, HttpStatus.OK);
	}

	@Operation(summary = "프로젝트 단일 조회")
	@GetMapping("/{projectId}")
	public ResponseEntity<ProjectResponse> findProject(Authentication auth, @PathVariable("projectId") Long projectId) {
		String username = auth == null ? "" : auth.getName();
		ProjectResponse response = projectService.readProject(projectId, username);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "프로젝트 수정")
	@PatchMapping(path = "/{projectId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProjectResponse> updateProject(
		Authentication auth,
		@PathVariable("projectId") Long projectId,
		@RequestPart("projectModify") @Valid ProjectModify projectModify,
		@RequestPart("repImage") MultipartFile repImage,
		@RequestPart("projectImageList") List<MultipartFile> projectImageList
	) {
		FileImage fileImage = fileImageService.createFileImage(repImage);
		List<FileImage> fileImageList = fileImageService.createFileImageList(projectImageList);
		ProjectUpdate projectUpdate = projectModify.toService(fileImage);
		ProjectResponse projectResponse = projectService.updateProject(auth.getName(), projectId, projectUpdate,
			fileImageList);
		return new ResponseEntity<>(projectResponse, HttpStatus.OK);
	}

	@Operation(summary = "프로젝트 삭제")
	@DeleteMapping("/{projectId}")
	public ResponseEntity<Void> deleteProject(Authentication auth, @PathVariable("projectId") Long projectId) {
		projectService.deleteProject(auth.getName(), projectId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "프로젝트 좋아요 기능")
	@PostMapping("/{projectId}/like")
	public ResponseEntity<Void> like(Authentication auth, @PathVariable("projectId") Long projectId) {
		projectService.like(projectId, auth.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
