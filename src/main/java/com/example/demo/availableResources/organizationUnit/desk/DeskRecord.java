package com.example.demo.availableResources.organizationUnit.desk;


import com.example.demo.availableResources.organizationUnit.OrganizationUnit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "Desk")
@Entity
public class DeskRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean isAvailable;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_unit_id", referencedColumnName = "id")
    private OrganizationUnit organizationUnit;
}

