package com.isfive.usearth.web.project;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.project.dto.*;
import com.isfive.usearth.domain.project.service.ProjectService;
import com.isfive.usearth.web.project.dto.ProjectModify;
import com.isfive.usearth.web.project.dto.ProjectRegister;
import com.isfive.usearth.web.project.dto.RewardRegister;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

	private final ProjectService projectService;
	private final FileImageService fileImageService;

	@PostMapping
	public ResponseEntity<ProjectResponse> createProject(
			Authentication auth,
			@RequestPart @Valid ProjectRegister projectRegister,           // 프로젝트 정보
			@RequestPart MultipartFile repImage,                    // 대표 이미지
			@RequestPart List<MultipartFile> projectImageList,      // 프로젝트 첨부 이미지 리스트
			@RequestPart @Valid List<RewardRegister> rewardRegisterList    // 리워드 정보 리스트
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

	@GetMapping
	public ResponseEntity<Page<ProjectsResponse>> findProjects(Authentication auth, @RequestParam(defaultValue = "1") Integer page) {
		String username = auth == null ? "" : auth.getName();
		Page<ProjectsResponse> projectsResponses = projectService.readProjects(page, username);
		return new ResponseEntity<>(projectsResponses, HttpStatus.OK);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<ProjectResponse> findProject(Authentication auth, @PathVariable Long projectId) {
		String username = auth == null ? "" : auth.getName();
		ProjectResponse response = projectService.readProject(projectId, username);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping("/{projectId}")
	public ResponseEntity<ProjectResponse> updateProject(
		Authentication auth,
		@PathVariable Long projectId,
		@RequestPart @Valid ProjectModify projectModify,
		@RequestPart MultipartFile repImage,
		@RequestPart List<MultipartFile> projectImageList
	) {
		FileImage fileImage = fileImageService.createFileImage(repImage);
		List<FileImage> fileImageList = fileImageService.createFileImageList(projectImageList);
		ProjectUpdate projectUpdate = projectModify.toService(fileImage);
		ProjectResponse projectResponse = projectService.updateProject(auth.getName(), projectId, projectUpdate,
			fileImageList);
		return new ResponseEntity<>(projectResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<Void> deleteProject(Authentication auth, @PathVariable Long projectId) {
		projectService.deleteProject(auth.getName(), projectId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/{projectId}/like")
	public ResponseEntity<Void> like(Authentication auth, @PathVariable Long projectId) {
		projectService.like(projectId, auth.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
