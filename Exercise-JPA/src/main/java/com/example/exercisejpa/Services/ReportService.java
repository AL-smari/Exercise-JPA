package com.example.exercisejpa.Services;

import com.example.exercisejpa.Model.Merchant;
import com.example.exercisejpa.Model.Report;
import com.example.exercisejpa.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;




    public List<Report> getReports(){

        return reportRepository.findAll();
    }

    public boolean addReport(Report report, List<Merchant> merchants){


        for (int i = 0; i < merchants.size(); i++) {

            if(merchants.get(i).getId().equals(report.getMerchantID())){
                reportRepository.save(report);
                return true;
            }

        }

        return false;
    }

    public int updateReport(Integer id,Report report,List<Merchant>merchants){
        int response=0;
        Report report1 = reportRepository.getById(id);
        if(report1==null){
          response=1;
         }
        if(response!=0){
            return response;
        }

        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId().equals(report.getMerchantID())){
                response=0;
                report1.setMessage(report.getMessage());
                report1.setMerchantID(report.getMerchantID());
                reportRepository.save(report1);
                return response;

            }else response =2;

        }
        return response;
    }

    public boolean deleteReport(Integer id){
        Report report1 = reportRepository.getById(id);
        if(report1==null){
            return false;

        }
        reportRepository.delete(report1);
        return true;

    }
}
