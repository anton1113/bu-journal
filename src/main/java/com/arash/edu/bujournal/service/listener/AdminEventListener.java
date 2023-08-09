package com.arash.edu.bujournal.service.listener;

import com.arash.edu.bujournal.domain.Admin;
import com.arash.edu.bujournal.service.auth.BuUserRegisterService;
import com.arash.edu.bujournal.service.auth.BuUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AdminEventListener {

    private final BuUserRegisterService buUserRegisterService;
    private final BuUserService buUserService;

    public void onAdminDeleted(@NonNull UUID adminId) {
        log.info("Received admin_deleted event, adminId={}", adminId);
        buUserService.deleteUserByExternalId(adminId);
    }

    public void onAdminCreated(@NonNull Admin admin) {
        log.info("Received admin_created event, {}", admin);
        buUserRegisterService.registerAdmin(admin);
    }
}
