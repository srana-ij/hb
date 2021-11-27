package com.bz.hb.model.security;

/**
 * created by srana on 07/10/20 at 10.41 AM
 */

import com.bz.hb.model.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
@Entity
@Table(name = "BG_ROLE")
public class Role extends BaseEntity {
    @Column(name = "ROLE")
    private String role;

}
