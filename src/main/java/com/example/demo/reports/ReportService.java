package com.example.demo.reports;

import com.example.demo.reservation.Reservation;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final AppUserRepository userRepository;

    public void addReport(Reservation reservation, AppUser user) {
        Report report = new Report(reservation, user);
        sendWarningToUser(user);
        reportRepository.save(report);

    }


    public void addReport(Reservation reservation) {
        Report report = new Report(reservation);
        reportRepository.save(report);
    }

    private void sendWarningToUser(AppUser user) {

        user.setKarma(user.getKarma() + 1);
        userRepository.updateKarma(user.getEmail(), user.getKarma());
        if (user.getKarma() == 3) {
            user.setUnlocked(false);
            userRepository.lockAppUser(user.getEmail());

        }
    }
}
