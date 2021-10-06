package com.example.demo.availableResources.desk;


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

    @ManyToOne
    @JoinColumn(name = "organization_unit_id",nullable = false)
    private OrganizationUnit organizationUnit;
}

