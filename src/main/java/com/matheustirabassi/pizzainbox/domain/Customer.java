package com.matheustirabassi.pizzainbox.domain;

import com.matheustirabassi.pizzainbox.domain.enums.DocumentType;
import com.matheustirabassi.pizzainbox.domain.enums.PermissionStatus;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Principal classe do projeto.
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_customer")
public class Customer extends BaseEntity {

  private String name;
  @Column(unique = true)
  private String email;
  @NonNull
  private String document;
  private Integer documentType;
  private Integer permissionStatus;

  @ElementCollection
  @CollectionTable(name = "tb_cellphone")
  @Fetch(FetchMode.JOIN)
  private Set<String> cellphones = new HashSet<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  @BatchSize(size = 1000)
  List<Address> addresses = new ArrayList<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "customer")
  private List<Order> orders = new ArrayList<>();
  @ToString.Exclude
  @OneToOne(cascade = CascadeType.ALL)
  private Login login;

  public DocumentType getDocumentType() {
    return DocumentType.toEnum(documentType);
  }

  public void setDocumentType(DocumentType documentType) {
    this.documentType = documentType.getCod();
  }

  public PermissionStatus getPermissionStatus() {
    return PermissionStatus.toEnum(permissionStatus);
  }

  public void setPermissionStatus(PermissionStatus permissionStatus) {
    this.permissionStatus = permissionStatus.getCod();
  }

}