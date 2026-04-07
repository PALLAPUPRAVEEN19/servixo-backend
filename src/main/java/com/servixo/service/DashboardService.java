package com.servixo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servixo.dto.DashboardStatsDTO;
import com.servixo.repository.BookingRepository;
import com.servixo.repository.UserRepository;

@Service
public class DashboardService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    public DashboardStatsDTO getStats(Long userId, String role) {

        DashboardStatsDTO dto = new DashboardStatsDTO();

        if (role.equalsIgnoreCase("USER")) {

            dto.setTotalBookings(
                    bookingRepository.countByUser_Id(userId)
            );

            dto.setActiveServices(
                    bookingRepository.countActiveBookingsForUser(userId)
            );

            dto.setCompletedJobs(
                    bookingRepository.countByUser_IdAndStatus(userId, "COMPLETED")
            );
        }

        else if (role.equalsIgnoreCase("PROFESSIONAL")) {

            dto.setTotalBookings(
                    bookingRepository.countByProfessional_Id(userId)
            );

            dto.setActiveServices(
                    bookingRepository.countActiveBookingsForProfessional(userId)
            );

            dto.setCompletedJobs(
                    bookingRepository.countByProfessional_IdAndStatus(userId, "COMPLETED")
            );
        }

        else if (role.equalsIgnoreCase("ADMIN")) {

            dto.setTotalBookings(
                    bookingRepository.getTotalBookings()
            );

            dto.setActiveServices(
                    bookingRepository.getActiveBookings()
            );

            dto.setCompletedJobs(
                    bookingRepository.getCompletedBookings()
            );

            dto.setTotalUsers(
                    userRepository.countByRole_Name("USER")
            );

            dto.setTotalProfessionals(
                    userRepository.countByRole_Name("PROFESSIONAL")
            );
        }

        return dto;
    }
}