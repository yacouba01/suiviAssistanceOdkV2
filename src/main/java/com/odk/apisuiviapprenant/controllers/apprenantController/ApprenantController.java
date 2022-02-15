package com.odk.apisuiviapprenant.controllers.apprenantController;

import com.odk.apisuiviapprenant.models.apprenantModel.Apprenant;
import com.odk.apisuiviapprenant.service.apprenantService.ApprenantServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class ApprenantController {

    @Autowired
    ApprenantServiceImpl apprenantService;

    @PostMapping("ajoutApprenant")
    Apprenant ajoutApprenant(@RequestBody Apprenant apprenant){
        return apprenantService.addApprenant(apprenant);
    }

    @GetMapping("allAprenant")
    List<Apprenant> allAprenant(){
        return apprenantService.allAprenants();
    }

    @GetMapping("apprenantById/{id}")
    Apprenant apprenantById(@PathVariable("id")Long id){
        return apprenantService.apprenantById(id);
    }

    @DeleteMapping("deleteApprenant/{id}")
    void deleteApprenant(@PathVariable("id")Long id){
        apprenantService.deleteApprenant(id);
    }

    @PutMapping("updateApprenant/{id}")
    Apprenant updateApprenant(@RequestBody Apprenant apprenant, @PathVariable("id") Long id){
        return apprenantService.updateApprenant(apprenant, id);
    }

    @GetMapping("login/{login}&{motDePass}")
    Apprenant connexion(@PathVariable("login") String login, @PathVariable("motDePass") String motDePasse){
        return apprenantService.authentification(login, motDePasse);
    }

    @DeleteMapping("restoreApprenant/{id}")
    void restoreApprenant(@PathVariable("id") Long id){
        apprenantService.restaureAppre(id);
    }

    @GetMapping("allApprenantAssister/{assis}")
    List<Apprenant> allApprenantAssister(@PathVariable("assis")boolean assis){
        return apprenantService.findApprenantByAssister(assis);
    }

    @GetMapping("/allApprenantNonAssister/{assis}")
    List<Apprenant> allApprenantNonAssister(@PathVariable("assis")boolean assis){
        return apprenantService.findApprenantNoByAssister(assis);
    }

    @PostMapping("updatePassword/{id}")
    Apprenant updatePassword(Apprenant apprenant, @PathVariable("id") Long id){
        return apprenantService.updatePassword(apprenant, id);
    }

    @PostMapping("addByExcel")
    List<Apprenant> addByExcel(@RequestParam("file")MultipartFile file) throws IOException{
        List<Apprenant> apprenant = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet workSheet = workbook.getSheetAt(0);
        for (int index=0; index > workSheet.getPhysicalNumberOfRows(); index++){
            if (index > 0){
                XSSFRow row = workSheet.getRow(index);
                Apprenant ap = new Apprenant();
                ap.setPrenom(getCellValue(row, 0));
                ap.setNom(getCellValue(row, 1));
                ap.setLogin(getCellValue(row,2));
                ap.setMotDePass(getCellValue(row,3));
                ap.setGenre(getCellValue(row,4));
                apprenantService.addApprenant(ap);
            }
        }
        return apprenant;
    }

    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();

        Cell cell = row.getCell(cellNo);

        return formatter.formatCellValue(cell);
    }

}
