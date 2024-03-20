package com.app.bdt.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "BT_TM_TALENTO")
public class Talent implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_TALENTO")
  private Long id;

  @Column(name = "NO_NOMBRE")
  private String name;

  @Column(name = "AP_APELLIDO_PATERNO")
  private String paternalSurname;

  @Column(name = "AP_APELLIDO_MATERNO")
  private String maternalSurname;

  @Lob
  @Column(name = "IM_IMAGEN")
  private byte[] image;

  @Column(name = "DE_DESCRIPCION")
  private String description;

  @Column(name = "NU_MONTO_INICIAL")
  private Double initialAmount;

  @Column(name = "NU_MONTO_FINAL")
  private Double finalAmount;

  @Column(name = "NU_CELULAR")
  private String cellPhoneNumber;

  @Column(name = "DI_LINKDN")
  private String linkedinLink;

  @Column(name = "DI_GITHUB")
  private String githubLink;

  @Column(name = "FE_CREACION", updatable = false, columnDefinition = "TIMESTAMP")
  @CreationTimestamp
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate creationDate;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "ID_TALENTO")
  private List<SoftSkill> softSkillsList;

  @OneToMany()
  @JoinColumn(name = "ID_TALENTO")
  private List<TechnicalSkill> technicalSkillsList;

  @OneToMany()
  @JoinColumn(name = "ID_TALENTO")
  private List<EducationalExperience> educationalExperiencesList;

  @OneToMany()
  @JoinColumn(name = "ID_TALENTO")
  private List<WorkExperience> workExperiencesList;

  @OneToMany
  @JoinColumn(name = "ID_TALENTO")
  private List<Language> languagesList;

  @OneToMany
  @JoinColumn(name = "ID_TALENTO")
  private List<File> filesList;

  @OneToMany
  @JoinColumn(name = "ID_TALENTO")
  private List<Feedback> feedbacksList;
}