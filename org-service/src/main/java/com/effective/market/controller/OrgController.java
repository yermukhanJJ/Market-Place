package com.effective.market.controller;

import com.effective.market.dto.OrgCreateDto;
import com.effective.market.dto.OrgDto;
import com.effective.market.entity.Organization;
import com.effective.market.service.OrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * REST controller для операций с организациями
 *
 * @author YermukhanJJ
 */
@RestController
@RequestMapping("/api/org")
@RequiredArgsConstructor
public class OrgController {

    private final OrgService orgService;

    /**
     * @param orgCreateDto
     * @param principal    <b>Response code</b>: 201<br>
     */
    @PostMapping("/request")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createOrg(@RequestBody @Valid OrgCreateDto orgCreateDto, Principal principal) {
        orgService.createOrgRequest(orgCreateDto, principal);
    }

    /**
     * @param principal
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<OrgDto>}
     */
    @GetMapping("/myorg")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<OrgDto>> getUserOrg(Principal principal) {
        return ResponseEntity.ok(orgService.getMyOrgs(principal));
    }

    /**
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<Organization>}
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Organization>> getAllOrg() {
        return ResponseEntity.ok(orgService.getAllOrg());
    }

    /**
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<Organization>}
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/approved")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Organization>> getAllEnabledOrg() {
        return ResponseEntity.ok(orgService.getAllEnabledOrg());
    }

    /**
     * @param id <b>Response code</b>: 202<br>
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/approve/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void approve(@PathVariable(value = "id", required = false) Long id) {
        orgService.approve(id);
    }

    /**
     * @param id <b>Response code</b>: 204<br>
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        orgService.delOrg(id);
    }

    /**
     * @param id <b>Response code</b>: 204<br>
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/freeze/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void freeze(@PathVariable(value = "id") Long id) {
        orgService.freezeOrg(id);
    }
}
