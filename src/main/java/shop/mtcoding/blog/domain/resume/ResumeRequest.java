package shop.mtcoding.blog.domain.resume;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.domain.jobs.JobsRequest;
import shop.mtcoding.blog.domain.skill.Skill;
import shop.mtcoding.blog.domain.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeRequest {

    @Data
    public static class UpdateDTO {

        private Integer id;

        @Size(max = 10, message = "제목은 10글자를 넘길 수 없습니다.")
        @NotEmpty(message = "제목을 작성하여 주십시오.")
        private String title;

        @NotEmpty(message = "근무 지역을 선택하여 주십시오.")
        private String area;

        @NotEmpty(message = "학력사항을 선택하여 주십시오.")
        private String edu;

        @NotEmpty(message = "경력사항을 선택하여 주십시오.")
        private String career;

        @Size(max = 100, message = "내용은 100글자 이내로 작성하여 주십시오")
        @NotEmpty(message = "내용을 작성하여 주십시오.")
        private String content;

        private LocalDate deadLine;

        @NotEmpty(message = "업무를 선택하여 주십시오.")
        private String task;

        @Size(max = 100, message = "내용은 100글자 이내로 작성하여 주십시오")
        @NotEmpty(message = "내용을 작성하여 주십시오.")
        private String introduce;

        @Size(max = 100, message = "주소는 100글자 이내로 작성하여 주십시오")
        @NotEmpty(message = "주소를 작성하여 주십시오.")
        private String portLink;

        @NotEmpty(message = "필요기술을 선택하여 주십시오.")
        private List<Skill> skillList;


        @NotEmpty(message = "필요기술을 선택하여 주십시오.")
        private List<SkillDTO> skill;

        @Data
        public static class SkillDTO {
            private Integer resume;
            private String name;
            private Integer role;

            public SkillDTO(Integer resume, String name, Integer role) {
                this.resume = resume;
                this.name = name;
                this.role = role;
            }

            public Skill toEntity(Resume resume) {
                return Skill.builder()
                        .name(name)
                        .role(role)
                        .resume(resume)
                        .build();
            }
        }
    }

    @Data
    public static class SaveDTO{

        @Size(max = 10, message = "제목은 10글자를 넘길 수 없습니다.")
        @NotEmpty(message = "제목을 작성하여 주십시오.")
        private String title;

        @NotEmpty(message = "근무 지역을 선택하여 주십시오.")
        private String area;

        @NotEmpty(message = "학력사항을 선택하여 주십시오.")
        private String edu;

        @NotEmpty(message = "경력사항을 선택하여 주십시오.")
        private String career;

        @Size(max = 100, message = "내용은 100글자 이내로 작성하여 주십시오")
        @NotEmpty(message = "내용을 작성하여 주십시오.")
        private String content;

        private LocalDate deadLine;

        @NotEmpty(message = "업무를 선택하여 주십시오.")
        private String task;

        @Size(max = 100, message = "내용은 100글자 이내로 작성하여 주십시오")
        @NotEmpty(message = "내용을 작성하여 주십시오.")
        private String introduce;

        @Size(max = 100, message = "주소는 100글자 이내로 작성하여 주십시오")
        @NotEmpty(message = "주소를 작성하여 주십시오.")
        private String portLink;

        @NotEmpty(message = "필요기술을 선택하여 주십시오.")
        private List<Skill> skillList;

        public Resume toEntity(User user){
            return Resume.builder()
                    .title(title)
                    .area(area)
                    .edu(edu)
                    .career(career)
                    .introduce(introduce)
                    .portLink(portLink)
                    .user(user)
                    .skillList(skillList)
                    .build();
        }

        @Data
        public static class WriteSkillDTO {
            private Integer id;
            private String name;
            private Integer role;

            @Builder
            public WriteSkillDTO(Integer id, String name, Integer role) {
                this.id = id;
                this.name = name;
                this.role = role;
            }

            public Skill toEntity(Resume resume, Integer role){
                return Skill.builder()
                        .id(id)
                        .name(name)
                        .role(role)
                        .resume(resume)
                        .build();
            }
        }
    }

    @Data
    public static class UserViewDTO{
        private Integer id;
        private String title;
        private String edu;
        private String area;
        private String career;
        private List<SkillDTO> skills = new ArrayList<>();

        @Builder
        public UserViewDTO(Resume resume, List<Skill> skills) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.area = resume.getArea();
            this.career = resume.getCareer();

            this.skills = skills.stream().map(skill -> new SkillDTO(skill))
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class SkillDTO {
        private Integer id;
        private String name;
        private String color;

        public SkillDTO(Skill skill) {
            this.id = skill.getId();
            this.name = skill.getName();

            // 혹시 언어 추가할게 있으면 else if랑 컬러, 같은 양식 맞춰서 추가가능
            if (this.name.equals("Jquery")) {
                this.color = "badge badge-pill bg-primary";
            } else if (this.name.equals("JavaScript")) {
                this.color = "badge badge-pill bg-secondary";
            } else if (this.name.equals("Spring")) {
                this.color = "badge badge-pill bg-success";
            } else if (this.name.equals("HTML/CSS")) {
                this.color = "badge badge-pill bg-danger";
            } else if (this.name.equals("JSP")) {
                this.color = "badge badge-pill bg-warning";
            } else if (this.name.equals("Java")) {
                this.color = "badge badge-pill bg-info";
            } else if (this.name.equals("React")) {
                this.color = "badge badge-pill bg-dark";
            } else if (this.name.equals("Oracle")) {
                this.color = "badge badge-pill bg-brown";
            } else if (this.name.equals("Vue.js")) {
                this.color = "badge badge-pill bg-indigo";
            } else if (this.name.equals("MySql")) {
                this.color = "badge badge-pill bg-purple";
            }
        }
    }
}