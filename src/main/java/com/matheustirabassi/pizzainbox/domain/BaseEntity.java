package com.matheustirabassi.pizzainbox.domain;


import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;

  protected BaseEntity(@NonNull Date creationDate, Date modificationDate) {
    this.creationDate = creationDate;
    this.modificationDate = modificationDate;
  }

  @PrePersist
  @PreUpdate
  public void configureDateCreationAndModification() {
    this.modificationDate = new Date();
    if (this.creationDate == null) {
      this.creationDate = new Date();
    }
  }

}