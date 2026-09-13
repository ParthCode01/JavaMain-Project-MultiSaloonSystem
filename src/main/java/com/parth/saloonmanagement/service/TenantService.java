package com.parth.saloonmanagement.service;

import com.parth.saloonmanagement.dto.TenantSummaryResponse;
import com.parth.saloonmanagement.entity.Tenant;
import com.parth.saloonmanagement.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenantService {

    private  final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public List<TenantSummaryResponse> getTenantSummary(){
        List<Tenant> tenants =  tenantRepository.findAll();

        List<TenantSummaryResponse> responses=new ArrayList<>();

        for(Tenant tenant: tenants){
            TenantSummaryResponse response = new TenantSummaryResponse(
                    tenant.getId(),
                    tenant.getName(),
                    tenant.getAddress()
            );

            responses.add(response);
        }

        return responses;
    }
}
