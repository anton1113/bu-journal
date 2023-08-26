package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Admin;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.AdminRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public List<Admin> findAll() {
        log.info("Find all admins");
        return adminRepository.findAll();
    }

    public Admin findById(@NonNull UUID id) {
        log.info("Find admin by id [{}]", id);
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin not found by id " + id));
    }
}
