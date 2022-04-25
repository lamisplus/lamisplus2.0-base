package org.lamisplus.modules.base.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.base.domain.entity.Menu;
import org.lamisplus.modules.base.domain.entity.Module;
import org.lamisplus.modules.base.domain.enumeration.MenuLevel;
import org.lamisplus.modules.base.domain.enumeration.MenuType;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
@Slf4j
public final class MenuDTO {

    private Long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    private String state;

    private String level = "LEVEL_1";

    private Integer position = 1;

    private String icon;

    private String tooltip;

    private String breadcrumb;

    private String url;

    private Boolean disabled = false;

    private Set<String> authorities = new HashSet<>();

    private Integer parentId;

    private String parentName;

    //@NotNull(message = "moduleId is mandatory")
    private Long moduleId;

    private String type;

    private Set<Menu> subs = new TreeSet<>();

    /*private Menu parent;*/
}
