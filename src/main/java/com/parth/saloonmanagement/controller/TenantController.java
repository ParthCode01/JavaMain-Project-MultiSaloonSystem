package com.parth.saloonmanagement.controller;

import com.parth.saloonmanagement.dto.TenantSummaryResponse;
import com.parth.saloonmanagement.service.TenantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/tenants")
public class TenantController {


    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }


    @GetMapping
    public List<TenantSummaryResponse> getTenantSummary(){
        return tenantService.getTenantSummary();
    }
}
