package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Admin;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.AdminRepository;
import com.arash.edu.bujournal.service.listener.AdminEventListener;
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
    private final AdminEventListener adminEventListener;

    public List<Admin> findAll() {
        log.info("Find all admins");
        return adminRepository.findAll();
    }

    public Admin findById(@NonNull UUID id) {
        log.info("Find admin by id [{}]", id);
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin not found by id " + id));
    }

    public Admin addAdmin(@NonNull Admin admin) {
        log.info("Adding admin {}", admin);
        if (admin.getId() == null) {
            admin.setId(UUID.randomUUID());
        }
        Admin created = adminRepository.save(admin);
        adminEventListener.onAdminCreated(created);
        return created;
    }

    public Admin editAdmin(@NonNull UUID id, @NonNull Admin admin) {
        log.info("Editing admin with {}, {}", id, admin);
        if (!adminRepository.existsById(id)) {
            throw new NotFoundException("Admin with id " + id + "not found, unable to edit");
        }
        admin.setId(id);
        return adminRepository.save(admin);
    }

    public void deleteById(@NonNull UUID id) {
        log.info("Deleting admin by id {}", id);
        adminRepository.deleteById(id);
        adminEventListener.onAdminDeleted(id);
    }
}
